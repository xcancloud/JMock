
package cloud.xcan.jmock.core.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MGender;
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
 Benchmark                             Mode  Cnt           Score            Error  Units
 MGenderBenchmark.defaultConstructor  thrpt    3  1050874109.193 ± 3852456300.429  ops/s
 MGenderBenchmark.dictConstructor     thrpt    3  1003171914.474 ± 3352941842.504  ops/s
 MGenderBenchmark.enConstructor       thrpt    3   980796672.832 ± 2940492236.244  ops/s
 *  </pre>
 */
public class MGenderBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MGenderBenchmark.class
            .getSimpleName())
//        .threads(1)
        .threads(5)
        .build();
    new Runner(opt).run();
  }

  MGender defaultConstructor;
  MGender enConstructor;
  MGender dictConstructor;

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

    FunctionToken token = new FunctionToken("MGender",
        new String[]{});
    this.defaultConstructor = (MGender) parser.parse(token);

    token = new FunctionToken("MGender",
        new String[]{"en"});
    this.enConstructor = (MGender) parser.parse(token);

    token = new FunctionToken("MGender",
        new String[]{"F|M"});
    this.dictConstructor = (MGender) parser.parse(token);

  }

}
