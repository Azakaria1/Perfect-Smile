package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Acte;
import ma.perfectsmile.projetpfa.Model.Medicament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MedicamentRepository extends JpaRepository<Medicament,Long> {
    Medicament findByIdMedicament(Long id);

    void deleteByIdMedicament(Long id);

    Page<Medicament> findByNomContains(String acteName, Pageable pageable);
    Page<Medicament> findByDescriptionContains(String acteName, Pageable pageable);

}