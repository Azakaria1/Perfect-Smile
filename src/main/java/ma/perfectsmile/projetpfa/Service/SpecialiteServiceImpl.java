package ma.perfectsmile.projetpfa.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Specialite;
import ma.perfectsmile.projetpfa.repositories.SpecialiteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SpecialiteServiceImpl implements SpecialiteService {

    private final SpecialiteRepository actRepo;


    @Override
    public Specialite save(Specialite act) {
        log.info("enregistrements du nouveau Specialite : {}", act.getNom());
        return actRepo.save(act);
    }


    @Override
    public List<Specialite> findAll() {
        log.info("recherche tous les Specialites");
        return actRepo.findAll();
    }

    @Override
    public Specialite getSpecialite(Long id) {
        log.info("recherche Specialite : {}", id);
        return actRepo.findDistinctByIdSpecialite(id);
    }


    @Override
    public Page<Specialite> findByNomContains(String keyword, PageRequest of) {
        return actRepo.findByNomContains(keyword,of);
    }

    @Override
    public void deleteByIdSpecialite(Long id) {
        actRepo.deleteByIdSpecialite(id);
    }


    @Override
    public Specialite findByIdSpecialite(Long id) {
        return actRepo.findDistinctByIdSpecialite(id);
    }

}
