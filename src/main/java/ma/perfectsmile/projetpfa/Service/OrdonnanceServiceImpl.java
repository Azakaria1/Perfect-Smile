package ma.perfectsmile.projetpfa.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Ordonnance;
import ma.perfectsmile.projetpfa.repositories.OrdonnanceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Ordonnance save(Ordonnance ordonnance) {
        log.info("ajout de l'ordonnance : {}");
        return ordoRepo.save(ordonnance);
    }

    @Override
    public List<Ordonnance> findAll() {
        log.info("Liste des Ordonnances");
        return ordoRepo.findAll();
    }

    @Override
    public Ordonnance getOrdonnance(Long id) {
        log.info("recherche Ordonnance : {}");
        return ordoRepo.findByIdOrdonnance(id);
    }

    @Override
    public Ordonnance update(Ordonnance ordonnance) {
        log.info("mise à jour d'Act: {}");
        return ordoRepo.saveAndFlush(ordonnance);
    }

    @Override
    public void delete(Ordonnance ordonnance) {
        log.info("suppression d'Act: {}", ordonnance.getDescription());
        ordoRepo.delete(ordonnance);
    }

    @Override
    public Page<Ordonnance> findByDescriptionContains(String keyword, PageRequest of)
    {
        return ordoRepo.findByDescriptionContains(keyword, of);
    }
    @Override
    public void deleteByIdOrdonnance(Long id)
    {
        ordoRepo.deleteByIdOrdonnance(id);
    }
    @Override
    public Ordonnance findByIdOrdonnance(Long id)
    {
        return ordoRepo.findByIdOrdonnance(id);
    }
}