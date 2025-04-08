
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.locale.MTimeZone;
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
 Benchmark                               Mode  Cnt          Score            Error  Units
 MTimeZoneBenchmark.defaultConstructor  thrpt    3  717407650.106 ± 1651561581.164  ops/s
 MTimeZoneBenchmark.dictConstructor     thrpt    3  789940627.238 ± 1569153615.454  ops/s
 *  </pre>
 */
public class MTimeZoneBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MTimeZoneBenchmark.class
            .getSimpleName())
//        .threads(1)
        .threads(5)
        .build();
    new Runner(opt).run();
  }

  MTimeZone defaultConstructor;

  MTimeZone dictConstructor;

  @Benchmark
  public String defaultConstructor() {
    return defaultConstructor.mock();
  }

  @Benchmark
  public String dictConstructor() {
    return dictConstructor.mock();
  }

  @Setup
  public void setUp() throws Exception {

    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MTimeZone",
        new String[]{});
    this.defaultConstructor = (MTimeZone) parser.parse(token);

    token = new FunctionToken("MTimeZone",
        new String[]{"Pacific/Guadalcanal|Asia/Ho_Chi_Minh"});
    this.dictConstructor = (MTimeZone) parser.parse(token);

  }

}
