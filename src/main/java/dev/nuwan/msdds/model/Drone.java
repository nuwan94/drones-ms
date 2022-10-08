package dev.nuwan.msdds.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
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
  @Length(max = 100)
  private String serialNumber;

  @Range(min = 0, max = 500)
  private Double weight;

  private Double battery;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "model_id")
  private DroneModel model;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "state_id")
  private DroneState state;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
      name = "drone_medications",
      joinColumns = @JoinColumn(name = "drone_id"),
      inverseJoinColumns = @JoinColumn(name = "medication_id")
  )
  private Collection<Medication> medications;
}
