package ma.perfectsmile.projetpfa.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Facture;
import ma.perfectsmile.projetpfa.Model.Patient;
import ma.perfectsmile.projetpfa.Model.SituationFinanciere;
import ma.perfectsmile.projetpfa.repositories.SituationFinanciereRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class SituationfinanciereServiceImpl implements SituationFinanciereService {

    private final SituationFinanciereRepository situationFinanciereRepo;
    private final FactureServiceImpl factureService;

    @Override
    public SituationFinanciere ajoutSituationFinanciere(Patient patient, SituationFinanciere situationFinanciere) {
        log.info("ajout d'une situation financière ");
        situationFinanciere.setPatient(patient);
        return situationFinanciereRepo.save(situationFinanciere);
    }

    @Override
    public List<SituationFinanciere> getAllSituationFinanciere() {
        log.info("recherche toutes les Situations financieres ");
        return situationFinanciereRepo.findAll();
    }

    @Override
    public SituationFinanciere getSituationFinanciere(Long id) {
        log.info("recherche Situation financière : {}", id);
        return situationFinanciereRepo.findDistinctByIdSituation(id);
    }

    @Override
    public void modifierStuationFinanciere(Patient patient, SituationFinanciere situationFinanciere) {
        log.info("modification de la situation financière numéro : {} ", situationFinanciere.getIdSituation());
        situationFinanciere.setPatient(patient);
        situationFinanciereRepo.save(situationFinanciere);
    }

    @Override
    public void supprimerSituationFinanciere(Patient patient) {
        log.info("suppression de la Situation financière");
        patient.setSituationFinanciere(new SituationFinanciere());
    }

    @Override
    public void addFactureToPatient(Patient patient, Facture facture) {
        patient.getSituationFinanciere().getFactures().add(facture);
    }

    @Override
    public void updatePatientFacture(Patient patient, Facture facture) {
        List<Facture> factures = patient.getSituationFinanciere().getFactures();
        for (Facture f : factures) {
            if (f.getIdfacture().equals(facture.getIdfacture())) factureService.update(f);
        }
    }
}
