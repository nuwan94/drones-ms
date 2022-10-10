package dev.nuwan.msdds.dto;

import dev.nuwan.msdds.base.ValidationTest;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DroneDtoTest extends ValidationTest {

  @Test
  void testSerialNumberLengthValidations() {
    int testDataSerialNumberLength = 101;
    DroneDto droneDto = DroneDto.builder()
        .serialNo(RandomStringUtils.random(testDataSerialNumberLength))
        .build();
    Set<ConstraintViolation<DroneDto>> violations = validator.validate(droneDto);
    Assertions.assertFalse(violations.isEmpty());
  }

  @Test
  void testSerialNullValidations() {
    DroneDto droneDto = DroneDto.builder().build();
    Set<ConstraintViolation<DroneDto>> violations = validator.validate(droneDto);
    Assertions.assertFalse(violations.isEmpty());
  }

}
