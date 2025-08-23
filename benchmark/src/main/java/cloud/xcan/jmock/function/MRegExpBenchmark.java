package cloud.xcan.jmock.function;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MRegExp;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MRegExpBenchmark extends AbstractBenchmark {

  MRegExp mockNoArgConstructor;
  MRegExp mockLocale;
  MRegExp mockDict;

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
   * Benchmark                               Mode  Cnt        Score         Error  Units
   * MRegExpBenchmark.mockDict              thrpt    3  7768500.841 ± 6130405.814  ops/s
   * MRegExpBenchmark.mockLocale            thrpt    3  4360481.551 ±  856618.883  ops/s
   * MRegExpBenchmark.mockNoArgConstructor  thrpt    3  1008786.621 ±   60757.175  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MRegExpBenchmark.class
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
  public String mockLocale() {
    return mockLocale.mock();
  }

  @Benchmark
  public String mockDict() {
    return mockDict.mock();
  }


  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MRegExp", new String[]{});
    mockNoArgConstructor = (MRegExp) parser.parse(token);

    token = new FunctionToken("MRegExp", new String[]{"[0-9]"});
    mockLocale = (MRegExp) parser.parse(token);

    token = new FunctionToken("MRegExp", new String[]{"[0-9]", "1:1"});
    mockDict = (MRegExp) parser.parse(token);
  }

}
