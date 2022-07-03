package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Medecin;
import ma.perfectsmile.projetpfa.Model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {


    Page<Medecin> findByNomContains(String mot, Pageable pageeable);
    Page<Medecin> findByPrenomContains(String mot, Pageable pageable);


    Medecin findDistinctByIdUtilisateur(Long id);
    boolean existsByEmail(String email);
}
