package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Medecin;
import ma.perfectsmile.projetpfa.Model.Medicament;
import ma.perfectsmile.projetpfa.Model.Secretaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SecretaireService {

    void ajouterSecretaire(Secretaire secretaire);

    void supprimerSecretaire(Secretaire secretaire);

    Secretaire getSecretaire(Long id);

    void modifierSecretaire(Secretaire secretaire);

    List<Secretaire> getSecretaires();

    Page<Secretaire> findByNomContains(String keyword, PageRequest of);

    Page<Secretaire> findByPrenomContains(String keyword, PageRequest of);

    Secretaire findDistinctByIdUtilisateur(Long id);

    void deleteById(Long id);
}