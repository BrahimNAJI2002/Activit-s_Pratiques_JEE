package ma.enset.activitepratiquen3.services;

import ma.enset.activitepratiquen3.entities.Consultation;
import ma.enset.activitepratiquen3.entities.Medecin;
import ma.enset.activitepratiquen3.entities.Patient;
import ma.enset.activitepratiquen3.entities.RendezVous;


public interface IHospitalService {

    //Save operations
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRendezVous(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);

}