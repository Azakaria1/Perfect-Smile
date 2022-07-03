package ma.perfectsmile.projetpfa.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Risque;
import ma.perfectsmile.projetpfa.repositories.RisqueRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RisqueServiceImpl implements RisqueService {

    private final RisqueRepository risqueRepository;

    @Override
    public Risque save(Risque risque) {
        log.info("création du risque : {}", risque.getMaladie());
        return risqueRepository.saveAndFlush(risque);
    }

    @Override
    public List<Risque> findAll() {
        log.info("recherche de tous les Risques");
        return risqueRepository.findAll();
    }

    @Override
    public Risque getRisque(Long id) {
        log.info("recherche Risque numéro : {}", id);
        return risqueRepository.findDistinctByIdRisque(id);
    }

    @Override
    public void update(Risque risque) {
        log.info("mise à jour du risque: {}", risque.getMaladie());
        risqueRepository.saveAndFlush(risque);
    }

    @Override
    public void deleteByIdRisque(Long id) {
        log.info("suppression du risque numéro: {}", id);
        risqueRepository.deleteByIdRisque(id);
    }

}
