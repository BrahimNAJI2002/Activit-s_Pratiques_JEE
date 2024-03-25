package ma.enset.activitepratiquen3.repositories;

import ma.enset.activitepratiquen3.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}