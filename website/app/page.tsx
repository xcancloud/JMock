import Link from 'next/link';
import {getCategories, loadFunctionSpec} from '@/lib/loadFunctions';

const FEATURES = [
    {icon: '⚡', title: 'High Performance', desc: '2M+ records/sec generation speed'},
    {icon: '🌍', title: 'Internationalization', desc: 'Full i18n with Chinese & English'},
    {icon: '🔌', title: 'Pluggable', desc: 'SPI-based plugin architecture'},
    {icon: '📦', title: '130+ Functions', desc: 'Rich built-in mock function library'},
];

export default function HomePage() {
    const spec = loadFunctionSpec('en');
    const categories = getCategories(spec);
    const functionCount = spec.functions?.length ?? '130+';

    return (
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
            {/* Hero */}
            <section className="py-20 text-center">
                <h1 className="text-5xl font-extrabold tracking-tight text-slate-900">
                    JMock
                </h1>
                <p className="mt-4 text-xl text-slate-600 max-w-2xl mx-auto">
                    High-performance, business-grade mock data generation framework.
                    <br/>
                    Realistic data at 2M+ records/sec with {String(functionCount)} built-in
                    functions.
                </p>
                <div className="mt-8 flex gap-4 justify-center">
                    <Link
                        href="/docs/getting-started"
                        className="px-6 py-3 bg-primary-600 text-white rounded-lg font-medium hover:bg-primary-700 transition"
                    >
                        Get Started
                    </Link>
                    <Link
                        href="/playground"
                        className="px-6 py-3 bg-slate-100 text-slate-700 rounded-lg font-medium hover:bg-slate-200 transition"
                    >
                        Try Online
                    </Link>
                </div>
            </section>

            {/* Features */}
            <section className="py-12">
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
                    {FEATURES.map((f) => (
                        <div key={f.title} className="p-6 bg-white rounded-xl shadow-sm border">
                            <div className="text-3xl mb-3">{f.icon}</div>
                            <h3 className="font-semibold text-lg">{f.title}</h3>
                            <p className="text-slate-500 text-sm mt-1">{f.desc}</p>
                        </div>
                    ))}
                </div>
            </section>

            {/* Quick Example */}
            <section className="py-12">
                <h2 className="text-2xl font-bold text-center mb-8">Quick Example</h2>
                <div className="grid grid-cols-1 md:grid-cols-2 gap-6 max-w-4xl mx-auto">
                    <div>
                        <h3 className="text-sm font-medium text-slate-500 mb-2">Input Template</h3>
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
                        <h3 className="text-sm font-medium text-slate-500 mb-2">Generated
                            Output</h3>
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

            {/* Category Navigation */}
            {categories.length > 0 && (
                <section className="py-12">
                    <h2 className="text-2xl font-bold text-center mb-8">Function Categories</h2>
                    <div className="flex flex-wrap justify-center gap-3">
                        {categories.map((cat) => (
                            <Link
                                key={cat}
                                href={`/docs/functions?category=${encodeURIComponent(cat)}`}
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
