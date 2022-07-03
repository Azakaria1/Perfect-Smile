package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Permission;
import ma.perfectsmile.projetpfa.Model.Role;
import ma.perfectsmile.projetpfa.repositories.PermissionRepository;
import ma.perfectsmile.projetpfa.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Permission ajouterPermission(Permission permission) {
        return permissionRepository.saveAndFlush(permission);
    }

    @Override
    public Permission modifierPermission(Permission permission) {
        return permissionRepository.saveAndFlush(permission);
    }

    @Override
    public void supprimerPermission(Permission permission) {
        permissionRepository.delete(permission);
    }

    @Override
    public void addPermissionToRole(String rolename, String permissionName) {
     //   getRole(rolename).getPermissionList().add(getPermission(permissionName));
    }

    @Override
    public void modifyRolePermission(String rolename, String permissionName) {
      // getRole(rolename).getPermissionList();
    }
    public  Role findById(Long id)
    {
        return roleRepository.findById(id).orElse(null);
    }
    @Override
    public void deletePermissionFromRole(String rolename, String permissionName) {
       /* List<Permission> permissionList = getRole(rolename).getPermissionList();
        Permission permission = new Permission();
        for (Permission p : permissionList) {
            permission = p.getNom().equals(permissionName) ? p : null;
        }
        permissionList.remove(permission);
*/
    }

    @Override
    public Role getRole(String rolename) {
        return roleRepository.findDistinctByNomContains(rolename);
    }

    @Override
    public Permission getPermission(String permissionName) {
        return permissionRepository.findDistinctByNomContains(permissionName);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Page<Role> findByNomContains(String mot, PageRequest pageable) {
        return roleRepository.findByNomContains(mot, pageable);
    }

    public Role findDistinctByIdRole(Long id) {
        return roleRepository.findDistinctByIdRole(id);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    public Role findDistinctByNom(String mot) {
        return roleRepository.findDistinctByNom(mot);
    }

    public void delete(Role role) {
        roleRepository.delete(role);
    }
}
