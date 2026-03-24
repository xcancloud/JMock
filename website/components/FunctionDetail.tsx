import Link from 'next/link';
import type {Messages} from '@/lib/messages';
import type {MockFunctionDef} from '@/lib/types';

interface Props {
    fn: MockFunctionDef;
    localePrefix: string;
    labels: Messages['detail'];
}

export default function FunctionDetail({fn, localePrefix, labels: d}: Props) {
    return (
        <div>
            {/* Header */}
            <div className="mb-8">
                <div className="flex items-center gap-3 mb-2">
                    <h1 className="text-3xl font-bold">@{fn.name}()</h1>
                    {fn.since === '2.0.0' && (
                        <span
                            className="text-sm bg-green-100 text-green-700 px-3 py-1 rounded-full font-medium">
              {d.newIn}
            </span>
                    )}
                    {fn.deprecated && (
                        <span
                            className="text-sm bg-red-100 text-red-700 px-3 py-1 rounded-full font-medium">
              {d.deprecated}
            </span>
                    )}
                </div>
                <p className="text-lg text-slate-600">{fn.description}</p>
                <div className="mt-2 flex gap-2">
                    {fn.tags?.map((tag) => (
                        <span key={tag}
                              className="text-xs bg-slate-100 text-slate-500 px-2 py-1 rounded">
              {tag}
            </span>
                    ))}
                    <span className="text-xs text-slate-400">
                        {d.since} {fn.since}
                    </span>
                </div>
            </div>

            {/* Parameters */}
            {fn.parameters && fn.parameters.length > 0 && (
                <section className="mb-8">
                    <h2 className="text-xl font-semibold mb-4">{d.parameters}</h2>
                    <div className="overflow-x-auto">
                        <table className="w-full text-sm border-collapse">
                            <thead>
                            <tr className="bg-slate-50 border-b">
                                <th className="text-left p-3 font-medium text-slate-600">{d.thName}</th>
                                <th className="text-left p-3 font-medium text-slate-600">{d.thType}</th>
                                <th className="text-left p-3 font-medium text-slate-600">{d.thRequired}</th>
                                <th className="text-left p-3 font-medium text-slate-600">{d.thDefault}</th>
                                <th className="text-left p-3 font-medium text-slate-600">{d.thDescription}</th>
                            </tr>
                            </thead>
                            <tbody>
                            {fn.parameters.map((p) => (
                                <tr key={p.name} className="border-b">
                                    <td className="p-3 font-mono text-primary-700">{p.name}</td>
                                    <td className="p-3 font-mono text-slate-500">{p.type}</td>
                                    <td className="p-3">{p.required ? '✓' : '—'}</td>
                                    <td className="p-3 font-mono text-slate-500">{p.defaultValue || '—'}</td>
                                    <td className="p-3 text-slate-600">{p.description}</td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </section>
            )}

            {/* Constructors */}
            {fn.constructors && fn.constructors.length > 0 && (
                <section className="mb-8">
                    <h2 className="text-xl font-semibold mb-4">{d.usage}</h2>
                    <div className="space-y-4">
                        {fn.constructors.map((c, idx) => (
                            <div key={idx} className="p-4 bg-white border rounded-lg">
                                <code
                                    className="text-sm font-mono text-primary-700 bg-primary-50 px-2 py-1 rounded">
                                    {c.signature || c.example}
                                </code>
                                <p className="text-sm text-slate-600 mt-2">{c.description}</p>
                                {c.example && (
                                    <div className="mt-3">
                                        <span
                                            className="text-xs font-medium text-slate-500">{d.example}</span>
                                        <pre
                                            className="mt-1 bg-slate-900 text-green-400 p-3 rounded text-sm">
                      {c.example}
                    </pre>
                                    </div>
                                )}
                                {c.exampleOutput && c.exampleOutput.length > 0 && (
                                    <div className="mt-2">
                                        <span
                                            className="text-xs font-medium text-slate-500">{d.output}</span>
                                        <div className="mt-1 flex flex-wrap gap-2">
                                            {c.exampleOutput.map((val, i) => (
                                                <code
                                                    key={i}
                                                    className="text-xs bg-blue-50 text-blue-700 px-2 py-1 rounded font-mono"
                                                >
                                                    {val}
                                                </code>
                                            ))}
                                        </div>
                                    </div>
                                )}
                            </div>
                        ))}
                    </div>
                </section>
            )}

            {/* Back link */}
            <Link
                href={`${localePrefix}/docs/functions`}
                className="text-sm text-primary-600 hover:text-primary-700 font-medium"
            >
                {d.back}
            </Link>
        </div>
    );
}
