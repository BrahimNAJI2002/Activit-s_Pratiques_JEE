package ma.enset.activitepratiquen2hopitale.repositories;

import ma.enset.activitepratiquen2hopitale.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByNom(String nom); //On suppose que le nom est unique
}