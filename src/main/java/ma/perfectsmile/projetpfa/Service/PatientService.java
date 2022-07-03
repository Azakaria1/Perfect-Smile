package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Patient;
import ma.perfectsmile.projetpfa.Model.RendezVous;

import java.util.List;

public interface PatientService {

    void ajouterRendezVous(Patient patient, RendezVous rendezVous);

    void supprimerRendezVous(Patient patient, RendezVous rendezVous);

    void modifierRendezVous(Patient patient, Long idrdv);

    Patient getPatient(Long id);

    List<Patient> getPatients();

    void ajouterPatient(Patient patient);

    void modifierPatient(Patient patient);

    void supprimerPatient(Patient patient);

}