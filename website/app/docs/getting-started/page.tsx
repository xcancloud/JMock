export default function GettingStartedPage() {
    return (
        <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
            <h1 className="text-3xl font-bold mb-8">Getting Started</h1>

            <section className="prose prose-slate max-w-none">
                <h2>Installation</h2>
                <p>Add JMock to your Maven project:</p>
                <pre className="bg-slate-900 text-green-400 p-4 rounded-lg text-sm overflow-x-auto">
{`<dependency>
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
</dependency>`}
        </pre>

                <h2>Basic Usage</h2>
                <pre className="bg-slate-900 text-green-400 p-4 rounded-lg text-sm overflow-x-auto">
{`import cloud.xcan.jmock.core.engine.MockEngine;

// Simple template rendering
MockEngine engine = MockEngine.defaultEngine();
String result = engine.render("Hello @Name(), your ID is @Integer(1000,9999)");
// → "Hello Alice, your ID is 4527"

// Batch generation
List<String> records = engine.renderBatch(template, 1000);

// Single expression evaluation
Object value = engine.evaluate("@Email()");
// → "john.doe@example.com"`}
        </pre>

                <h2>Template Syntax</h2>
                <p>JMock templates use <code>@FunctionName(params)</code> syntax:</p>
                <ul>
                    <li><code>@String()</code> — Random 6-char string</li>
                    <li><code>@Integer(1,100)</code> — Random integer between 1 and 100</li>
                    <li><code>@Email()</code> — Random email address</li>
                    <li><code>@Name()</code> — Random person name</li>
                    <li><code>@Repeat(@Email(),3)</code> — Array of 3 random emails</li>
                </ul>

                <h2>Plugin Architecture</h2>
                <p>
                    JMock uses Java SPI for plugin discovery. All functions are automatically
                    registered at runtime. You can add custom functions by implementing
                    <code>MockFunction</code> and registering via
                    <code>META-INF/services</code>.
                </p>
            </section>
        </div>
    );
}
