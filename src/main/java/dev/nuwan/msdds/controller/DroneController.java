package dev.nuwan.msdds.controller;

import dev.nuwan.msdds.dto.DroneDto;
import dev.nuwan.msdds.dto.ResponseDto;
import dev.nuwan.msdds.service.DroneService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/drone")
public class DroneController extends BaseController {

  private final DroneService droneService;

  public DroneController(DroneService droneService) {
    this.droneService = droneService;
  }

  @ResponseBody
  @PostMapping(value = "/register", produces = "application/json", consumes = "application/json")
  public ResponseEntity<ResponseDto> register(@Valid @RequestBody DroneDto drone) {
    ResponseDto response = droneService.registerDrone(drone);
    if (response.getStatus() != 0) {
      return ResponseEntity.badRequest().body(response);
    }
    return ResponseEntity.ok(response);
  }

}