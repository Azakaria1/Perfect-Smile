package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.DossierMedical;
import ma.perfectsmile.projetpfa.Model.Role;
import ma.perfectsmile.projetpfa.Model.Utilisateur;
import ma.perfectsmile.projetpfa.Service.DossierMedicalServiceImpl;
import ma.perfectsmile.projetpfa.Service.UtilisateurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("dossier")
public class DossierMedicalController {

    @Autowired
    private DossierMedicalServiceImpl dossierMedicalService;

    @Autowired
    private UtilisateurServiceImpl utilisateurService;

    @GetMapping(path = "/index")
    public String dossierMedicaux(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        Page<DossierMedical> dossierMedicalParAllergie = dossierMedicalService.findByAllergieContainsIgnoreCase(keyword, PageRequest.of(page, size));
        Page<DossierMedical> dossierMedicalParMaladie = dossierMedicalService.findByAllergieContainsIgnoreCase(keyword, PageRequest.of(page, size));
        Page<DossierMedical> dossierMedicalParTypeMaladie = dossierMedicalService.findByAllergieContainsIgnoreCase(keyword, PageRequest.of(page, size));
        Page<DossierMedical> dossierMedicalParTraitement = dossierMedicalService.findByAllergieContainsIgnoreCase(keyword, PageRequest.of(page, size));

        Page<DossierMedical> dossierMedical1 = dossierMedicalParAllergie.isEmpty() ? dossierMedicalParTraitement : dossierMedicalParAllergie;
        Page<DossierMedical> dossierMedical2 = dossierMedicalParMaladie.isEmpty() ? dossierMedicalParTypeMaladie : dossierMedicalParAllergie;

        Page<DossierMedical> dossierMedicals = dossierMedical1.isEmpty() ? dossierMedical2 : dossierMedical1;

        System.out.println("Dossiers médicaux : " + dossierMedicals.getContent());

        model.addAttribute("dossierMedical", dossierMedicals.getContent());
        model.addAttribute("pages", new int[dossierMedicals.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "dossierMedical/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        dossierMedicalService.deleteByIdDossierMedical(id);
        return "redirect:/dossierMedical/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {

        List<Utilisateur> patients = new ArrayList<>();
        for (Utilisateur ut : utilisateurService.getUtilisateurs()) {
            for (Role role : ut.getRoles()) {
                if (role.getNom().equals("Patient")) {
                    patients.add(ut);
                }
            }
        }
        model.addAttribute("dossierMedical", new DossierMedical());
        model.addAttribute("patients", patients);

        return "dossierMedical/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid DossierMedical dossierMedical, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) return "dossierMedical/save";

        dossierMedicalService.save(dossierMedical);

        return "redirect:/dossier/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String editDossierMedical(Model model, @PathVariable Long id, String keyword, int page) {

        if (dossierMedicalService.findByIdDossierMedical(id) == null)
            throw new RuntimeException("Page introuvable a sadi9");

        model.addAttribute("dossierMedical", dossierMedicalService.findByIdDossierMedical(id));
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "dossierMedical/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid DossierMedical dossierMedical, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) return "dossierMedical/save";
        DossierMedical dossierMedical1 = dossierMedicalService.findByIdDossierMedical(id);
        dossierMedical.setIdDossierMedical(dossierMedical1.getIdDossierMedical());
        dossierMedicalService.save(dossierMedical);
        //      dossierMedicalService.update(dossierMedical);
        return "redirect:/dossier/index?page=" + page + "&keyword=" + keyword;
    }

}
