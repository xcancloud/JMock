
package cloud.xcan.comp.jmock.core.function;

import cloud.xcan.comp.jmock.AbstractBenchmark;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.basic.MEnum;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MEnumBenchmark extends AbstractBenchmark {

  MEnum mockDict;
  MEnum mockDictAndWeight;
  MEnum mockDictAndWeightAndNullWeight;

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
   * Benchmark                                       Mode  Cnt         Score          Error  Units
   * MEnumBenchmark.mockDict                        thrpt    3  80600901.635 ± 34401046.311  ops/s
   * MEnumBenchmark.mockDictAndWeight               thrpt    3  46903336.191 ±  6066776.195  ops/s
   * MEnumBenchmark.mockDictAndWeightAndNullWeight  thrpt    3  45713922.702 ± 25498147.827  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MEnumBenchmark.class
            .getSimpleName())
        .threads(1)
        //.threads(5)
        .build();
    new Runner(opt).run();
  }


  @Benchmark
  public String mockDict() {
    return mockDict.mock();
  }

  @Benchmark
  public String mockDictAndWeight() {
    return mockDictAndWeight.mock();
  }

  @Benchmark
  public String mockDictAndWeightAndNullWeight() {
    return mockDictAndWeightAndNullWeight.mock();
  }


  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MEnum", new String[]{"上官|慕容"});
    mockDict = (MEnum) parser.parse(token);

    token = new FunctionToken("MEnum", new String[]{"上官|慕容|司马", "1:2:3"});
    mockDictAndWeight = (MEnum) parser.parse(token);

    token = new FunctionToken("MEnum", new String[]{"上官|慕容|司马", "1:2:3", "1:9"});
    mockDictAndWeightAndNullWeight = (MEnum) parser.parse(token);
  }

}
