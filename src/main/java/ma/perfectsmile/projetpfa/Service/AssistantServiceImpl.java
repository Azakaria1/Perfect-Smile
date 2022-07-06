package ma.perfectsmile.projetpfa.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.*;
import ma.perfectsmile.projetpfa.repositories.AssistantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class AssistantServiceImpl implements AssistantService {

    public final OrdonnanceServiceImpl ordonnanceService;
    private final AssistantRepository assistantRepo;
    private final SituationfinanciereServiceImpl situationfinanciereService;
    private final RendezVousServiceImpl rendezVousService;
    private final ConsultationServiceImpl consultationService;
    private List<Ordonnance> ordonnances;

    @Override
    public void ajouterAssistant(Assistant assistant) {
        log.info("ajout du nouveau assistant : {}", assistant.getNom());
        assistantRepo.save(assistant);
    }

    @Override
    public Assistant getAssistant(Long id) {
        log.info("recherche assistant : {}", id);
        return assistantRepo.findDistinctByIdUtilisateur(id);
    }

    @Override
    public List<Assistant> getAssistants() {
        log.info("Liste des assistants : {} ", assistantRepo.findAll());
        return assistantRepo.findAll();
    }

    @Override
    public void modifierAssistant(Assistant assistant) {
        log.info("mise à jour du compte assistant: {}", assistant.getNom());
        assistantRepo.save(assistant);
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
    public void supprimerAssistant(Assistant assistant) {
        log.info("suppression du assistant: {}", assistant.getNom());
        assistantRepo.delete(assistant);
    }

    @Override
    public void validerRendezVous(Assistant assistant, RendezVous rendezVous) {
        if (rendezVous.equals(rendezVousService.getRendezVous(rendezVous.getIdRDV()))
                && rendezVous.getStatut().equals("En attente")) {
            rendezVous.setStatut("Validé");
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
    public Page<Assistant> findByNomContains(String keyword, PageRequest of) {
        return assistantRepo.findByNomContains(keyword,of);
    }
    @Override
    public Page<Assistant> findByPrenomContains(String keyword, PageRequest of) {
        return assistantRepo.findByPrenomContains(keyword,of);
    }

    @Override
    public Assistant findDistinctByIdUtilisateur(Long id) {
        return assistantRepo.findDistinctByIdUtilisateur(id);
    }
    @Override
    public void deleteById(Long id) {
        assistantRepo.deleteById(id);
    }
}
