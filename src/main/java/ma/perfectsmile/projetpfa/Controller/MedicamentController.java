package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.Medicament;
import ma.perfectsmile.projetpfa.Service.MedicamentServiceImpl;
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
@RequestMapping("medicament")
public class MedicamentController {

    @Autowired
    private MedicamentServiceImpl medicamentService;


    @GetMapping(path = "/")
    public String medicaments(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Medicament> medicamentsParNom = medicamentService.findByNomContains(keyword, PageRequest.of(page, size));
        Page<Medicament> medicamentsParDescription = medicamentService.findByDescriptionContains(keyword, PageRequest.of(page, size));
        Page<Medicament> medicaments = medicamentsParNom.isEmpty() ? medicamentsParDescription : medicamentsParNom;
/*
        Page<Medicament> medicamentsPrix = medicamentService.findByPrixContains(keyword, PageRequest.of(page, size));
*/

        System.out.println("Medicaments : " + medicaments.getContent());

        model.addAttribute("medicaments", medicaments.getContent());
        model.addAttribute("pages", new int[medicaments.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "medicament/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        medicamentService.delete(id);
        return "redirect:/medicament/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("medicament", new Medicament());

        return "medicament/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid Medicament medicament, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (bindingresult.hasErrors()) return "medicament/save";

        System.out.println(medicament.getNom() + " => id: " + medicament.getIdMedicament()); // id = null ???!!!
        medicamentService.save(medicament);
        return "redirect:/medicament/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String editMedicament(Model model, @PathVariable("id") Long id, String keyword, int page) {
        // Medicament medicament= medicamentService.findById(id).get();
        Medicament medicament = medicamentService.findByIdMedicament(id);
        if (medicament == null) throw new RuntimeException("Page introuvable a sadi9");

        model.addAttribute("medicament", medicament);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "medicament/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid Medicament medicament, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (bindingresult.hasErrors()) return "medicament/save";
        Medicament medicament2 = medicamentService.findByIdMedicament(id);
        medicament.setIdMedicament(medicament2.getIdMedicament());
        medicamentService.save(medicament);
        return "redirect:/medicament/?page=" + page + "&keyword=" + keyword;

    }
}