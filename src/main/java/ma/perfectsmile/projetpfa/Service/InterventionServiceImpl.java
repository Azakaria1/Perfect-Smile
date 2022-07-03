package ma.perfectsmile.projetpfa.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Intervention;
import ma.perfectsmile.projetpfa.repositories.InterventionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InterventionServiceImpl implements InterventionService {

    private final InterventionRepository interventionRepository;


    @Override
    public Intervention save(Intervention intervention) {
        return interventionRepository.save(intervention);
    }


    @Override
    public List<Intervention> findAll() {
        log.info("recherche tous les Interventions");
        return interventionRepository.findAll();
    }

    @Override
    public Intervention getIntervention(Long id) {
        log.info("recherche Intervention : {}", id);
        return interventionRepository.findDistinctByIdIntervention(id);
    }


    @Override
    public Page<Intervention> findByDent(String keyword, PageRequest of) {
        return interventionRepository.findByNbDent(Integer.parseInt(keyword), of);
    }

    @Override
    public void deleteByIdIntervention(Long id) {
        interventionRepository.deleteByIdIntervention(id);
    }


    @Override
    public Intervention findByIdIntervention(Long id) {
        return interventionRepository.findDistinctByIdIntervention(id);
    }



    @Override
    public Page<Intervention> findByPrixContains(String prix, PageRequest of) {
        return interventionRepository.findByPrixContains(prix,of);
    }

    public Page<Intervention> findAllByDent(int keyword, PageRequest of) {
        return interventionRepository.findAllByNbDent(keyword,of);

    }

    public Page<Intervention> findAll(PageRequest of) {
        return interventionRepository.findAll(of);
    }
}
