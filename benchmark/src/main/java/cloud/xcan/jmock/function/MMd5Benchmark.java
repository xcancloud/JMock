package cloud.xcan.jmock.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.plugin.MMd5;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MMd5Benchmark extends AbstractBenchmark {

  MMd5 mockFixLength16;
  MMd5 mockFixLength32;

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
   *  * ============================
   *  # Threads: 1 thread, will synchronize iterations
   * Benchmark                       Mode  Cnt        Score         Error  Units
   * MMd5Benchmark.mockFixLength16  thrpt    3  2658178.009 ± 1390776.830  ops/s
   * MMd5Benchmark.mockFixLength32  thrpt    3  2799124.693 ±  255711.983  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MMd5Benchmark.class
            .getSimpleName())
        .threads(1)
        //.threads(5)
        .build();
    new Runner(opt).run();
  }

  @Benchmark
  public String mockFixLength16() {
    return mockFixLength16.mock();
  }

  @Benchmark
  public String mockFixLength32() {
    return mockFixLength32.mock();
  }


  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MMd5", new String[]{"16"});
    mockFixLength16 = (MMd5) parser.parse(token);

    token = new FunctionToken("MMd5", new String[]{"32"});
    mockFixLength32 = (MMd5) parser.parse(token);
  }

}
