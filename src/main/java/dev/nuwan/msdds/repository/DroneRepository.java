package dev.nuwan.msdds.repository;

import dev.nuwan.msdds.model.Drone;
import dev.nuwan.msdds.model.Medication;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DroneRepository extends JpaRepository<Drone, Long> {

  Drone findBySerialNumber(String serialNumber);

  @Query("select d.medications from Drone d where d.serialNumber = ?1")
  List<Medication> findMedicationsBySerialNumber(String serialNumber);

  List<Drone> findByWeightLessThanAndBatteryGreaterThan(Double weight, Double battery);

}