package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.*;
import ma.perfectsmile.projetpfa.Service.ActeServiceImpl;
import ma.perfectsmile.projetpfa.Service.ConsultationServiceImpl;
import ma.perfectsmile.projetpfa.Service.InterventionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("intervention")
public class InterventionController {

    @Autowired
    private InterventionServiceImpl interventionService;

    @Autowired
    private ActeServiceImpl acteService;

    @Autowired
    private ConsultationServiceImpl consultationService;


    @GetMapping(path = "/index")
    public String interventions(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "0") int keyword) {
        Page<Intervention> interventions = interventionService.findAll(PageRequest.of(page, size));

        System.out.println("Interventions : " + interventions.getContent());

        model.addAttribute("interventions", interventions.getContent());
        model.addAttribute("pages", new int[interventions.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "intervention/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        interventionService.deleteByIdIntervention(id);
        return "redirect:/intervention/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("intervention", new Intervention());

        List<Acte> actes = acteService.findAll();
        List<Consultation> consultations = consultationService.getConsultations();
        model.addAttribute("consultations", consultations);
        model.addAttribute("actes", actes);

        return "intervention/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid Intervention intervention, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (bindingresult.hasErrors()) return "intervention/save";

        System.out.println(intervention.getNbDent() + " => id: " + intervention.getIdIntervention()); // id = null ???!!!

        interventionService.save(intervention);
        return "redirect:/intervention/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String editIntervention(Model model, @PathVariable("id") Long id, String keyword, int page) {
        //Intervention intervention= interventionService.findById(id).get();
        Intervention intervention = interventionService.findByIdIntervention(id);
        if (intervention == null) throw new RuntimeException("Page introuvable a sadi9");
        model.addAttribute("intervention", intervention);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "intervention/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid Intervention intervention, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (bindingresult.hasErrors()) return "intervention/save";
        Intervention intervention2 = interventionService.findByIdIntervention(id);
        intervention.setIdIntervention(intervention2.getIdIntervention());
        interventionService.save(intervention);
        return "redirect:/intervention/index?page=" + page + "&keyword=" + keyword;

    }

}
 