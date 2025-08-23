package cloud.xcan.jmock.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MLocaleDateTime;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * @author Bao Zhang
 * @author XiaoLong Liu
 */
public class MLocaleDateTimeBenchmark extends AbstractBenchmark {

  MLocaleDateTime defaultValue;
  MLocaleDateTime mockDictValue;

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
   * Benchmark                                Mode  Cnt        Score         Error  Units
   * MLocaleDateTimeBenchmark.mockDefault    thrpt    3  5428863.393 ± 5094888.652  ops/s
   * MLocaleDateTimeBenchmark.mockDictValue  thrpt    3  2607121.383 ±  698554.892  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MLocaleDateTimeBenchmark.class
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

    FunctionToken token = new FunctionToken("MLocaleDateTime", new String[]{});
    defaultValue = (MLocaleDateTime) parser.parse(token);

    token = new FunctionToken("MLocaleDateTime", new String[]{"yyyy yy y MM M dd d"});
    mockDictValue = (MLocaleDateTime) parser.parse(token);
  }

}

