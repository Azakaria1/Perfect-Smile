package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Facture;

import java.util.List;

public interface FactureService {
    Facture save(Facture facture);

    List<Facture> findAll();

    Facture getFacture(Long id);

    void update(Facture facture);

    void deleteByIdfacture(Long id);

    Facture findDistinctByIdFacture(Long id);
}
