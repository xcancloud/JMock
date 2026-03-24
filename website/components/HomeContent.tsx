import Link from 'next/link';
import {routePrefix, type UiLocale} from '@/lib/locale';
import type {Messages} from '@/lib/messages';
import type {FunctionSpec} from '@/lib/types';

interface Props {
    spec: FunctionSpec;
    categories: string[];
    messages: Messages;
    locale: UiLocale;
}

export default function HomeContent({spec, categories, messages: m, locale}: Props) {
    const prefix = routePrefix(locale);
    const functionCount = spec.functions?.length ?? '130+';

    return (
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            <section className="py-20 text-center">
                <h1 className="text-5xl font-extrabold tracking-tight text-slate-900">JMock</h1>
                <p className="mt-4 text-xl text-slate-600 max-w-2xl mx-auto">
                    {m.home.heroLine1}
                    <br/>
                    {m.home.sublineBefore}
                    {String(functionCount)}
                    {m.home.sublineAfter}
                </p>
                <div className="mt-8 flex gap-4 justify-center">
                    <Link
                        href={`${prefix}/docs/getting-started`}
                        className="px-6 py-3 bg-primary-600 text-white rounded-lg font-medium hover:bg-primary-700 transition"
                    >
                        {m.home.getStarted}
                    </Link>
                    <Link
                        href={`${prefix}/playground`}
                        className="px-6 py-3 bg-slate-100 text-slate-700 rounded-lg font-medium hover:bg-slate-200 transition"
                    >
                        {m.home.tryOnline}
                    </Link>
                </div>
            </section>

            <section className="py-12">
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
                    {m.home.features.map((f) => (
                        <div key={f.title} className="p-6 bg-white rounded-xl shadow-sm border">
                            <div className="text-3xl mb-3">{f.icon}</div>
                            <h3 className="font-semibold text-lg">{f.title}</h3>
                            <p className="text-slate-500 text-sm mt-1">{f.desc}</p>
                        </div>
                    ))}
                </div>
            </section>

            <section className="py-12">
                <h2 className="text-2xl font-bold text-center mb-8">{m.home.quickExample}</h2>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6 max-w-4xl mx-auto">
                    <div>
                        <h3 className="text-sm font-medium text-slate-500 mb-2">{m.home.inputTemplate}</h3>
                        <pre
                            className="bg-slate-900 text-green-400 p-4 rounded-lg text-sm overflow-x-auto">
{`{
  "name": "@Name()",
  "email": "@Email()",
  "age": "@Integer(18,65)",
  "city": "@City()"
}`}
                        </pre>
                    </div>
                    <div>
                        <h3 className="text-sm font-medium text-slate-500 mb-2">{m.home.generatedOutput}</h3>
                        <pre
                            className="bg-slate-900 text-blue-400 p-4 rounded-lg text-sm overflow-x-auto">
{`{
  "name": "Alice Johnson",
  "email": "alice.j@example.com",
  "age": 32,
  "city": "San Francisco"
}`}
                        </pre>
                    </div>
                </div>
            </section>

            {categories.length > 0 && (
                <section className="py-12">
                    <h2 className="text-2xl font-bold text-center mb-8">{m.home.functionCategories}</h2>
                    <div className="flex flex-wrap justify-center gap-3">
                        {categories.map((cat) => (
                            <Link
                                key={cat}
                                href={`${prefix}/docs/functions?category=${encodeURIComponent(cat)}`}
                                className="px-4 py-2 bg-white border rounded-full text-sm font-medium text-slate-600 hover:bg-primary-50 hover:text-primary-700 hover:border-primary-200 transition"
                            >
                                {cat}
                            </Link>
                        ))}
                    </div>
                </section>
            )}
        </div>
    );
}
