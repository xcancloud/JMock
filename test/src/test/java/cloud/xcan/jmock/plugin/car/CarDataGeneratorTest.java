package cloud.xcan.jmock.plugin.car;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MBrand;
import cloud.xcan.jmock.plugin.MDrivetrain;
import cloud.xcan.jmock.plugin.MEngine;
import cloud.xcan.jmock.plugin.MHorsepower;
import cloud.xcan.jmock.plugin.MPlate;
import cloud.xcan.jmock.plugin.MVehicle;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class CarDataGeneratorTest {

  // Expected values for validation
  private static final List<String> EXPECTED_BRANDS = Arrays.asList(
      "BYD", "Geely", "Chery", "Great Wall", "Changan",
      "FAW", "SAIC Motor", "NIO", "XPeng", "Li Auto",
      "Toyota", "Honda", "Ford", "Chevrolet", "Volkswagen"
  );

  private static final List<String> EXPECTED_TYPES = Arrays.asList(
      "Sedan", "SUV", "Truck", "Hatchback", "Coupe"
  );

  private static final List<String> EXPECTED_ENGINES = Arrays.asList(
      "1.5L I4 Turbo", "2.0L I4", "3.0L V6", "Electric Motor"
  );

  private static final List<String> EXPECTED_DRIVETRAINS = Arrays.asList(
      "FWD (Front-Wheel Drive)", "RWD (Rear-Wheel Drive)", "AWD (All-Wheel Drive)",
      "4WD (Four-Wheel Drive)"
  );

  // --- Test for random car brand generation ---
  @RepeatedTest(20)
  public void testGenerateRandomBrand() {
    String brand = new MBrand().mock();
    assertNotNull(brand);
    assertTrue(EXPECTED_BRANDS.contains(brand) || MBrand.CAR_BRANDS.contains(brand));
  }

  @Test
  public void testBrandCoverage() {
    Set<String> generatedBrands = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      generatedBrands.add(new MBrand().mock());
    }

    // Verify at least 10 distinct brands are generated
    assertTrue(generatedBrands.size() >= 10,
        "Expected at least 10 distinct brands, got: " + generatedBrands.size());

    // Verify Chinese brands are included
    assertTrue(generatedBrands.contains("BYD") ||
            generatedBrands.contains("Geely") ||
            generatedBrands.contains("NIO"),
        "Should contain Chinese brands");
  }

  // --- Test for random vehicle type generation ---
  @RepeatedTest(20)
  public void testGenerateRandomVehicleType() {
    String type = new MVehicle().mock();
    assertNotNull(type);
    assertTrue(EXPECTED_TYPES.contains(type) || MVehicle.VEHICLE_TYPES.contains(type));
  }

  @Test
  public void testVehicleTypeCoverage() {
    Set<String> generatedTypes = new HashSet<>();
    for (int i = 0; i < 50; i++) {
      generatedTypes.add(new MVehicle().mock());
    }
    assertTrue(generatedTypes.size() >= 5, "Expected at least 5 distinct types");
  }

  // --- Test for random engine type generation ---
  @RepeatedTest(20)
  public void testGenerateRandomEngineType() {
    String engine = new MEngine().mock();
    assertNotNull(engine);
    assertTrue(engine.contains("L ") || engine.contains("Electric") || engine.contains("Hybrid"),
        "Unexpected engine format: " + engine);
  }

  @Test
  public void testEngineTypeCoverage() {
    Set<String> generatedEngines = new HashSet<>();
    for (int i = 0; i < 30; i++) {
      generatedEngines.add(new MEngine().mock());
    }
    assertTrue(generatedEngines.size() >= 5);
  }

  // --- Test for random drivetrain generation ---
  @RepeatedTest(20)
  public void testGenerateRandomDrivetrain() {
    String drivetrain = new MDrivetrain().mock();
    assertNotNull(drivetrain);
    System.out.println(drivetrain);
  }

  @Test
  public void testDrivetrainCoverage() {
    Set<String> generatedDrivetrains = new HashSet<>();
    for (int i = 0; i < 30; i++) {
      generatedDrivetrains.add(new MDrivetrain().mock());
    }
    assertEquals(6, generatedDrivetrains.size(), "All 6 types should be covered");
  }

  // --- Test for random horsepower generation ---
  @RepeatedTest(20)
  public void testGenerateRandomHorsepower() {
    String hp = new MHorsepower().mock();
    assertNotNull(hp);
    assertTrue(hp.endsWith(" HP"), "Should end with ' HP': " + hp);

    // Extract numeric value and validate range
    int hpValue = Integer.parseInt(hp.split(" ")[0]);
    assertTrue(hpValue >= 70 && hpValue <= 1000,
        "Horsepower out of range: " + hpValue);
  }

  @Test
  public void testHorsepowerRangeCoverage() {
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;

    // Test range coverage over multiple generations
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
    System.out.println(plate);
  }

}
