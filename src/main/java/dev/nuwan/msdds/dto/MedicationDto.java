package dev.nuwan.msdds.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.nuwan.msdds.model.Medication;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDto implements Serializable {

  @JsonIgnore
  private Long id;

  @ApiModelProperty(notes = "Medication Name", example = "Medication_Name-01", required = true)
  @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "Allowed only letters, numbers, '-', '_'")
  @NotEmpty(message = "Medication name must not be empty")
  private String name;

  @ApiModelProperty(notes = "Medication Weight", example = "100.0", required = true)
  @NotNull(message = "Medication weight must not be empty")
  private Double weight;

  @ApiModelProperty(notes = "Medication Code", example = "CODE99", required = true)
  @Pattern(regexp = "^[A-Z0-9_]+$", message = "Allowed only upper case letters, underscore and numbers")
  @NotEmpty(message = "Medication code must not be empty")
  private String code;

  @ApiModelProperty(notes = "Picture of the Medication Case", example = "medication.jpg", required = true)
  @NotEmpty(message = "Medication image must not be empty")
  private String image;

  public Medication toEntity() {
    return Medication.builder()
        .name(name)
        .code(code)
        .weight(weight)
        .image(image)
        .build();
  }
}
