package cloud.xcan.comp.jmock.core.support.utils;

import java.util.Random;

public class WeightedRandomSamplingAlgorithmTest {


  public void weightedRandomSelect(int times, double[] weights) {

    System.out.println("DocGenerator weight:    " + weights[0]);
    System.out.println("B weight:   " + weights[1]);
    System.out.println("C weight: " + weights[2]);

    double totalWeight = 0;
    for (double n : weights) {
      totalWeight += n;
    }
    for (int i = 0; i < weights.length; i++) {
      weights[i] = weights[i] / totalWeight;
    }

    int[] count = new int[3];
    for (int i = 0; i < times; i++) {
      double random = new Random().nextDouble();
      double countWeight = 0;
      for (int index = 0; index < weights.length; index++) {
        countWeight += weights[index];
        if (countWeight >= random) {
          count[index]++; // Hit
          break;
        }
      }
    }

    System.out
        .println("DocGenerator Hit:" + count[0] + ", " + 100 * count[0] / (double) times + "%");
    System.out.println("B Hit:" + count[1] + ", " + 100 * count[1] / (double) times + "%");
    System.out.println("C Hit:" + count[2] + ", " + 100 * count[2] / (double) times + "%");
  }

  public static void main(String[] args) {
    WeightedRandomSamplingAlgorithmTest w = new WeightedRandomSamplingAlgorithmTest();

    System.out.println("\n-------------------Case 1-----------------");
    double[] case1 = {4, 4, 8};
    w.weightedRandomSelect(10000, case1);

    System.out.println("\n-------------------Case 2-----------------");
    double[] case2 = {0.40, 0.80, 0.20};
    w.weightedRandomSelect(10000, case2);

    System.out.println("\n-------------------Case 3-----------------");

    double[] case3 = {0.20, 0.30, 0.50};
    w.weightedRandomSelect(10000, case3);
  }

}