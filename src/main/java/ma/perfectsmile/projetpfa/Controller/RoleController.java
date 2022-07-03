package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.Role;
import ma.perfectsmile.projetpfa.Service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping(path = "/")
    public String roles(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Role> roles = roleService.findByNomContains(keyword, PageRequest.of(page, size));

        System.out.println("Rôles : " + roles.getContent());

        model.addAttribute("roles", roles.getContent());
        model.addAttribute("pages", new int[roles.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "role/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        roleService.deleteById(id);
        return "redirect:/role/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("permissions", roleService.getPermissions());

        return "role/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid Role role, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) return "role/save";

        roleService.save(role);

        return "redirect:/role/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String editRole(Model model, @PathVariable Long id, String keyword, int page) {

        if (roleService.findById(id) == null) throw new RuntimeException("Page introuvable a sadi9");

        model.addAttribute("role", roleService.findDistinctByIdRole(id));
        model.addAttribute("permissions", roleService.getPermissions());
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "role/edit";
    }
    @PostMapping(path = "/update/{id}")
    public String update(@Valid Role role, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) return "role/save";
        Role role1 = roleService.findById(id);
        role.setIdRole(role1.getIdRole());
        roleService.save(role);
        //      roleService.update(role);
        return "redirect:/role/?page=" + page + "&keyword=" + keyword;
    }
}