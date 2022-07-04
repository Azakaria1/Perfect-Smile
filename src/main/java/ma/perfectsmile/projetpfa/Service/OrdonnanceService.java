package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Ordonnance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OrdonnanceService {
    Ordonnance save(Ordonnance ordonnance);

    List<Ordonnance> findAll();

    Ordonnance getOrdonnance(Long id);

    Ordonnance update(Ordonnance ordonnance);

    void delete(Ordonnance ordonnance);

    Page<Ordonnance> findByDescriptionContains(String keyword, PageRequest of);

    void deleteByIdOrdonnance(Long id);

    Ordonnance findByIdOrdonnance(Long id);
}
