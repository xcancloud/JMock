# JMock 2.0.0 重构设计方案

> 基于对 v1.0.0 全量代码（api / core / 16 个 plugin / test / benchmark / docs JSON）的完整审计，本文无遗漏地覆盖三大目标：  
> ① 扩展更多常见数据格式函数（含数组类型）  
> ② 架构评审与合理化重构  
> ③ 新增 JMock 组件宣传官网，基于 JSON 规范文件动态加载函数信息  

---

## 目录

1. [现有架构评审](#1-现有架构评审)
2. [2.0.0 架构设计](#2-20-架构设计)
3. [函数体系扩展设计](#3-函数体系扩展设计)
4. [数组类型函数设计](#4-数组类型函数设计)
5. [官网设计方案](#5-官网设计方案)
6. [模块拆分与目录结构](#6-模块拆分与目录结构)
7. [关键接口与类设计](#7-关键接口与类设计)
8. [迁移与兼容性策略](#8-迁移与兼容性策略)
9. [实施路线图](#9-实施路线图)

---

## 1. 现有架构评审

### 1.1 架构总览（v1.0.0）

```
┌─────────┐    ┌─────────┐    ┌──────────────────────────┐
│  api    │◄───│  core   │───►│  plugins (16 modules)    │
│         │    │         │    │  ├── basic / article ...  │
│MockFunc │    │Parser   │    │  └── all (aggregator)    │
│Token    │    │Replacer │    └──────────────────────────┘
│Utils    │    │Env(SPI) │
└─────────┘    └─────────┘
```

### 1.2 现有优点

| 维度 | 评价 |
|------|------|
| SPI 自动发现 | `ServiceLoader<MockFunction>` 模式使插件即放即用，零配置 |
| i18n 体系 | `MessageResources` + `ThreadLocaleHolder` 实现线程级别国际化 |
| 表达式解析 | 手写 char-level 状态机，无外部依赖，高性能 |
| 文档自生成 | `MockFunctionDocParser` 通过反射注解直接导出 JSON，保持文档与代码同步 |
| 性能基线 | JMH 基准完备（53 个），单线程 @String 24M ops/s |

### 1.3 需要解决的问题

#### P0 — 架构级

| # | 问题 | 影响 | 建议 |
|---|------|------|------|
| A1 | **core 直接依赖 `all-plugin` 聚合包** | core 与全部插件形成循环感知；无法仅用 core + 选定插件；第三方扩展时 classloader 冲突 | core 不依赖任何 plugin；插件按需引入 |
| A2 | **`MockFunction.mock()` 返回 `Object`** | 调用方必须强转；无法在编译期或文档中表达返回类型；数组场景返回类型无法区分标量/集合 | 引入泛型 `MockFunction<T>` + 类型注册表 |
| A3 | **无数组 / 集合 / 复合类型语法** | 用户无法在模板中表达"生成 N 个 Email 的数组"；JSON 数组场景完全靠手写 | 新增 `@Repeat` / 数组包裹语法 `[@Email(), 5]` |
| A4 | **构造器反射不支持可选参数** | `ConstructorUtils.invokeConstructor` 按参数个数精确匹配；一旦多加一个可选参数就必须新增构造器重载 | 改用 Builder / Map + `@DefaultValue` |
| A5 | **`DefaultMockExpressionReplacer` 双重 synchronized** | 整个替换流程串行化，多线程场景瓶颈；cacheMap 用 HashMap 而非 ConcurrentHashMap | 改用无状态 parse + ThreadLocal cache 或 ConcurrentHashMap |

#### P1 — 设计级

| # | 问题 | 影响 | 建议 |
|---|------|------|------|
| B1 | 函数命名前缀 `M` (`MString`, `MEmail`) | 对用户无意义，且与 `@String()` 表达式名不一致；增加查找成本 | 2.0 内部类名保留 `M` 前缀作为约定，但新增 `@Alias` 支持多别名注册 |
| B2 | `FunctionToken.params` 用 `Map<String, String>` | 位置参数 key 为 `"1"`, `"2"` 字符串；无法区分显式传空 vs 未传 | 引入 `Parameter` 对象（name, value, present） |
| B3 | 工具类散落在 `api.support.utils` | `RandomUtils`、`IPUtils`、`EncryptionUtils` 等与 API 契约混在一起 | 拆出独立 `jmock-support` 模块 |
| B4 | 插件 i18n key 重复定义（每个插件一个 DocMessage 接口） | 新增函数必须同时改 DocMessage + properties + 注解 | 统一元数据走 JSON descriptor，i18n 自动合并 |
| B5 | 无函数组合 / 管道语法 | 无法表达 `@String() | @Base64()` 等链式转换 | 2.0 预留 Pipe 语法 |
| B6 | JSON spec 文件手动导出 | 需运行 `MockFunctionDocParser.main()` 手动执行 | 绑定 Maven `generate-resources` 阶段自动输出 |

#### P2 — 实现级

| # | 问题 | 影响 | 建议 |
|---|------|------|------|
| C1 | `SecureRandom` 在每个插件类中各自 `new` | 熵源竞争，低吞吐 | 提供 `JMockRandom` 共享实例，默认 `ThreadLocalRandom`，安全场景可选 `SecureRandom` |
| C2 | 部分插件硬编码数据（如 `MBrand` 35 个品牌写死 Java 数组） | 不易更新，非 i18n | 数据全部迁移到 resource properties / CSV |
| C3 | 权重采样 `StringToTypeUtils.calcNullWeight` 实现分散 | 各函数各自解析 `"1:2"` | 提供统一 `WeightedSampler` |
| C4 | 偏移量替换算法逐 token 重建 StringBuilder | 多 token 时 O(n*m) 复杂度 | 改为一次遍历双指针拼接 |

---

## 2. 2.0 架构设计

### 2.1 模块架构（目标）

```
jmock-bom                   ← Maven BOM
jmock-api                   ← 纯接口 + 注解 + 通用模型（零外部依赖）
jmock-support               ← 工具类（Random, IP, Encryption…）
jmock-core                  ← 解析 / 替换 / 环境 / 函数注册（不依赖任何插件）
jmock-plugins/
  ├── jmock-plugin-basic    ← String, Integer, Long, Float, Double, Bool, Enum, RegExp
  ├── jmock-plugin-text     ← Word, Title, Sentence, Paragraph, Article, TangPoetry
  ├── jmock-plugin-datetime ← Timestamp, Date, Time, DateTime, Month, Week, Duration ★
  ├── jmock-plugin-geo      ← Country, Province, City, Address, Lat, Lng, Coordinates, Zip
  ├── jmock-plugin-id       ← UUID, GUID, IncId, SnowId, ULID ★, NanoId ★
  ├── jmock-plugin-user     ← Name, Firstname, Lastname, Gender, Age, Email, Mobile, ...
  ├── jmock-plugin-network  ← IPv4, IPv6, MAC, Port, Protocol, URL, AppName, AppVersion, UserAgent ★
  ├── jmock-plugin-web      ← Color, Emoji, MimeType ★, HttpHeader ★
  ├── jmock-plugin-crypto   ← Base64, Hash, Hex, KeyPair, SymmetricKey, JWT ★
  ├── jmock-plugin-hash     ← MD5, SHA
  ├── jmock-plugin-car      ← Brand, Vehicle, Engine, Horsepower, Drivetrain, Plate
  ├── jmock-plugin-company  ← Company, Industry, Department, Job, Logo ★
  ├── jmock-plugin-code     ← CodeSnippet, ErrorType, Html, Markdown, ProgrammingLanguage
  ├── jmock-plugin-compute  ← Browser, OS, Database, Cloud, CPU, GPU, Device, ...
  ├── jmock-plugin-finance  ← Amount, Currency, Budget, Invoice, TaxCode, IBAN ★, CreditCard ★
  ├── jmock-plugin-locale   ← Locale, TimeZone
  ├── jmock-plugin-json ★   ← JsonObject, JsonArray, JsonPointer ★
  ├── jmock-plugin-array ★  ← Repeat, Sequence, Shuffle, Sample ★
  └── jmock-plugin-all      ← 聚合全部插件（使用者可一键引入）
jmock-test                   ← 集成测试
jmock-benchmark              ← JMH 性能基准
jmock-docs-generator         ← Maven 插件：自动生成 JSON Spec ★
jmock-website ★              ← 官网（React/Next.js SSG，读 JSON Spec 渲染）
```

> ★ = 2.0.0 新增模块/函数

### 2.2 依赖关系

```
jmock-api  ←── jmock-support ←── jmock-core
                                      ↑ (SPI 运行时)
                              jmock-plugin-* (各插件)
                                      ↑
                              jmock-plugin-all (聚合)

jmock-website  ── reads ──→ docs/jmock-functions.json (构建时)
jmock-docs-generator ── scans ──→ jmock-plugin-all (导出 JSON)
```

**核心原则：`jmock-core` 对插件零编译依赖，仅通过 SPI 运行时发现。**

### 2.3 核心流程（2.0）

```
用户模板文本 / JSON
       │
       ▼
  ┌──────────────────┐
  │  Lexer (新增)     │  ← 词法分析：拆分 Text/Function/Array/Pipe token
  └────────┬─────────┘
           ▼
  ┌──────────────────┐
  │  Parser           │  ← 语法分析：构建 AST（表达式树）
  └────────┬─────────┘
           ▼
  ┌──────────────────┐
  │  Resolver         │  ← 函数解析：Registry 查找 + 参数绑定
  └────────┬─────────┘
           ▼
  ┌──────────────────┐
  │  Evaluator        │  ← 求值：调用 mock()，支持数组展开 / 管道
  └────────┬─────────┘
           ▼
  ┌──────────────────┐
  │  Renderer         │  ← 文本重建：一次遍历双指针拼接输出
  └────────┘─────────┘
```

与 1.0 对比改进：
- 将 `Extractor` + `Replacer` 分解为 **Lexer → Parser → Resolver → Evaluator → Renderer** 五阶段，每阶段单一职责
- 引入轻量 **AST** 支持数组、管道、嵌套等复合表达式
- Renderer 一次遍历完成所有替换，消除 O(n*m) 偏移量问题

---

## 3. 函数体系扩展设计

### 3.1 现有函数清单（94 个）

| 类别 | 函数 | 数量 |
|------|------|------|
| Data Types | String, Integer, Long, Float, Double, Bool, Enum, RegExp | 8 |
| Text | Word, Title, Sentence, Paragraph, Article, TangPoetry | 6 |
| Date/Time | Timestamp, LocaleDate, LocaleTime, LocaleDateTime, Month, Week | 6 |
| ID | UUID, GUID, IncId, SnowId | 4 |
| Geography | Country, Province, City, Address, Latitude, Longitude, Coordinates, Zip | 8 |
| Hash | MD5, SHA | 2 |
| User | Name, Firstname, Lastname, Gender, Age, Email, Mobile, Landline, Password, Education | 10 |
| Network | IPv4, IPv6, MAC, Port, Protocol, URL, AppName, AppVersion | 8 |
| Web | Color, Emoji | 2 |
| Car | Brand, Vehicle, Engine, Horsepower, Drivetrain, Plate | 6 |
| Code | CodeSnippet, ErrorType, Html, Markdown, ProgrammingLanguage | 5 |
| Compute | Browser, OS, Database, CloudService, CPU, GPU, Device, FileName, FilePath, Framework, HttpStatus, Ram, Vulnerability | 13 |
| Crypto | Base64, Hash, Hex, KeyPair, SymmetricKey | 5 |
| Financial | Amount, Currency, BudgetCategory, InvoiceNumber, TaxCode | 5 |
| Locale | Locale, TimeZone | 2 |
| Company | Company, Industry, Department, Job | 4 |
| **合计** | | **94** |

### 3.2 新增函数（2.0.0，40+ 个）

#### 数据类型扩展

| 新函数 | 表达式 | 说明 |
|--------|--------|------|
| **Decimal** | `@Decimal(precision, scale)` | BigDecimal 精确小数 |
| **Char** | `@Char()` | 单个随机字符 |
| **Byte** | `@Byte()` | 随机字节 |
| **Short** | `@Short(min, max)` | 短整型 |
| **Null** | `@Null()` | 始终返回 null |
| **Constant** | `@Constant(value)` | 返回固定值 |

#### 日期时间扩展

| 新函数 | 表达式 | 说明 |
|--------|--------|------|
| **Duration** | `@Duration(min, max, unit)` | 随机时间段 `PT3H25M` |
| **FutureDate** | `@FutureDate(maxDays)` | 未来日期 |
| **PastDate** | `@PastDate(maxDays)` | 过去日期 |
| **DateRange** | `@DateRange(start, end, format)` | 指定范围内日期 |
| **Year** | `@Year(min, max)` | 随机年份 |

#### ID 扩展

| 新函数 | 表达式 | 说明 |
|--------|--------|------|
| **ULID** | `@Ulid()` | 有序 UUID 替代 |
| **NanoId** | `@NanoId(size)` | 紧凑型唯一 ID |
| **ObjectId** | `@ObjectId()` | MongoDB 风格 24 位 hex |
| **TSID** | `@Tsid()` | 时间排序 ID |

#### 网络扩展

| 新函数 | 表达式 | 说明 |
|--------|--------|------|
| **UserAgent** | `@UserAgent()` | 随机 UA 字符串 |
| **Domain** | `@Domain()` | 随机域名 |
| **HttpMethod** | `@HttpMethod()` | GET/POST/PUT… |
| **HttpHeader** | `@HttpHeader()` | 随机请求头键值 |
| **MimeType** | `@MimeType()` | application/json 等 |

#### 金融扩展

| 新函数 | 表达式 | 说明 |
|--------|--------|------|
| **IBAN** | `@Iban(country)` | 国际银行帐号 |
| **CreditCard** | `@CreditCard(brand)` | 信用卡号（Luhn 校验） |
| **BIC** | `@Bic()` | SWIFT/BIC 代码 |
| **StockSymbol** | `@StockSymbol(market)` | 股票代码 |

#### JSON / 结构化数据（全新类别）

| 新函数 | 表达式 | 说明 |
|--------|--------|------|
| **JsonObject** | `@JsonObject(keys, valueFuncs)` | 生成 JSON 对象 |
| **JsonArray** | `@JsonArray(itemFunc, size)` | 生成 JSON 数组 |
| **Csv** | `@Csv(columns, rows)` | CSV 行生成 |
| **Xml** | `@Xml(rootTag, schema)` | 简单 XML 片段 |

#### 数组 / 集合操作（全新类别）

| 新函数 | 表达式 | 说明 |
|--------|--------|------|
| **Repeat** | `@Repeat(@Email(), 5)` | 重复生成 N 个值，返回数组 |
| **Sequence** | `@Sequence(start, step, count)` | 等差数列 |
| **Shuffle** | `@Shuffle(a\|b\|c\|d)` | 随机打乱给定列表 |
| **Sample** | `@Sample(a\|b\|c, 2)` | 从列表中不重复抽取 N 个 |
| **OneOf** | `@OneOf(@Email()\|@Mobile())` | 从多个函数中随机选一个执行 |

#### 其他常见数据

| 新函数 | 表达式 | 说明 |
|--------|--------|------|
| **Avatar** | `@Avatar(size)` | 随机头像 URL |
| **Paragraph** 增强 | `@Paragraph(sentences, locale)` | 增强段落 |
| **PhoneNumber** | `@PhoneNumber(region)` | 支持全球号码格式 |
| **SSN** | `@Ssn(country)` | 社会安全号码 |
| **Sentence** 增强 | `@Sentence(words, locale)` | 支持字数控制和多语言 |
| **Logo** | `@Logo()` | 生成公司 Logo URL |
| **ISBN** | `@Isbn()` | 10/13 位国际书号 |
| **Barcode** | `@Barcode(type)` | EAN-13 / UPC 条码 |

### 3.3 函数版本管理

每个函数注解新增 `since` 字段：

```java
@JMockFunctionRegister(
    descI18nKey = "...",
    categoryI18nKey = {"..."},
    order = 100,
    since = "2.0.0"   // ★ 新增
)
```

JSON Spec 中也将包含 `since` 字段，官网可据此标注新增函数徽章。

---

## 4. 数组类型函数设计

### 4.1 语法设计

**方案：函数嵌套 + `@Repeat` 专用函数**

```
// 方式一：Repeat 包裹函数（推荐）
@Repeat(@Email(), 5)
→ ["a@test.com", "b@test.com", "c@test.com", "d@test.com", "e@test.com"]

// 方式二：在 JSON 模板中直接用
{
  "emails": "@Repeat(@Email(), 3)",
  "tags": "@Sample(tech|health|food|sports, 2)",
  "scores": "@Repeat(@Integer(60, 100), 5)"
}

// 方式三：Sequence 生成数值数组
@Sequence(1, 1, 10)
→ [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
```

### 4.2 返回类型

数组函数返回 `List<T>`，Renderer 根据上下文判断序列化策略：

| 上下文 | 序列化方式 |
|--------|-----------|
| JSON 模板中（`"field": "@Repeat(…)"` ） | 自动展开为 JSON Array `[v1, v2, v3]` |
| 纯文本模板中 | 逗号分隔 `v1, v2, v3` |
| 编程 API 调用 | 返回 `List<T>` 原始对象 |

### 4.3 AST 支持

```java
// 新增 AST 节点类型
sealed interface MockExpr {
    record Literal(String text) implements MockExpr {}
    record FunctionCall(String name, List<MockExpr> args) implements MockExpr {}
    record ArrayExpr(MockExpr itemExpr, int count) implements MockExpr {}
    record PipeExpr(MockExpr source, MockExpr transform) implements MockExpr {}  // 预留
}
```

解析器识别 `@Repeat(@Email(), 5)` 时：
1. 外层识别 `Repeat` 函数
2. 第一个参数递归解析为 `FunctionCall("Email", [])`
3. 第二个参数解析为 `Literal("5")`
4. 构建 `ArrayExpr(FunctionCall("Email", []), 5)`

### 4.4 内置数组函数实现

```java
@JMockFunctionRegister(descI18nKey = "func.repeat.desc",
    categoryI18nKey = {"Array"}, order = 1, since = "2.0.0")
public class MRepeat<T> extends AbstractMockFunction {

    private final MockFunction<T> innerFunction;
    private final int count;

    @JMockConstructor(descI18nKey = "func.repeat.c1",
        example = "@Repeat(@Email(), 3)",
        exampleValues = {"[\"a@x.com\",\"b@y.com\",\"c@z.org\"]"})
    public MRepeat(MockFunction<T> innerFunction, int count) {
        this.innerFunction = innerFunction;
        this.count = count;
    }

    @Override
    public List<T> mock() {
        List<T> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            result.add(innerFunction.mock());
        }
        return result;
    }
}
```

---

## 5. 官网设计方案

### 5.1 技术选型

| 层面 | 技术 | 理由 |
|------|------|------|
| 框架 | **Next.js 14+ (App Router)** | SSG 支持、i18n 路由、SEO 友好 |
| CSS | **Tailwind CSS** | 快速原型、响应式 |
| 组件 | **shadcn/ui** | 高质量无依赖组件 |
| 部署 | **GitHub Pages / Vercel** | 免费静态托管 |
| 数据 | `docs/jmock-functions-{lang}.json` | 唯一数据源 |

### 5.2 页面结构

```
/                       ← 首页（产品介绍 + 特性 + 在线体验）
/docs                   ← 文档首页
/docs/getting-started   ← 快速开始
/docs/functions         ← 函数列表（核心页面，读取 JSON）
/docs/functions/[name]  ← 函数详情页（动态路由）
/playground             ← 在线 Playground（输入模板 → 生成结果）
/blog                   ← 更新日志 / 文章
```

### 5.3 JSON Spec 驱动机制（核心）

**原则：替换 JSON 文件即可更新官网全部函数信息，无需修改任何前端代码。**

#### 5.3.1 增强版 JSON Spec 格式（v2）

```jsonc
{
  "version": "2.0.0",
  "generatedAt": "2026-03-20T12:00:00Z",
  "categories": [
    {
      "id": "data-types",
      "name": "Data Types",       // i18n: 从对应语言 JSON 取值
      "icon": "type",             // 官网图标映射 key
      "order": 1
    }
    // ...
  ],
  "functions": [
    {
      "name": "String",
      "class": "cloud.xcan.jmock.plugin.basic.MString",
      "description": "Generate random strings",
      "categoryId": "data-types",
      "since": "1.0.0",
      "deprecated": false,
      "tags": ["Data Types"],
      "returnType": "String",       // ★ 新增：返回类型
      "parameters": [
        {
          "name": "length",
          "type": "int",            // ★ 新增：参数类型
          "required": false,
          "defaultValue": "6",      // ★ 新增：默认值
          "description": "String length, default 6"
        }
      ],
      "constructors": [
        {
          "signature": "@String()",
          "description": "Generate 6-char random string",
          "parameters": [],
          "example": "@String()",
          "exampleOutput": ["ceja7d", "x8kp2m"]
        },
        {
          "signature": "@String(length)",
          "description": "Generate fixed-length string",
          "parameters": ["length"],
          "example": "@String(10)",
          "exampleOutput": ["a8x2mR4kLp"]
        }
      ]
    }
    // ... 134+ functions
  ]
}
```

#### 5.3.2 数据加载流程

```
构建时 (next build)
  │
  ├─ getStaticProps() 读取 docs/jmock-functions-en.json
  │                    读取 docs/jmock-functions-zh.json
  │
  ├─ 生成 /docs/functions 页面（分类卡片 + 搜索 + 筛选）
  │
  └─ 对每个 function 生成 /docs/functions/[name] 详情页
      └─ 含：描述、参数表、构造器列表、示例代码、示例输出
```

#### 5.3.3 前端核心组件

```
components/
  ├── FunctionCatalog.tsx      ← 函数列表：分类筛选 + 全文搜索
  ├── FunctionCard.tsx         ← 单个函数卡片（名称、描述、标签）
  ├── FunctionDetail.tsx       ← 详情页主体
  ├── ConstructorTable.tsx     ← 构造器签名表格
  ├── ParameterTable.tsx       ← 参数说明表
  ├── ExampleBlock.tsx         ← 代码示例 + 输出展示
  ├── PlaygroundEditor.tsx     ← 在线模板编辑器（Monaco）
  ├── CategoryNav.tsx          ← 左侧分类导航
  ├── SearchBar.tsx            ← 全局函数搜索
  ├── VersionBadge.tsx         ← "New in 2.0" 徽章
  └── LanguageSwitcher.tsx     ← 中英切换（切换 JSON 源）
```

#### 5.3.4 更新流程（运维视角）

```
1. 开发者新增/修改 MockFunction 插件
2. 运行 mvn generate-resources（或 CI 自动触发）
3. jmock-docs-generator 扫描全部插件注解 → 输出 JSON
4. JSON 文件提交到 docs/ 目录
5. 官网 CI (Vercel/GitHub Actions) 检测到 JSON 变更 → 触发重新构建
6. 静态页面自动更新，无需改动前端代码
```

### 5.4 官网首页设计

```
┌────────────────────────────────────────────────────┐
│  JMock — 高性能业务级数据生成框架                      │
│  Realistic mock data at 2M+ records/sec             │
│                                                      │
│  [快速开始]    [在线体验]    [GitHub ⭐]              │
├────────────────────────────────────────────────────┤
│                                                      │
│  ╔══ 核心特性 ══════════════════════════════════╗   │
│  ║  ⚡ 高性能    🌍 国际化    🔌 插件化    📦 134+ 函数 ║  │
│  ╚════════════════════════════════════════════════╝   │
│                                                      │
│  ╔══ 快速体验 ══════════════════════════════════╗   │
│  ║  输入:                                        ║   │
│  ║  ┌──────────────────────────────────────┐    ║   │
│  ║  │ {                                     │    ║   │
│  ║  │   "name": "@Name()",                  │    ║   │
│  ║  │   "emails": "@Repeat(@Email(), 3)",   │    ║   │  ← 实时预览
│  ║  │   "age": "@Age()"                     │    ║   │
│  ║  │ }                                     │    ║   │
│  ║  └──────────────────────────────────────┘    ║   │
│  ║                    ▼                          ║   │
│  ║  输出:                                        ║   │
│  ║  ┌──────────────────────────────────────┐    ║   │
│  ║  │ {                                     │    ║   │
│  ║  │   "name": "张伟",                      │    ║   │
│  ║  │   "emails": ["a@x.com","b@y.com",...] │    ║   │
│  ║  │   "age": 28                           │    ║   │
│  ║  │ }                                     │    ║   │
│  ║  └──────────────────────────────────────┘    ║   │
│  ╚════════════════════════════════════════════════╝   │
│                                                      │
│  ╔══ 函数分类快速导航 ═══════════════════════════╗   │
│  ║  [Data Types 8]  [Text 7]  [DateTime 11]     ║   │
│  ║  [User 10]  [Network 13]  [Finance 9] ...    ║   │
│  ╚════════════════════════════════════════════════╝   │
└────────────────────────────────────────────────────┘
```

### 5.5 官网 Playground（核心亮点）

用 WebAssembly 或后端 API 提供在线模板解析：

- **方案 A（推荐）**：前端嵌入一个轻量 mock 引擎（TypeScript 重写核心解析逻辑）  
  - 优点：纯客户端、无服务器成本、响应即时  
  - 实现：TypeScript 读取同一份 JSON Spec，仅需实现 Lexer/Parser/Resolver  
  - 各函数的 `mock()` 逻辑用 JS 字典驱动（JSON 自带 exampleOutput 可作兜底）

- **方案 B**：后端 Java API  
  - 部署一个轻量 Spring Boot 服务  
  - 缺点：需要服务器维护、有延迟

---

## 6. 模块拆分与目录结构

### 6.0 新增模块一览

```
JMock/
├── api/                          ← jmock-api（保留，增强）
├── support/ ★                    ← jmock-support（从 api 拆出工具类）
├── core/                         ← jmock-core（保留，重构解析引擎）
├── plugins/
│   ├── basic/                    ← 保留
│   ├── text/ ★                   ← article → text（更准确的命名）
│   ├── datetime/ ★               ← date → datetime
│   ├── geo/                      ← geography → geo（简化）
│   ├── id/                       ← 保留
│   ├── user/                     ← 保留
│   ├── network/                  ← 保留
│   ├── web/                      ← 保留
│   ├── crypto/                   ← 保留
│   ├── hash/                     ← 保留
│   ├── car/                      ← 保留
│   ├── company/                  ← 保留
│   ├── code/                     ← 保留
│   ├── compute/                  ← 保留
│   ├── finance/ ★                ← financial → finance
│   ├── locale/                   ← 保留
│   ├── json/ ★                   ← 新增
│   ├── array/ ★                  ← 新增
│   └── all/                      ← 聚合
├── docs-generator/ ★             ← Maven 插件
├── website/ ★                    ← Next.js 官网
├── test/
├── benchmark/
├── bom/
└── docs/
    ├── jmock-functions-en.json   ← v2 格式
    └── jmock-functions-zh.json
```

---

## 7. 关键接口与类设计

### 7.1 `MockFunction<T>`（泛型化）

```java
// jmock-api
@FunctionalInterface
public interface MockFunction<T> {
    T mock();

    /**
     * 返回值类型声明（用于文档和序列化推断）
     */
    default Class<T> returnType() {
        return (Class<T>) Object.class;
    }
}
```

### 7.2 `MockFunctionMetadata`（统一元数据）

```java
// jmock-api — 替代散落的反射读取
public record MockFunctionMetadata(
    String name,
    String[] aliases,
    String description,
    String category,
    String since,
    boolean deprecated,
    Class<?> returnType,
    List<ParameterMeta> parameters,
    List<ConstructorMeta> constructors
) {}
```

### 7.3 `FunctionRegistry`（替代 Environment）

```java
// jmock-core
public interface FunctionRegistry {
    void register(Class<? extends MockFunction<?>> clazz);
    void register(String alias, Class<? extends MockFunction<?>> clazz);
    Optional<Class<? extends MockFunction<?>>> lookup(String name);
    Collection<MockFunctionMetadata> allMetadata();
    Stream<MockFunctionMetadata> search(String keyword);
}

// 默认实现
public class DefaultFunctionRegistry implements FunctionRegistry {
    private final ConcurrentHashMap<String, Class<? extends MockFunction<?>>> registry;
    // SPI 自动加载 + 手动注册均支持
}
```

### 7.4 `MockExpr` AST（新增）

```java
// jmock-core
public sealed interface MockExpr {
    record TextSegment(String text, int start, int end) implements MockExpr {}
    record FunctionCall(String name, List<Argument> args, int start, int end) implements MockExpr {}
    record ArrayExpr(MockExpr itemExpr, int count, int start, int end) implements MockExpr {}
    record PipeExpr(MockExpr source, MockExpr transform, int start, int end) implements MockExpr {}

    record Argument(String name, MockExpr value) {}
}
```

### 7.5 `MockEngine` 统一入口（新增）

```java
// jmock-core — 取代直接使用 Replacer/Extractor
public class MockEngine {
    private final FunctionRegistry registry;
    private final MockLexer lexer;
    private final MockParser parser;
    private final MockEvaluator evaluator;

    public static MockEngine defaultEngine() {
        return new MockEngine(new DefaultFunctionRegistry());
    }

    /** 模板文本替换 */
    public String render(String template) { ... }

    /** 直接执行单个表达式 */
    public <T> T evaluate(String expression) { ... }

    /** 批量生成：同一模板生成 N 条记录 */
    public List<String> renderBatch(String template, int count) { ... }

    /** 导出函数目录为 JSON */
    public String exportFunctionSpec(Locale locale) { ... }
}
```

用户 API 简化对比：

```java
// v1.0.0 — 需要了解 Replacer, Extractor, Environment
String result = new DefaultMockTextReplacer().replace(template);

// v2.0.0 — 一个入口搞定
String result = MockEngine.defaultEngine().render(template);

// v2.0.0 — 批量生成
List<String> records = MockEngine.defaultEngine().renderBatch(template, 1000);
```

### 7.6 线程安全改进

```java
// v1.0.0 问题：双重 synchronized + HashMap
public synchronized String replace(...) { ... }

// v2.0.0 方案
public class MockEvaluator {
    // 无状态解析：每次 render 创建独立的 context
    public String evaluate(List<MockExpr> ast, FunctionRegistry registry) {
        EvalContext ctx = new EvalContext(registry);
        StringBuilder result = new StringBuilder(template.length());
        for (MockExpr expr : ast) {
            result.append(ctx.eval(expr));
        }
        return result.toString();
    }
}

// FunctionToken → MockFunction 缓存使用 ConcurrentHashMap
// 或完全无状态（MockFunction 实例轻量，不缓存）
```

### 7.7 参数绑定改进

```java
// v1.0.0：ConstructorUtils 按参数个数精确匹配
// v2.0.0：引入参数绑定器

public class ParameterBinder {
    /**
     * 支持：
     * 1. 位置参数 → 按声明顺序绑定
     * 2. 命名参数 → 按名称绑定
     * 3. 可选参数 → @DefaultValue 注解
     * 4. MockExpr 参数 → 嵌套函数（数组场景）
     */
    public Object[] bind(ConstructorMeta meta, List<Argument> args) { ... }
}

// 函数声明方式改进
public class MString extends AbstractMockFunction<String> {

    @JMockParameter(descI18nKey = "...", defaultValue = "6")
    private int length;

    // 新增 @DefaultValue，无需大量构造器重载
    public MString(
        @DefaultValue("6") int length,
        @DefaultValue("") String chars,
        @DefaultValue("") String nullWeight
    ) {
        this.length = length;
        this.chars = chars;
        this.nullWeight = nullWeight;
    }
}
```

---

## 8. 迁移与兼容性策略

### 8.1 版本策略

| 层面 | 策略 |
|------|------|
| Maven groupId | 保持 `cloud.xcan.jmock` |
| artifactId | `xcan-jmock.api` → `jmock-api`（简化，breaking change） |
| 旧函数表达式 | **100% 兼容**：`@String()`, `@Email()` 等所有 v1 表达式在 v2 无需修改 |
| 旧 Java API | 提供 `jmock-compat` 桥接模块：`DefaultMockTextReplacer` 委托到 `MockEngine` |
| 新表达式 | 仅在 v2 引擎可用：`@Repeat()`, `@Sequence()`, Pipe 语法 |

### 8.2 迁移路径

```
用户 v1.0 项目
  │
  ├─ 替换依赖：xcan-jmock.core → jmock-core + jmock-plugin-all
  │              或只引入需要的 jmock-plugin-*
  │
  ├─ 代码调整（可选）：
  │   - new DefaultMockTextReplacer() → MockEngine.defaultEngine()
  │   - 或引入 jmock-compat 桥接，零代码改动
  │
  └─ 所有 @Function() 模板表达式无需修改
```

---

## 9. 实施路线图

### Phase 1：基础架构重构

```
├─ 拆出 jmock-support 模块（工具类迁移）
├─ MockFunction<T> 泛型化
├─ 新建 FunctionRegistry 替代 Environment
├─ 实现 Lexer → Parser → Resolver → Evaluator → Renderer 五阶段管线
├─ 无状态线程安全 Evaluator
├─ 参数绑定器 + @DefaultValue
├─ 一次遍历 Renderer（消除 O(n*m) 偏移问题）
├─ JMockRandom 共享随机源
└─ 全部现有 94 个函数迁移 + 回归测试通过
```

### Phase 2：函数扩展 + 数组支持

```
├─ AST 支持嵌套函数调用
├─ 实现 @Repeat, @Sequence, @Shuffle, @Sample, @OneOf
├─ 实现 @JsonObject, @JsonArray
├─ 新增 40+ 个扩展函数（按优先级分批）
│   ├─ P0: Decimal, ULID, NanoId, UserAgent, Domain, CreditCard, IBAN
│   ├─ P1: Duration, FutureDate, PastDate, HttpMethod, MimeType, JWT
│   └─ P2: Csv, Xml, Avatar, ISBN, Barcode, SSN
├─ JSON Spec v2 格式输出
├─ JMH 基准扩展（新函数覆盖）
└─ 文档生成器 Maven 插件
```

### Phase 3：官网开发

```
├─ Next.js 项目初始化 + Tailwind + shadcn/ui
├─ JSON Spec 加载 + getStaticProps
├─ 首页：Hero + 特性展示 + 快速体验
├─ /docs/functions 函数列表（分类 + 搜索 + 筛选）
├─ /docs/functions/[name] 函数详情页
├─ Playground（TypeScript 轻量 mock 引擎）
├─ 中英文切换（双 JSON 源）
├─ 响应式适配（移动端）
├─ CI/CD：JSON 变更 → 自动重建 → 部署
└─ SEO 优化 + Open Graph
```

### Phase 4：高级特性（可选后续版本）

```
├─ Pipe 语法：@String() | @Base64()
├─ 自定义函数 DSL（用户通过 YAML 定义新函数）
├─ 性能模式：预编译模板 + 对象池
├─ 流式 API：MockStream.of(template).limit(10000).forEach(...)
├─ CLI 工具：jmock generate --template user.json --count 1000
└─ IntelliJ / VS Code 插件：模板语法高亮 + 实时预览
```

---

## 附录 A — 完整函数清单（2.0.0，134+）

| # | 类别 | 函数名 | 新增 | 说明 |
|---|------|--------|------|------|
| 1 | Data Types | String | | 随机字符串 |
| 2 | Data Types | Integer | | 随机整数 |
| 3 | Data Types | Long | | 随机长整型 |
| 4 | Data Types | Float | | 随机浮点 |
| 5 | Data Types | Double | | 随机双精度 |
| 6 | Data Types | Bool | | 随机布尔 |
| 7 | Data Types | Enum | | 枚举选择 |
| 8 | Data Types | RegExp | | 正则生成 |
| 9 | Data Types | Decimal | ★ | BigDecimal |
| 10 | Data Types | Char | ★ | 单字符 |
| 11 | Data Types | Byte | ★ | 随机字节 |
| 12 | Data Types | Short | ★ | 短整型 |
| 13 | Data Types | Null | ★ | 常量 null |
| 14 | Data Types | Constant | ★ | 固定值 |
| 15 | Text | Word | | 随机词 |
| 16 | Text | Title | | 标题 |
| 17 | Text | Sentence | | 句子 |
| 18 | Text | Paragraph | | 段落 |
| 19 | Text | Article | | 文章 |
| 20 | Text | TangPoetry | | 唐诗 |
| 21 | DateTime | Timestamp | | 时间戳 |
| 22 | DateTime | LocaleDate | | 本地日期 |
| 23 | DateTime | LocaleTime | | 本地时间 |
| 24 | DateTime | LocaleDateTime | | 本地日期时间 |
| 25 | DateTime | Month | | 月份 |
| 26 | DateTime | Week | | 星期 |
| 27 | DateTime | Duration | ★ | 时间段 |
| 28 | DateTime | FutureDate | ★ | 未来日期 |
| 29 | DateTime | PastDate | ★ | 过去日期 |
| 30 | DateTime | DateRange | ★ | 范围日期 |
| 31 | DateTime | Year | ★ | 年份 |
| 32 | ID | UUID | | UUID |
| 33 | ID | GUID | | GUID |
| 34 | ID | IncId | | 自增 ID |
| 35 | ID | SnowId | | 雪花 ID |
| 36 | ID | Ulid | ★ | 有序 UUID |
| 37 | ID | NanoId | ★ | 紧凑 ID |
| 38 | ID | ObjectId | ★ | MongoDB ID |
| 39 | ID | Tsid | ★ | 时间排序 ID |
| 40 | Geography | Country | | 国家 |
| 41 | Geography | Province | | 省份 |
| 42 | Geography | City | | 城市 |
| 43 | Geography | Address | | 地址 |
| 44 | Geography | Latitude | | 纬度 |
| 45 | Geography | Longitude | | 经度 |
| 46 | Geography | Coordinates | | 坐标 |
| 47 | Geography | Zip | | 邮编 |
| 48 | Hash | MD5 | | MD5 |
| 49 | Hash | SHA | | SHA |
| 50 | User | Name | | 姓名 |
| 51 | User | Firstname | | 名 |
| 52 | User | Lastname | | 姓 |
| 53 | User | Gender | | 性别 |
| 54 | User | Age | | 年龄 |
| 55 | User | Email | | 邮箱 |
| 56 | User | Mobile | | 手机 |
| 57 | User | Landline | | 座机 |
| 58 | User | Password | | 密码 |
| 59 | User | Education | | 学历 |
| 60 | Network | IPv4 | | IPv4 |
| 61 | Network | IPv6 | | IPv6 |
| 62 | Network | MAC | | MAC 地址 |
| 63 | Network | Port | | 端口 |
| 64 | Network | Protocol | | 协议 |
| 65 | Network | URL | | URL |
| 66 | Network | AppName | | 应用名 |
| 67 | Network | AppVersion | | 版本号 |
| 68 | Network | UserAgent | ★ | UA 字符串 |
| 69 | Network | Domain | ★ | 域名 |
| 70 | Network | HttpMethod | ★ | HTTP 方法 |
| 71 | Network | HttpHeader | ★ | 请求头 |
| 72 | Network | MimeType | ★ | MIME 类型 |
| 73 | Web | Color | | 颜色 |
| 74 | Web | Emoji | | 表情 |
| 75 | Car | Brand | | 品牌 |
| 76 | Car | Vehicle | | 车型 |
| 77 | Car | Engine | | 引擎 |
| 78 | Car | Horsepower | | 马力 |
| 79 | Car | Drivetrain | | 驱动 |
| 80 | Car | Plate | | 车牌 |
| 81 | Code | CodeSnippet | | 代码片段 |
| 82 | Code | ErrorType | | 错误类型 |
| 83 | Code | Html | | HTML |
| 84 | Code | Markdown | | Markdown |
| 85 | Code | ProgrammingLanguage | | 编程语言 |
| 86 | Compute | Browser | | 浏览器 |
| 87 | Compute | OS | | 操作系统 |
| 88 | Compute | Database | | 数据库 |
| 89 | Compute | CloudService | | 云服务 |
| 90 | Compute | CpuModel | | CPU |
| 91 | Compute | GpuModel | | GPU |
| 92 | Compute | Device | | 设备 |
| 93 | Compute | FileName | | 文件名 |
| 94 | Compute | FilePath | | 文件路径 |
| 95 | Compute | Framework | | 框架 |
| 96 | Compute | HttpStatus | | HTTP 状态码 |
| 97 | Compute | Ram | | 内存 |
| 98 | Compute | Vulnerability | | 漏洞 |
| 99 | Crypto | Base64 | | Base64 |
| 100 | Crypto | Hash | | 哈希 |
| 101 | Crypto | Hex | | 十六进制 |
| 102 | Crypto | KeyPair | | 密钥对 |
| 103 | Crypto | SymmetricKey | | 对称密钥 |
| 104 | Crypto | JWT | ★ | JWT Token |
| 105 | Financial | Amount | | 金额 |
| 106 | Financial | Currency | | 货币 |
| 107 | Financial | BudgetCategory | | 预算分类 |
| 108 | Financial | InvoiceNumber | | 发票号 |
| 109 | Financial | TaxCode | | 税号 |
| 110 | Financial | IBAN | ★ | 国际银行帐号 |
| 111 | Financial | CreditCard | ★ | 信用卡号 |
| 112 | Financial | BIC | ★ | SWIFT 代码 |
| 113 | Financial | StockSymbol | ★ | 股票代码 |
| 114 | Locale | Locale | | 语言环境 |
| 115 | Locale | TimeZone | | 时区 |
| 116 | Company | Company | | 公司名 |
| 117 | Company | Industry | | 行业 |
| 118 | Company | Department | | 部门 |
| 119 | Company | Job | | 职位 |
| 120 | Company | Logo | ★ | 公司 Logo |
| 121 | Array | Repeat | ★ | 重复生成数组 |
| 122 | Array | Sequence | ★ | 等差数列 |
| 123 | Array | Shuffle | ★ | 随机打乱 |
| 124 | Array | Sample | ★ | 不重复抽样 |
| 125 | Array | OneOf | ★ | 随机选一 |
| 126 | JSON | JsonObject | ★ | JSON 对象 |
| 127 | JSON | JsonArray | ★ | JSON 数组 |
| 128 | JSON | Csv | ★ | CSV 生成 |
| 129 | JSON | Xml | ★ | XML 片段 |
| 130 | Misc | Avatar | ★ | 头像 URL |
| 131 | Misc | PhoneNumber | ★ | 国际手机号 |
| 132 | Misc | SSN | ★ | 社会安全号 |
| 133 | Misc | ISBN | ★ | 国际书号 |
| 134 | Misc | Barcode | ★ | 条码 |

---

## 附录 B — 官网技术架构图

```
┌─────────────────────────────────────────────────────┐
│                 GitHub Repository                    │
│                                                      │
│  docs/                                               │
│  ├── jmock-functions-en.json  ◄── docs-generator     │
│  └── jmock-functions-zh.json  ◄── (Maven phase)      │
│                                                      │
│  website/                                            │
│  ├── app/                                            │
│  │   ├── page.tsx              ← 首页               │
│  │   ├── docs/                                      │
│  │   │   ├── functions/                             │
│  │   │   │   ├── page.tsx      ← 函数列表           │
│  │   │   │   └── [name]/                            │
│  │   │   │       └── page.tsx  ← 函数详情           │
│  │   │   └── getting-started/                       │
│  │   │       └── page.tsx      ← 快速开始           │
│  │   └── playground/                                │
│  │       └── page.tsx          ← 在线体验           │
│  ├── lib/                                           │
│  │   ├── loadFunctions.ts      ← 读取 JSON Spec     │
│  │   └── mockEngine.ts         ← TS 轻量 mock 引擎  │
│  ├── components/               ← UI 组件            │
│  └── public/                                        │
│      └── functions/            ← JSON 副本 (运行时)  │
└──────────────┬──────────────────────────────────────┘
               │ git push
               ▼
┌─────────────────────┐     ┌───────────────────┐
│  GitHub Actions CI   │────►│  Vercel / Pages   │
│  - build website     │     │  静态部署          │
│  - validate JSON     │     └───────────────────┘
└─────────────────────┘
```

---

## 附录 C — 核心类变更对照

| v1.0.0 类 | v2.0.0 类 | 变更说明 |
|-----------|-----------|---------|
| `MockFunction` | `MockFunction<T>` | 泛型化 |
| `AbstractMockFunction` | `AbstractMockFunction<T>` | 泛型化 |
| `Environment` / `DefaultEvalEnvironment` | `FunctionRegistry` / `DefaultFunctionRegistry` | 更清晰的命名；ConcurrentHashMap |
| `DefaultMockTextExtractor` | `MockLexer` + `MockParser` | 拆分词法/语法分析 |
| `DefaultMockExpressionReplacer` | `MockEvaluator` + `MockRenderer` | 拆分求值/渲染；无状态 |
| `DefaultMockTextReplacer` | `MockEngine` | 统一入口；无 synchronized |
| `SimpleMockFunctionTokenParser` | `FunctionResolver` + `ParameterBinder` | 拆分解析/绑定 |
| `MockFunctionDocParser` | `FunctionSpecExporter` (docs-generator) | Maven 插件化 |
| `FunctionToken` | `MockExpr.FunctionCall` | AST 节点 |
| N/A | `MockExpr.ArrayExpr` | ★ 数组 AST |
| N/A | `MockExpr.PipeExpr` | ★ 管道 AST（预留） |
| N/A | `JMockRandom` | ★ 共享随机源 |
| N/A | `WeightedSampler` | ★ 统一权重采样 |
