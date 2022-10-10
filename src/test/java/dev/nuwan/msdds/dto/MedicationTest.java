package dev.nuwan.msdds.dto;

import dev.nuwan.msdds.base.ValidationBase;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MedicationTest extends ValidationBase {

  private Set<ConstraintViolation<MedicationDto>> violations;
  private MedicationDto medicationDto;

  @Test
  void testNamePatternValidations() {
    String invalidNamePattern = "medicine*1";
    medicationDto = MedicationDto.builder()
        .name(invalidNamePattern)
        .build();
    violations = validator.validate(medicationDto);
    Assertions.assertFalse(violations.isEmpty());
  }

  @Test
  void testCodePatternValidations() {
    String invalidCodePattern = "cDA1";
    medicationDto = MedicationDto.builder()
        .code(invalidCodePattern)
        .build();
    violations = validator.validate(medicationDto);
    Assertions.assertFalse(violations.isEmpty());
  }

}
