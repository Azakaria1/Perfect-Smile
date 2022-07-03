package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Consultation;
import ma.perfectsmile.projetpfa.Model.Facture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    Facture findDistinctByIdfacture(Long id);

    void deleteByIdfacture(Long id);

}
