
package cloud.xcan.comp.jmock.core.function;

import cloud.xcan.comp.jmock.AbstractBenchmark;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.article.MParagraph;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MParagraphBenchmark extends AbstractBenchmark {

  MParagraph mockNoArgConstructor;
  MParagraph mockLocale;
  MParagraph mockDict;

  /**
   * <pre>
   * Benchmark result:
   * # JMH version: 1.25.2
   * # VM version: JDK 11.0.13, OpenJDK 64-Bit Server VM, 11.0.13+8-LTS
   * # VM invoker: /Users/admin/Library/Java/JavaVirtualMachines/corretto-11.0.13/Contents/Home/bin/java
   * # VM options: -server -Xms256M -Xmx256M
   * # Warmup: 1 iterations, 1 s each
   * # Measurement: 3 iterations, 5 s each
   * # Timeout: 10 min per iteration
   * # Threads: 1 thread, will synchronize iterations
   *  * ============================
   *  # Threads: 1 thread, will synchronize iterations
   Benchmark                                  Mode  Cnt          Score           Error  Units
   MParagraphBenchmark.mockDict              thrpt    3  178314902.054 ±  77656833.702  ops/s
   MParagraphBenchmark.mockLocale            thrpt    3  146094563.056 ± 169042517.290  ops/s
   MParagraphBenchmark.mockNoArgConstructor  thrpt    3  140424263.434 ±  29742984.568  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MParagraphBenchmark.class
            .getSimpleName())
        .threads(1)
        //.threads(5)
        .build();
    new Runner(opt).run();
  }

  @Benchmark
  public String mockNoArgConstructor() {
    return mockNoArgConstructor.mock();
  }

  @Benchmark
  public String mockLocale() {
    return mockLocale.mock();
  }

  @Benchmark
  public String mockDict() {
    return mockDict.mock();
  }


  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MParagraph", new String[]{});
    mockNoArgConstructor = (MParagraph) parser.parse(token);

    token = new FunctionToken("MParagraph", new String[]{"en"});
    mockLocale = (MParagraph) parser.parse(token);

    token = new FunctionToken("MParagraph", new String[]{"上官|慕容"});
    mockDict = (MParagraph) parser.parse(token);
  }

}
