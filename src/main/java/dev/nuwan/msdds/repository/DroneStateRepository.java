package dev.nuwan.msdds.repository;

import dev.nuwan.msdds.model.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneStateRepository extends JpaRepository<DroneState, Long> {

  DroneState findByName(String name);

}