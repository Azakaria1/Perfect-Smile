package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Ordonnance;

import java.util.List;

public interface OrdonnanceService {
    Ordonnance addOrdonnance(Ordonnance ordonnance);

    List<Ordonnance> getOrdonnances();

    Ordonnance getOrdonnance(Long id);

    Ordonnance updateOrdonnance(Ordonnance ordonnance);

    void deleteOrdonnance(Ordonnance ordonnance);
}
