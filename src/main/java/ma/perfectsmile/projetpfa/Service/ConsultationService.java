package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Consultation;
import ma.perfectsmile.projetpfa.Model.Facture;
import ma.perfectsmile.projetpfa.Model.Patient;
import ma.perfectsmile.projetpfa.Model.SituationFinanciere;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ConsultationService {
    void create(Consultation consultation);

    List<Consultation> getConsultations();

    Consultation getConsultation(Long id);

    void updateConsultation(Consultation consultation);

    void deleteByIdConsultation(Long id);

    Consultation findByIdConsultation(Long id);

    Facture ajouteFacture(Facture facture, Long idconsultation, Patient patient);

    void save(Consultation consultation);

    Page<Consultation> findByMotifContains(String keyword, PageRequest of);

    SituationFinanciere ajouterSituationFinanciere(SituationFinanciere sf, Long idconsultation, Patient patient);
}
