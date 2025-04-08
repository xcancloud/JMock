
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.geography.MCity;
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
 Benchmark                           Mode  Cnt          Score           Error  Units
 MCityBenchmark.defaultConstructor  thrpt    3   82724224.191 ±  37290216.510  ops/s
 MCityBenchmark.dictConstructor     thrpt    3  208378917.378 ± 210418401.420  ops/s
 MCityBenchmark.enConstructor       thrpt    3   80203869.086 ±  94778132.180  ops/s
 *  </pre>
 */
public class MCityBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MCityBenchmark.class
            .getSimpleName())
        .threads(1)
//        .threads(5)
        .build();
    new Runner(opt).run();
  }

  MCity defaultConstructor;
  MCity enConstructor;
  MCity dictConstructor;

  @Benchmark
  public String defaultConstructor() {
    return defaultConstructor.mock();
  }

  @Benchmark
  public String enConstructor() {
    return enConstructor.mock();
  }

  @Benchmark
  public String dictConstructor() {
    return dictConstructor.mock();
  }

  @Setup
  public void setUp() throws Exception {

    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("City",
        new String[]{});
    this.defaultConstructor = (MCity) parser.parse(token);

     token = new FunctionToken("City",
        new String[]{"en"});
    this.enConstructor = (MCity) parser.parse(token);

     token = new FunctionToken("City",
        new String[]{"en|zn"});
    this.dictConstructor = (MCity) parser.parse(token);

  }

}
