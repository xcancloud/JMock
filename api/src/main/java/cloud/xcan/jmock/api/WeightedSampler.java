package cloud.xcan.jmock.api;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Thread-safe weighted sampler that determines whether to return null
 * based on a configured weight ratio.
 * <p>
 * Weight format: "nullCount:nonNullCount", e.g., "1:9" means 10% null probability.
 */
public final class WeightedSampler {

  private final double nullProbability;

  private WeightedSampler(double nullProbability) {
    this.nullProbability = nullProbability;
  }

  /**
   * Creates a sampler from a weight string like "1:9".
   *
   * @param weight format "nullWeight:nonNullWeight"
   * @return a new WeightedSampler
   */
  public static WeightedSampler of(String weight) {
    if (weight == null || weight.isEmpty()) {
      return new WeightedSampler(0.0);
    }
    String[] parts = weight.split(":");
    if (parts.length != 2) {
      throw new IllegalArgumentException("Invalid weight format: " + weight + ", expected 'n:m'");
    }
    double nullW = Double.parseDouble(parts[0].trim());
    double nonNullW = Double.parseDouble(parts[1].trim());
    double total = nullW + nonNullW;
    if (total <= 0) {
      throw new IllegalArgumentException("Total weight must be positive: " + weight);
    }
    return new WeightedSampler(nullW / total);
  }

  /**
   * Creates a sampler from a pre-calculated null probability.
   */
  public static WeightedSampler of(double nullProbability) {
    return new WeightedSampler(nullProbability);
  }

  /**
   * Returns true if this sample should be null, based on the configured probability.
   */
  public boolean shouldBeNull() {
    return nullProbability > 0 && ThreadLocalRandom.current().nextDouble() < nullProbability;
  }

  public double getNullProbability() {
    return nullProbability;
  }
}
