package ma.enset.activitepratiquen3;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ma.enset.activitepratiquen3.services.*;
import ma.enset.activitepratiquen3.repositories.*;
import ma.enset.activitepratiquen3.entities.*;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class ActivitePratiqueN3Application {

    public static void main(String[] args) {
        SpringApplication.run(ActivitePratiqueN3Application.class, args);
    }

    @Bean
    CommandLineRunner initializeData(IHospitalService hospitalService,
                                     PatientRepository patientRepository, MedecinRepository medecinRepository,
                                     RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        return args -> {
            // Ajout de patients
            Stream.of("Brahim", "Ahmed", "Mohammed", "Khadija", "Abdesslam").forEach(nom -> {
                Patient patient = new Patient();
                patient.setNom(nom);
                patient.setDateNaissance(new Date());
                patient.setMalade(false);
                hospitalService.savePatient(patient);
            });

            // Ajout de médecins
            Stream.of("Brahim", "Ahmed", "Mohammed", "Khadija", "Abdesslam").forEach(nom -> {
                Medecin medecin = new Medecin();
                medecin.setNom(nom);
                medecin.setEmail(nom.toLowerCase() + "@gmail.com");
                medecin.setSpecialite(Math.random() > 0.5 ? "Cardiologue" : "Dentiste");
                hospitalService.saveMedecin(medecin);
            });

            // Récupération de patients et médecins pour les rendez-vous
            Patient patient1 = patientRepository.findById(1L).orElse(null);
            Patient patient2 = patientRepository.findByNom("Ahmed");
            Medecin medecin1 = medecinRepository.findById(1L).orElse(null);
            Medecin medecin2 = medecinRepository.findByNom("Brahim");

            // Création d'un rendez-vous
            if (patient1 != null && medecin1 != null) {
                RendezVous rendezVous = new RendezVous();
                rendezVous.setDate(new Date());
                rendezVous.setPatient(patient1);
                rendezVous.setMedecin(medecin1);
                rendezVous.setStatus(StatusRDV.DONE);
                hospitalService.saveRendezVous(rendezVous);

                // Création d'une consultation associée au rendez-vous
                Consultation consultation = new Consultation();
                consultation.setDateConsultation(new Date());
                consultation.setDiagnostic("Diagnostic 1");
                consultation.setRendezVous(rendezVous);
                hospitalService.saveConsultation(consultation);
            }
        };
    }
}
