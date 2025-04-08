
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MFirstname;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MFirstnameBenchmark extends AbstractBenchmark {

  MFirstname mockNoArgConstructor;
  MFirstname mockLocale;
  MFirstname mockDict;

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
   MFirstnameBenchmark.mockDict              thrpt    3  176055997.094 ±   9600746.923  ops/s
   MFirstnameBenchmark.mockLocale            thrpt    3  147408916.901 ± 245827438.500  ops/s
   MFirstnameBenchmark.mockNoArgConstructor  thrpt    3  147549445.404 ± 232376819.736  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MFirstnameBenchmark.class
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

    FunctionToken token = new FunctionToken("MFirstname", new String[]{});
    mockNoArgConstructor = (MFirstname) parser.parse(token);

    token = new FunctionToken("MFirstname", new String[]{"zh_Cn"});
    mockLocale = (MFirstname) parser.parse(token);

    token = new FunctionToken("MFirstname", new String[]{"上官|慕容"});
    mockDict = (MFirstname) parser.parse(token);
  }

}
