
package cloud.xcan.comp.jmock.core.function;

import cloud.xcan.comp.jmock.AbstractBenchmark;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.network.MAppVersion;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MAppVersionBenchmark extends AbstractBenchmark {

  MAppVersion mockNoArgConstructor;
  MAppVersion preMock;
  MAppVersion mockBuild;
  MAppVersion mockRelease;

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
   MAppVersionBenchmark.enMock                thrpt    3  6891333.002 ± 7856828.160  ops/s
   MAppVersionBenchmark.mockDict              thrpt    3  5577949.033 ± 2593594.683  ops/s
   MAppVersionBenchmark.mockNoArgConstructor  thrpt    3  7001965.557 ±  590237.454  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MAppVersionBenchmark.class
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
    return preMock.mock();
  }

  @Benchmark
  public String mockDict() {
    return mockBuild.mock();
  }


  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MAppVersion", new String[]{});
    mockNoArgConstructor = (MAppVersion) parser.parse(token);

    token = new FunctionToken("MAppVersion", new String[]{"v|e"});
    preMock = (MAppVersion) parser.parse(token);

    token = new FunctionToken("MAppVersion", new String[]{"v|e","g|s"});
    mockRelease = (MAppVersion) parser.parse(token);


    token = new FunctionToken("MAppVersion", new String[]{"v|e","g|s","a|q"});
    mockBuild = (MAppVersion) parser.parse(token);
  }

}
