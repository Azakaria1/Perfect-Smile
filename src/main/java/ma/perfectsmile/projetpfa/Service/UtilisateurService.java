package ma.perfectsmile.projetpfa.Service;


import ma.perfectsmile.projetpfa.Model.Role;
import ma.perfectsmile.projetpfa.Model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UtilisateurService {


    Utilisateur ajouterUtilisateur(Utilisateur utilisateur);

    void supprimerUtilisateur(Utilisateur utilisateur);

    List<Utilisateur> getUtilisateurs();

    void modifierUtilisateur(Utilisateur utilisateur);

    Role ajouterRole(Role role);

    void supprimerRole(Role role);

    void modifierRole(Role role);

    void addRoleToUser(Long idUtilisateur, Long idRole);

    void addRolesToUser(Long idUtilisateur, List<Role> roleList);

    void updateUserRole(Long idUtilisateur, Long idRole);

    void deleteRoleFromUser(Long idUtilisateur, Long idRole);

    Role getRole(Long idRole);

    List<Role> getRoles();

    // @Query("from Patient p where p.nom like :m or p.prenom like :m")
    Page<Utilisateur> findByNomContains(String mot, Pageable pageable);

    Page<Utilisateur> findByPrenomContains(String mot, Pageable pageable);

    Utilisateur findDistinctByIdUtilisateur(Long id);

    boolean existsByEmail(String email);

    void deleteById(Long id);

    void save(Utilisateur utilisateur);

    Utilisateur register(Utilisateur utilisateur);


    Utilisateur findByNom(String name);

    Utilisateur findByUsername(String first, String second);
}
