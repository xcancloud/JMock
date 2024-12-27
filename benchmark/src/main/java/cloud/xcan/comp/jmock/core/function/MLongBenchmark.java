
package cloud.xcan.comp.jmock.core.function;

import cloud.xcan.comp.jmock.AbstractBenchmark;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.basic.MLong;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * <pre>
 * Benchmark result:
 * # JMH version: 1.25.2
 * # VM version: JDK 1.8.0_281, Java HotSpot(TM) 64-Bit Server VM, 25.281-b09
 * # VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_281.jdk/Contents/Home/jre/bin/java
 * # VM options: -server -Xms256M -Xmx256M
 * # Warmup: 1 iterations, 1 s each
 * # Measurement: 3 iterations, 5 s each
 * # Timeout: 10 min per iteration
 * # Threads: 1 thread, will synchronize iterations
 * # Benchmark mode: Throughput, ops/time
 * Benchmark                                Mode  Cnt         Score          Error  Units
 * MLongBenchmark.mockDefaultAllowNull     thrpt    3  62037439.372 ±  6596783.006  ops/s
 * MLongBenchmark.mockDefaultValue         thrpt    3  72531441.483 ± 34557313.598  ops/s
 * MLongBenchmark.mockRangeAllowNullValue  thrpt    3  60892765.673 ± 14157834.951  ops/s
 * MLongBenchmark.mockRangeValue           thrpt    3  73538367.013 ± 38304669.865  ops/s
 *  </pre>
 */
public class MLongBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MLongBenchmark.class
            .getSimpleName())
        .threads(1)
        //.threads(5)
        .build();
    new Runner(opt).run();
  }

  MLong defaultValue;
  MLong defaultAllowNull;
  MLong rangeValue;
  MLong rangeAllowNullValue;

  @Benchmark
  public Long mockDefaultValue() {
    return defaultValue.mock();
  }

  @Benchmark
  public Long mockDefaultAllowNull() {
    return defaultAllowNull.mock();
  }

  @Benchmark
  public Long mockRangeValue() {
    return rangeValue.mock();
  }

  @Benchmark
  public Long mockRangeAllowNullValue() {
    return rangeAllowNullValue.mock();
  }

  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Long", new String[]{});
    defaultValue = (MLong) parser.parse(token);

    token = new FunctionToken("Long", new String[]{"1:9"});
    defaultAllowNull = (MLong) parser.parse(token);

    token = new FunctionToken("Long", new String[]{"-1L","10000000L"});
    rangeValue = (MLong) parser.parse(token);

    token = new FunctionToken("Long", new String[]{"-10000L","100000L","1:9"});
    rangeAllowNullValue = (MLong) parser.parse(token);
  }

}
