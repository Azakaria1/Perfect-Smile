package ma.perfectsmile.projetpfa.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.RendezVous;
import ma.perfectsmile.projetpfa.repositories.RendezVousRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class RendezVousServiceImpl implements RendezVousService {

    private final RendezVousRepository rdvRepo;

    @Override
    public void save(RendezVous rdv){
        rdvRepo.save(rdv);
    }

    @Override
    public List<RendezVous> findAll(){
        log.info("recherche de tous les Rendez-Vous");
        return rdvRepo.findAll();
    }
    @Override
    public RendezVous getRendezVous(Long id){
        log.info("recherche Rendez-Vous : {}",id);
        return rdvRepo.findDistinctByIdRDV(id);
    }

    @Override
    public void update(RendezVous rendezVous){
        log.info("mise à jour du Rendez-Vous: {}");
        rdvRepo.save(rendezVous);
    }

    @Override
    public void deleteRendezVous(Long id){
        log.info("suppression du Rendez-Vous: {}",id);
        rdvRepo.deleteByIdRDV(id);
    }

    @Override
    public Page<RendezVous> findByMotifContains(String keyword, PageRequest of) {
        return rdvRepo.findByMotifContains(keyword,of);
    }

    @Override
    public void deleteById(Long id) {
        rdvRepo.deleteById(id);
    }

    @Override
    public RendezVous findDistinctByIdRDV(Long id) {
        return rdvRepo.findDistinctByIdRDV(id);
    }
}
