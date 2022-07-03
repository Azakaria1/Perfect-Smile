package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.DossierMedical;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DossierMedicalService {

    DossierMedical save(DossierMedical acte);

    List<DossierMedical> findAll();

    DossierMedical getDossierMedical(Long id);
    void deleteByIdDossierMedical(Long id);

    DossierMedical findByIdDossierMedical(Long id);

    Page<DossierMedical> findByAllergieContainsIgnoreCase(String prix, PageRequest of);
    Page<DossierMedical> findByMaladieContainsIgnoreCase(String prix, PageRequest of);
    Page<DossierMedical> findByTypeMaladieContainsIgnoreCase(String prix, PageRequest of);
    Page<DossierMedical> findByTraitementContainsIgnoreCase(String prix, PageRequest of);
}
