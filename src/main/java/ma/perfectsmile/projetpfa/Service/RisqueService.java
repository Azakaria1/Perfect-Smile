package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Risque;

import java.util.List;

public interface RisqueService {

    Risque save(Risque risque);

    List<Risque> findAll();

    Risque getRisque(Long id);

    void update(Risque risque);

    void deleteByIdRisque(Long id);
}
