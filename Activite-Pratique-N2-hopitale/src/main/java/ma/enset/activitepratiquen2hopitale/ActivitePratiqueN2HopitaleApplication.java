package ma.enset.activitepratiquen2hopitale;

import ma.enset.activitepratiquen2hopitale.entities.*;
import ma.enset.activitepratiquen2hopitale.repositories.*;
import ma.enset.activitepratiquen2hopitale.services.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class ActivitePratiqueN2HopitaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitePratiqueN2HopitaleApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IHospitalService hospitalService,
						   PatientRepository patientRepository, MedecinRepository medecinRepository,
						   RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
		return args -> {
			createPatients(hospitalService, patientRepository);
			createMedecins(hospitalService, medecinRepository);

			Patient p1 = patientRepository.findById(1L).orElse(null);
			Patient p2 = patientRepository.findByNom("Naji");

			Medecin m1 = medecinRepository.findById(1L).orElse(null);
			Medecin m2 = medecinRepository.findByNom("Mohammed");

			if (p1 != null && m1 != null) {
				RendezVous rdv1 = new RendezVous();
				rdv1.setDate(new Date());
				rdv1.setPatient(p1);
				rdv1.setMedecin(m1);
				rdv1.setStatus(StatusRDV.DONE);
				hospitalService.saveRendezVous(rdv1);

				Consultation c1 = new Consultation();
				c1.setDateConsultation(new Date());
				c1.setDiagnostic("Diagnostic 1");
				c1.setRendezVous(rdv1);
				hospitalService.saveConsultation(c1);
			}
		};
	}

	private void createPatients(IHospitalService hospitalService, PatientRepository patientRepository) {
		String[] names = {"Brahim", "Naji", "Mohammed", "Habib", "Ahmed", "Abderrahmane"};
		Stream.of(names).forEach(nom -> {
			Patient patient = new Patient();
			patient.setNom(nom);
			patient.setDateNaissance(new Date());
			patient.setMalade(false);
			hospitalService.savePatient(patient);
		});
	}

	private void createMedecins(IHospitalService hospitalService, MedecinRepository medecinRepository) {
		String[] names = {"Brahim", "Naji", "Mohammed", "Habib", "Ahmed", "Abderrahmane"};
		Stream.of(names).forEach(nom -> {
			Medecin medecin = new Medecin();
			medecin.setNom(nom);
			medecin.setEmail(nom.toLowerCase() + "@gmail.com");
			medecin.setSpecialite(Math.random() > 0.5 ? "Cardiologue" : "Dentiste");
			hospitalService.saveMedecin(medecin);
		});
	}
}
