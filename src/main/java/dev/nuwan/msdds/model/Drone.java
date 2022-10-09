package dev.nuwan.msdds.model;

import static javax.persistence.GenerationType.IDENTITY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "drone_id")
  private Long id;

  @Column(length = 100, unique = true)
  @NotBlank
  private String serialNumber;

  @Column(columnDefinition = "integer default 25")
  private Double weight;

  private Double battery;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "model_id")
  private DroneModel model;

  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "state_id")
  private DroneState state;

  @JsonIgnore
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "drone_medications",
      joinColumns = @JoinColumn(name = "drone_id"),
      inverseJoinColumns = @JoinColumn(name = "medication_id")
  )
  private List<Medication> medications;
}
