package ma.perfectsmile.projetpfa.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.Consultation;
import ma.perfectsmile.projetpfa.Model.Facture;
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

    private final ConsultationRepository consulRepo;
    @Override
    public void create(Consultation consultation) {
        log.info("enregistrement d'une nouvelle consultation : {}");
        consulRepo.saveAndFlush(consultation);
    }

    @Override
    public List<Consultation> getConsultations() {
        log.info("recherche tous les Consultations");
        return consulRepo.findAll();
    }

    @Override
    public Consultation getConsultation(Long id) {
        log.info("recherche Consultation numéro : {}", id);
        return consulRepo.findDistinctByIdConsultation(id);
    }

    @Override
    public void updateConsultation(Consultation consultation) {
        log.info("mise à jour de la consultation numéro : {}", consultation.getIdConsultation());
        consulRepo.saveAndFlush(consultation);
    }

    @Override
    public void deleteByIdConsultation(Long id) {
        log.info("suppression de la consultation numéro : {}", id);
        consulRepo.deleteByIdConsultation(id);
    }
    @Override
    public Consultation findByIdConsultation(Long id) {
        log.info("suppression de la consultation numéro : {}", id);
        return consulRepo.findByIdConsultation(id);
    }

    @Override
    public void ajouterFacture(Facture facture, Consultation consultation) {
        consultation.getFactures().add(facture);
    }

    @Override
    public void save(Consultation consultation) {
        consulRepo.save(consultation);
    }

    @Override
    public Page<Consultation> findByMotifContains(String keyword, PageRequest of) {
        return consulRepo.findByMotifContains(keyword,of);
    }
}
