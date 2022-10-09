package dev.nuwan.msdds.repository;

import dev.nuwan.msdds.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

  Medication findByCode(String code);

  long deleteByCode(String code);
}
