package ma.perfectsmile.projetpfa.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Facture;
import ma.perfectsmile.projetpfa.repositories.FactureRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class FactureServiceImpl implements FactureService {

    private final FactureRepository factRepo;

    @Override
    public Facture save(Facture facture) {
        log.info("enregistrements de facture : {}", facture.getPrixTotal());
        return factRepo.save(facture);
    }

    @Override
    public List<Facture> findAll() {
        log.info("recherche de toutes les factures");
        return factRepo.findAll();
    }

    @Override
    public Facture getFacture(Long id) {
        log.info("recherche Facture : {}", id);
        return factRepo.findDistinctByIdfacture(id);
    }

    @Override
    public void update(Facture facture) {
        log.info("mise à jour de la facture: {}");
        factRepo.saveAndFlush(facture);
    }

    @Override
    public void deleteByIdfacture(Long id) {
        log.info("suppression de la facture: {}");
        factRepo.deleteByIdfacture(id);
    }

    @Override
    public Facture findDistinctByIdFacture(Long id) {
        return factRepo.findDistinctByIdfacture(id);
    }
}