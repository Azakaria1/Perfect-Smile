package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Acte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface ActeService {

    Acte save(Acte acte);

    List<Acte> findAll();

    Acte getActe(Long id);

    Page<Acte> findByLibelleContains(String keyword, PageRequest of);

    void deleteByIdActe(Long id);

    Acte findByIdActe(Long id);

    Page<Acte> findByPrixContains(String prix, PageRequest of);
}
