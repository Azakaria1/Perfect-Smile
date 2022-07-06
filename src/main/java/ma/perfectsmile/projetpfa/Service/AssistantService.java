package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AssistantService {

    void ajouterAssistant(Assistant assistant);

    void supprimerAssistant(Assistant assistant);

    Assistant getAssistant(Long id);

    void modifierAssistant(Assistant consultation);

    void ajouterConsultation(Consultation consultation);

    void supprimerConsultation(Consultation consultation);

    Consultation getConsultation(Long id);

    void modifierConsultation(Consultation consultation);

    List<Assistant> getAssistants();

    void validerRendezVous(Assistant assistant, RendezVous rendezVous);

    void ajouterSituationFinanciere(Patient patient, SituationFinanciere situationFinanciere);

    void modifierSituationFinanciere(Patient patient, SituationFinanciere situationFinanciere);

    Page<Assistant> findByNomContains(String keyword, PageRequest of);

    Page<Assistant> findByPrenomContains(String keyword, PageRequest of);

    Assistant findDistinctByIdUtilisateur(Long id);

    void deleteById(Long id);
}
