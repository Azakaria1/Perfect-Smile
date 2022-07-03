package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Page<Utilisateur> findByNomContains(String mot, Pageable pageable);
    Utilisateur findByNomContains(String mot);

    Page<Utilisateur> findByPrenomContains(String mot, Pageable pageable);

    Utilisateur findDistinctByIdUtilisateur(Long id);

    boolean existsByEmail(String email);

    Utilisateur findDistinctByPrenom(String username);

    Utilisateur findDistinctByNom(String username);

    Utilisateur findByEmail(String email);

    Utilisateur findByNom(String mot);

    @Query("from Utilisateur u where u.nom =?1 and u.prenom =?2")
    Utilisateur findByUsername(String nom, String prenom);
}