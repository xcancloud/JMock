import type {Metadata} from 'next';

export const metadata: Metadata = {
    title: 'JMock — 高性能业务级模拟数据生成',
    description:
        '每秒百万级记录生成，百余种内置 Mock 函数，可插拔、支持国际化与自定义扩展。',
};

export default function ZhLayout({children}: {children: React.ReactNode}) {
    return children;
}
