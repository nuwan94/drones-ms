package dev.nuwan.msdds.controller;

import dev.nuwan.msdds.dto.MedicationDto;
import dev.nuwan.msdds.dto.ResponseDto;
import dev.nuwan.msdds.service.MedicationService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medication")
public class MedicationController extends BaseController {

  private final MedicationService medicationService;

  public MedicationController(MedicationService medicationService) {
    this.medicationService = medicationService;
  }

  @ResponseBody
  @PostMapping(value = "/")
  public ResponseEntity<ResponseDto>
  create(@Valid @RequestBody MedicationDto medication) {
    ResponseDto response = medicationService.create(medication);
    if (response.getStatus() != 0) {
      return ResponseEntity.badRequest().body(response);
    }
    return ResponseEntity.ok(response);
  }

  @ResponseBody
  @DeleteMapping(value = "/{code}")
  public ResponseEntity<ResponseDto> delete(@PathVariable String code) {
    ResponseDto response = medicationService.delete(code);
    if (response.getStatus() != 0) {
      return ResponseEntity.badRequest().body(response);
    }
    return ResponseEntity.ok(response);
  }
}
