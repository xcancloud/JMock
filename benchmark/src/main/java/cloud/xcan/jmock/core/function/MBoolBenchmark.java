
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.basic.MBool;
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
 * # JMH version: 1.25.2
 * # VM version: JDK 1.8.0_281, Java HotSpot(TM) 64-Bit Server VM, 25.281-b09
 * # VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_281.jdk/Contents/Home/jre/bin/java
 * # VM options: -server -Xms256M -Xmx256M
 * # Warmup: 1 iterations, 1 s each
 * # Measurement: 3 iterations, 5 s each
 * # Timeout: 10 min per iteration
 * # Threads: 1 thread, will synchronize iterations
 * # Benchmark mode: Throughput, ops/time
 Benchmark                                Mode  Cnt         Score           Error  Units
 MBoolBenchmark.mockDefaultAllowNull     thrpt    3  82598083.042 ± 103457044.933  ops/s
 MBoolBenchmark.mockDefaultValue         thrpt    3  69958369.430 ±  40180203.771  ops/s
 MBoolBenchmark.mockRangeAllowNullValue  thrpt    3  62349507.095 ±  81342876.482  ops/s
 MBoolBenchmark.mockRangeValue           thrpt    3  67601902.437 ±  28644804.552  ops/s
 *  </pre>
 */
public class MBoolBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MBoolBenchmark.class
            .getSimpleName())
        .threads(1)
        //.threads(5)
        .build();
    new Runner(opt).run();
  }

  MBool defaultValue;
  MBool defaultAllowNull;
  MBool rangeValue;
  MBool rangeAllowNullValue;

  @Benchmark
  public String mockDefaultValue() {
    return defaultValue.mock();
  }

  @Benchmark
  public String mockDefaultAllowNull() {
    return defaultAllowNull.mock();
  }

  @Benchmark
  public String mockRangeValue() {
    return rangeValue.mock();
  }

  @Benchmark
  public String mockRangeAllowNullValue() {
    return rangeAllowNullValue.mock();
  }

  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MBool", new String[]{});
    defaultValue = (MBool) parser.parse(token);

    token = new FunctionToken("MBool", new String[]{"5:1"});
    defaultAllowNull = (MBool) parser.parse(token);

    token = new FunctionToken("MBool", new String[]{"5:1","1:9"});
    rangeValue = (MBool) parser.parse(token);

    token = new FunctionToken("MBool", new String[]{"5:1","1:9","0|1"});
    rangeAllowNullValue = (MBool) parser.parse(token);
  }

}
