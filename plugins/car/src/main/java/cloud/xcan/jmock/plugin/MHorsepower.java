package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MBrand.random;

import cloud.xcan.jmock.api.AbstractMockFunction;

public class MHorsepower extends AbstractMockFunction {

  // Horsepower range constants
  private static final int MIN_HORSEPOWER = 70;
  private static final int MAX_HORSEPOWER = 1000;

  public MHorsepower() {
  }

  @Override
  public String mock() {
    int hp = random.nextInt(MIN_HORSEPOWER, MAX_HORSEPOWER + 1);
    return hp + " HP";
  }
}
