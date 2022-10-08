package dev.nuwan.msdds.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Pattern;
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

  @Pattern(regexp = "^[a-zA-Z0-9-_]+$")
  private String name;

  private Double weight;

  @Pattern(regexp = "^[A-Z0-9_]+$")
  private String code;

  private String image;

  @ManyToMany(mappedBy = "medications")
  private Collection<Drone> drones;

}
