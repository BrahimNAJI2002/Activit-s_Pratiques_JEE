package ma.enset.activitepratiquen3.web;

import ma.enset.activitepratiquen3.entities.Patient;
import ma.enset.activitepratiquen3.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientRestController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/restpatients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
