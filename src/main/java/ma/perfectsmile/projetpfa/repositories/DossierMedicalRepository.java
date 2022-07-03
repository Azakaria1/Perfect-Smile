package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.DossierMedical;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical,Long> {
    DossierMedical findDistinctByIdDossierMedical(Long id);

    void deleteByIdDossierMedical(Long id);

    Page<DossierMedical> findByAllergieContainsIgnoreCase(String keyword, Pageable pageable);
    Page<DossierMedical> findByMaladieContainsIgnoreCase(String keyword, Pageable pageable);
    Page<DossierMedical> findByTypeMaladieContainsIgnoreCase(String keyword, Pageable pageable);
    Page<DossierMedical> findByTraitementContainsIgnoreCase(String keyword, Pageable pageable);

}
