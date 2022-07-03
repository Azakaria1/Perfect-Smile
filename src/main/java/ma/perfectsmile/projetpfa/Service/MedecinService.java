package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface MedecinService {

    void ajouterMedecin(Medecin medecin);


    Page<Medecin> findByNomContains(String keyword, PageRequest of);

    Page<Medecin> findByPrenomContains(String keyword, PageRequest of);

    void supprimerMedecin(Medecin medecin);

    Medecin getMedecin(Long id);

    void modifierMedecin(Medecin consultation);

    void ajouterConsultation(Consultation consultation);

    void supprimerConsultation(Consultation consultation);

    Consultation getConsultation(Long id);

    void modifierConsultation(Consultation consultation);

    List<Medecin> getMedecins();

    void validerRendezVous(Medecin medecin, RendezVous rendezVous);

    void remplirOrdonnance(Patient patient, Ordonnance ordonnance);

    void supprimerOrdonnance(Patient patient, Ordonnance ordonnance);

    void modifierOrdonnance(Patient patient, Ordonnance ordonnance);

    void ajouterSituationFinanciere(Patient patient, SituationFinanciere situationFinanciere);

    void modifierSituationFinanciere(Patient patient, SituationFinanciere situationFinanciere);

    void deleteById(Long id);
}
