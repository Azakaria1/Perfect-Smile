package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Assistant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssistantRepository extends JpaRepository<Assistant, Long> {

    Page<Assistant> findByNomContains(String mot, Pageable pageeable);
    Page<Assistant> findByPrenomContains(String mot, Pageable pageable);

    Assistant findDistinctByIdUtilisateur(Long id);
}
