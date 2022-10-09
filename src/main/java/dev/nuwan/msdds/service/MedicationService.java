package dev.nuwan.msdds.service;

import dev.nuwan.msdds.dto.MedicationDto;
import dev.nuwan.msdds.dto.ResponseDto;

public interface MedicationService {

  ResponseDto addMedication(MedicationDto medication);

  ResponseDto deleteMedication(MedicationDto medication);
}
