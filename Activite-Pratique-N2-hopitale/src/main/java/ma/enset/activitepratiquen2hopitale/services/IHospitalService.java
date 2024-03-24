package ma.enset.activitepratiquen2hopitale.services;


import ma.enset.activitepratiquen2hopitale.entities.Consultation;
import ma.enset.activitepratiquen2hopitale.entities.Medecin;
import ma.enset.activitepratiquen2hopitale.entities.Patient;
import ma.enset.activitepratiquen2hopitale.entities.RendezVous;

public interface IHospitalService {

    //Save operations
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);


}