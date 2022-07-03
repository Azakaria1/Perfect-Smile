package ma.perfectsmile.projetpfa.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Secretaire;
import ma.perfectsmile.projetpfa.repositories.SecretaireRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SecretaireServiceImpl implements SecretaireService {

    private final SecretaireRepository secretaireRepo;

    @Override
    public void ajouterSecretaire(Secretaire secretaire) {
        log.info("ajout du nouveau secrétaire : {}", secretaire.getNom());
        secretaireRepo.save(secretaire);
    }


    @Override
    public Secretaire getSecretaire(Long id) {
        log.info("recherche secretaire : {}", id);
        return secretaireRepo.findDistinctByIdUtilisateur(id);
    }

    @Override
    public List<Secretaire> getSecretaires() {
        log.info("Liste des secrétaires : {} ", secretaireRepo.findAll());
        return secretaireRepo.findAll();
    }

    @Override
    public void modifierSecretaire(Secretaire secretaire) {
        log.info("mise à jour du compte secretaire: {}", secretaire.getNom());
        secretaireRepo.save(secretaire);
    }

    @Override
    public void supprimerSecretaire(Secretaire secretaire) {
        log.info("suppression du secretaire: {}", secretaire.getNom());
        secretaireRepo.delete(secretaire);
    }
    @Override
    public Page<Secretaire> findByNomContains(String keyword, PageRequest of) {
        return secretaireRepo.findByNomContains(keyword,of);
    }
    @Override
    public Page<Secretaire> findByPrenomContains(String keyword, PageRequest of) {
        return secretaireRepo.findByPrenomContains(keyword,of);
    }

    @Override
    public Secretaire findDistinctByIdUtilisateur(Long id) {
        return secretaireRepo.findDistinctByIdUtilisateur(id);
    }
    @Override
    public void deleteById(Long id) {
        secretaireRepo.deleteById(id);
    }
}
