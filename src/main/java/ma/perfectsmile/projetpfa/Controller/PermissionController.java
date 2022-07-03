package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.Permission;
import ma.perfectsmile.projetpfa.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private PermissionRepository permissionrepository;

    @GetMapping(path = "/")
    public String permissions(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Permission> permissions = permissionrepository.findByNomContains(keyword, PageRequest.of(page, size));

        System.out.println("Permissions : " + permissions.getContent());

        model.addAttribute("permissions", permissions.getContent());
        model.addAttribute("pages", new int[permissions.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "permission/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        permissionrepository.deleteById(id);
        return "redirect:/permission/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("permission", new Permission());

        return "permission/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid Permission permission, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (bindingresult.hasErrors()) return "permission/save";

        System.out.println(permission.getNom() + " => id: " + permission.getId_Permission()); // id = null ???!!!

        permissionrepository.save(permission);
        return "redirect:/permission/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String editPermission(Model model, @PathVariable("id") Long id, String keyword, int page) {
        //Permission permission= permissionrepository.findById(id).get();
        Permission permission = permissionrepository.findById(id).orElse(null);
        if (permission == null) throw new RuntimeException("Page introuvable a sadi9");
        model.addAttribute("permission", permission);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "permission/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid Permission permission, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (bindingresult.hasErrors()) return "permission/save";
        Permission permission2 = permissionrepository.findById(id).orElse(null);
        permission.setId_Permission(permission2.getId_Permission());
        permissionrepository.save(permission);
        System.out.println(permission.getNom() + " => id: " + permission.getId_Permission()); // id = null ???!!!
        return "redirect:/permission/?page=" + page + "&keyword=" + keyword;

    }

}
 