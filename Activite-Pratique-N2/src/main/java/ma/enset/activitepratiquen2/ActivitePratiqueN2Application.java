package ma.enset.activitepratiquen2;

import ma.enset.activitepratiquen2.entities.Patient;
import ma.enset.activitepratiquen2.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ActivitePratiqueN2Application implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {

        SpringApplication.run(ActivitePratiqueN2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null, "NAJI",new Date(04-12-2002),false,20));
        patientRepository.save(new Patient(null, "SADFI",new Date(29-04-1999),false,37));
        patientRepository.save(new Patient(null, "SALHI",new Date(12-04-1980),true,89));
        List<Patient> patients = patientRepository.findAll();
        System.out.println("Affichage avant suppression :");
        patients.forEach(patient -> System.out.println(patient.toString()));
        System.out.println("Affichage du patient 1 :");
        System.out.println(patientRepository.findById(1L).toString());
        System.out.println("Affichage du patient avec le nom contient 'NA' :");
        System.out.println(patientRepository.findByNomContains("NA"));

        patientRepository.deleteById(Long.valueOf(2));
        System.out.println("Affichage après suppression de patient 2 :");
        patients = patientRepository.findAll();
        patients.forEach(patient -> System.out.println(patient.toString()));

    }
}
