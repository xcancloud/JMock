
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.geography.MLongitude;
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
 *  # VM version: JDK 1.8.0_302, OpenJDK 64-Bit Server VM, 25.302-b08
 *  # VM invoker: D:\openjdk-1.8.0.302-1\jre\bin\java.exe
 *  # VM options: -server -Xms256M -Xmx256M
 *  # Warmup: 1 iterations, 1 s each
 *  # Measurement: 3 iterations, 5 s each
 *  # Timeout: 10 min per iteration
 *  * ============================
 *  # Threads: 1 thread, will synchronize iterations
 *  # Benchmark                      Mode  Cnt          Score           Error  Units
 *  # MAgeBenchmark.defaultConstructor  thrpt    3  132864547.697 ± 322477927.625  ops/s
 *  * ============================
 *  # Threads: 5 threads, will synchronize iterations
 *  # Benchmark                                Mode  Cnt        Score         Error  Units
 *  # MLongitudeBenchmark.defaultConstructor  thrpt    3  1038081.530 ± 1735743.820  ops/s
 *  </pre>
 */
//TODO 不合格
public class MLongitudeBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MLongitudeBenchmark.class
            .getSimpleName())
        .threads(1)
//        .threads(5)
        .build();
    new Runner(opt).run();
  }

  MLongitude defaultConstructor;

  @Benchmark
  public String defaultConstructor() {
    return defaultConstructor.mock();
  }

  @Setup
  public void setUp() throws Exception {

    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Longitude",
        new String[]{});
    this.defaultConstructor = (MLongitude) parser.parse(token);

  }

}
