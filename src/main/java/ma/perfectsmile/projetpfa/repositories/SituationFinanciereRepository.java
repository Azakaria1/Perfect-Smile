package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.SituationFinanciere;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituationFinanciereRepository extends JpaRepository<SituationFinanciere,Long> {

    void deleteByIdSituation(Long id);

    SituationFinanciere findDistinctByIdSituation(Long id);
}
