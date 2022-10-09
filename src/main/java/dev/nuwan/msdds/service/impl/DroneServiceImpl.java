package dev.nuwan.msdds.service.impl;

import dev.nuwan.msdds.constants.Constants;
import dev.nuwan.msdds.constants.Messages;
import dev.nuwan.msdds.constants.StatusCodes;
import dev.nuwan.msdds.dto.DroneDto;
import dev.nuwan.msdds.dto.DroneLoadDto;
import dev.nuwan.msdds.dto.ResponseDto;
import dev.nuwan.msdds.model.Drone;
import dev.nuwan.msdds.model.DroneModel;
import dev.nuwan.msdds.model.DroneState;
import dev.nuwan.msdds.model.Medication;
import dev.nuwan.msdds.repository.DroneModelRepository;
import dev.nuwan.msdds.repository.DroneRepository;
import dev.nuwan.msdds.repository.DroneStateRepository;
import dev.nuwan.msdds.repository.MedicationRepository;
import dev.nuwan.msdds.service.DroneService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@CommonsLog
public class DroneServiceImpl implements DroneService {

  final
  DroneRepository droneRepository;
  final
  DroneModelRepository droneModelRepository;
  DroneStateRepository droneStateRepository;
  final
  MedicationRepository medicationRepository;

  public DroneServiceImpl(DroneRepository droneRepository,
      DroneModelRepository droneModelRepository, DroneStateRepository droneStateRepository,
      MedicationRepository medicationRepository) {
    this.droneRepository = droneRepository;
    this.droneModelRepository = droneModelRepository;
    this.droneStateRepository = droneStateRepository;
    this.medicationRepository = medicationRepository;
  }

  @Override
  public ResponseDto register(@Valid DroneDto requestDrone) {
    Drone droneExist = droneRepository.findBySerialNumber(requestDrone.getSerialNo());
    if (droneExist != null) {
      log.error("Drone with serial number " + requestDrone.getSerialNo() + " already exist.");
      return ResponseDto.builder()
          .status(StatusCodes.DUPLICATE)
          .message(Messages.DRONE_EXISTS)
          .build();
    }
    DroneModel droneModel = droneModelRepository.findByName(requestDrone.getModel());
    if (droneModel == null) {
      log.error("Drone model " + requestDrone.getModel() + " is invalid.");
      return ResponseDto.builder()
          .status(StatusCodes.INVALID)
          .message(Messages.DRONE_INVALID_MODEL)
          .build();
    }
    DroneState droneState = droneStateRepository.findByName(requestDrone.getState());
    if (droneState == null) {
      log.error("Drone state " + requestDrone.getState() + " is invalid.");
      return ResponseDto.builder()
          .status(StatusCodes.INVALID)
          .message(Messages.DRONE_INVALID_STATE)
          .build();
    }
    Drone drone = Drone.builder()
        .serialNumber(requestDrone.getSerialNo())
        .model(droneModel)
        .battery(requestDrone.getBattery().doubleValue())
        .state(droneState)
        .weight(0.0)
        .build();
    Drone savedDrone = droneRepository.save(drone);
    log.info("Drone with serial number " + requestDrone.getSerialNo() + " saved successfully");
    return ResponseDto.builder()
        .status(StatusCodes.SUCCESS)
        .message(Messages.DRONE_REGISTERED)
        .data(savedDrone).build();
  }

  @Override
  public ResponseDto loadMedication(DroneLoadDto droneLoadDto) {
    Drone droneExist = droneRepository.findBySerialNumber(droneLoadDto.getDroneSerialNo());
    Medication medicationExist = medicationRepository.findByCode(droneLoadDto.getMedicationCode());

    // Check if drone and medication exist in the DB
    if (droneExist == null) {
      return ResponseDto.builder()
          .status(StatusCodes.NOT_FOUND)
          .message(Messages.DRONE_NOT_EXISTS)
          .build();
    }
    if (medicationExist == null) {
      return ResponseDto.builder()
          .status(StatusCodes.NOT_FOUND)
          .message(Messages.MEDICATION_NOT_EXISTS)
          .build();
    }

    // Check for weight limit
    if (!canLoad(droneExist, medicationExist)) {
      return ResponseDto.builder()
          .status(StatusCodes.FAILURE)
          .message(Messages.DRONE_WEIGHT_LIMIT_EXCEED)
          .build();
    }

    // Update existing drone
    List<Medication> medicationCollections = new ArrayList<>(droneExist.getMedications());
    medicationCollections.add(medicationExist);
    droneExist.setMedications(medicationCollections);
    droneExist.setWeight(droneExist.getWeight() + medicationExist.getWeight());

    // Update DB
    droneRepository.save(droneExist);

    return ResponseDto.builder()
        .status(StatusCodes.SUCCESS)
        .message(Messages.DRONE_LOADED_SUCCESSFULLY)
        .build();
  }

  @Override
  public ResponseDto checkMedications(String serialNumber) {
    List<Medication> medications = droneRepository.findMedicationsBySerialNumber(serialNumber);
    if (medications == null) {
      return ResponseDto.builder()
          .status(StatusCodes.NOT_FOUND)
          .message(Messages.DRONE_NOT_EXISTS)
          .build();
    }

    return ResponseDto.builder()
        .status(StatusCodes.SUCCESS)
        .message(Messages.DRONE_FOUND)
        .data(medications).build();
  }

  @Override
  public ResponseDto checkAvailable() {
    List<Drone> drones = droneRepository.findByWeightLessThanAndBatteryGreaterThan(
        Constants.DRONE_MAX_WEIGHT_LIMIT,
        Constants.DRONE_MIN_BATTERY_LIMIT);
    List<String> dronesList = drones.stream().map(Drone::getSerialNumber).toList();
    return ResponseDto.builder()
        .status(StatusCodes.SUCCESS)
        .message(dronesList.size() + " " + Messages.DRONE_FOUND)
        .data(dronesList).build();
  }

  @Override
  public ResponseDto checkBattery(String serialNumber) {
    Double battery = droneRepository.findBatteryBySerialNumber(serialNumber);
    if (battery == null) {
      return ResponseDto.builder()
          .status(StatusCodes.NOT_FOUND)
          .message(Messages.DRONE_NOT_EXISTS)
          .build();
    }

    return ResponseDto.builder()
        .status(StatusCodes.SUCCESS)
        .message(Messages.DRONE_FOUND)
        .data(battery).build();
  }

  private boolean canLoad(Drone drone, Medication medication) {
    return drone.getWeight() + medication.getWeight() <= Constants.DRONE_MAX_WEIGHT_LIMIT;
  }
}
