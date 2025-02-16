
package cloud.xcan.comp.jmock.core.function;

import cloud.xcan.comp.jmock.AbstractBenchmark;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.date.MLocaleTime;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author bao.zhang
 * @author xiaolong.liu
*/
public class MLocaleTimeBenchmark extends AbstractBenchmark {

  MLocaleTime defaultValue;
  MLocaleTime mockDictValue;

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
   Benchmark                            Mode  Cnt        Score         Error  Units
   MLocaleTimeBenchmark.mockDefault    thrpt    3  4720910.605 ± 3198762.997  ops/s
   MLocaleTimeBenchmark.mockDictValue  thrpt    3  2513577.120 ± 2139241.338  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MLocaleTimeBenchmark.class
            .getSimpleName())
        .threads(1)
        //.threads(5)
        .build();
    new Runner(opt).run();
  }

  @Benchmark
  public String mockDefault() {
    return defaultValue.mock();
  }

  @Benchmark
  public String mockDictValue() {
    return mockDictValue.mock();
  }


  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MLocaleTime", new String[]{});
    defaultValue = (MLocaleTime) parser.parse(token);

    token = new FunctionToken("MLocaleTime", new String[]{"yyyy yy y MM M dd d"});
    mockDictValue = (MLocaleTime) parser.parse(token);
  }

}

