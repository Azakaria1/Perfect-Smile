package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.Assistant;
import ma.perfectsmile.projetpfa.Service.AssistantServiceImpl;
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
@RequestMapping("assistant")
public class AssistantController {

    @Autowired
    private AssistantServiceImpl assistantService ;

    PasswordEncoder passwordEncoder = encoder();

    @Autowired
    private RoleServiceImpl roleService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping(path = "/")
    public String assistants(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Assistant> pageassistantsParNom = assistantService.findByNomContains(keyword, PageRequest.of(page, size));
        Page<Assistant> pageassistantsParPrenom = assistantService.findByPrenomContains(keyword, PageRequest.of(page, size));

        Page<Assistant> assistants = pageassistantsParNom.isEmpty() ? pageassistantsParPrenom : pageassistantsParNom;

        System.out.println("Assistants : " + assistants.getContent());

        model.addAttribute("assistant", assistants.getContent());
        model.addAttribute("pages", new int[assistants.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "assistant/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        assistantService.deleteById(id);
        return "redirect:/assistant/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("assistant", new Assistant());

        return "assistant/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid Assistant assistant, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) return "assistant/save";
        assistant.setPassword(passwordEncoder.encode(assistant.getPassword()));
        assistant.getRoles().add(roleService.findDistinctByNom("Assistant"));
        assistantService.ajouterAssistant(assistant);

        return "redirect:/assistant/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String edit(Model model, @PathVariable Long id, String keyword, int page) {

        if (assistantService.findDistinctByIdUtilisateur(id) == null)
            throw new RuntimeException("Page introuvable !");

        model.addAttribute("assistant", assistantService.findDistinctByIdUtilisateur(id));
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);

        return "assistant/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid Assistant assistant, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) {
            System.err.println("kayna erreur f update !");
            return "assistant/save";
        }
        System.out.println("Id transmis >" + id);
        Assistant assistant1 = assistantService.findDistinctByIdUtilisateur(id);
        assistant.setIdUtilisateur(assistant1.getIdUtilisateur());
        assistant.setVersion(assistant1.getVersion());
        System.out.println("Assistant à modifier => " + assistant);

        assistantService.ajouterAssistant(assistant);

        return "redirect:/assistant/?page=" + page + "&keyword=" + keyword;

    }
}
