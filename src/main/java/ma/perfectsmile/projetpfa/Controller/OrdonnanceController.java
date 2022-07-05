package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.Ordonnance;
import ma.perfectsmile.projetpfa.Service.ConsultationServiceImpl;
import ma.perfectsmile.projetpfa.Service.MedicamentServiceImpl;
import ma.perfectsmile.projetpfa.Service.OrdonnanceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/ordonnance")
public class OrdonnanceController {

    @Autowired
    private OrdonnanceServiceImpl ordonnanceService;

    @Autowired
    private ConsultationServiceImpl consultationService;

    @Autowired
    private MedicamentServiceImpl medicamentService;

    @GetMapping(path = "/")
    public String ordonnances(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Ordonnance> ordonnances = ordonnanceService.findByDescriptionContains(keyword, PageRequest.of(page, size));

        System.out.println("Ordonnances : " + ordonnances.getContent());

        model.addAttribute("ordonnances", ordonnances.getContent());
        model.addAttribute("pages", new int[ordonnances.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "ordonnance/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        ordonnanceService.deleteByIdOrdonnance(id);
        return "redirect:/ordonnance/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add/{id}")
    public String add(Model model, @PathVariable("id") Long id) {

        model.addAttribute("ordonnance", new Ordonnance());
        model.addAttribute("medicaments", medicamentService.findAll());

        return "ordonnance/save";
    }

    @PostMapping(path = "/save/{id}")
    public String save(@Valid Ordonnance ordonnance, BindingResult bindingresult, @PathVariable("id") Long id,@RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) return "ordonnance/save";

        System.err.println("Patient recherché => " + consultationService.findByIdConsultation(id).getRendezVous().getPatient());
        ordonnance.setPatient(consultationService.findByIdConsultation(id).getRendezVous().getPatient());

        ordonnanceService.save(ordonnance);


        return "redirect:/ordonnance/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String editOrdonnance(Model model, @PathVariable Long id, String keyword, int page) {

        if (ordonnanceService.findByIdOrdonnance(id) == null) throw new RuntimeException("Page introuvable a sadi9");

        model.addAttribute("ordonnance", ordonnanceService.findByIdOrdonnance(id));
        model.addAttribute("medicaments", medicamentService.findAll());
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "ordonnance/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid Ordonnance ordonnance, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) return "ordonnance/save";
        Ordonnance ordonnance1 = ordonnanceService.findByIdOrdonnance(id);
        ordonnance.setIdOrdonnance(ordonnance1.getIdOrdonnance());
        ordonnanceService.save(ordonnance);
        return "redirect:/ordonnance/?page=" + page + "&keyword=" + keyword;
    }
}