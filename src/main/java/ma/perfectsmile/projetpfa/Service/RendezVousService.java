package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RendezVousService {
    void save(RendezVous rdv);

    List<RendezVous> findAll();

    RendezVous getRendezVous(Long id);

    void update(RendezVous rendezVous);

    void deleteRendezVous(Long id);

    Page<RendezVous> findByMotifContains(String keyword, PageRequest of);

    void deleteById(Long id);

    RendezVous findDistinctByIdRDV(Long id);
}
