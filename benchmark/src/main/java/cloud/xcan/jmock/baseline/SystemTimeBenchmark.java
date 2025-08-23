package cloud.xcan.jmock.baseline;

import cloud.xcan.jmock.AbstractBenchmark;
import java.util.Calendar;
import java.util.Date;
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
 *  Benchmark                                    Mode  Cnt          Score           Error  Units
 * SystemTimeBenchmark.CalendarMillis          thrpt    3    6006585.284 ±   4236143.016  ops/s
 * SystemTimeBenchmark.DateMillis              thrpt    3   37611565.175 ±  16050405.042  ops/s
 * SystemTimeBenchmark.currentTimeMillis       thrpt    3   38360385.724 ±  19747300.668  ops/s
 *  * ============================
 *  # Threads: 5 threads, will synchronize iterations
 *  Benchmark                                    Mode  Cnt           Score            Error  Units
 * SystemTimeBenchmark.CalendarMillis          thrpt    3    26398904.999 ±   10838928.182  ops/s
 * SystemTimeBenchmark.DateMillis              thrpt    3   176734329.222 ±   93984824.857  ops/s
 * SystemTimeBenchmark.currentTimeMillis       thrpt    3   179308713.324 ±   42823991.452  ops/s
 * </pre>
 */
public class SystemTimeBenchmark extends AbstractBenchmark {

  public static void main(String[] args) throws RunnerException, InterruptedException {
    Options opt = new OptionsBuilder()
        .include(SystemTimeBenchmark.class
            .getSimpleName()) // like match and ThreadLocalRandomBenchmark is included
        //.threads(1)
        .threads(5)
        .build();
    new Runner(opt).run();
  }


  @Benchmark
  public long currentTimeMillis() {
    return System.currentTimeMillis();
  }

  @Benchmark
  public long DateMillis() {
    return new Date().getTime();
  }

  @Benchmark
  public long CalendarMillis() {
    return Calendar.getInstance().getTimeInMillis();
  }

}
