package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.Specialite;
import ma.perfectsmile.projetpfa.Service.SpecialiteServiceImpl;
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
@RequestMapping("specialite")
public class SpecialiteController {

    @Autowired
    private SpecialiteServiceImpl specialiteService;

    @GetMapping("/")
    public String specialites(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Specialite> specialites = specialiteService.findByNomContains(keyword, PageRequest.of(page, size));

        System.out.println("Specialites : " + specialites.getContent());

        model.addAttribute("specialites", specialites.getContent());
        model.addAttribute("pages", new int[specialites.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "specialite/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        specialiteService.deleteByIdSpecialite(id);
        return "redirect:/specialite/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("specialite", new Specialite());

        return "specialite/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid Specialite specialite, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (bindingresult.hasErrors()) return "specialite/save";

        System.out.println(specialite.getNom() + " => id: " + specialite.getIdSpecialite()); // id = null ???!!!
        specialiteService.save(specialite);
        return "redirect:/specialite/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String editSpecialite(Model model, @PathVariable("id") Long id, String keyword, int page) {
        // Specialite specialite= specialiteService.findById(id).get();
        Specialite specialite = specialiteService.findByIdSpecialite(id);
        if (specialite == null) throw new RuntimeException("Page introuvable a sadi9");

        model.addAttribute("specialite", specialite);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "specialite/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid Specialite specialite, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (bindingresult.hasErrors()) return "specialite/save";
        Specialite specialite2 = specialiteService.findByIdSpecialite(id);
        specialite.setIdSpecialite(specialite2.getIdSpecialite());
        specialiteService.save(specialite);
        return "redirect:/specialite/?page=" + page + "&keyword=" + keyword;
    }
}