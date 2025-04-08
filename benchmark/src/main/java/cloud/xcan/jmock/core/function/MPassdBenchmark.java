
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MPassd;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MPassdBenchmark extends AbstractBenchmark {

  MPassd mockNoArgConstructor;
  MPassd mockLocale;
  MPassd mockCase3;
  MPassd mockCase4;
  MPassd mockDict;

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
   Benchmark                              Mode  Cnt         Score         Error  Units
   MPassdBenchmark.mockCase3             thrpt    3  10957351.546 ± 1680572.019  ops/s
   MPassdBenchmark.mockCase4             thrpt    3  10973472.655 ± 1733429.419  ops/s
   MPassdBenchmark.mockDict              thrpt    3   1682049.777 ±  210059.560  ops/s
   MPassdBenchmark.mockLocale            thrpt    3   1697131.692 ±   59364.679  ops/s
   MPassdBenchmark.mockNoArgConstructor  thrpt    3  10946209.846 ± 2553043.624  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MPassdBenchmark.class
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

  @Benchmark
  public String mockCase3() {
    return mockCase3.mock();
  }
  @Benchmark
  public String mockCase4() {
    return mockCase4.mock();
  }

  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MPassd", new String[]{});
    mockNoArgConstructor = (MPassd) parser.parse(token);

    token = new FunctionToken("MPassd", new String[]{"1","200"});
    mockLocale = (MPassd) parser.parse(token);

    token = new FunctionToken("MPassd", new String[]{"true","true","true","true"});
    mockCase3 = (MPassd) parser.parse(token);

    token = new FunctionToken("MPassd", new String[]{"true","false","false","true"});
    mockCase4 = (MPassd) parser.parse(token);

    token = new FunctionToken("MPassd", new String[]{"1","200","true","true","true","true"});
    mockDict = (MPassd) parser.parse(token);
  }

}
