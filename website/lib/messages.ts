import type {UiLocale} from './locale';

export interface Messages {
    nav: {
        docs: string;
        functions: string;
        playground: string;
        github: string;
    };
    footer: string;
    lang: {en: string; zh: string};
    home: {
        heroLine1: string;
        sublineBefore: string;
        sublineAfter: string;
        getStarted: string;
        tryOnline: string;
        features: Array<{icon: string; title: string; desc: string}>;
        quickExample: string;
        inputTemplate: string;
        generatedOutput: string;
        functionCategories: string;
    };
    functionsPage: {
        title: string;
    };
    catalog: {
        searchPlaceholder: string;
        all: string;
        newBadge: string;
        empty: string;
    };
    detail: {
        newIn: string;
        deprecated: string;
        since: string;
        parameters: string;
        thName: string;
        thType: string;
        thRequired: string;
        thDefault: string;
        thDescription: string;
        usage: string;
        example: string;
        output: string;
        back: string;
    };
    playground: {
        title: string;
        subtitle: string;
        /** Shown in a callout: use Java library for real / complex generation */
        javaLibraryHint: string;
        template: string;
        output: string;
        generate: string;
        outputPlaceholder: string;
    };
    gettingStarted: {
        title: string;
        lead: string;
        docBadge: string;
        installation: string;
        installP: string;
        basicUsage: string;
        templateSyntax: string;
        templateSyntaxP: string;
        syntaxItems: Array<{code: string; desc: string}>;
        syntaxTableExpression: string;
        syntaxTableMeaning: string;
        plugin: string;
        pluginP: string;
        codeMaven: string;
        codeJava: string;
    };
}

const en: Messages = {
    nav: {
        docs: 'Docs',
        functions: 'Functions',
        playground: 'Playground',
        github: 'GitHub',
    },
    footer: 'Open Source Mock Data Framework',
    lang: {en: 'English', zh: '中文'},
    home: {
        heroLine1: 'High-performance, business-grade mock data generation framework.',
        sublineBefore: 'Realistic data at 2M+ records/sec with ',
        sublineAfter: ' built-in functions.',
        getStarted: 'Get Started',
        tryOnline: 'Try Online',
        features: [
            {icon: '⚡', title: 'High Performance', desc: '2M+ records/sec generation speed'},
            {icon: '🌍', title: 'Internationalization', desc: 'Full i18n with Chinese & English'},
            {icon: '🔌', title: 'Pluggable', desc: 'SPI-based plugin architecture'},
            {icon: '📦', title: '130+ Functions', desc: 'Rich built-in mock function library'},
        ],
        quickExample: 'Quick Example',
        inputTemplate: 'Input Template',
        generatedOutput: 'Generated Output',
        functionCategories: 'Function Categories',
    },
    functionsPage: {
        title: 'Function Reference',
    },
    catalog: {
        searchPlaceholder: 'Search functions...',
        all: 'All',
        newBadge: 'New',
        empty: 'No functions found matching your criteria.',
    },
    detail: {
        newIn: 'New in 2.0',
        deprecated: 'Deprecated',
        since: 'Since',
        parameters: 'Parameters',
        thName: 'Name',
        thType: 'Type',
        thRequired: 'Required',
        thDefault: 'Default',
        thDescription: 'Description',
        usage: 'Usage',
        example: 'Example:',
        output: 'Output:',
        back: '← Back to Function Reference',
    },
    playground: {
        title: 'Playground',
        subtitle: 'Enter a JMock template and generate mock data instantly.',
        javaLibraryHint:
            'This page runs a small browser-side demo with only a few patterns. For nested JSON, custom functions, plugins, or complex batch rules, generate data with the Java JMock library using MockEngine in your application.',
        template: 'Template',
        output: 'Output',
        generate: 'Generate',
        outputPlaceholder: 'Click "Generate" to see output...',
    },
    gettingStarted: {
        title: 'Getting Started',
        lead: 'Add JMock to a Java project and render your first template in minutes.',
        docBadge: 'Documentation',
        installation: 'Installation',
        installP: 'Add the core artifact to your Maven pom.xml. Include all-plugin at runtime for the full built-in function set.',
        basicUsage: 'Basic Usage',
        templateSyntax: 'Template Syntax',
        templateSyntaxP: 'JMock templates use @FunctionName(params) syntax. Common examples:',
        syntaxItems: [
            {code: '@String()', desc: 'Random 6-char string'},
            {code: '@Integer(1,100)', desc: 'Random integer between 1 and 100'},
            {code: '@Email()', desc: 'Random email address'},
            {code: '@Name()', desc: 'Random person name'},
            {code: '@Repeat(@Email(),3)', desc: 'Array of 3 random emails'},
        ],
        syntaxTableExpression: 'Expression',
        syntaxTableMeaning: 'Description',
        plugin: 'Plugin Architecture',
        pluginP:
            'JMock uses Java SPI for plugin discovery. All functions are automatically registered at runtime. You can add custom functions by implementing MockFunction and registering via META-INF/services.',
        codeMaven: 'pom.xml',
        codeJava: 'Example.java',
    },
};

const zh: Messages = {
    nav: {
        docs: '文档',
        functions: '函数',
        playground: '演练场',
        github: 'GitHub',
    },
    footer: '开源模拟数据框架',
    lang: {en: 'English', zh: '中文'},
    home: {
        heroLine1: '高性能、面向业务的模拟数据生成框架。',
        sublineBefore: '单线程每秒可生成 200 万+ 条记录，内置 ',
        sublineAfter: ' 个模拟函数，数据更贴近真实业务。',
        getStarted: '快速开始',
        tryOnline: '在线体验',
        features: [
            {icon: '⚡', title: '高性能', desc: '每秒百万级记录生成能力'},
            {icon: '🌍', title: '国际化', desc: '内置中英文等多语言数据与规则'},
            {icon: '🔌', title: '可插拔', desc: '基于 SPI 的插件扩展架构'},
            {icon: '📦', title: '丰富函数库', desc: '百余种内置 Mock 函数'},
        ],
        quickExample: '快速示例',
        inputTemplate: '输入模板',
        generatedOutput: '生成结果',
        functionCategories: '函数分类',
    },
    functionsPage: {
        title: '函数参考',
    },
    catalog: {
        searchPlaceholder: '搜索函数…',
        all: '全部',
        newBadge: '新',
        empty: '没有符合筛选条件的函数。',
    },
    detail: {
        newIn: '2.0 新增',
        deprecated: '已弃用',
        since: '起始版本',
        parameters: '参数',
        thName: '名称',
        thType: '类型',
        thRequired: '必填',
        thDefault: '默认值',
        thDescription: '说明',
        usage: '用法',
        example: '示例：',
        output: '输出：',
        back: '← 返回函数参考',
    },
    playground: {
        title: '演练场',
        subtitle: '输入 JMock 模板，即时生成模拟数据（前端演示简化替换）。',
        javaLibraryHint:
            '本页仅在浏览器内演示少量固定模式。若需要嵌套结构、自定义函数、插件能力或批量复杂规则，请在应用中使用 Java JMock 库，通过 MockEngine 完成真实生成。',
        template: '模板',
        output: '输出',
        generate: '生成',
        outputPlaceholder: '点击「生成」查看输出…',
    },
    gettingStarted: {
        title: '快速开始',
        lead: '将 JMock 接入 Java 项目，几分钟内即可渲染首条模板。',
        docBadge: '文档',
        installation: '安装',
        installP: '在 Maven 的 pom.xml 中加入核心依赖；若需全部内置函数，请在 runtime 引入聚合插件 all-plugin。',
        basicUsage: '基本用法',
        templateSyntax: '模板语法',
        templateSyntaxP: 'JMock 模板使用 @函数名(参数) 形式，常见示例见下表：',
        syntaxItems: [
            {code: '@String()', desc: '随机 6 位字符串'},
            {code: '@Integer(1,100)', desc: '1～100 之间的随机整数'},
            {code: '@Email()', desc: '随机邮箱'},
            {code: '@Name()', desc: '随机人名'},
            {code: '@Repeat(@Email(),3)', desc: '3 个随机邮箱组成的数组'},
        ],
        syntaxTableExpression: '表达式',
        syntaxTableMeaning: '说明',
        plugin: '插件机制',
        pluginP:
            'JMock 通过 Java SPI 发现插件，运行时自动注册全部函数。实现 MockFunction 并在 META-INF/services 中注册即可扩展自定义函数。',
        codeMaven: 'pom.xml',
        codeJava: '示例代码',
    },
};

export function getMessages(locale: UiLocale): Messages {
    return locale === 'zh' ? zh : en;
}

export function functionsPageSubtitle(
    locale: UiLocale,
    fnCount: number,
    catCount: number,
): string {
    if (locale === 'zh') {
        return `共 ${fnCount} 个内置函数，涵盖 ${catCount} 个分类`;
    }
    return `${fnCount} built-in functions across ${catCount} categories`;
}
