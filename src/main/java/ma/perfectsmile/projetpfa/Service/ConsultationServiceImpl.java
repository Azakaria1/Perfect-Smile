package ma.perfectsmile.projetpfa.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.*;
import ma.perfectsmile.projetpfa.repositories.ConsultationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ConsultationServiceImpl implements ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final SituationfinanciereServiceImpl situationfinanciereService;
    private final InterventionServiceImpl interventionService;
    private final FactureServiceImpl factureService;

    @Override
    public void create(Consultation consultation) {
        log.info("enregistrement d'une nouvelle consultation : {}");
        consultationRepository.save(consultation);
    }

    @Override
    public List<Consultation> getConsultations() {
        log.info("recherche tous les Consultations");
        return consultationRepository.findAll();
    }

    @Override
    public Consultation getConsultation(Long id) {
        log.info("recherche Consultation numéro : {}", id);
        return consultationRepository.findDistinctByIdConsultation(id);
    }

    @Override
    public void updateConsultation(Consultation consultation) {
        log.info("mise à jour de la consultation numéro : {}", consultation.getIdConsultation());
        consultationRepository.save(consultation);
    }

    @Override
    public void deleteByIdConsultation(Long id) {
        log.info("suppression de la consultation numéro : {}", id);
        consultationRepository.deleteByIdConsultation(id);
    }

    @Override
    public Consultation findByIdConsultation(Long id) {
        log.info("suppression de la consultation numéro : {}", id);
        return consultationRepository.findByIdConsultation(id);
    }

    @Override
    public Facture ajouteFacture(Facture facture, Long idconsultation, Patient patient) {
        int i;
        Consultation consultation = consultationRepository.findByIdConsultation(idconsultation);
        List<Facture> factures = factureService.findAll();
        for (i = 0; i < factures.size(); i++) {
            Facture fact = factures.get(i);
            if (fact.getConsultation() == consultation) {
                SituationFinanciere sf = fact.getSituationFinanciere();
                facture.setSituationFinanciere(sf);
                facture.setPrixTotal(consultation.getPrix());
                sf.setPaye(sf.getPaye() + facture.getPrixTotal());

                // sf.setReste( sf.getTotal()- sf.getPaye() );
                situationfinanciereService.ajoutSituationFinanciere(patient, sf);
                factureService.save(facture);
                i = factures.size();
            }
        }
        return factureService.save(facture);
    }

    @Override
    public void save(Consultation consultation) {
        consultationRepository.save(consultation);
    }

    @Override
    public Page<Consultation> findByMotifContains(String keyword, PageRequest of) {
        return consultationRepository.findByMotifContains(keyword, of);
    }

    @Override
    public SituationFinanciere ajouterSituationFinanciere(SituationFinanciere sf , Long idconsultation,  Patient patient) {
        Long somme = 0L;
        Consultation consultation = consultationRepository.findByIdConsultation(idconsultation);

        List<Intervention> interventions = interventionService.findAll();

        List<Intervention> intervention = interventionService.findAll();

        for (int i = 0; i < interventions.size(); i++) {
            Intervention intervention2 = intervention.get(i);
            for (Consultation consultation1 : intervention2.getConsultations()) {
                if (consultation1.equals(consultation)) somme += (intervention2.getPrix());
            }
        }

        sf.setTotal(somme);
        sf.setReste((sf.getTotal()) - (sf.getPaye()));

        Facture facture = new Facture();
        facture.setConsultation(consultation);
        facture.setSituationFinanciere(sf);
        facture.setPrixTotal(sf.getPaye());
        facture.setDate_paiement(consultation.getDate_consultation());

        factureService.save(facture);

        return situationfinanciereService.ajoutSituationFinanciere(patient, sf);
    }
}
