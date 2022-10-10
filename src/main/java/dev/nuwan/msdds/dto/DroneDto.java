package dev.nuwan.msdds.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DroneDto implements Serializable {

  @ApiModelProperty(notes = "Serial Number", example = "D101010", required = true)
  @Size(max = 100, message = "Serial Number length is limited to 100 characters")
  @NotEmpty(message = "Serial number cannot be empty")
  private String serialNo;

  @ApiModelProperty(notes = "Drone Model", example = "Lightweight", required = true)
  @NotEmpty(message = "Model cannot be empty")
  private String model;

  @ApiModelProperty(notes = "Drone State", example = "IDLE", required = true)
  @NotEmpty(message = "State cannot be empty")
  private String state;

  @ApiModelProperty(notes = "Battery Percentage", example = "50.0", required = true)
  @Max(value = 100L, message = "Maximum battery percentage is 100")
  @Min(value = 0L, message = "Minimum battery percentage is 0")
  @NotNull(message = "Battery percentage cannot be empty")
  private BigDecimal battery;
}
