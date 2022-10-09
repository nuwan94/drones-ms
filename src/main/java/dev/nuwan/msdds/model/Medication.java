package dev.nuwan.msdds.model;

import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "medication_id")
  private Long id;

  private String name;

  private Double weight;

  @Column(unique = true)
  private String code;

  private String image;

  @JsonIgnore
  @ManyToMany(mappedBy = "medications")
  private Collection<Drone> drones;

}
