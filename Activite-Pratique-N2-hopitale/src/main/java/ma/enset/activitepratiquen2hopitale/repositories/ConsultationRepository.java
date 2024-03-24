package ma.enset.activitepratiquen2hopitale.repositories;


import ma.enset.activitepratiquen2hopitale.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}