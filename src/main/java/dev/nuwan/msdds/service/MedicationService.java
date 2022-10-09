package dev.nuwan.msdds.service;

import dev.nuwan.msdds.dto.MedicationDto;
import dev.nuwan.msdds.dto.ResponseDto;

public interface MedicationService {

  ResponseDto create(MedicationDto medication);

  ResponseDto delete(String code);
}
