package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Specialite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SpecialiteService {

    Specialite save(Specialite acte);

    List<Specialite> findAll();

    Specialite getSpecialite(Long id);

    Page<Specialite> findByNomContains(String keyword, PageRequest of);

    void deleteByIdSpecialite(Long id);

    Specialite findByIdSpecialite(Long id);
}