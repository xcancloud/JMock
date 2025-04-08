
package cloud.xcan.jmock.core.function;

import static cloud.xcan.jmock.core.support.utils.EncryptionUtils.SHA1_VERSION;
import static cloud.xcan.jmock.core.support.utils.EncryptionUtils.SHA224_VERSION;
import static cloud.xcan.jmock.core.support.utils.EncryptionUtils.SHA256_VERSION;
import static cloud.xcan.jmock.core.support.utils.EncryptionUtils.SHA384_VERSION;

import cloud.xcan.jmock.AbstractBenchmark;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.hash.MSha;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class MShaBenchmark extends AbstractBenchmark {


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
   * Benchmark                  Mode  Cnt        Score        Error  Units
   * MShaBenchmark.mockSha1    thrpt    3  2335605.440 ± 363544.681  ops/s
   * MShaBenchmark.mockSha224  thrpt    3  1405159.505 ±  57791.019  ops/s
   * MShaBenchmark.mockSha256  thrpt    3  1795709.630 ± 143880.699  ops/s
   * MShaBenchmark.mockSha384  thrpt    3  1326824.085 ± 100054.040  ops/s
   * MShaBenchmark.mockSha512  thrpt    3  1317257.887 ±  63613.819  ops/s
   *  </pre>
   */
  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MShaBenchmark.class
            .getSimpleName())
        .threads(1)
        //.threads(5)
        .build();
    new Runner(opt).run();
  }


  MSha mockSha1;
  MSha mockSha224;
  MSha mockSha256;
  MSha mockSha384;
  MSha mockSha512;

  @Benchmark
  public String mockSha1() {
    return mockSha1.mock();
  }

  @Benchmark
  public String mockSha224() {
    return mockSha224.mock();
  }

  @Benchmark
  public String mockSha256() {
    return mockSha256.mock();
  }

  @Benchmark
  public String mockSha384() {
    return mockSha384.mock();
  }

  @Benchmark
  public String mockSha512() {
    return mockSha512.mock();
  }


  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("MSha", new String[]{});
    mockSha512 = (MSha) parser.parse(token);

    token = new FunctionToken("MSha", new String[]{SHA224_VERSION});
    mockSha224 = (MSha) parser.parse(token);

    token = new FunctionToken("MSha", new String[]{SHA256_VERSION});
    mockSha256 = (MSha) parser.parse(token);

    token = new FunctionToken("MSha", new String[]{SHA384_VERSION});
    mockSha384 = (MSha) parser.parse(token);

    token = new FunctionToken("MSha", new String[]{SHA1_VERSION});
    mockSha1 = (MSha) parser.parse(token);
  }

}
