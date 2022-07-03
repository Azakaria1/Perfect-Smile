package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Intervention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface InterventionService {

    Intervention save(Intervention acte);

    List<Intervention> findAll();

    Intervention getIntervention(Long id);

    Page<Intervention> findByDent(String keyword, PageRequest of);

    void deleteByIdIntervention(Long id);

    Intervention findByIdIntervention(Long id);

    Page<Intervention> findByPrixContains(String prix, PageRequest of);
}
