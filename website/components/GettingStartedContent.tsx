import type {Messages} from '@/lib/messages';

const MAVEN_SNIPPET = `<dependency>
  <groupId>cloud.xcan.jmock</groupId>
  <artifactId>xcan-jmock.core</artifactId>
  <version>2.0.0</version>
</dependency>

<!-- Include all plugins -->
<dependency>
  <groupId>cloud.xcan.jmock</groupId>
  <artifactId>xcan-jmock.all-plugin</artifactId>
  <version>2.0.0</version>
  <scope>runtime</scope>
</dependency>`;

const USAGE_SNIPPET = `import cloud.xcan.jmock.core.engine.MockEngine;

// Simple template rendering
MockEngine engine = MockEngine.defaultEngine();
String result = engine.render("Hello @Name(), your ID is @Integer(1000,9999)");
// → "Hello Alice, your ID is 4527"

// Batch generation
List<String> records = engine.renderBatch(template, 1000);

// Single expression evaluation
Object value = engine.evaluate("@Email()");
// → "john.doe@example.com"`;

function CodePanel({label, children}: {label: string; children: string}) {
    return (
        <div className="mt-5 rounded-xl border border-slate-200/80 bg-slate-950 shadow-sm overflow-hidden ring-1 ring-slate-900/5">
            <div className="flex items-center gap-2 px-4 py-2.5 bg-slate-900 border-b border-slate-700/80">
                <span className="h-2 w-2 rounded-full bg-red-400/90"/>
                <span className="h-2 w-2 rounded-full bg-amber-400/90"/>
                <span className="h-2 w-2 rounded-full bg-emerald-400/90"/>
                <span className="ml-2 text-xs font-medium text-slate-400 font-mono tracking-tight">
                    {label}
                </span>
            </div>
            <pre className="p-4 sm:p-5 text-[13px] sm:text-sm text-emerald-400/95 overflow-x-auto font-mono leading-relaxed whitespace-pre">
                {children}
            </pre>
        </div>
    );
}

function StepSection({
    step,
    title,
    children,
}: {
    step: number;
    title: string;
    children: React.ReactNode;
}) {
    return (
        <section className="relative scroll-mt-24">
            <div className="rounded-2xl border border-slate-200/90 bg-white/90 shadow-sm shadow-slate-200/40 overflow-hidden backdrop-blur-sm">
                <div className="flex flex-col sm:flex-row sm:items-start gap-4 p-6 sm:p-8 border-b border-slate-100 bg-gradient-to-br from-white to-slate-50/80">
                    <div
                        className="flex h-11 w-11 shrink-0 items-center justify-center rounded-xl bg-primary-600 text-lg font-bold text-white shadow-md shadow-primary-600/25">
                        {step}
                    </div>
                    <div className="min-w-0 flex-1">
                        <h2 className="text-xl sm:text-2xl font-bold tracking-tight text-slate-900">
                            {title}
                        </h2>
                    </div>
                </div>
                <div className="px-6 sm:px-8 py-6 sm:py-8 text-slate-600 leading-relaxed">{children}</div>
            </div>
        </section>
    );
}

interface Props {
    messages: Messages;
}

export default function GettingStartedContent({messages: m}: Props) {
    return (
        <div className="max-w-3xl mx-auto px-4 sm:px-6 lg:px-8 py-10 sm:py-14 pb-20">
            <header className="mb-12 sm:mb-14 text-center sm:text-left">
                <p className="text-xs sm:text-sm font-semibold uppercase tracking-wider text-primary-600 mb-3">
                    {m.gettingStarted.docBadge}
                </p>
                <h1 className="text-3xl sm:text-4xl font-extrabold tracking-tight text-slate-900">
                    {m.gettingStarted.title}
                </h1>
                <p className="mt-4 text-base sm:text-lg text-slate-600 max-w-2xl mx-auto sm:mx-0 leading-relaxed">
                    {m.gettingStarted.lead}
                </p>
            </header>

            <div className="flex flex-col gap-8 sm:gap-10">
                <StepSection step={1} title={m.gettingStarted.installation}>
                    <p className="text-slate-600 mb-1">{m.gettingStarted.installP}</p>
                    <CodePanel label={m.gettingStarted.codeMaven}>{MAVEN_SNIPPET}</CodePanel>
                </StepSection>

                <StepSection step={2} title={m.gettingStarted.basicUsage}>
                    <CodePanel label={m.gettingStarted.codeJava}>{USAGE_SNIPPET}</CodePanel>
                </StepSection>

                <StepSection step={3} title={m.gettingStarted.templateSyntax}>
                    <p>{m.gettingStarted.templateSyntaxP}</p>
                    <div className="mt-6 rounded-xl border border-slate-200 overflow-hidden bg-white shadow-sm">
                        <div className="overflow-x-auto">
                            <table className="w-full min-w-[32rem] text-sm text-left">
                                <thead>
                                    <tr className="bg-slate-50 border-b border-slate-200">
                                        <th className="px-4 py-3 font-semibold text-slate-800 w-[40%]">
                                            {m.gettingStarted.syntaxTableExpression}
                                        </th>
                                        <th className="px-4 py-3 font-semibold text-slate-800">
                                            {m.gettingStarted.syntaxTableMeaning}
                                        </th>
                                    </tr>
                                </thead>
                                <tbody className="divide-y divide-slate-100">
                                    {m.gettingStarted.syntaxItems.map((item) => (
                                        <tr key={item.code} className="hover:bg-slate-50/80 transition-colors">
                                            <td className="px-4 py-3 align-top">
                                                <code className="text-[13px] font-mono text-primary-700 bg-primary-50 px-2 py-1 rounded-md">
                                                    {item.code}
                                                </code>
                                            </td>
                                            <td className="px-4 py-3 text-slate-600">{item.desc}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </StepSection>

                <section className="rounded-2xl border border-primary-200/80 bg-gradient-to-br from-primary-50/90 to-white p-6 sm:p-8 shadow-sm shadow-primary-100/50">
                    <div className="flex gap-4">
                        <div
                            className="flex h-11 w-11 shrink-0 items-center justify-center rounded-xl bg-primary-100 text-primary-700 text-lg font-bold">
                            4
                        </div>
                        <div>
                            <h2 className="text-xl font-bold text-slate-900 mb-3">{m.gettingStarted.plugin}</h2>
                            <p className="text-slate-600 leading-relaxed">{m.gettingStarted.pluginP}</p>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    );
}
