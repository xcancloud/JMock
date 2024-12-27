
package cloud.xcan.comp.jmock.core.function;

import cloud.xcan.comp.jmock.AbstractBenchmark;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.basic.MArray;
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
 * Benchmark                                     Mode  Cnt          Score          Error  Units
 * MDoubleBenchmark.mockDefaultAllowNull        thrpt    3   56925968.434 ± 14033432.686  ops/s
 * MDoubleBenchmark.mockDefaultValue            thrpt    3   78359731.380 ± 46551674.301  ops/s
 * MDoubleBenchmark.mockFixScaleRangeAllowNull  thrpt    3   36192982.842 ±  1764994.407  ops/s
 * MDoubleBenchmark.mockRangeValue              thrpt    3  108918476.836 ± 31579241.367  ops/s
 *  * ============================
 *  # Threads: 5 threads, will synchronize iterations
 * MDoubleBenchmark.mockDefaultAllowNull        thrpt    3  223076899.065 ± 189469253.080  ops/s
 * MDoubleBenchmark.mockDefaultValue            thrpt    3  267405603.779 ± 164874488.820  ops/s
 * MDoubleBenchmark.mockFixScaleRangeAllowNull  thrpt    3  132493959.802 ±  24017334.560  ops/s
 * MDoubleBenchmark.mockRangeValue              thrpt    3  333074386.787 ± 350068622.732  ops/s
 *  </pre>
 */
public class MArrayBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MArrayBenchmark.class
            .getSimpleName())
        .threads(1)
        //.threads(5)
        .build();
    new Runner(opt).run();
  }

  MArray defaultSizeValue;
  MArray defaultMockFuncValue;
  MArray sizeAndGenExpValue;


  @Benchmark
  public Object[] mockDefaultSizeValue() {
    return defaultSizeValue.mock();
  }

  @Benchmark
  public Object[] mockDefaultMockFuncValue() {
    return defaultMockFuncValue.mock();
  }

  @Benchmark
  public Object[] mockSizeAndGenExpValue() {
    return sizeAndGenExpValue.mock();
  }

  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MArray", new String[]{"@Integer()"});
    defaultSizeValue = (MArray) parser.parse(token);

    token = new FunctionToken("MArray", new String[]{});
    defaultMockFuncValue = (MArray) parser.parse(token);

    token = new FunctionToken("MArray", new String[]{"1000000", "@Integer()"});
    sizeAndGenExpValue = (MArray) parser.parse(token);
  }

}