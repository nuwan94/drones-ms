package dev.nuwan.msdds.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.nuwan.msdds.model.Medication;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDto {

  @JsonIgnore
  private Long id;

  @ApiModelProperty(notes = "Medication Name", example = "Penadol-9mg", required = true)
  @Pattern(regexp = "^[a-zA-Z0-9-_]+$")
  private String name;

  private Double weight;

  @ApiModelProperty(notes = "Medication Code", example = "ABCD_99", required = true)
  @Pattern(regexp = "^[A-Z0-9_]+$")
  private String code;

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
