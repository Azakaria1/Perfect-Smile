package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Permission;
import ma.perfectsmile.projetpfa.Model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    Permission ajouterPermission(Permission permission);
    Permission modifierPermission(Permission permission);
    void supprimerPermission(Permission permission);
    void addPermissionToRole(String rolename, String permissionName);
    void deletePermissionFromRole(String username, String rolename);
    void modifyRolePermission(String rolename, String permissionNQame);
    Role getRole(String rolename);
    Permission getPermission(String rolename);
    List<Role> getRoles();
    List<Permission> getPermissions();

    Page<Role> findByNomContains(String mot, PageRequest pageable);

    void deleteById(Long id);
}
