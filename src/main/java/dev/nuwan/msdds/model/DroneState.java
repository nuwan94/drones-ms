package dev.nuwan.msdds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneState {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "state_id")
  private Long id;

  private String name;

  @OneToOne(mappedBy = "state")
  private Drone drone;

}
