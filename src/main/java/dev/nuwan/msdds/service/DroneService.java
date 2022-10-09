package dev.nuwan.msdds.service;

import dev.nuwan.msdds.dto.DroneAuditDto;
import dev.nuwan.msdds.dto.DroneDto;
import dev.nuwan.msdds.dto.DroneLoadDto;
import dev.nuwan.msdds.dto.ResponseDto;
import java.util.List;

public interface DroneService {

  ResponseDto register(DroneDto drone);

  ResponseDto loadMedication(DroneLoadDto droneLoadDto);

  ResponseDto checkMedications(String serialNumber);

  ResponseDto checkAvailable();

  ResponseDto checkBattery(String serialNumber);

  List<DroneAuditDto> getAllBatteryInfo();
}
