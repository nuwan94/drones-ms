package dev.nuwan.msdds.service;

import dev.nuwan.msdds.dto.DroneDto;
import dev.nuwan.msdds.dto.DroneLoadDto;
import dev.nuwan.msdds.dto.ResponseDto;

public interface DroneService {

  ResponseDto registerDrone(DroneDto drone);

  ResponseDto loadMedication(DroneLoadDto droneLoadDto);

  ResponseDto checkDrone(String serialNumber);
}
