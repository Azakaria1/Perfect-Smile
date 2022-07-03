package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    Consultation findDistinctByIdConsultation(Long id);
    Page<Consultation> findByMotifContains(String keyword, Pageable pageable);
    
    Page<Consultation> findByPrixContains(String keyword, Pageable pageable);
    void deleteByIdConsultation(Long id);

    Consultation findByIdConsultation(Long id);
}
