package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Ordonnance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdonnanceRepository extends JpaRepository<Ordonnance,Long> {
    Ordonnance findByIdOrdonnance(Long id);

    void deleteByIdOrdonnance(Long id);
    Page<Ordonnance> findByDescriptionContains(String keyword, Pageable pageable);
}
