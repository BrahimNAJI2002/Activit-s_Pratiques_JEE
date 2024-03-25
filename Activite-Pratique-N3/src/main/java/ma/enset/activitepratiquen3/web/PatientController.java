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
        //On stock les patients dans le model
        model.addAttribute("patients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("motCle", motCle);
        model.addAttribute("size", patients.getTotalElements());
        return "patients";
    }
    @GetMapping("deletePatient")
    public String deletePatient(Long id, int page, String motCle){
        patientRepository.deleteById(id);
        return "redirect:/patients?page="+page+"&motCle="+motCle;
    }
    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id){
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient", patient);
        model.addAttribute("dateNaissance", patient.getDateNaissance());
        return "editPatient";
    }
    @PostMapping("/updatePatient")
    public String updatePatient(String nom, String dateNaissance, String malade, Long id){
        Patient patient = patientRepository.findById(id).get();
        patient.setNom(nom);
        String [] date = dateNaissance.split("-");
        patient.setDateNaissance(new Date(Integer.parseInt(date[0])-1900, Integer.parseInt(date[1])-1, Integer.parseInt(date[2])));
        if (malade==null)
            patient.setMalade(false);
        else
            patient.setMalade(true);
        patientRepository.save(patient);
        return "redirect:/patients";
    }
}