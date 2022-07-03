package ma.perfectsmile.projetpfa.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.DossierMedical;
import ma.perfectsmile.projetpfa.repositories.DossierMedicalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DossierMedicalServiceImpl implements DossierMedicalService {

    private final DossierMedicalRepository dossierMedicalRepository;


    @Override
    public DossierMedical save(DossierMedical doss) {
        return dossierMedicalRepository.save(doss);
    }


    @Override
    public List<DossierMedical> findAll() {
        log.info("recherche tous les DossierMedicals");
        return dossierMedicalRepository.findAll();
    }

    @Override
    public DossierMedical getDossierMedical(Long id) {
        return dossierMedicalRepository.findDistinctByIdDossierMedical(id);
    }
    @Override
    public Page<DossierMedical> findByAllergieContainsIgnoreCase(String keyword, PageRequest of) {
        return dossierMedicalRepository.findByAllergieContainsIgnoreCase(keyword, of);
    }

    @Override
    public Page<DossierMedical> findByMaladieContainsIgnoreCase(String keyword, PageRequest of) {
        return dossierMedicalRepository.findByMaladieContainsIgnoreCase(keyword, of);
    }

    @Override
    public Page<DossierMedical> findByTypeMaladieContainsIgnoreCase(String keyword, PageRequest of) {
        return dossierMedicalRepository.findByTypeMaladieContainsIgnoreCase(keyword, of);
    }

    @Override
    public Page<DossierMedical> findByTraitementContainsIgnoreCase(String keyword, PageRequest of) {
        return null;
    }

    @Override
    public void deleteByIdDossierMedical(Long id) {
        dossierMedicalRepository.deleteByIdDossierMedical(id);
    }


    @Override
    public DossierMedical findByIdDossierMedical(Long id) {
        return dossierMedicalRepository.findDistinctByIdDossierMedical(id);
    }
}