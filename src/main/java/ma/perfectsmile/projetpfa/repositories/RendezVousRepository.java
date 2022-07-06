package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.RendezVous;
import ma.perfectsmile.projetpfa.Model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {

 @Transactional
 @Modifying
 @Query("update RendezVous rv set rv.statut = ?1,rv.motif = ?2, rv.HeureRDV = ?3, rv.dateRDV = ?4 ,rv.patient =?5 where rv.idRDV = ?6")
 int update(String statut, String motif, LocalTime heureRDV, LocalDate dateRDV, @NotNull Utilisateur patient, Long id);

 Page<RendezVous>  findByMotifContains(String kw , Pageable pageeable );

 @Query("select count(rdv)>0 from RendezVous rdv where rdv.motif like :m or rdv.statut like :m")
 boolean existsByStatutOrMotif(@Param("m") String StatutOuMotif  );

 RendezVous findDistinctByIdRDV(Long id);


 void deleteByIdRDV(Long id);

}