package dev.nuwan.msdds.service.impl;

import dev.nuwan.msdds.constants.Constants;
import dev.nuwan.msdds.constants.Messages;
import dev.nuwan.msdds.constants.StatusCodes;
import dev.nuwan.msdds.dto.DroneDto;
import dev.nuwan.msdds.dto.DroneLoadDto;
import dev.nuwan.msdds.dto.ResponseDto;
import dev.nuwan.msdds.model.Drone;
import dev.nuwan.msdds.model.DroneModel;
import dev.nuwan.msdds.model.Medication;
import dev.nuwan.msdds.repository.DroneModelRepository;
import dev.nuwan.msdds.repository.DroneRepository;
import dev.nuwan.msdds.repository.MedicationRepository;
import dev.nuwan.msdds.service.DroneService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneServiceImpl implements DroneService {

  @Autowired
  DroneRepository droneRepository;
  @Autowired
  DroneModelRepository droneModelRepository;
  @Autowired
  MedicationRepository medicationRepository;

  @Override
  public ResponseDto registerDrone(@Valid DroneDto requestDrone) {
    Drone droneExist = droneRepository.findBySerialNumber(requestDrone.getSerialNo());
    if (droneExist != null) {
      return ResponseDto.builder()
          .status(StatusCodes.DUPLICATE)
          .message(Messages.DRONE_EXISTS)
          .build();
    }
    DroneModel droneModel = droneModelRepository.findByName(requestDrone.getModel());
    if (droneModel == null) {
      return ResponseDto.builder()
          .status(StatusCodes.INVALID)
          .message(Messages.DRONE_INVALID_MODEL)
          .build();
    }
    Drone drone = Drone.builder()
        .serialNumber(requestDrone.getSerialNo())
        .model(droneModel)
        .battery(requestDrone.getBattery().doubleValue())
        .build();
    Drone savedDrone = droneRepository.save(drone);
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("drone", savedDrone);
    return ResponseDto.builder()
        .status(StatusCodes.SUCCESS)
        .message(Messages.DRONE_REGISTERED)
        .data(jsonObject).build();
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
      return ResponseDto.builder().status(StatusCodes.FAILURE)
          .message(Messages.DRONE_WEIGHT_LIMIT_EXCEED)
          .build();
    }

    // Update existing drone
    List<Medication> medicationCollections = new ArrayList<>(droneExist.getMedications());
    medicationCollections.add(medicationExist);
    droneExist.setMedications(medicationCollections);
    droneExist.setWeight(droneExist.getWeight() + medicationExist.getWeight());

    // Update DB
    Drone savedDrone = droneRepository.save(droneExist);

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("drone", savedDrone);
    return ResponseDto.builder().status(StatusCodes.SUCCESS)
        .message(Messages.DRONE_LOADED_SUCCESSFULLY)
        .data(jsonObject).build();
  }

  @Override
  public ResponseDto checkDrone(String serialNumber) {
    Drone drone = droneRepository.findBySerialNumber(serialNumber);
    if (drone == null) {
      return ResponseDto.builder()
          .status(StatusCodes.NOT_FOUND)
          .message(Messages.DRONE_NOT_EXISTS)
          .build();
    }
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("drone", drone);
    return ResponseDto.builder().status(StatusCodes.SUCCESS)
        .message(Messages.DRONE_FOUND)
        .data(jsonObject).build();
  }

  private boolean canLoad(Drone drone, Medication medication) {
    return !(drone.getWeight() + medication.getWeight() > Constants.DRONE_MAX_WEIGHT_LIMIT);
  }
}
