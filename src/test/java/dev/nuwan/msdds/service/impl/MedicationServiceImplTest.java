package dev.nuwan.msdds.service.impl;

import dev.nuwan.msdds.constants.StatusCodes;
import dev.nuwan.msdds.dto.MedicationDto;
import dev.nuwan.msdds.dto.ResponseDto;
import dev.nuwan.msdds.model.Medication;
import dev.nuwan.msdds.repository.MedicationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MedicationServiceImplTest {

  private static final String TEST_CODE = "CODE";
  @Mock
  private MedicationRepository medicationRepository;

  @InjectMocks
  MedicationServiceImpl medicationService;

  Medication medication;
  MedicationDto medicationDto;

  @BeforeEach
  void setup() {
    medication = Medication.builder().code("TEST_CODE").build();
    medicationDto = MedicationDto.builder().code(TEST_CODE).build();
    Mockito.when(medicationRepository.findByCode(TEST_CODE)).thenReturn(
        medication);
  }

  @Test
  void testMedicationExistsCreate() {
    ResponseDto responseDto = medicationService.create(medicationDto);
    Assertions.assertEquals(StatusCodes.DUPLICATE, responseDto.getStatus());
  }
}