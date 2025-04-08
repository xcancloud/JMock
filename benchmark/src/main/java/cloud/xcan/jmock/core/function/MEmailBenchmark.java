
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MEmail;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MEmailBenchmark extends AbstractBenchmark {

  MEmail mockNoArgConstructor;
  MEmail enMock;
  MEmail mockDict;

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
   MEmailBenchmark.enMock                thrpt    3  13725163.197 ± 7539218.878  ops/s
   MEmailBenchmark.mockDict              thrpt    3  14013018.424 ± 3574247.664  ops/s
   MEmailBenchmark.mockNoArgConstructor  thrpt    3   8235006.063 ±  944389.008  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MEmailBenchmark.class
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

    FunctionToken token = new FunctionToken("MEmail", new String[]{});
    mockNoArgConstructor = (MEmail) parser.parse(token);

    token = new FunctionToken("MEmail", new String[]{"1","10"});
    enMock = (MEmail) parser.parse(token);

    token = new FunctionToken("MEmail", new String[]{"1","10","qq.com|163.com"});
    mockDict = (MEmail) parser.parse(token);
  }

}
