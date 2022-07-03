package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.Facture;
import ma.perfectsmile.projetpfa.Service.ConsultationServiceImpl;
import ma.perfectsmile.projetpfa.Service.FactureServiceImpl;
import ma.perfectsmile.projetpfa.Service.SituationfinanciereServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("facture")
public class FactureController {

    @Autowired
    private ConsultationServiceImpl consultationService;

    @Autowired
    private FactureServiceImpl factureService;

    @Autowired
    private SituationfinanciereServiceImpl situationfinanciereService;


    @GetMapping(path = "/index")
    public String factures(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Facture> pagefactures = new PageImpl<>(factureService.findAll());

        model.addAttribute("factures", pagefactures.getContent());
        model.addAttribute("pages", new int[pagefactures.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "facture/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        factureService.deleteByIdfacture(id);
        return "redirect:/facture/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/")
    public String home() {
        return "redirect:/facture/index";
    }

    @GetMapping(path = "/add")
    public String add(Model model) {

        model.addAttribute("facture", new Facture());
        model.addAttribute("consultations", consultationService.getConsultations());
/*
        model.addAttribute("situationfinanciere", situationfinanciereService.getAllSituationFinanciere());
*/

        return "facture/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid Facture facture, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page, Principal principal) {
        if (bindingresult.hasErrors()) {
            bindingresult.getAllErrors().forEach(System.err::println);
            return "redirect:/facture/add";
        }

        factureService.save(facture);
        System.out.println("Montant de la facture: " + facture.getPrixTotal());

        return "redirect:/facture/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String edit(Model model, @PathVariable Long id, String keyword, int page) {

        if (factureService.findDistinctByIdFacture(id) == null) throw new RuntimeException("Page introuvable a sadi9");

        model.addAttribute("facture", factureService.findDistinctByIdFacture(id));
        model.addAttribute("consultations", consultationService.getConsultations());
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
/*
        model.addAttribute("situationfinanciere", situationfinanciereService.getAllSituationFinanciere());
*/
        return "facture/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid Facture facture, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) {
            System.err.println("kayna erreur f update !");
            return "facture/save";
        }
        Facture facture1 = factureService.findDistinctByIdFacture(id);
        facture.setIdfacture(facture1.getIdfacture());
        System.out.println("Facture à modifier => " + facture);
        factureService.save(facture);

        return "redirect:/facture/index?page=" + page + "&keyword=" + keyword;

    }
}
