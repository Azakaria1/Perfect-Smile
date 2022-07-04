package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Medicament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface MedicamentService {
    void save(Medicament medicament);

    List<Medicament> findAll();

    Medicament getMedicament(Long id);

    Medicament update(Medicament medicament);

    void delete(Long id);

    Page<Medicament> findByNomContains(String keyword, PageRequest of);

    Page<Medicament> findByDescriptionContains(String keyword, PageRequest of);

    Medicament findByIdMedicament(Long id);
}
