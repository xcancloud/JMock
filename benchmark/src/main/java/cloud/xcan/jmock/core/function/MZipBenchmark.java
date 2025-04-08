
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.geography.MZip;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MZipBenchmark extends AbstractBenchmark {

  MZip mockNoArgConstructor;
  MZip mockLocale;
  MZip mockDict;

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
   Benchmark                            Mode  Cnt          Score           Error  Units
   MZipBenchmark.mockDict              thrpt    3  170659320.598 ± 128152684.989  ops/s
   MZipBenchmark.mockLocale            thrpt    3  146938158.847 ±  37285348.675  ops/s
   MZipBenchmark.mockNoArgConstructor  thrpt    3  146103993.704 ± 160715053.172  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MZipBenchmark.class
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

    FunctionToken token = new FunctionToken("MZip", new String[]{});
    mockNoArgConstructor = (MZip) parser.parse(token);

    token = new FunctionToken("MZip", new String[]{"en"});
    mockLocale = (MZip) parser.parse(token);

    token = new FunctionToken("MZip", new String[]{"标题1|标题2"});
    mockDict = (MZip) parser.parse(token);
  }

}
