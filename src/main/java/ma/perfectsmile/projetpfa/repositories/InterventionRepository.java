package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Intervention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterventionRepository extends JpaRepository<Intervention, Long> {

    Intervention findDistinctByIdIntervention(Long id);

    void deleteByIdIntervention(Long id);

    Page<Intervention> findByNbDent(int dent, Pageable pageable);

    Page<Intervention> findAllByNbDent(int dent, Pageable pageable);

    Page<Intervention> findByPrixContains(String prix, Pageable pageable);
}