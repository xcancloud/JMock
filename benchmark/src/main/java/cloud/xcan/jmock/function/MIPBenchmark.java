package cloud.xcan.jmock.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MIPv4;
import cloud.xcan.jmock.plugin.MIPv6;
import cloud.xcan.jmock.plugin.MMac;
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
 * Benchmark                         Mode  Cnt         Score          Error  Units
 * MIPBenchmark.defaultConstructor  thrpt    3  27031766.267 ± 64061357.135  ops/s
 * MIPBenchmark.dictConstructor     thrpt    3  24716645.596 ± 12415287.499  ops/s
 * MIPBenchmark.enConstructor       thrpt    3  14597791.447 ± 18856213.747  ops/s
 *  </pre>
 */
public class MIPBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MIPBenchmark.class
            .getSimpleName())
//        .threads(1)
        .threads(5)
        .build();
    new Runner(opt).run();
  }

  MIPv4 defaultConstructor;
  MIPv6 enConstructor;
  MMac dictConstructor;

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

    FunctionToken token = new FunctionToken("MIPv4",
        new String[]{});
    this.defaultConstructor = (MIPv4) parser.parse(token);

    FunctionToken token1 = new FunctionToken("MIPv6",
        new String[]{});
    this.enConstructor = (MIPv6) parser.parse(token1);

    FunctionToken token2 = new FunctionToken("MMac",
        new String[]{});
    this.dictConstructor = (MMac) parser.parse(token2);

  }

}
