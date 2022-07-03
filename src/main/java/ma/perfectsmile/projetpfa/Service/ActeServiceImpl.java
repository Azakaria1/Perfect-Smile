package ma.perfectsmile.projetpfa.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Acte;
import ma.perfectsmile.projetpfa.repositories.ActeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ActeServiceImpl implements ActeService {

    private final ActeRepository actRepo;


    @Override
    public Acte save(Acte act) {
        log.info("enregistrements du nouveau Acte : {}", act.getLibelle());
        return actRepo.save(act);
    }


    @Override
    public List<Acte> findAll() {
        log.info("recherche tous les Actes");
        return actRepo.findAll();
    }

    @Override
    public Acte getActe(Long id) {
        log.info("recherche Acte : {}", id);
        return actRepo.findDistinctByIdActe(id);
    }


    @Override
    public Page<Acte> findByLibelleContains(String keyword, PageRequest of) {
        return actRepo.findByLibelleContains(keyword,of);
    }

    @Override
    public void deleteByIdActe(Long id) {
        actRepo.deleteByIdActe(id);
    }


    @Override
    public Acte findByIdActe(Long id) {
        return actRepo.findDistinctByIdActe(id);
    }



    @Override
    public Page<Acte> findByPrixContains(String prix, PageRequest of) {
        return actRepo.findByPrixContains(prix,of);
    }
}
