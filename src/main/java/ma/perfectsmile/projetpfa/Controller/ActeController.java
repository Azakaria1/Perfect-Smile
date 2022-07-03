package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.Acte;
import ma.perfectsmile.projetpfa.Service.ActeServiceImpl;
import ma.perfectsmile.projetpfa.Service.InterventionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("acte")
public class ActeController {

    @Autowired
    private ActeServiceImpl acteService;

    @Autowired
    private InterventionServiceImpl interventionService;

    @GetMapping(path = "/")
    public String actes(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Acte> actes = acteService.findByLibelleContains(keyword, PageRequest.of(page, size));

/*
        Page<Acte> actesPrix = acteService.findByPrixContains(keyword, PageRequest.of(page, size));
*/

        System.out.println("Actes : " + actes.getContent());

        model.addAttribute("actes", actes.getContent());
        model.addAttribute("pages", new int[actes.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "acte/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        acteService.deleteByIdActe(id);
        return "redirect:/acte/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("acte", new Acte());
        model.addAttribute("interventions", interventionService.findAll());

        return "acte/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid Acte acte, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (bindingresult.hasErrors()) return "acte/save";

        System.out.println(acte.getLibelle() + " => id: " + acte.getIdActe()); // id = null ???!!!
        acteService.save(acte);
        return "redirect:/acte/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String editActe(Model model, @PathVariable("id") Long id, String keyword, int page) {
        // Acte acte= acteService.findById(id).get();
        Acte acte = acteService.findByIdActe(id);
        if (acte == null) throw new RuntimeException("Page introuvable a sadi9");

        model.addAttribute("acte", acte);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "acte/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid Acte acte, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (bindingresult.hasErrors()) return "acte/save";
        Acte acte2 = acteService.findByIdActe(id);
        acte.setIdActe(acte2.getIdActe());
        acteService.save(acte);
        return "redirect:/acte/?page=" + page + "&keyword=" + keyword;

    }

}
 