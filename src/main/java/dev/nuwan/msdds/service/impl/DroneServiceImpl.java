package dev.nuwan.msdds.service.impl;

import dev.nuwan.msdds.constants.Messages;
import dev.nuwan.msdds.constants.StatusCodes;
import dev.nuwan.msdds.dto.DroneDto;
import dev.nuwan.msdds.dto.ResponseDto;
import dev.nuwan.msdds.model.Drone;
import dev.nuwan.msdds.model.DroneModel;
import dev.nuwan.msdds.repository.DroneModelRepository;
import dev.nuwan.msdds.repository.DroneRepository;
import dev.nuwan.msdds.service.DroneService;
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
}
