package dev.nuwan.msdds.controller;

import dev.nuwan.msdds.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseDto handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    ResponseDto responseDto = new ResponseDto();
    ex.getBindingResult().getAllErrors()
        .stream()
        .filter(FieldError.class::isInstance)
        .map(FieldError.class::cast)
        .forEach((error) -> {
          responseDto.setMessage(error.getDefaultMessage());
        });
    return responseDto;
  }
}
