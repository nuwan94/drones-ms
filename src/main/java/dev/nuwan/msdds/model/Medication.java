package dev.nuwan.msdds.model;

import static javax.persistence.GenerationType.IDENTITY;

import dev.nuwan.msdds.dto.MedicationDto;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "medication_id")
  private Long id;

  private String name;

  private Double weight;

  private String code;

  private String image;

  @ManyToMany(mappedBy = "medications")
  private Collection<Drone> drones;

  public MedicationDto toDto() {
    return MedicationDto.builder()
        .id(id)
        .name(name)
        .weight(weight)
        .code(code)
        .image(image)
        .build();
  }

}
