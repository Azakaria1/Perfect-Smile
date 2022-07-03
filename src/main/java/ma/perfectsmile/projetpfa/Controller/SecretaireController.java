package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.Secretaire;
import ma.perfectsmile.projetpfa.Service.SecretaireServiceImpl;
import ma.perfectsmile.projetpfa.Service.RoleServiceImpl;
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

@Controller
@RequestMapping("secretaire")
public class SecretaireController {

    @Autowired
    private SecretaireServiceImpl secretaireService ;

    PasswordEncoder passwordEncoder = encoder();

    @Autowired
    private RoleServiceImpl roleService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping(path = "/")
    public String secretaires(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Secretaire> pagesecretairesParNom = secretaireService.findByNomContains(keyword, PageRequest.of(page, size));
        Page<Secretaire> pagesecretairesParPrenom = secretaireService.findByPrenomContains(keyword, PageRequest.of(page, size));

        Page<Secretaire> secretaires = pagesecretairesParNom.isEmpty() ? pagesecretairesParPrenom : pagesecretairesParNom;

        System.out.println("Secretaires : " + secretaires.getContent());

        model.addAttribute("secretaire", secretaires.getContent());
        model.addAttribute("pages", new int[secretaires.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "secretaire/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        secretaireService.deleteById(id);
        return "redirect:/secretaire/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("secretaire", new Secretaire());

        return "secretaire/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid Secretaire secretaire, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) return "secretaire/save";
        secretaire.setPassword(passwordEncoder.encode(secretaire.getPassword()));
        secretaire.getRoles().add(roleService.findDistinctByNom("Médecin"));
        secretaireService.ajouterSecretaire(secretaire);

        return "redirect:/secretaire/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String edit(Model model, @PathVariable Long id, String keyword, int page) {

        if (secretaireService.findDistinctByIdUtilisateur(id) == null)
            throw new RuntimeException("Page introuvable !");

        model.addAttribute("secretaire", secretaireService.findDistinctByIdUtilisateur(id));
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);

        return "secretaire/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid Secretaire secretaire, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) {
            System.err.println("kayna erreur f update !");
            return "secretaire/save";
        }
        System.out.println("Id transmis >" + id);
        Secretaire secretaire1 = secretaireService.findDistinctByIdUtilisateur(id);
        secretaire.setIdUtilisateur(secretaire1.getIdUtilisateur());
        secretaire.setVersion(secretaire1.getVersion());
        System.out.println("Secretaire à modifier => " + secretaire);

        secretaireService.ajouterSecretaire(secretaire);

        return "redirect:/secretaire/?page=" + page + "&keyword=" + keyword;

    }
}
