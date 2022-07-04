package ma.perfectsmile.projetpfa.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Medicament;
import ma.perfectsmile.projetpfa.repositories.MedicamentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class MedicamentServiceImpl implements MedicamentService {

    private final MedicamentRepository medicRepo;

    @Override
    public void save(Medicament medicament) {
        log.info("ajout du medicament : {}", medicament.getNom());
        medicRepo.save(medicament);
    }

    @Override
    public List<Medicament> findAll() {
        log.info("recherche de tous les Médicaments");
        return medicRepo.findAll();
    }

    @Override
    public Medicament getMedicament(Long id) {
        log.info("recherche du médicament numéro : {}", id);
        return medicRepo.findByIdMedicament(id);
    }

    @Override
    public Medicament update(Medicament medicament) {
        log.info("mise à jour d'Medicament: {}", medicament.getNom());
        return medicRepo.save(medicament);
    }

    @Override
    public void delete(Long id) {
        log.info("suppression d'un médicament: {}", id);
        medicRepo.deleteByIdMedicament(id);
    }

    @Override
    public Page<Medicament> findByNomContains(String keyword, PageRequest of)
    {
        return medicRepo.findByNomContains(keyword,of);
    }
    @Override
    public Page<Medicament> findByDescriptionContains(String keyword, PageRequest of)
    {
        return medicRepo.findByDescriptionContains(keyword,of);
    }
    @Override
    public Medicament findByIdMedicament(Long id)
    {
        return medicRepo.findByIdMedicament(id);
    }
}