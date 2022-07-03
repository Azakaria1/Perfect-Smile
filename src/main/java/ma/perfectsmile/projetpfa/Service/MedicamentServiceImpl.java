package ma.perfectsmile.projetpfa.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Medicament;
import ma.perfectsmile.projetpfa.repositories.MedicamentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class MedicamentServiceImpl implements MedicamentService {

    private final MedicamentRepository medicRepo;

    @Override
    public void addMedicament(Medicament medicament) {
        log.info("ajout du medicament : {}", medicament.getNom());
        medicRepo.save(medicament);
    }

    @Override
    public List<Medicament> getMedicaments() {
        log.info("recherche de tous les Médicaments");
        return medicRepo.findAll();
    }

    @Override
    public Medicament getMedicament(Long id) {
        log.info("recherche du médicament numéro : {}", id);
        return medicRepo.findMedicamentByIdMedicament(id);
    }

    @Override
    public Medicament update(Medicament medicament) {
        log.info("mise à jour d'Medicament: {}", medicament.getNom());
        return medicRepo.save(medicament);
    }

    @Override
    public Boolean deleteMedicament(Long id) {
        log.info("suppression d'Medicament: {}", id);
        medicRepo.deleteMedicamentByIdMedicament(id);
        return TRUE;
    }
}
