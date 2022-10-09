package dev.nuwan.msdds.repository;

import dev.nuwan.msdds.model.DroneModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneModelRepository extends JpaRepository<DroneModel, Long> {

  DroneModel findByName(String name);

}