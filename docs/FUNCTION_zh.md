模拟函数实现
===

## 数据类型

### String()

- 功能：生成随机字符串。

- 描述：基于默认字符集生成指定长度的随机字符串，允许用户自定义随机字符串字符集，默认字符集：ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890。

- 构造器：

    - @String()：默认生成6位固定长度的随机字符。
    - @String(length)：生成固定长度字符串。
    - @String(length,nullWeight)：生产固定范围长度的字符串，并指定null值占比。
    - @String(length,nullWeight,chars)：在用户指定字符集内生成固定长度字符串，并指定null值占比。
    - @String(min,max)：生成指定长度范围的随机字符。
    - @String(length,min,max,chars,nullWeight)：完整参数构造器，具体请查看完整参数说明。

- 参数说明：

    - length：字符串长度，默认 6，最大长度 2^31-1 个字符。
    - min：最小长度，不指定时默认长度 0（返回空串""），如果同时指定了 length 当前参数不生效。
    - max：最大长度，最大长度上限 2^31-1 个字符，如果同时指定了 length 当前参数不生效。
    - chars：用于生成字符串的字符集元素，不指定时默认从 52 英文字母（a-z A-Z）和 10 个阿拉伯数字（0-9）中随机选择指定长度字符串，最大 2^31-1 个字符数，如：123456ABCDEF。
    - nullWeight：为空(null)比例，如值：“1:2” ，表示生成随机字符串 3次平均1次为null。

  > 不指定 length 或 max 时，默认随机生成 6 个字符随机字符串。

- 使用示例：

    1. 调用 @String() 1 次结果：ceja7d。
    2. 调用 @String(2) 5 次结果：uy h8 ut cs 9i。
    3. 调用 @String(2,6) 5 次结果：67h1g ht8ut cy t5s 9iyb5g。
    4. 调用 @String(3,abcdef123456) 3 次结果：ec2 f36 c3a。
    5. 调用 @String("1:2") 3 次结果：luj76 null nj6gH。
    6. 调用 @String(1,,,ABCDE,"1:2") 5 次结果：A null B C null。

### Integer()

- 功能：生成任意整型数值。

- 描述：用于生成4字节任意整型数值，有符号时值范围为 -2147483648 ～ 2147483647。

- 构造器：

    - @Integer()：默认生成一个值范围为 0 ～ 2147483647 随机整数。
    - @Integer(nullWeight)：生成一个值范围为 0 ～ 2147483647 随机整数，并指定null值占比。
    - @Integer(min,max)：生成一个值范围为 min ～ max 随机整数。
    - @Integer(min,max,nullWeight)：完整参数构造器，生成一个值范围为 min ～ max 随机整数，并指定null值占比。

- 参数说明：

    - min：最小值，不指定默认最小 0。
    - max：最大值，不指定时默认最大 2147483647。
    - nullWeight：为空(null)比例，如值："1:9" ，表示生成随机整数 10 次平均 1 次为null，默认不为空。

- 使用示例：

    1. 调用 @Integer() 1 次结果：3821。
    2. 调用 @Integer("1:2") 3 次结果：8760182 237 null。
    3. 调用 @Integer(100,200) 3 次结果：162 133 191。
    4. 调用 @Integer(-100000,100000,"1:3") 5 次结果：-7811 null 78732 12909 76028。

### Long()

- 功能：生成任意长整型数值。

- 描述：用于生成8字节任意长整型数值，有符号时值范围为 -9223372036854775808 ～ 9223372036854775807。

- 构造器：

    - @Long()：默认生成一个值范围为 0 ～ 9223372036854775807 随机长整数。
    - @Long(nullWeight)：生成一个值范围为 0 ～ 9223372036854775807 随机长整数，并指定null值占比。
    - @Long(min,max)：生成一个值范围为 min ～ max 随机长整数。
    - @Long(min,max,nullWeight)：完整参数构造器，生成一个值范围为 min ～ max 随机长整数，并指定null值占比。

- 参数说明：

    - min：最小值，不指定默认最小 0L，其中字符`L`表示值为长整形参数值。
    - max：最大值，不指定时默认最大 9223372036854775807L。
    - nullWeight：为空(null)比例，如值："1:9" ，表示生成随机随机长整数 10 次平均 1 次为null，默认不为空。

- 使用示例：

    1. 调用 @Long() 1 次结果：2567071027。
    2. 调用 @Long("1:2") 3 次结果：98089 null 28907625479。
    3. 调用 @Long(1L,10000000000L) 3 次结果：1109 34008978 87256252199901。
    4. 调用 @Long(-100000L,100000L,) 5 次结果：198 594 -17865 9876 37092。

### Float()

- 功能：生成任意浮点型数值。

- 描述：生成4字节任意浮点型数值，有符号时值范围为 1.40129846432481707e-45 ～ 3.40282346638528860e+38。

- 构造器：

    - @Float()：默认生成一个值范围为 0 ～ 3.40282346638528860e+38 随机浮点型数值。
    - @Float(nullWeight)：生成一个值范围为 0 ～ 3.40282346638528860e+38 随机浮点型数值，并指定null值占比。
    - @Float(min,max)：生成一个值范围为 min ～ max 随机浮点型数值。
    - @Float(scale)：生成一个值范围为 0 ～ 3.40282346638528860e+38 精度为 scale 随机浮点型数值
    - @Float(min,max,scale)：生成一个值范围为 min ～ max 且精度为 scale 随机浮点型数值
    - @Float(min,max,scale,nullWeight)：完整参数构造器，具体请查看完整参数说明。

- 参数说明：

    - min：最小值，不指定默认最小 0。
    - max：最大值，不指定时默认最大 3.40282346638528860e+38。
    - scale：精度位，不指定时默认为 2。
    - nullWeight：为空(null)比例，如值："1:9" ，表示生成随机浮点型数 10 次平均 1 次为null，默认不为空。

- 使用示例：

    1. 调用 @Float() 1 次结果：817829.19。
    2. 调用 @Float(-10000000F,10000000F) 2 次结果：7182.09 -229045.82。
    3. 调用 @Float(5) 2 次结果：87901.01127 -3092290435.18326。
    4. 调用 @Float(100F,1000F,3) 5 次结果：891.018 231.098 23.101。
    5. 调用 @Float("1:3") 5 次结果：9801.09 -2285631.09 null。
    6. 调用 @Float(100.900F,10000000.456F,3,"1:3") 5 次结果：233.810 98012.634 null 190881.183 587212.304。

### Double()

- 功能：生成任意双精度浮点数。

- 描述：生成8字节任意任意双精度浮点数，有符号时值范围为 4.94065645841246544e-324 ～ 1.79769313486231570e+308。

- 构造器：

    - @Double()：默认生成一个值范围为 0 ～ 3.40282346638528860e+38 随机双精度浮点型数值。
    - @Double(nullWeight)：生成一个值范围为 0 ～ 3.40282346638528860e+38 随机双精度浮点型数值，并指定null值占比。
    - @Double(min,max)：生成一个值范围为 min ～ max 随机双精度浮点型数值。
    - @Double(scale)：生成一个值范围为 0 ～ 3.40282346638528860e+38 精度为 scale 随机双精度浮点型数值
    - @Double(min,max,scale)：生成一个值范围为 min ～ max 且精度为 scale 随机双精度浮点型数值
    - @Double(min,max,scale,nullWeight)：完整参数构造器，具体请查看完整参数说明。

- 参数说明：

    - min：最小值，不指定默认最小 0D，其中字符`D`表示值为双精度浮点型参数值。
    - max：最大值，不指定时默认最大 1.79769313486231570e+308。
    - scale：精度位，不指定时默认为 2。
    - nullWeight：为空(null)比例，如值："1:9" ，表示生成随机双精度浮点数 10 次平均 1 次为null，默认不为空。

- 使用示例：

    1. 调用 @Double() 1 次结果：817829.19。
    2. 调用 @Double(-10000000D,10000000D) 2 次结果：347072.67 -8018231.12。
    3. 调用 @Double(5) 2 次结果：32678034.27846 2309.83108。
    4. 调用 @Double(100D,1000D,3) 5 次结果：889.110 318.008 427.809。
    5. 调用 @Double("1:3") 5 次结果：null 53081229.21 23.101。
    6. 调用 @Double(-10000.456D,10000000.456D,4,"1:3") 5 次结果：233.0134 null 98012.0809 457.1183 null。

### Bool()

- 功能：生成布尔类型值。

- 构造器：生成布尔类型值，对应true/false，可应用与二选一的情况

    - @Bool()：默认生成true或false，比例2：1
    - @Bool(trueWeight)：生成true、false、null，其中null按指定占比生成
    - @Bool(trueWeight,nullWeight)：生成true、false、null，其中true占比为trueWeight，null占比为nullWeight
    - @Bool(trueWeight,nullWeight,dict) 完整参数构造器，具体请查看完整参数说明。

- 参数说明：

    - trueWeight：为真比例，如值：“2:1” ，表示生成随机 Bool 值 3 次平均 2 次为 true，一次为 false，默认：2:1。
    - nullWeight：为空(null)比例，如值："1:9" ，表示生成随机字符串 10 次平均 1 次为null，默认不为空。
    - dict：用于生成布尔值的字典，字典值通过|分隔。
        - 只能有两个值，且第一个对应true，第二个对应false，例如：1|0,男|女
        - 不指定时默认 true 和 false。

- 使用示例：

    1. 调用 @Bool() 1 次结果：false。
    2. 调用 @Bool(2:1) 6 次结果：true false true false true true。
    3. 调用 @Bool(2:1,"1:5") 6 次结果：true false true null true true。
    4. 调用 @Bool(1:2,"1:5","1|0") 6 次结果：0 0 0 1 null 0。

### Enum()

- 功能：用于随机生成固定常量。

- 构造器：

    - @Enum(dict)：默认，生成指定字典的常量值，每个常量值占比相同，即为1:1。
    - @Enum(dict,valueWeight)：生成指定字典的常量值，并指定每个常量值的占比
    - @Enum(dict,valueWeight,nullWeight)：生成指定字典的常量值，并指定每个常量值的占比和null值占比

- 参数说明：

    - dict：用于生成枚举常量的值，多个值通过“|”分隔，不能为空。
    - valueWeight：值的生成比例，不指定默认：“1:1:1....”。
    - nullWeight：为空(null)比例，如值："1:9" ，表示生成随机字符串 10 次平均 1 次为null，默认不为空。

- 使用示例：

    1. 调用 @Enum(red|orange|yellow) 5 orange orange red orange red。
    2. 调用 @Enum(red|orange|yellow,8:1:1) 5 red yellow red red orange red orange red red red。
    3. 调用 @Enum(red|orange,3:1,1:7) 8 次结果：red red red red orange red red null red。

### RegExp()

- 功能：根据正则表达式生成随机值。

- 构造器：根据正则表达式生成随机值

    - @RegExp(regexp)：默认，根据正则表达式生成随机值。
    - @RegExp(regexp,nullWeight)：根据正则表达式生成随机值，并指定null值占比

- 参数说明：

    - regexp：用于生成数据的正则表达式。
    - nullWeight：为空(null)比例，如值："1:9" ，表示生成随机字符串 10 次平均 1 次为null，默认不为空。

- 使用示例：

    1. 调用 @RegExp([a-z][a-z][0-9]) 3 次结果：tM2 vN4 kK8。
    2. 调用 @RegExp([a-z][a-z][0-9],1:2) 3 次结果：uH7 null jI3。

## 日期与时间

### LocaleDate()

- 功能：生成本地化的当前日期，时区默认为应用 JVM 设置时区或操作系统时区。

- 构造器：

    - @LocaleDate() - 默认生成当前日期，格式为yyyy-MM-dd，时区为Asia/Shanghai
    - @LocaleDate(format) - 生成当前日期，格式为format，时区为Asia/Shanghai
    - @LocaleDate(format,zoneId) - 生成指定时区的当前日期，格式为format
    - @LocaleDate(format,random) - 生成随机日期，格式为format，时区为Asia/Shanghai
    - @LocaleDate(format,zoneId,random) - 生成随机日期，格式为format，时区为zoneId

- 参数说明：

    - format：日期格式化模版，默认格式 “yyyy-MM-dd”，详情请查看 Java [DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html) 实现。
    - zoneId：时区 ID，默认值 “Asia/Shanghai”，详情请查看 Java [ZoneId](https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html)
    - random: 生成过去10年和未来10年的随机日期，默认 false。

- 使用示例：

    1. 调用 @LocaleDate() 1 次结果：1973-09-27。
    2. 调用 @LocaleDate("yyyy-MM-dd") 1 次结果：1991-08-08。
    3. 调用 @LocaleDate("yyyy-MM-dd", "Asia/Shanghai") 1 次结果：1991-08-08。
    4. 调用 @LocaleDate("y-MM-dd") 1 次结果：84-05-31。
    5. 调用 @LocaleDate("y-M-d") 1 次结果：81-1-7。
    6. 调用 @LocaleDate("yyyy yy y MM M dd d") 1 次结果：1994 94 94 05 5 30 30。

### LocaleTime()

- 功能：生成本地化的当前时间，时区默认为应用 JVM 设置时区或操作系统时区。

- 构造器：

    - @LocaleTime() - 默认生成当前时间，格式为HH:mm:ss，时区为Asia/Shanghai。
    - @LocaleTime(format) - 生成当前时间，格式为format，时区为Asia/Shanghai。
    - @LocaleTime(format,zoneId) - 生成指定时区当前时间，格式为format。
    - @LocaleTime(format,random) - 生成随机时间，格式为format，时区为Asia/Shanghai。
    - @LocaleTime(format,zoneId,random) - 生成随机时间，格式为format，时区为zoneId。

- 参数说明：

    - format：日期格式化模版，默认格式 “HH:mm:ss”，详情请查看 Java [DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html) 实现。
    - zoneId：时区 ID，默认值 “Asia/Shanghai”，详情请查看 Java [ZoneId](https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html)
    - random: 生成一天内随机时间，默认 false。

- 使用示例：

    1. 调用 @Time() 1 次结果：09:22:41。
    2. 调用 @Time("A HH:mm:ss") 1 次结果：PM 12:39:05。
    3. 调用 @Time("HH:mm:ss") 1 次结果：06:08:21。
    4. 调用 @Time("HH:mm:ss","Asia/Tokyo") 1 次结果：07:08:21。
    5. 调用 @Time("H:m:s") 1 次结果：18:5:54。
    6. 调用 @Time("HH H hh h mm m ss s SS S A a T") 1 次结果：18 18 06 6 46 46 53 53 984 984 PM pm 1409568413984。

### LocaleDateTime()

- 功能：生成本地化的当前日期时间，时区默认为应用 JVM 设置时区或操作系统时区。

- 构造器：

    - @LocaleDateTime() - 默认生成当前日期和时间，格式为yyyy-MM-dd HH:mm:ss，时区为Asia/Shanghai。
    - @LocaleDateTime(format) - 生成当前日期和时间，格式为format，时区为Asia/Shanghai。
    - @LocaleDateTime(format,zoneId) - 生成指定时区当前日期和时间，格式为format。
    - @LocaleDateTime(format,random) - 生成随机日期和时间，格式为format，时区为Asia/Shanghai。
    - @LocaleDateTime(format,zoneId,random) - 生成随机日期和时间，格式为format，时区为zoneId。

- 参数说明：

    - format：日期格式化模版，默认格式 “yyyy-MM-dd HH:mm:ss”，详情请查看 Java [DateTimeFormatter](https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html) 实现。
    - zoneId：时区 ID，默认值 “Asia/Shanghai”，详情请查看 Java [ZoneId](https://docs.oracle.com/javase/8/docs/api/java/time/ZoneId.html)
    - random: 生成过去10年和未来10年随机日期时间，默认 false。

- 使用示例：

    1. 调用 @LocaleDateTime() 1 次结果：2021-01-09 10:39:51。
    2. 调用 @LocaleDateTime("yyyy-MM-dd A HH:mm:ss") 1 次结果：2015-04-23 AM 03:29:57。
    3. 调用 @LocaleDateTime("yy-MM-dd a HH:mm:ss") 1 次结果：15-03-31 am 05:27:27。
    4. 调用 @LocaleDateTime("yy-MM-dd a HH:mm:ss","Asia/Tokyo") 1 次结果：15-03-31 am 06:27:27。
    5. 调用 @LocaleDateTime("y-M-d H:m:s") 1 次结果：78-12-19 9:29:22。
    6. 调用 @LocaleDateTime("yyyy yy y MM M dd d HH H hh h mm m ss s SS S A a T") 1 次结果：2006 06 06 01 1 21 21 00 0 00 0 40 40 16 16 556 556 AM am 1137775216556。

### Timestamp()

- 功能：生成当前时间对应的时间戳。

- 构造器：

    - @Timestamp() - 默认生成当前操作系统的时间戳
    - @Timestamp(unix) - 生成指定类型的当前操作系统时间戳

- 参数说明：

    - unix：默认 false，生成包含毫秒值的系统时间戳，设置成 true 时生成 Unix 时间戳，即自1970年1月1日00:00:00 UTC以来经过的秒数。

- 使用示例：

    1. 调用 @LocaleDateTime() 1 次结果：1723354996857。
    2. 调用 @LocaleDateTime(true) 1 次结果：1723354996。

### Week()

- 功能：生成随机星期。

- 构造器：

    - @Week() - 默认生成随机中文星期。
    - @Week(locale) - 生成一个星期，语言使用指定的locale。

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。

- 使用示例：

    1. 调用 @Week() 1 次结果：星期二。
    2. 调用 @Week(en) 1 次结果：Tuesday。

### Month()

- 功能：生成随机月份。

- 构造器：

    - @Month() - 默认生成随机中文月份。
    - @Month(locale) - 生成一个月份，语言使用指定的locale。

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。

- 使用示例：

    1. 调用 @Month() 1 次结果：一月。
    2. 调用 @Month(en) 1 次结果：January。

## ID生成

### Guid()

- 功能：基于 GUID 算法生成 ID 标识。

- 构造器：

    - @Guid() - 默认基于GUID算法生成没有分隔符ID。
    - @Guid(withoutSeparator) - 基于GUID算法生成包含"-"分隔符的ID。

- 参数说明：

    - withoutSeparator：布尔值，控制是否不带分割符号"-"，false 展示分割符，true 不展示分割符，默认 false。

- 使用示例：

    1. 调用 @Guid() 1 次结果：1a63f6c6-bae4-4b69-a4b4-8f88c4039803。
    2. 调用 @Guid(false) 1 次结果：1a63f6c6bae44b69a4b48f88c4039803。

### Uuid()

- 功能：基于 UUID 算法生成 ID 标识。

- 构造器：

    - @Uuid()- 默认基于UUID算法生成没有分隔符ID。
    - @Uuid(withoutSeparator) - 基于GUID算法生成包含"-"分隔符的ID。

- 参数说明：

    - withoutSeparator：布尔值，控制是否不带分割符号"-"，false 展示分割符，true 不展示分割符，默认 false。

- 使用示例：

    1. 调用 @Uuid() 1 次结果：690bce55-07dd-4d9e-af79-18d337ac0dba。
    2. 调用 @Uuid(false) 1 次结果：690bce5507dd4d9eaf7918d337ac0dba。

### SnowId()

- 功能：基于 SnowFlake 算法生成 ID 标识，分布式多节点生成 ID 不重复。

- 构造器：

    - @SnowId() - 基于SnowFlake算法生成ID，默认数据中心ID为1、机器ID为1。
    - @SnowId(dcId,mId) - 基于SnowFlake算法生成ID，数据中心ID为指定dcId，机器ID为指定mid。

- 参数说明：

    - dcId：数据中心 ID 标识，默认 1。
    - mId：机器 ID 标识，默认 1，多节点分布式压测时根据压测节点自动设置机器 ID。

  > 分布式多环境多节点时 dcId 和 mId 必须保持唯一。

- 使用示例：

    1. 调用 @SnowId() 3 次结果：601810569825357825 601810569825357826 601810569825357827。
    2. 调用 @SnowId(1,3) 3 次结果：601810727057240170 601810727057240171 601810727057240172。

### IncId()

- 功能：基于自增生成 ID 标识，适用单节点测试，分布式多节点生成 ID 可能重复。

- 构造器：

    - @Incid() - 默认生成一个自增ID，初始化值为1，自增步长为1。
    - @Incid(init) - 生成一个自增ID，初始化值为init，自增步长为1。
    - @Incid(init,step) - 生成一个自增ID，初始化值为init，自增步长为step。

- 参数说明：

    - init：初始化值，默认从 1 开始。
    - step：自增步长，默认每次加 1。

- 使用示例：

    1. 调用 @Incid() 1 次结果：1。
    2. 调用 @Incid(100) 1 次结果：101。
    3. 调用 @Incid(100,100) 2 次结果：200 300。

## 文本信息

### Paragraph()

- 功能：生成段落，默认段落长度 200-1000 个字符，默认支持 100 个段落。

- 构造器：

    - Paragraph() - 生成一个段落，语言默认中文，字典使用默认。
    - Paragraph(locale) - 生成一个段落，语言使用指定的locale。
    - Paragraph(dict) - 生成一个段落，字典使用指定的dict。

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：自定义段落字典，多个段落以“|”分割。

- 使用示例：略。

### Sentence()

- 功能：生成句子，默认句子长度 5-100 个字符，默认支持 50 个语句。

- 构造器：

    - Sentence() - 生成一个句子，语言默认中文，字典使用默认。
    - Sentence(locale) - 生成一个句子，语言使用指定的locale。
    - Sentence(dict) - 生成一个句子，字典使用指定的dict。

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：自定义语句字典，多个语句以“|”分割。

- 使用示例：略。

### Word()

- 功能：生成单词，默认支持 100 个词。

- 构造器：

    - Word() - 生成一个单词，语言默认中文，字典使用默认。
    - Word(locale) - 生成一个单词，语言使用指定的locale。
    - Word(dict) - 生成一个单词，字典使用指定的dict。

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：自定义单词字典，多个单词以“|”分割。

- 使用示例：略。

### Title()

- 功能：生成标题，单个标题 2-20 个字符，默认支持 100 个标题。

- 构造器：

    - Title() - 生成一个标题，语言默认中文，字典使用默认。
    - Title(locale) - 生成一个标题，语言使用指定的locale。
    - Title(dict) - 生成一个标题，字典使用指定的dict。

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：自定义标题字典，多个标题以“|”分割。

- 使用示例：略。

### TangPoetry()

- 功能：生成唐诗，默认支持 100 首。

- 构造器：

    - TangPoetry() - 生成一个唐诗，语言默认中文，字典使用默认。
    - TangPoetry(locale) - 生成一个唐诗，语言使用指定的locale。
    - TangPoetry(dict) - 生成一个唐诗，字典使用指定的dict。

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：自定义唐诗字典，多个唐诗以 “|” 分割。

- 使用示例：略。

## 地理信息

### Country()

- 功能：生成国家。（基于 ISO 3166-1 标准）

- 构造器：

    - Country() - 生成一个国家，语言默认中文，字典使用默认。
    - Country(locale) - 生成一个国家，语言使用指定的locale。
    - Country(dict) - 生成一个国家，字典使用指定的dict。

- 参数说明：
    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：自定义国家字典，多个国家以 “|” 分割。

- 使用示例：略。

### Province()

- 功能：生成省份。

- 构造器：

    - Province() - 生成一个省份，语言默认中文，字典使用默认。
    - Province(locale) - 生成一个省份，语言使用指定的locale。
    - Province(dict) - 生成一个省份，字典使用指定的dict。

- 参数说明：
    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：自定义省份字典，多个省份以 “|” 分割。

- 使用示例：略。

### City()

- 功能：生成城市。

- 构造器：

    - City() - 生成一个城市，语言默认中文，字典使用默认。
    - City(locale) - 生成一个城市，语言使用指定的locale。
    - City(dict) - 生成一个城市，字典使用指定的dict。

- 参数说明：
    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：自定义城市字典，多个城市以 “|” 分割。

- 使用示例：略。

### Address()

- 功能：生成地址。

- 构造器：

    - Address() - 生成一个地址，语言默认中文，字典使用默认。
    - Address(locale) - 生成一个地址，语言使用指定的locale。
    - Address(dict) - 生成一个地址，字典使用指定的dict。

- 参数说明：
    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：自定义地址字典，多个地址以 “|” 分割。

- 使用示例：略。

### Coordinates()

- 功能：生成随机经纬度坐标值，格式：(经度,纬度) 如：(116.41366,39.910981)。

- 构造器：

    - Coordinates() - 生成一个经纬度坐标值，默认的范围随机的经纬度坐标。
    - Coordinates(minLng,maxLng, minLat,maxLat,scale,nullWeight) - 生成一个经纬度坐标值，按指定的minLng、maxLng、minLat、maxLat、scale、nullWeight条件生成经纬度坐标。

- 参数说明：
    - minLng：最小经度，最小值 -180 （double类型）
    - maxLng：最大经度，最大值 +180
    - minLat：最小纬度，最小值 -90
    - maxLat：最大纬度，最大值 +90
    - scale：精度位，不指定时默认为 2。
    - nullWeight：为空(null)比例，如值：“1:2” ，表示生成随机值 3次平均1次为null。

- 使用示例：略。

### Longitude()

- 功能：生成随机经度坐标值，如：116.41366。

- 构造器：

    - Longitude() - 生成一个经度坐标值，默认的范围随机的经度坐标值。
    - Longitude(minLng,maxLng,scale,nullWeight) - 生成一个经度坐标值，按指定的minLng、maxLng、scale、nullWeight条件生成经度坐标值。

- 参数说明：
    - minLng：最小经度，最小值 -180 （double类型）。
    - maxLng：最大经度，最大值 +180。
    - scale：精度位，不指定时默认为 2。
    - nullWeight：为空(null)比例，如值：“1:2” ，表示生成随机值 3次平均1次为null。

- 使用示例：略。

### Latitude()

- 功能：生成随机纬度坐标值，如：39.910981。

- 构造器：

    - Latitude() - 生成一个纬度坐标值，默认的范围随机的纬度坐标值。
    - Latitude(minLat,maxLat,scale,nullWeight) - 生成一个纬度坐标值，按指定的minLat、maxLat、scale、nullWeight条件生成纬度坐标值。

- 参数说明：
    - minLat：最小纬度，最小值 -90 （double类型）。
    - maxLat：最大纬度，最大值 +90。
    - scale：精度位，不指定时默认为 2。
    - nullWeight：为空(null)比例，如值：“1:2” ，表示生成随机值 3次平均1次为null。

- 使用示例：略。

### Zip()

- 功能：生成邮政编码。

- 构造器：

    - Zip() - 生成一个邮政编码，语言默认中文，字典使用默认。
    - Zip(locale) - 生成一个邮政编码，语言使用指定的locale。
    - Zip(dict) - 生成一个邮政编码，字典使用指定的dict。

- 参数说明：

    - dict：邮政编码字典，不指定时将使用默认字典。
        - 中国区域默认（30 个）：252867|252866|252865|252864|252863|252862|252861|252800|252666|252665|252662|252661|252660|252659|252658|252657|252656|252655|252654|252653|102208|102206|102205|102204|102202|102200|101414|101413|101412|101411
        - 英国区域默认（20 个）：AGA 9AA|1AHA 4ZZ|1AJA 9AA|1ALA 5BQ|1ANA B10|1APA B10|1AQA 5BQ|1ARA B10|1ASA 9AA|1AUA 4ZZ|1AWA B10|1AXA B10|1BAA 4ZZ|1BBA 1HQ|1BDA B10|1BFA B10|1BHA 1HQ|1BRA 5BQ|1BSA 4AB|EC1A 1HQ

- 使用示例：

    1. 调用 @Zip() 3 次结果：252863 252665 1252866
    2. 调用 @Zip(en) 3 次结果：AGA 9AA, 1AHA 4ZZ, 1ANA B10
    3. 调用 @Zip(101407|101406|101405) 3 次结果：101406 101405 101406

## 哈希算法

### Sha()

- 功能：生成随机哈希值。

- 构造器：

    - Sha() - 生成一个随机哈希值，版本默认SHA-512。
    - Sha(version) - 生成一个随机哈希值，版本使用指定的version。

- 参数说明：

    - version：算法版本，固定值：SHA-1、SHA-224、SHA-256、SHA-384、SHA-512，默认 SHA-512

- 使用示例：

    1. 调用 @Sha() 1 次结果：77560f7fe014006b9fdbd78055df8c2d4d2d3fbeae54a65bd8cc4ef948de28a18b2632b051a188c8de7041b180af692028294ef80831669f6aa97d856bf40a0e
    2. 调用 @Sha(SHA-224) 1 次结果：6c01126a9010fa4ebfd2efd99b6897b7d4ecbabbfda1c673b83d3498

### Md5()

- 功能：生成随机 MD5 值。

- 构造器：

    - Md5() - 生成一个MD5 值，长度默认为32。
    - Md5(length) - 生成一个MD5 值，长度使用指定的length。

- 参数说明：

    - Length：摘要长度，固定值：16、32，默认 32

- 使用示例：

    1. 调用 @Md5() 1 次结果：15cc0ab708534530e04c42d13876ca2f
    2. 调用 @Md5(16) 1 次结果：08534530e04c42d1

## 网络信息

### Url()

- 功能：生成 URL。

- 构造器：

    - @Url()
    - @Url(max)
    - @Url(main,protocol,domain,allowQueryParams)

- 参数说明：

    - max：最大长度，默认 50 个字符，最大允许 65535 个字符。
    - protocol：协议，固定值：http 或 https，默认 http。
    - domain：域名或 IP，如：www.xcan.org、192.168.1.2，默认 127.0.0.1:8080。
        - 常用路径资源名称：
    - allowQueryParams：是否允许生产查询参数，如：http://127.0.0.1:8080/api/v1/user?`name=Tome&gender=male`, 默认 false。
        - 常用参数名：

- 使用示例：

    1. 调用 @Url() 1 次结果： http://127.0.0.1:8080/aacj/uics。
    2. 调用 @Url(80) 1 次结果：http://127.0.0.1:8080/sjHbAhst6k09jsBj/089Cuic/jssPc8sm。
    3. 调用 @Url(30,"https","www.xcan.org",true) 2 次结果：http://www.xcan.org:8080/aacj/uics?acc=67&ubc7=8jFc http://www.xcan.org:8080/aacj/uics?name=8jkc&gggbssiu=78hbss。

### Protocol()

- 功能：生成协议。

- 构造器：

    - @Protocol()
    - @Protocol(dict)

- 参数说明：

    - dict：协议字典，不指定时默认：FTP|TFTP|HTTP|SMTP|DHCP|Telnet|DNS|SNMP|DNS|TCP|UDP|ARP|DHCP|SIP|RTP|RLP|RAP|L2TP|PPTP|SNMP|TFTP。

- 使用示例：

    1. 调用 @Protocol() 3 次结果： SMTP DNS ARP。
    2. 调用 @Protocol(“POP|POP3|IMAP|IMAP4”) 3 次结果：POP3 IMAP4 POP3。

### IPv4()

- 功能：生成 IPv4 地址，范围：0.0.0.0 — 255.255.255.255。

- 构造器：

    - @IPv4()

- 使用示例：

### IPv6()

- 功能：生成 IPv6 地址，范围：0000:0000:0000:0000:0000:0000:0000:0000 — ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff（16 字节 128 位，8 段 16 进制“:”相连）。

- 构造器：

    - @IPv6()

- 使用示例：

### Port()

- 功能：生成服务器随机端口，范围：0 - 65535。

- 构造器：

    - @Port()
    - @Port(min,max)

- 参数说明：

    - min：最小端口，不指定时默认 1024，最小 0。
    - max：最大端口，不指定时默认 65535，最大 65535。

  > 通常端口 0-1023 会被系统服务或者应用所使用。

- 使用示例：

### Mac()

- 功能：生成 MAC 地址，范围：00:00:00:00:00:00 - FF:FF:FF:FF:FF:FF（6 字节 48 位，6 段 16 进制“:”相连）。

- 构造器：

    - @Mac()

- 参数说明：

- 使用示例：

### AppName()

- 功能：生成应用名称

- 构造器：

    - @AppName()
    - @AppName(dict)

- 参数说明：

- 使用示例：

### AppVersion()

- 功能：生成应用版本，格式：major.minor.patch[-releaseState][+buildstate]，其中 major 为主版本号，minor 为子版本号，patch 为修订版本号，取值范围：0-255。

- 构造器：

    - @AppVersion()
    - @AppVersion(prefix)
    - @AppVersion(prefix,releaseStateDict)
    - @AppVersion(prefix,releaseStateDict,buildStateDict)

- 参数说明：

    - prefix：版本前缀，如：v，wchat ，默认不指定时无前缀。
    - releaseStateDict：版本发布状态字典，示例：SNAPSHOT、BETA、RELEASE、DEMO、SP、TRIAL、LITE、FREE、ENHANCE。
    - buildStateDict：版本构建状态字典，示例：build.1、build.2、build.3。

- 使用示例：

    1. 调用 @AppVersion() 1 次结果：18.30.9。
    2. 调用 @AppVersion(wchat,SNAPSHOT|BETA|RELEASE) 1 次结果：wchat-10.0.1-RELEASE。
    3. 调用 @AppVersion(v,RELEASE,build.1|build.2) 1 次结果：v105.12.215-RELEASE+build.1。

## 用户相关

### Name()

- 功能：生成用户的姓名。

- 构造器：

    - @Name()
    - @Name(locale)
    - @Name(dict)

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：名字字典，不指定时将使用 @Firstname() 和 @Lastname() 默认字典组合生成。

  > 生成中文名称姓在前名在后，英文名相反。

- 使用示例：

    1. 调用 @Name() 3 次结果：罗超俊 郑倾宇 何政贤。
    2. 调用 @Name(“en”) 3 次结果：Chris Uggen Daniel Aiello Albert Fuller。
    3. 调用 @Name(欧阳娜娜|贾玲) 3 次结果：欧阳娜娜 贾玲 欧阳娜娜。

### Passd()

- 功能：生成指定强度随机密码

- 构造器：

    - @Passd()
    - @Passd(min,max)
    - @Passd(allowUpperCase,allowLowerCase,allowDigits,allowSpecialChar)
    - @Passd(min,max,allowUpperCase,allowLowerCase,allowDigits,allowSpecialChar)

- 参数说明：

    - min：密码最小长度，默认 6 个字符，最小允许 1 个字符。
    - max：密码最大长度，默认 20 个字符，最大允许 65535 个字符。
    - allowUpperCase：布尔值，是否允许大写字母，默认 true。
    - allowLowerCase：布尔值，是否允许小写字母，默认 true。
    - allowDigits：布尔值，是否允许数字，默认 true。
    - allowSpecialChar：布尔值，是否允许特殊符号，默认 false，特殊字符包括：`-=[];',./~!@#$%^&\*()\_+{}:"<>? 。

- 使用示例：

    1. 调用 @Passd() 2 次结果：js8Jms kj0Oksc9JCs TbSNNi0m5d。
    2. 调用 @Passd(5,8) 3 次结果：a9KmsCm J07liS Incs4f0。
    3. 调用 @Passd(5,5,false,false,true,true) 3 次结果：\*0%6c ^]<1> :=$%! 。

### Firstname()

- 功能：生成用户的名字。

- 构造器：

    - @Firstname()
    - @Firstname(locale)
    - @Firstname(dict)

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：名字字典，不指定时将使用默认名字，默认如下：
        - 中文默认名字（50 个）：薇薇|紫沫|韩薇|艾莉|冥雪|雨玄|容夜|倾宇|翼杉|七夜|羽熙|超俊|政贤|阳洛|洛夜|宇成|佑染|政晧|诩浩|相宇|傲然|桂花|建国|淑华|桂兰|淑兰|婷婷|秀珍|凤兰|志强|浩|豪|天|明|鸣|碌|鹏|德|华|博|州|斌|强|晋|杰|宏|睿|瑜|勇|坤
        - 英文默认名字（50 个）：Jack|David|Ben|Aaron|Carl|Abel|Bill|Cary|Adrian|Alex|Alexander|Colin|Alan|Caspar|Albert|Derek|Fred|Edward|Bob|Denny|Gaby|Brandon|Oliver|Charles|Frank|Oliver|Brant|Jackson|Bruce|Gavin|Cheney|Chris|Evan|Edwin|George|Jacob|Daniel|Gabriel|Edgar|Howard|Eric|Franklin|Gary|Hugo|Jim|Hunk|Harrison|Joshua|Mark|Simon

- 使用示例：

    1. 调用 @Firstname() 3 次结果：洛夜 相宇 秀珍。
    2. 调用 @Firstname(“en”) 3 次结果：Edwin Jack Daniel。
    3. 调用 @Firstname(冥雪|倾宇) 3 次结果：倾宇 冥雪 倾宇。

### Lastname()

- 功能：生成用户的姓氏。

- 构造器：

    - @Lastname()
    - @Lastname(locale)
    - @Lastname(dict)

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：姓氏字典，不指定时将使用默认姓氏，默认如下：
        - 中文默认姓氏（50 个）：李|王|张|刘|陈|杨|赵|黄|周|吴|徐|孙|胡|朱|高|林|何|郭|马|罗|梁|宋|郑|谢|韩|唐|冯|于|董|萧|程|曹|袁|邓|许|傅|沈|曾|彭|吕|苏|卢|蒋|蔡|贾|欧阳|太史|端木|上官|司马
        - 英文默认姓氏（50 个）：Abra|Bandy|Acherman|Benz|Cabin|Christian|Dale|Levi|Durfee|Earl|Guetzkow|Ewick|Burt|Morales|Warner|Falletta|Rio Blanco|Fuller|Riguera|Gustafson|Hirtz|Slammon|Ho|Hoffmann|Tarant|Jorgensen|Uggen|Kohler-Hausmann|Lessan|Aiello|Ahmed|Maddox|Smith|Lin|Chriss|Tsai|Shlala|Liu|Stark4|Wilson|Lyons|McEwen|De Lung|Morrill|Ninh|Saegusa|Poon|McElvain|Romero|Ryo|Saguy|

- 使用示例：

    1. 调用 @Lastname() 3 次结果：孙 林 梁。
    2. 调用 @Lastname(“en”) 3 次结果：Aiello Maddox Chriss。
    3. 调用 @Lastname(贾|欧阳) 3 次结果：欧阳 欧阳 贾。

### Gender()

- 功能：生成用户的姓别。

- 构造器：

    - @Gender()
    - @Gender(locale)
    - @Gender(dict)

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：性别字典，默认如下：
      中文默认性别：男|女
      英文默认性别：male|female

- 使用示例：

    1. 调用 @Gender() 3 次结果：男 女 女。
    2. 调用 @Gender(“en”) 3 次结果：male female male。
    3. 调用 @Gender(“M|F”) 3 次结果： M F F。

### Age()

- 功能：生成用户的年龄，默认 1-100 岁。

- 构造器：

    - @Age()
    - @Age(min,max)

- 参数说明：

    - min：年龄最大值，默认最小 0 岁，最大 100 岁。
    - max：年龄最大值，默认最小 0 岁，最大 100 岁。

- 使用示例：

    1. 调用 @Age() 3 次结果：7 10 89。
    2. 调用 @Age(130) 3 次结果：17 109 30。

### Mobile()

- 功能：生成手机号。

- 构造器：

    - @Mobile()
    - @Mobile(locale)

  > 中国手机号格式：1xx xxxx xxxx，英国手机号格式：07xxx xxx xxx。

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。

- 使用示例：

    1. 调用 @Mobile() 3 次结果：13200879156 13987610099 15210879213。
    2. 调用 @Mobile(“en”) 3 次结果：07247560503 07247560503 072475605033。

### Landline()

- 功能：生成座机号。

- 构造器：

    - @Landline()
    - @Landline(locale)

- 使用示例：

    1. 调用 @Landline() 3 次结果：
    2. 调用 @Landline(“en”) 3 次结果：

### Email()

- 功能：生成邮箱。

- 构造器：

    - @Email()
    - @Email(min,max)
    - @Email(suffix)
    - @Email(min,max,suffix)

- 参数说明：

    - min：邮箱最小长度，默认 6 个字符，最小 1 个字符。
    - max：邮箱最大长度，默认 20 个字符，最大 65535 个字符。
    - suffix：邮箱后缀，多个值以“|”分割，默认值： @xcan.company |@hotmail.com |@126.com |@yahoo.com.cn |@yahoo.com |@live.com |@153.com |@qq.com

- 使用示例：

    1. 调用 @Email() 1 次结果：shh6r2@qq.com。
    2. 调用 @Email(5,10) 3 次结果：zjs7cb@hotmail.com 98gsHnC@126.com jsGH6sc@yahoo.com。
    3. 调用 @Email(5,5,@xcan.company) 3 次结果：4g4Fv@xcan.company Hv8jh@xcan.company MrGv1@xcan.company。

### Education()

- 功能：生成学历。

- 构造器：

    - @Education()
    - @Education(locale)
    - @Education(dict)

- 参数说明：

    - locale：国际化语言，目前只支持中文（zh_CN）和英文（en）、默认中文。
    - dict：学历字典，默认如下：
        - 中国学历：小学、初中、高中、大专、本科、研究生。
        - 英语学历：primary school, junior high school, senior high school, junior college, undergraduate, graduate

- 使用示例：

    1. 调用 @Education() 2 次结果：小学 研究生。
    2. 调用 @Education(en) 3 次结果：junior high school, undergraduate, graduate。
    3. 调用 @Education(本科|研究生) 3 次结果：研究生 本科 本科。

## 本地化

### Locale()

- 功能：生成国际化配置，包括语言和国家区域。

- 构造器：

    - @Locale()
    - @Locale(joiner)

- 参数说明：

    - joiner：国家和语言连接符.支持一个字符,默认使用 "_"。

- 使用示例：

    1. 调用 @Locale() 3 次结果：en zh_CN en。
    2. 调用 @Locale("-") 3 次结果：en zh-CN zh-CN。

  > 目前只支持中文（zh_CN）和英文（en）。

### TimeZone()

- 功能：生成国际化时区。

- 构造器：

    - @TimeZone()
    - @TimeZone(dict)

- 参数说明：

    - dict：区域字典，多个区域“|”分割，默认值：Australia/Darwin | Australia/Sydney | America/Argentina/Buenos_Aires | Africa/Cairo | America/Anchorage | America/Sao_Paulo | Asia/Dhaka | Africa/Harare | America/St_Johns | America/Chicago | Asia/Shanghai | Africa/Addis_Ababa | Europe/Paris | America/Indiana/Indianapolis | Asia/Kolkata | Asia/Tokyo | Pacific/Apia | Asia/Yerevan | Pacific/Auckland | Asia/Karachi | America/Phoenix | America/Puerto_Rico | America/Los_Angeles | Pacific/Guadalcanal | Asia/Ho_Chi_Minh。

- 使用示例：

    1. 调用 @TimeZone() 3 次结果：Australia/Darwin Africa/Cairo Europe/Paris。
    2. 调用 @TimeZone(“Pacific/Guadalcanal | Asia/Ho_Chi_Minh”) 3 次结果：Pacific/Guadalcanal Asia/Ho_Chi_Minh Pacific/Guadalcanal。


## Web网页

### MEmoji()

- 功能：生成随机Emoji表情符。

- 构造器：

    - @MEmoji() - 生成随机Emoji表情符。

- 使用示例：

    1. 调用 @MEmoji() 2 次结果：😘, 😅。

### MColor()

- 功能：生成Web颜色值。

- 构造器：

    - @MColor() - 生成默认 rgb 格式随机颜色值。
    - @MColor(format) - 生成指定格式随机颜色值。

- 参数说明：

    - format: 颜色格式，支持值：rgb、hsl、hwb、lch、cmyk，不指定或者指定不支持格式时默认 rgb。

- 使用示例：

    1. 调用 @MColor() 2 次结果：rgb(88, 245, 14) rgb(97, 69, 216)。
    2. 调用 @MColor() 2 次结果：hwb(108, 67%, 45%) hwb(133, 2%, 93%)。
