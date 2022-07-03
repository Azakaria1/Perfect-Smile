package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.Specialite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {

    Specialite findDistinctByIdSpecialite(Long id);
    void deleteByIdSpecialite(Long id);
    Page<Specialite> findByNomContains(String acteName, Pageable pageable);
}