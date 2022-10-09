package dev.nuwan.msdds.repository;

import dev.nuwan.msdds.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {

  Drone findBySerialNumber(String serialNumber);

}