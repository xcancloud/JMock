
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.id.MSnowId;
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
 *  # JMH version: 1.25.2
 *  # VM version: JDK 11.0.2, OpenJDK 64-Bit Server VM, 11.0.2+9
 *  # VM invoker: /Volumes/softwares/openjdk-11.0.2_osx-x64_bin/jdk-11.0.2.jdk/Contents/Home/bin/java
 *  # VM options: -server -Xms256M -Xmx256M
 *  # Warmup: 1 iterations, 1 s each
 *  # Measurement: 3 iterations, 5 s each
 *  # Timeout: 10 min per iteration
 *  * ============================
 *  # Threads: 1 thread, will synchronize iterations
 *  Benchmark                                Mode  Cnt          Score          Error  Units
 *  MIntegerBenchmark.mockDefaultAllowNull  thrpt    3   88212028.553 ± 23164252.791  ops/s
 *  MIntegerBenchmark.mockDefaultValue      thrpt    3  142461042.618 ± 50003529.560  ops/s
 *  MIntegerBenchmark.mockRangeValue        thrpt    3  137052643.404 ± 56617603.796  ops/s
 *  * ============================
 *  # Threads: 5 threads, will synchronize iterations
 *  Benchmark                                Mode  Cnt          Score           Error  Units
 *  MIntegerBenchmark.mockDefaultAllowNull  thrpt    3  390478720.014 ±  71429866.183  ops/s
 *  MIntegerBenchmark.mockDefaultValue      thrpt    3  563533566.433 ± 259658427.697  ops/s
 *  MIntegerBenchmark.mockRangeValue        thrpt    3  545559438.913 ± 156443692.179  ops/s
 *  </pre>
 */
public class MSnowIdBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MSnowIdBenchmark.class
            .getSimpleName())
        .threads(1)
//        .threads(5)
        .build();
    new Runner(opt).run();
  }

  MSnowId rangeValue;

  @Benchmark
  public Long mockRangeValue() {
    return rangeValue.mock();
  }

  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("SnowId", new String[]{"1", "2"});
    rangeValue = (MSnowId) parser.parse(token);
  }

}
