package dev.nuwan.msdds.service.impl;

import dev.nuwan.msdds.constants.StatusCodes;
import dev.nuwan.msdds.dto.DroneDto;
import dev.nuwan.msdds.dto.ResponseDto;
import dev.nuwan.msdds.model.Drone;
import dev.nuwan.msdds.repository.DroneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest {

  private static final String TEST_DRONE_SERIAL_NUMBER_EXIST = "12345";
  @Mock
  private DroneRepository droneRepository;

  @InjectMocks
  DroneServiceImpl droneService;

  Drone drone;
  DroneDto droneDto;

  @BeforeEach
  void setup() {
    droneDto = DroneDto.builder().serialNo(TEST_DRONE_SERIAL_NUMBER_EXIST).build();
    drone = Drone.builder().serialNumber(TEST_DRONE_SERIAL_NUMBER_EXIST).build();
    Mockito.when(droneRepository.findBySerialNumber(TEST_DRONE_SERIAL_NUMBER_EXIST))
        .thenReturn(drone);
  }

  @Test
  void testDroneExistsRegister() {
    ResponseDto responseDto = droneService.register(droneDto);
    Assertions.assertEquals(StatusCodes.DUPLICATE, responseDto.getStatus());
  }
}