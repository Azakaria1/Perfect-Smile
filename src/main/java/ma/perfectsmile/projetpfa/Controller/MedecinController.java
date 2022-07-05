package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.Medecin;
import ma.perfectsmile.projetpfa.Model.Role;
import ma.perfectsmile.projetpfa.Service.MedecinServiceImpl;
import ma.perfectsmile.projetpfa.Service.RoleServiceImpl;
import ma.perfectsmile.projetpfa.Service.SpecialiteServiceImpl;
import ma.perfectsmile.projetpfa.Service.UtilisateurServiceImpl;
import ma.perfectsmile.projetpfa.repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("medecin")
public class MedecinController {

    @Autowired
    private MedecinServiceImpl medecinService ;

    @Autowired
    private SpecialiteServiceImpl specialiteService;

    PasswordEncoder passwordEncoder = encoder();

    @Autowired
    private RoleServiceImpl roleService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping
    public String medecins(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Medecin> pagemedecinsParNom = medecinService.findByNomContains(keyword, PageRequest.of(page, size));
        Page<Medecin> pagemedecinsParPrenom = medecinService.findByPrenomContains(keyword, PageRequest.of(page, size));

        Page<Medecin> medecins = pagemedecinsParNom.isEmpty() ? pagemedecinsParPrenom : pagemedecinsParNom;

        System.out.println("Medecins : " + medecins.getContent());

        model.addAttribute("medecin", medecins.getContent());
        model.addAttribute("pages", new int[medecins.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "medecin/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        medecinService.deleteById(id);
        return "redirect:/medecin/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {

        model.addAttribute("medecin", new Medecin());
        model.addAttribute("specialites", specialiteService.findAll());

        return "medecin/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid Medecin medecin, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) return "medecin/save";
        medecin.setPassword(passwordEncoder.encode(medecin.getPassword()));
        medecin.getRoles().add(roleService.findDistinctByNom("Médecin"));
        medecinService.ajouterMedecin(medecin);

        return "redirect:/medecin/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String edit(Model model, @PathVariable Long id, String keyword, int page) {

        if (medecinService.findDistinctByIdUtilisateur(id) == null)
            throw new RuntimeException("Page introuvable !");

        model.addAttribute("medecin", medecinService.findDistinctByIdUtilisateur(id));
        model.addAttribute("specialites", specialiteService.findAll());
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);

        return "medecin/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid Medecin medecin, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) {
            return "medecin/save";
        }
        System.out.println("Id transmis >" + id);
        Medecin medecin1 = medecinService.findDistinctByIdUtilisateur(id);
        medecin.setIdUtilisateur(medecin1.getIdUtilisateur());
        medecin.setVersion(medecin1.getVersion());
        System.out.println("Medecin à modifier => " + medecin);

        medecinService.ajouterMedecin(medecin);

        return "redirect:/medecin?page=" + page + "&keyword=" + keyword;

    }
}
