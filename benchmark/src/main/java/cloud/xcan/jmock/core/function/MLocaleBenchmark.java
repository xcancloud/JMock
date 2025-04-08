
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.locale.MLocale;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * <pre>
 * Benchmark result:
 *  * ============================*
 * # JMH version: 1.25.2
 * # VM version: JDK 11.0.13, OpenJDK 64-Bit Server VM, 11.0.13+8-LTS
 * # VM invoker: /Users/yajing/Library/Java/JavaVirtualMachines/corretto-11.0.13/Contents/Home/bin/java
 * # VM options: -server -Xms256M -Xmx256M
 * # Warmup: 1 iterations, 1 s each
 * # Measurement: 3 iterations, 5 s each
 * # Timeout: 10 min per iteration
 *  * ============================
 *  # Threads: 1 thread, will synchronize iterations
 *  Benchmark                                 Mode  Cnt         Score         Error  Units
 * MLocaleBenchmark.mockDefault  thrpt    3  47133349.916 ± 28656823.773  ops/s
 * MLocaleBenchmark.mockJoiner   thrpt    3  43267471.100 ± 65263528.810  ops/s
 *  * ============================
 *  # Threads: 5 threads, will synchronize iterations
 *  Benchmark                                 Mode  Cnt         Score          Error  Units
 * MLocaleBenchmark.mockDefault  thrpt    3   67652680.242 ±  55855212.739  ops/s
 * MLocaleBenchmark.mockJoiner   thrpt    3  103008610.073 ± 101837140.260  ops/s
 *  </pre>
 */
public class MLocaleBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MLocaleBenchmark.class
            .getSimpleName())
        //.threads(1)
        .threads(5)
        .build();
    new Runner(opt).run();
  }

  MLocale defaultValue;

  MLocale mockJoinerValue;

  @Benchmark
  public String mockDefault() {
    return defaultValue.mock();
  }

  @Benchmark
  public String mockJoiner() {
    return mockJoinerValue.mock();
  }


  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Locale", new String[]{});
    defaultValue = (MLocale) parser.parse(token);

    token = new FunctionToken("Locale", new String[]{"-"});
    mockJoinerValue = (MLocale) parser.parse(token);
  }

}
