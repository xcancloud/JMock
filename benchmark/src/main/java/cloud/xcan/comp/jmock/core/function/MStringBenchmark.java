
package cloud.xcan.comp.jmock.core.function;

import cloud.xcan.comp.jmock.AbstractBenchmark;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.basic.MString;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
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
 *  # VM version: JDK 11.0.2, OpenJDK 64-Bit Server VM, 11.0.2+9
 *  # VM invoker: /Volumes/softwares/openjdk-11.0.2_osx-x64_bin/jdk-11.0.2.jdk/Contents/Home/bin/java
 *  # VM options: -server -Xms256M -Xmx256M
 *  # Warmup: 1 iterations, 1 s each
 *  # Measurement: 3 iterations, 5 s each
 *  # Timeout: 10 min per iteration
 *  * ============================
 *  # Threads: 1 thread, will synchronize iterations
 *  Benchmark                                 Mode  Cnt         Score         Error  Units
 *  MStringBenchmark.mockFixLength100        thrpt    3   1859792.946 ±  658890.487  ops/s
 *  MStringBenchmark.mockFixLength20         thrpt    3   8192528.992 ± 1973059.197  ops/s
 *  MStringBenchmark.mockFixLength6          thrpt    3  24048224.158 ± 3273930.695  ops/s
 *  MStringBenchmark.mockRangeAllowNull      thrpt    3  24281424.163 ± 1449326.833  ops/s
 *  MStringBenchmark.mockRangeLength10To100  thrpt    3   3225503.290 ±  101966.597  ops/s
 *  MStringBenchmark.mockRangeLength10To20   thrpt    3  10345704.150 ± 2392265.771  ops/s
 *  * ============================
 *  # Threads: 5 threads, will synchronize iterations
 *  Benchmark                                 Mode  Cnt         Score          Error  Units
 *  MStringBenchmark.mockFixLength100        thrpt    3   8149592.985 ±  1672679.170  ops/s
 *  MStringBenchmark.mockFixLength20         thrpt    3  35200745.269 ± 14419259.787  ops/s
 *  MStringBenchmark.mockFixLength6          thrpt    3  98450555.631 ± 14947744.997  ops/s
 *  MStringBenchmark.mockRangeAllowNull      thrpt    3  97036811.237 ± 21649245.805  ops/s
 *  MStringBenchmark.mockRangeLength10To100  thrpt    3  13062592.713 ±  4984396.232  ops/s
 *  MStringBenchmark.mockRangeLength10To20   thrpt    3  41165045.857 ± 22215775.103  ops/s
 *  </pre>
 */
public class MStringBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(MStringBenchmark.class
            .getSimpleName())
        //.threads(1)
        .threads(5)
        .build();
    new Runner(opt).run();
  }

  MString mockFixLength6;
  MString mockFixLength20;
  MString mockFixLength100;
  MString mockRangeLength10To20;
  MString mockRangeLength10To100;
  MString mockRangeAllowNull;

  @Benchmark
  public String mockFixLength6() {
    return mockFixLength6.mock();
  }

  @Benchmark
  public String mockFixLength20() {
    return mockFixLength20.mock();
  }

  @Benchmark
  public String mockFixLength100() {
    return mockFixLength100.mock();
  }

  @Benchmark
  public String mockRangeLength10To20() {
    return mockRangeLength10To20.mock();
  }

  @Benchmark
  public String mockRangeLength10To100() {
    return mockRangeLength10To100.mock();
  }

  @Benchmark
  public String mockRangeAllowNull() {
    return mockRangeAllowNull.mock();
  }

  @Setup
  public void setUp() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("String", new String[]{});
    mockFixLength6 = (MString) parser.parse(token);

    token = new FunctionToken("String", new String[]{"20"});
    mockFixLength20 = (MString) parser.parse(token);

    token = new FunctionToken("String", new String[]{"100"});
    mockFixLength100 = (MString) parser.parse(token);

    token = new FunctionToken("String", new String[]{"10", "20"});
    mockRangeLength10To20 = (MString) parser.parse(token);

    token = new FunctionToken("String", new String[]{"10", "100"});
    mockRangeLength10To100 = (MString) parser.parse(token);

    token = new FunctionToken("String", new String[]{"true", "1:9"});
    mockRangeAllowNull = (MString) parser.parse(token);
  }

}
