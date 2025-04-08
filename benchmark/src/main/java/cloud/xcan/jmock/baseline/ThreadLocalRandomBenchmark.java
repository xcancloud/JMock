
package cloud.xcan.jmock.baseline;

import cloud.xcan.jmock.AbstractBenchmark;
import java.util.concurrent.ThreadLocalRandom;
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
 *  Benchmark                                 Mode  Cnt          Score            Error  Units
 *  RandomBenchmark.nextBoolean              thrpt    3  121069538.753 ±   58844598.406  ops/s
 *  RandomBenchmark.nextDouble               thrpt    3   63918503.419 ±   15515234.472  ops/s
 *  RandomBenchmark.nextFloat                thrpt    3  125766609.340 ±   22582829.887  ops/s
 *  RandomBenchmark.nextGaussian             thrpt    3   21047867.704 ±    4897040.723  ops/s
 *  RandomBenchmark.nextInt                  thrpt    3  125363573.540 ±   10746838.172  ops/s
 *  RandomBenchmark.nextLong                 thrpt    3   62544180.441 ±    6720205.041  ops/s
 *  ThreadLocalRandomBenchmark.nextBoolean   thrpt    3  563992245.260 ±   67714216.114  ops/s
 *  ThreadLocalRandomBenchmark.nextDouble    thrpt    3  406563114.969 ±   35055679.010  ops/s
 *  ThreadLocalRandomBenchmark.nextFloat     thrpt    3  400439242.850 ±  599321230.962  ops/s
 *  ThreadLocalRandomBenchmark.nextGaussian  thrpt    3   29046082.930 ±    3110866.923  ops/s
 *  ThreadLocalRandomBenchmark.nextInt       thrpt    3  532601404.581 ± 1432636053.009  ops/s
 *  ThreadLocalRandomBenchmark.nextLong      thrpt    3  592691158.527 ±  147300085.083  ops/s
 *  * ============================
 *  # Threads: 5 threads, will synchronize iterations
 *  Benchmark                                 Mode  Cnt           Score            Error  Units
 *  RandomBenchmark.nextBoolean              thrpt    3   578966514.765 ±  121921570.577  ops/s
 *  RandomBenchmark.nextDouble               thrpt    3   295725784.898 ±   80918861.300  ops/s
 *  RandomBenchmark.nextFloat                thrpt    3   567728002.634 ±  283468589.276  ops/s
 *  RandomBenchmark.nextGaussian             thrpt    3    95529068.584 ±   50495836.622  ops/s
 *  RandomBenchmark.nextInt                  thrpt    3   574145366.066 ±  129494989.910  ops/s
 *  RandomBenchmark.nextLong                 thrpt    3   289970897.028 ±   16204031.944  ops/s
 *  ThreadLocalRandomBenchmark.nextBoolean   thrpt    3  2463283752.274 ±  216424224.066  ops/s
 *  ThreadLocalRandomBenchmark.nextDouble    thrpt    3  1774732511.687 ±  269717432.954  ops/s
 *  ThreadLocalRandomBenchmark.nextFloat     thrpt    3  1689416772.151 ± 2191308720.667  ops/s
 *  ThreadLocalRandomBenchmark.nextGaussian  thrpt    3   124767317.519 ±   32963609.930  ops/s
 *  ThreadLocalRandomBenchmark.nextInt       thrpt    3  2258892177.499 ± 5455935454.429  ops/s
 *  ThreadLocalRandomBenchmark.nextLong      thrpt    3  2516672573.257 ±  698708730.471  ops/s
 * </pre>
 */
public class ThreadLocalRandomBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(ThreadLocalRandomBenchmark.class.getSimpleName())
        .threads(1)
        //.threads(5)
        .build();
    new Runner(opt).run();
  }

  ThreadLocalRandom random = ThreadLocalRandom.current();

  @Benchmark
  public int nextInt() {
    return random.nextInt();
  }

  @Benchmark
  public long nextLong() {
    return random.nextLong();
  }

  @Benchmark
  public boolean nextBoolean() {
    return random.nextBoolean();
  }

  @Benchmark
  public float nextFloat() {
    return random.nextFloat();
  }

  @Benchmark
  public double nextDouble() {
    return random.nextDouble();
  }

  @Benchmark
  public double nextGaussian() {
    return random.nextGaussian();
  }
}
