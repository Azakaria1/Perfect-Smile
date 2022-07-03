package ma.perfectsmile.projetpfa.repositories;


import ma.perfectsmile.projetpfa.Model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Page<Permission> findByNomContains(String permissionName, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Permission permission set permission.nom =?1 where permission.id_Permission =?2")
    void update(String nom, Long id);

    Permission findDistinctByNomContains(String permissionName);
}