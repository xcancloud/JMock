
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.network.MAppName;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MAppNameBenchmark extends AbstractBenchmark {

  MAppName mockNoArgConstructor;
  MAppName enMock;
  MAppName mockDict;

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
   * Benchmark                                Mode  Cnt          Score           Error  Units
   * MAppNameBenchmark.enMock                thrpt    3  136215030.396 ± 116872170.439  ops/s
   * MAppNameBenchmark.mockDict              thrpt    3  182237763.284 ± 429320816.691  ops/s
   * MAppNameBenchmark.mockNoArgConstructor  thrpt    3  158196306.684 ± 134161553.931  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MAppNameBenchmark.class
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
  public String enMock() {
    return enMock.mock();
  }

  @Benchmark
  public String mockDict() {
    return mockDict.mock();
  }


  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MAppName", new String[]{});
    mockNoArgConstructor = (MAppName) parser.parse(token);

    token = new FunctionToken("MAppName", new String[]{"en"});
    enMock = (MAppName) parser.parse(token);

    token = new FunctionToken("MAppName", new String[]{"上官|慕容"});
    mockDict = (MAppName) parser.parse(token);
  }

}
