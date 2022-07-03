package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Consultation;
import ma.perfectsmile.projetpfa.Model.Facture;
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

    void ajouterFacture(Facture facture, Consultation consultation);

    void save(Consultation consultation);

    Page<Consultation> findByMotifContains(String keyword, PageRequest of);
}
