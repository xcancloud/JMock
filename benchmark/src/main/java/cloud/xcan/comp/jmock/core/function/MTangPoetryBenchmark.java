
package cloud.xcan.comp.jmock.core.function;

import cloud.xcan.comp.jmock.AbstractBenchmark;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.article.MTangPoetry;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MTangPoetryBenchmark extends AbstractBenchmark {

  MTangPoetry mockNoArgConstructor;
  MTangPoetry mockLocale;
  MTangPoetry mockDict;

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
   Benchmark                                   Mode  Cnt          Score          Error  Units
   MTangPoetryBenchmark.mockDict              thrpt    3  171318675.823 ± 90363903.782  ops/s
   MTangPoetryBenchmark.mockLocale            thrpt    3  142266620.979 ± 29872438.816  ops/s
   MTangPoetryBenchmark.mockNoArgConstructor  thrpt    3  142926133.324 ± 45526978.567  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MTangPoetryBenchmark.class
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

    FunctionToken token = new FunctionToken("MTangPoetry", new String[]{});
    mockNoArgConstructor = (MTangPoetry) parser.parse(token);

    token = new FunctionToken("MTangPoetry", new String[]{"en"});
    mockLocale = (MTangPoetry) parser.parse(token);

    token = new FunctionToken("MTangPoetry", new String[]{"上官|慕容"});
    mockDict = (MTangPoetry) parser.parse(token);
  }

}
