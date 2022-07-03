package ma.perfectsmile.projetpfa.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Ordonnance;
import ma.perfectsmile.projetpfa.repositories.OrdonnanceRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class OrdonnanceServiceImpl implements OrdonnanceService {

    private final OrdonnanceRepository ordoRepo;

    @Override
    public Ordonnance addOrdonnance(Ordonnance ordonnance) {
        log.info("ajout de l'ordonnance : {}");
        return ordoRepo.saveAndFlush(ordonnance);
    }

    @Override
    public List<Ordonnance> getOrdonnances() {
        log.info("Liste des Ordonnances");
        return ordoRepo.findAll();
    }

    @Override
    public Ordonnance getOrdonnance(Long id) {
        log.info("recherche Ordonnance : {}");
        return ordoRepo.findOrdonnanceByIdOrdonnance(id);
    }

    @Override
    public Ordonnance updateOrdonnance(Ordonnance ordonnance) {
        log.info("mise à jour d'Act: {}");
        return ordoRepo.saveAndFlush(ordonnance);
    }

    @Override
    public void deleteOrdonnance(Ordonnance ordonnance) {
        log.info("suppression d'Act: {}", ordonnance.getDescription());
        ordoRepo.delete(ordonnance);
    }

}
