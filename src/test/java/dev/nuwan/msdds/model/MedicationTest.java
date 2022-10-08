package dev.nuwan.msdds.model;

import dev.nuwan.msdds.base.ValidationTest;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MedicationTest extends ValidationTest {

  private final Long testDataId = 1L;
  private Set<ConstraintViolation<Medication>> violations;
  private Medication medication;

  @Test
  void testNamePatternValidations() {
    String invalidNamePattern = "medicine*1";
    medication = new Medication.MedicationBuilder()
        .id(testDataId)
        .name(invalidNamePattern)
        .build();
    violations = validator.validate(medication);
    Assertions.assertFalse(violations.isEmpty());
  }

  @Test
  void testCodePatternValidations() {
    String invalidCodePattern = "cDA1";
    medication = new Medication.MedicationBuilder()
        .id(testDataId)
        .code(invalidCodePattern)
        .build();
    violations = validator.validate(medication);
    Assertions.assertFalse(violations.isEmpty());
  }

}
