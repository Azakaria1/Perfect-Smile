package ma.perfectsmile.projetpfa.repositories;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.perfectsmile.projetpfa.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findDistinctByIdRole(Long roleName);

    Role findDistinctByNom(@NotNull @NotBlank @Length(min = 5) String nom);
    Role findDistinctByNomContains(String roleName);

    Page<Role> findByNomContains(String mot, PageRequest pageable);

}