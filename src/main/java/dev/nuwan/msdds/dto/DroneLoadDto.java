package dev.nuwan.msdds.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DroneLoadDto {

  @ApiModelProperty(notes = "Serial Number", example = "D10101010",
                    required = true)
  @Size(max = 100,
        message = "Serial Number length is limited to 100 characters")
  private String droneSerialNo;

  @ApiModelProperty(notes = "Medication Code", example = "CODE99",
                    required = true)
  @Pattern(regexp = "^[A-Z0-9_]+$")
  private String medicationCode;
}
