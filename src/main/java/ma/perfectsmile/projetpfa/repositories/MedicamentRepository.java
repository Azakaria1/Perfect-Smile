package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentRepository extends JpaRepository<Medicament,Long> {
    Medicament findMedicamentByIdMedicament(Long id);

    void deleteMedicamentByIdMedicament(Long id);
}
