
package cloud.xcan.comp.jmock.core.function;

import cloud.xcan.comp.jmock.AbstractBenchmark;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.geography.MAddress;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MAddressBenchmark extends AbstractBenchmark {

  MAddress mockNoArgConstructor;
  MAddress enMock;
  MAddress mockDict;

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
   Benchmark                                Mode  Cnt          Score           Error  Units
   MAddressBenchmark.enMock                thrpt    3  143427508.548 ±  95852110.199  ops/s
   MAddressBenchmark.mockDict              thrpt    3  180802008.197 ± 286667885.819  ops/s
   MAddressBenchmark.mockNoArgConstructor  thrpt    3  137259985.842 ±  46792115.680  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MAddressBenchmark.class
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
  public String enMock() {
    return enMock.mock();
  }

  @Benchmark
  public String mockDict() {
    return mockDict.mock();
  }


  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MAddress", new String[]{});
    mockNoArgConstructor = (MAddress) parser.parse(token);

    token = new FunctionToken("MAddress", new String[]{"en"});
    enMock = (MAddress) parser.parse(token);

    token = new FunctionToken("MAddress", new String[]{"上官|慕容"});
    mockDict = (MAddress) parser.parse(token);
  }

}
