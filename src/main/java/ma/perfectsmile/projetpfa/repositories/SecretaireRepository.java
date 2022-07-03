package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Secretaire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretaireRepository extends JpaRepository<Secretaire, Long> {

    Page<Secretaire> findByNomContains(String mot, Pageable pageeable);

    Page<Secretaire> findByPrenomContains(String mot, Pageable pageable);


    Secretaire findDistinctByIdUtilisateur(Long id);

    boolean existsByEmail(String email);
}
