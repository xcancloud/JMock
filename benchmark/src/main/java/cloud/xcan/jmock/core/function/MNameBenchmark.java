
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MName;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MNameBenchmark extends AbstractBenchmark {

  MName mockNoArgConstructor;
  MName mockLocale;
  MName mockDict;

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
   Benchmark                             Mode  Cnt          Score           Error  Units
   MNameBenchmark.mockDict              thrpt    3  226589028.904 ± 686389465.660  ops/s
   MNameBenchmark.mockLocale            thrpt    3   16933882.996 ±  17398315.463  ops/s
   MNameBenchmark.mockNoArgConstructor  thrpt    3   21606300.732 ±  39130680.207  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MNameBenchmark.class
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

    FunctionToken token = new FunctionToken("MName", new String[]{});
    mockNoArgConstructor = (MName) parser.parse(token);

    token = new FunctionToken("MName", new String[]{"en"});
    mockLocale = (MName) parser.parse(token);

    token = new FunctionToken("MName", new String[]{"上官|慕容"});
    mockDict = (MName) parser.parse(token);
  }

}
