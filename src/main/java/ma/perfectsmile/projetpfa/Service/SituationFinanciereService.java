package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Facture;
import ma.perfectsmile.projetpfa.Model.Patient;
import ma.perfectsmile.projetpfa.Model.SituationFinanciere;

import java.util.List;

public interface SituationFinanciereService {
    void ajoutSituationFinanciere(Patient patient, SituationFinanciere situationFinanciere);

    List<SituationFinanciere> getAllSituationFinanciere();

    SituationFinanciere getSituationFinanciere(Long id);

    void modifierStuationFinanciere(Patient patient,SituationFinanciere situationFinanciere);

    void supprimerSituationFinanciere(Patient patient);

    void addFactureToPatient(Patient patient , Facture facture);
    void updatePatientFacture(Patient patient , Facture facture);


}
