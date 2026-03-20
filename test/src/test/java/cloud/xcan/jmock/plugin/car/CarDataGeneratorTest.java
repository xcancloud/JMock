package cloud.xcan.jmock.plugin.car;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MBrand;
import cloud.xcan.jmock.plugin.MDrivetrain;
import cloud.xcan.jmock.plugin.MEngine;
import cloud.xcan.jmock.plugin.MHorsepower;
import cloud.xcan.jmock.plugin.MPlate;
import cloud.xcan.jmock.plugin.MVehicle;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class CarDataGeneratorTest {

  // --- Test for random car brand generation ---
  @RepeatedTest(20)
  public void testGenerateRandomBrand() {
    String brand = new MBrand().mock();
    assertNotNull(brand);
    assertTrue(brand.length() >= 2, "Brand name should have at least 2 chars: " + brand);
  }

  @Test
  public void testBrandCoverage() {
    Set<String> generatedBrands = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      generatedBrands.add(new MBrand().mock());
    }
    assertTrue(generatedBrands.size() >= 10,
        "Expected at least 10 distinct brands, got: " + generatedBrands.size());
  }

  // --- Test for random vehicle type generation ---
  @RepeatedTest(20)
  public void testGenerateRandomVehicleType() {
    String type = new MVehicle().mock();
    assertNotNull(type);
    assertTrue(type.length() >= 3, "Vehicle type too short: " + type);
  }

  @Test
  public void testVehicleTypeCoverage() {
    Set<String> generatedTypes = new HashSet<>();
    for (int i = 0; i < 50; i++) {
      generatedTypes.add(new MVehicle().mock());
    }
    assertTrue(generatedTypes.size() >= 3, "Expected at least 3 distinct types");
  }

  // --- Test for random engine type generation ---
  @RepeatedTest(20)
  public void testGenerateRandomEngineType() {
    String engine = new MEngine().mock();
    assertNotNull(engine);
    assertTrue(engine.length() >= 3, "Engine type too short: " + engine);
  }

  @Test
  public void testEngineTypeCoverage() {
    Set<String> generatedEngines = new HashSet<>();
    for (int i = 0; i < 30; i++) {
      generatedEngines.add(new MEngine().mock());
    }
    assertTrue(generatedEngines.size() >= 3);
  }

  // --- Test for random drivetrain generation ---
  @RepeatedTest(20)
  public void testGenerateRandomDrivetrain() {
    String drivetrain = new MDrivetrain().mock();
    assertNotNull(drivetrain);
    assertTrue(drivetrain.length() >= 3, "Drivetrain too short: " + drivetrain);
  }

  @Test
  public void testDrivetrainCoverage() {
    Set<String> generatedDrivetrains = new HashSet<>();
    for (int i = 0; i < 30; i++) {
      generatedDrivetrains.add(new MDrivetrain().mock());
    }
    assertTrue(generatedDrivetrains.size() >= 3, "Should cover multiple drivetrain types");
  }

  // --- Test for random horsepower generation ---
  @RepeatedTest(20)
  public void testGenerateRandomHorsepower() {
    String hp = new MHorsepower().mock();
    assertNotNull(hp);
    assertTrue(hp.endsWith(" HP"), "Should end with ' HP': " + hp);

    int hpValue = Integer.parseInt(hp.split(" ")[0]);
    assertTrue(hpValue >= 70 && hpValue <= 1000,
        "Horsepower out of range: " + hpValue);
  }

  @Test
  public void testHorsepowerRangeCoverage() {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < 100; i++) {
      String hp = new MHorsepower().mock();
      int hpValue = Integer.parseInt(hp.split(" ")[0]);
      if (hpValue < min) {
        min = hpValue;
      }
      if (hpValue > max) {
        max = hpValue;
      }
    }
    assertTrue(min <= 200, "Min HP should be <= 200: " + min);
    assertTrue(max >= 800, "Max HP should be >= 800: " + max);
  }

  // --- Test for license plate generation ---
  @RepeatedTest(20)
  public void testGenerateLicensePlate() {
    String plate = new MPlate().mock();
    assertNotNull(plate);
    assertTrue(plate.length() >= 5, "Plate too short: " + plate);
  }

}
