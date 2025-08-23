package cloud.xcan.jmock.baseline;

import cloud.xcan.jmock.AbstractBenchmark;
import org.openjdk.jmh.annotations.Benchmark;
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
 *  Benchmark                            Mode  Cnt           Score            Error  Units
 *  BaselineBenchmark.baselineInt       thrpt    3  1368283731.984 ±  826743084.223  ops/s
 *  BaselineBenchmark.baselineInteger   thrpt    3   508618664.882 ±  623164166.756  ops/s
 *  BaselineBenchmark.baselineObject    thrpt    3   519007644.047 ± 1389810561.338  ops/s
 *  BaselineBenchmark.consume           thrpt    3  1419338175.519 ±  397457431.388  ops/s
 *  BaselineBenchmark.consumeAdd        thrpt    3  1333311440.411 ±   99819861.523  ops/s
 *  BaselineBenchmark.increment         thrpt    3   868518545.371 ±  340279115.839  ops/s
 *  BaselineBenchmark.incrementConsume  thrpt    3   826921161.054 ± 1339745780.075  ops/s
 *  BaselineBenchmark.noop              thrpt    3  2443549271.281 ± 7692398274.835  ops/s
 *  * ============================
 *  # Threads: 5 threads, will synchronize iterations
 *  Benchmark                            Mode  Cnt            Score             Error  Units
 *  BaselineBenchmark.baselineInt       thrpt    3   5794422805.308 ±  4319305239.748  ops/s
 *  BaselineBenchmark.baselineInteger   thrpt    3   2268194699.908 ±  3851890320.394  ops/s
 *  BaselineBenchmark.consumeAdd        thrpt    3   5780161093.899 ±   175814020.154  ops/s
 *  BaselineBenchmark.increment         thrpt    3   3886324836.272 ±    92147732.188  ops/s
 *  BaselineBenchmark.incrementConsume  thrpt    3   3540855840.587 ±  4766983430.275  ops/s
 *  BaselineBenchmark.noop              thrpt    3  10523250198.802 ± 33705433674.641  ops/s
 * </pre>
 */
public class BaselineBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(BaselineBenchmark.class.getSimpleName())
        .threads(1)
        //.threads(5)
        .build();
    new Runner(opt).run();
  }

  int i;

  /**
   * Baseline for a JMH method call with no return value.
   */
  @Benchmark
  public void noop() {
    // Do nothing, this is a baseline
  }

  /**
   * Baseline for a JMH method call returning an {@code int}.
   */
  @Benchmark
  public int baselineInt() {
    // Do nothing, this is a baseline
    return i;
  }

  /**
   * Baseline for a JMH method call returning an {@code Integer}.
   */
  @Benchmark
  public Integer baselineInteger() {
    // Do nothing, this is a baseline
    return i;
  }

  /**
   * Baseline for a JMH method call returning an {@code Integer}.
   */
  @Benchmark
  public Object baselineObject() {
    // Do nothing, this is a baseline
    return i;
  }
//
//  @Benchmark
//  public void increment() {
//    i++;
//  }
//
//  @Benchmark
//  public int incrementConsume() {
//    return i++;
//  }
//
//  @Benchmark
//  public int consume() {
//    return i;
//  }
//
//  @Benchmark
//  public int consumeAdd() {
//    return i + 1;
//  }

}
