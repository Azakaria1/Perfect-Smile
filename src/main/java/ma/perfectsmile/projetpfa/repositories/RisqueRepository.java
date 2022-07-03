package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Risque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RisqueRepository extends JpaRepository<Risque,Long> {
    Risque findDistinctByIdRisque(Long id);

    void deleteByIdRisque(Long id);
}