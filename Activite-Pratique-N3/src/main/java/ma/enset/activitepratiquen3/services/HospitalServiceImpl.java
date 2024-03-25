package ma.enset.activitepratiquen3.services;

import jakarta.transaction.Transactional;
import ma.enset.activitepratiquen3.entities.Consultation;
import ma.enset.activitepratiquen3.entities.Medecin;
import ma.enset.activitepratiquen3.entities.Patient;
import ma.enset.activitepratiquen3.entities.RendezVous;
import ma.enset.activitepratiquen3.repositories.ConsultationRepository;
import ma.enset.activitepratiquen3.repositories.MedecinRepository;
import ma.enset.activitepratiquen3.repositories.PatientRepository;
import ma.enset.activitepratiquen3.repositories.RendezVousRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {

    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private ConsultationRepository consultationRepository;
    private RendezVousRepository rendezVousRepository;

    public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, ConsultationRepository consultationRepository, RendezVousRepository rendezVousRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.consultationRepository = consultationRepository;
        this.rendezVousRepository = rendezVousRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        //Insérer tout le code de validation
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}