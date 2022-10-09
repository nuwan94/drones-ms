package dev.nuwan.msdds.service.impl;

import dev.nuwan.msdds.constants.Messages;
import dev.nuwan.msdds.constants.StatusCodes;
import dev.nuwan.msdds.dto.MedicationDto;
import dev.nuwan.msdds.dto.ResponseDto;
import dev.nuwan.msdds.model.Medication;
import dev.nuwan.msdds.repository.MedicationRepository;
import dev.nuwan.msdds.service.MedicationService;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MedicationServiceImpl implements MedicationService {

  final MedicationRepository medicationRepository;

  public MedicationServiceImpl(MedicationRepository medicationRepository) {
    this.medicationRepository = medicationRepository;
  }

  private Medication checkIfExists(String code) {
    return medicationRepository.findByCode(code);
  }

  @Override
  public ResponseDto create(MedicationDto medicationDto) {
    Medication medicationExist = checkIfExists(medicationDto.getCode());
    if (medicationExist != null) {
      return ResponseDto.builder()
          .status(StatusCodes.DUPLICATE)
          .message(Messages.MEDICATION_EXISTS)
          .build();
    }
    Medication savedMedication =
        medicationRepository.save(medicationDto.toEntity());
    return ResponseDto.builder()
        .status(StatusCodes.SUCCESS)
        .message(Messages.MEDICATION_SAVED)
        .data(savedMedication)
        .build();
  }

  @Override
  public ResponseDto delete(String code) {
    Medication medicationExist = checkIfExists(code);
    if (medicationExist == null) {
      return ResponseDto.builder()
          .status(StatusCodes.NOT_FOUND)
          .message(Messages.MEDICATION_NOT_EXISTS)
          .build();
    }
    long deletedCount = medicationRepository.deleteByCode(code);
    if (deletedCount > 0) {
      return ResponseDto.builder()
          .status(StatusCodes.SUCCESS)
          .message(Messages.MEDICATION_DELETED)
          .build();
    }
    return ResponseDto.builder()
        .status(StatusCodes.FAILURE)
        .message(Messages.MEDICATION_DELETE_FAILURE)
        .build();
  }
}
