package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Ordonnance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdonnanceRepository extends JpaRepository<Ordonnance,Long> {
    Ordonnance findOrdonnanceByIdOrdonnance(Long id);

    void deleteOrdonnanceByIdOrdonnance(Long id);
}
