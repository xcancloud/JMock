
package cloud.xcan.jmock;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(value = 0, jvmArgs = {"-server", "-Xms256M", "-Xmx256M"})
@State(Scope.Thread)
public abstract class AbstractBenchmark {

  /**
   * Create a new instance.
   */
  protected AbstractBenchmark() {
    // Hide public constructor to prevent instantiation
  }

  // Empty. Serves as an annotation placeholder.
}
