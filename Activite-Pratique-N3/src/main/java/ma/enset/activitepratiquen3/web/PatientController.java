package ma.enset.activitepratiquen3.web;

import ma.enset.activitepratiquen3.entities.Patient;
import ma.enset.activitepratiquen3.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }

    @GetMapping("/patients")
    public String chercher(Model model, @RequestParam(name="page",defaultValue = "0")int page,
                           @RequestParam(name="motCle",defaultValue = "")String motCle){
        Page<Patient> patients = patientRepository.findByNomContains(motCle, PageRequest.of(page, 5));
        //On stocke les patients dans le modèle
        model.addAttribute("patients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("motCle", motCle);
        model.addAttribute("size", patients.getTotalElements());
        return "patients";
    }

    @GetMapping("/deletePatient")
    public String deletePatient(@RequestParam Long id, @RequestParam int page, @RequestParam String motCle){
        patientRepository.deleteById(id);
        return "redirect:/patients?page="+page+"&motCle="+motCle;
    }

    @GetMapping("/editPatient")
    public String editPatient(Model model, @RequestParam Long id){
        Patient patient = patientRepository.findById(id).orElse(null);
        model.addAttribute("patient", patient);
        model.addAttribute("dateNaissance", patient.getDateNaissance());
        return "editPatient";
    }

    @PostMapping("/updatePatient")
    public String updatePatient(@RequestParam String nom, @RequestParam String dateNaissance, @RequestParam String malade, @RequestParam Long id){
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient != null) {
            patient.setNom(nom);
            String[] date = dateNaissance.split("-");
            patient.setDateNaissance(new Date(Integer.parseInt(date[0]) - 1900, Integer.parseInt(date[1]) - 1, Integer.parseInt(date[2])));
            patient.setMalade("on".equals(malade));
            patientRepository.save(patient);
        }
        return "redirect:/patients";
    }
}
