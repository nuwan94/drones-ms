package dev.nuwan.msdds.model;

import dev.nuwan.msdds.base.ValidationTest;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DroneTest extends ValidationTest {

  private final Long testDataId = 1L;

  @Test
  void testSerialNumberLengthValidations() {
    int testDataSerialNumberLength = 101;
    Drone drone = new Drone.DroneBuilder()
        .id(testDataId)
        .serialNumber(RandomStringUtils.random(testDataSerialNumberLength))
        .build();
    Set<ConstraintViolation<Drone>> violations = validator.validate(drone);
    Assertions.assertFalse(violations.isEmpty());
  }

  @Test
  void testSerialNullValidations() {
    Drone drone = new Drone.DroneBuilder()
        .id(testDataId)
        .build();
    Set<ConstraintViolation<Drone>> violations = validator.validate(drone);
    Assertions.assertFalse(violations.isEmpty());
  }

}
