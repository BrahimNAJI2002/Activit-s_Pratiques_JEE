package ma.enset.activitepratiquen2.repository;

import ma.enset.activitepratiquen2.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
     List<Patient> findByNomContains(String nom);
     public void deleteById(Long id);
}
