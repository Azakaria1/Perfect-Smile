package ma.perfectsmile.projetpfa.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.*;
import ma.perfectsmile.projetpfa.repositories.MedecinRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class MedecinServiceImpl implements MedecinService {

    public final OrdonnanceServiceImpl ordonnanceService;
    private final MedecinRepository medecinRepo;
    private final SituationfinanciereServiceImpl situationfinanciereService;
    private final RendezVousServiceImpl rendezVousService;
    private final ConsultationServiceImpl consultationService;
    private List<Ordonnance> ordonnances;

    @Override
    public void ajouterMedecin(Medecin medecin) {
        log.info("ajout du nouveau médecin : {}", medecin.getNom());
        medecinRepo.save(medecin);
    }

    @Override
    public Medecin getMedecin(Long id) {
        log.info("recherche medecin : {}", id);
        return medecinRepo.findDistinctByIdUtilisateur(id);
    }

    @Override
    public List<Medecin> getMedecins() {
        log.info("Liste des médecins : {} ", medecinRepo.findAll());
        return medecinRepo.findAll();
    }

    @Override
    public void modifierMedecin(Medecin medecin) {
        log.info("mise à jour du compte medecin: {}", medecin.getNom());
        medecinRepo.saveAndFlush(medecin);
    }

    @Override
    public void ajouterConsultation(Consultation consultation) {
        consultationService.create(consultation);
    }

    @Override
    public void supprimerConsultation(Consultation consultation) {
        consultationService.deleteByIdConsultation(consultation.getIdConsultation());
    }

    @Override
    public Consultation getConsultation(Long id) {
        return consultationService.getConsultation(id);
    }

    @Override
    public void modifierConsultation(Consultation consultation) {
        consultationService.updateConsultation(consultation);
    }

    @Override
    public void supprimerMedecin(Medecin medecin) {
        log.info("suppression du medecin: {}", medecin.getNom());
        medecinRepo.delete(medecin);
    }

    @Override
    public void validerRendezVous(Medecin medecin, RendezVous rendezVous) {
        if (rendezVous.equals(rendezVousService.getRendezVous(rendezVous.getIdRDV())) && rendezVous.getStatut().equals("En attente")) {
            rendezVous.setStatut("Validé");
        }
    }

    @Override
    public void remplirOrdonnance(Patient patient, Ordonnance ordonnance) {
        patient.getOrdonnances().add(ordonnance);
    }

    @Override
    public void supprimerOrdonnance(Patient patient, Ordonnance ordonnance) {
        patient.getOrdonnances().remove(ordonnance);
    }

    @Override
    public void modifierOrdonnance(Patient patient, Ordonnance ordonnance) {
        for (Ordonnance o : patient.getOrdonnances()) {
            if (o.getIdOrdonnance().equals(ordonnance.getIdOrdonnance())) ordonnanceService.update(o);
        }
    }

    @Override
    public void ajouterSituationFinanciere(Patient patient, SituationFinanciere situationFinanciere) {
        patient.setSituationFinanciere(situationFinanciere);
    }

    @Override
    public void modifierSituationFinanciere(Patient patient, SituationFinanciere situationFinanciere) {
        ajouterSituationFinanciere(patient, situationFinanciere);
    }

    @Override
    public void deleteById(Long id) {
        medecinRepo.deleteById(id);
    }

    @Override
    public Page<Medecin> findByNomContains(String keyword, PageRequest of) {
        return medecinRepo.findByNomContains(keyword,of);
    }
    @Override
    public Page<Medecin> findByPrenomContains(String keyword, PageRequest of) {
        return medecinRepo.findByPrenomContains(keyword,of);
    }

    public Medecin findDistinctByIdUtilisateur(Long id) {
        return medecinRepo.findDistinctByIdUtilisateur(id);

    }
}
