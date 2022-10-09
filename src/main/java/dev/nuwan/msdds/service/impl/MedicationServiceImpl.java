package dev.nuwan.msdds.service.impl;

import dev.nuwan.msdds.constants.Messages;
import dev.nuwan.msdds.constants.StatusCodes;
import dev.nuwan.msdds.dto.MedicationDto;
import dev.nuwan.msdds.dto.ResponseDto;
import dev.nuwan.msdds.model.Medication;
import dev.nuwan.msdds.repository.MedicationRepository;
import dev.nuwan.msdds.service.MedicationService;
import javax.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MedicationServiceImpl implements MedicationService {

  @Autowired MedicationRepository medicationRepository;

  private Medication checkIfExists(MedicationDto medicationDto) {
    return medicationRepository.findByCode(medicationDto.getCode());
  }

  @Override
  public ResponseDto addMedication(MedicationDto medicationDto) {
    Medication medicationExist = checkIfExists(medicationDto);
    if (medicationExist != null) {
      return ResponseDto.builder()
          .status(StatusCodes.DUPLICATE)
          .message(Messages.MEDICATION_EXISTS)
          .build();
    }
    Medication savedMedication =
        medicationRepository.save(medicationDto.toEntity());
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("medication", savedMedication);
    return ResponseDto.builder()
        .status(StatusCodes.SUCCESS)
        .message(Messages.MEDICATION_SAVED)
        .build();
  }

  @Override
  public ResponseDto deleteMedication(MedicationDto medicationDto) {
    Medication medicationExist = checkIfExists(medicationDto);
    if (medicationExist == null) {
      return ResponseDto.builder()
          .status(StatusCodes.NOT_FOUND)
          .message(Messages.MEDICATION_NOT_EXISTS)
          .build();
    }
    long deletedCount =
        medicationRepository.deleteByCode(medicationDto.getCode());
    if (deletedCount > 0) {
    }
    JSONObject jsonObject = new JSONObject();
    return ResponseDto.builder()
        .status(StatusCodes.SUCCESS)
        .message(Messages.MEDICATION_DELETED)
        .data(jsonObject)
        .build();
  }
}
