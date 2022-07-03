package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Medicament;

import java.util.List;

public interface MedicamentService {
    void addMedicament(Medicament medicament);

    List<Medicament> getMedicaments();

    Medicament getMedicament(Long id);

    Medicament update(Medicament medicament);

    Boolean deleteMedicament(Long id);
}
