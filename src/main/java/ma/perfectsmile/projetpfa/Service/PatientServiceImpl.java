package ma.perfectsmile.projetpfa.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Patient;
import ma.perfectsmile.projetpfa.Model.RendezVous;
import ma.perfectsmile.projetpfa.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepo;
    private final RendezVousServiceImpl rendezVousService;

    @Override
    public void ajouterPatient(Patient patient) {
        log.info("ajout du nouveau patient : {}", patient.getNom());
        patientRepo.saveAndFlush(patient);
    }

    @Override
    public void ajouterRendezVous(Patient patient, RendezVous rendezVous) {
        patient.getRendezVousList().add(rendezVous);
    }

    @Override
    public void supprimerRendezVous(Patient patient, RendezVous rendezVous) {
        patient.getRendezVousList().remove(rendezVous);
    }

    @Override
    public void modifierRendezVous(Patient patient, Long idrdv) {

        RendezVous rendezVous = rendezVousService.getRendezVous(idrdv);
        List<RendezVous> rendezVousList = patient.getRendezVousList();

        for (RendezVous rdv : rendezVousList) {
            {
                if (rdv.getIdRDV().equals(rendezVous.getIdRDV())) rendezVousService.update(rdv);
            }
        }
    }

    @Override
    public Patient getPatient(Long id) {
        log.info("recherche patient : {}", id);
        return patientRepo.findDistinctByIdUtilisateur(id);
    }

    @Override
    public List<Patient> getPatients() {
        log.info("Liste des patients : {} ", patientRepo.findAll());
        return patientRepo.findAll();
    }

    @Override
    public void modifierPatient(Patient patient) {
        log.info("mise à jour du compte patient: {}", patient.getNom());
        patientRepo.saveAndFlush(patient);
    }

    @Override
    public void supprimerPatient(Patient patient) {
        log.info("suppression du patient: {}", patient.getNom());
        patientRepo.delete(patient);
    }

    public Page<Patient> findByNomContains(String keyword, PageRequest of) {
        return patientRepo.findByNomContains(keyword,of);
    }
    public Page<Patient> findByPrenomContains(String keyword, PageRequest of) {
        return patientRepo.findByNomContains(keyword,of);
    }

    public void deleteById(Long id) {
        patientRepo.deleteById(id);
    }

    public Patient findDistinctByIdUtilisateur(Long id) {
        return patientRepo.findDistinctByIdUtilisateur(id);
    }
}
