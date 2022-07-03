package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Acte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ActeRepository extends JpaRepository<Acte, Long> {

    Acte findDistinctByIdActe(Long id);

    void deleteByIdActe(Long id);

    Page<Acte> findByLibelleContains(String acteName, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Acte acte set acte.libelle =?1 where acte.idActe =?2")
    void update(String nom, Long id);

    Page<Acte> findByPrixContains(String prix, Pageable pageable);
}