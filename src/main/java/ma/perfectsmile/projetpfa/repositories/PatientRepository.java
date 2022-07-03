package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Medecin;
import ma.perfectsmile.projetpfa.Model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNomContains(String mot, Pageable pageeable);
    Page<Patient> findByPrenomContains(String mot, Pageable pageable);


    Patient findDistinctByIdUtilisateur(Long id);
    boolean existsByEmail(String email);

}