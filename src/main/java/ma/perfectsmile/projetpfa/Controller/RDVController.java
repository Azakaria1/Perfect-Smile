package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.*;
import ma.perfectsmile.projetpfa.Service.ConsultationServiceImpl;
import ma.perfectsmile.projetpfa.Service.RendezVousServiceImpl;
import ma.perfectsmile.projetpfa.Service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("rdv")
public class RDVController {

    @Autowired
    private RendezVousServiceImpl rendezVousService;

    @Autowired
    private ConsultationServiceImpl consultationService;

    @Autowired
    private UtilisateurService utilisateurService;

    public Utilisateur currentUser(Principal principal) {

        String[] arrOfStr = principal.getName().split(" ");
        return utilisateurService.findByUsername(arrOfStr[0], arrOfStr[1]);
    }

    @GetMapping(path = "/index")
    public String rdvs(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<RendezVous> pagerdvs = rendezVousService.findByMotifContains(keyword, PageRequest.of(page, size));

        model.addAttribute("rdvs", pagerdvs.getContent());
        model.addAttribute("pages", new int[pagerdvs.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "rdv/index";
    }

    @GetMapping(path = "/")
    public String rdvsPatient(Model model, Principal principal, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        List<RendezVous> rendezVousList = new ArrayList<>();
        for (RendezVous rendezVous : rendezVousService.findAll()) {
            if (rendezVous.getPatient().equals(currentUser(principal)))
                rendezVousList.add(rendezVous);
            else if (rendezVous.getMedecin().equals(currentUser(principal)))
                rendezVousList.add(rendezVous);

        }
        Page<RendezVous> pagerdvs = new PageImpl<>(rendezVousList);


        model.addAttribute("rdvs", pagerdvs.getContent());
        model.addAttribute("pages", new int[pagerdvs.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "rdv/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        rendezVousService.deleteById(id);
        return "redirect:/rdv/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/addR/{id}")
    public String addParPatient(Model model, Principal principal, @PathVariable("id") Long id) {

        RendezVous rendezVous = new RendezVous();
        rendezVous.setMedecin((Medecin) utilisateurService.findDistinctByIdUtilisateur(id));
        System.err.println("Current User =>" +currentUser(principal));
        model.addAttribute("rendezVous", rendezVous);
        model.addAttribute("medecin", utilisateurService.findDistinctByIdUtilisateur(id));

        return "rdv/saveR";
    }

    @PostMapping(path = "/saveR")
    public String saveParPatient(@Valid RendezVous rendezVous, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page, Principal principal) {
        if (bindingresult.hasErrors()) {
            bindingresult.getAllErrors().forEach(System.err::println);
            return "redirect:/rdv/addR";
        }
        Patient patient = (Patient) currentUser(principal);
        rendezVous.setPatient(patient);
        rendezVous.setStatut("En attente");

        rendezVousService.save(rendezVous);

        return "redirect:/rdv/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {

        List<Utilisateur> patients = new ArrayList<>();
        List<Utilisateur> medecins = new ArrayList<>();
        for (Utilisateur ut : utilisateurService.getUtilisateurs()) {
            for (Role role : ut.getRoles()) {
                if (role.getNom().equals("Patient")) patients.add(ut);
                else if (role.getNom().equals("Médecin")) {
                    medecins.add(ut);
                }
            }
        }
        model.addAttribute("rendezVous", new RendezVous());
        model.addAttribute("medecins", medecins);
        model.addAttribute("patients", patients);

        return "rdv/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid RendezVous rdv, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page, Principal principal) {
        if (bindingresult.hasErrors()) {
            bindingresult.getAllErrors().forEach(System.err::println);
            return "redirect:/rdv/add";
        }

        for (Role role : currentUser(principal).getRoles()) {
            if (role.getNom().equals("Patient")) rdv.setPatient((Patient) currentUser(principal));
            else if (role.getNom().equals("Médecin")) {
                rdv.setStatut("Accepté");
                rdv.setMedecin((Medecin) currentUser(principal));
            }
        }
        rendezVousService.save(rdv);
        System.out.println("Motif du Rendez Vous" + rdv.getMotif());

        return "redirect:/rdv/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String editRDV(Model model, @PathVariable("id") Long id, String keyword, int page) {

        if (rendezVousService.findDistinctByIdRDV(id) == null) throw new RuntimeException("Page introuvable a sadi9");

        List<Utilisateur> patients = new ArrayList<>();
        List<Utilisateur> medecins = new ArrayList<>();
        for (Utilisateur ut : utilisateurService.getUtilisateurs()) {
            for (Role role : ut.getRoles()) {
                if (role.getNom().equals("Patient")) patients.add(ut);
                else if (role.getNom().equals("Médecin")) {
                    medecins.add(ut);
                }
            }
        }
        model.addAttribute("rdv", rendezVousService.findDistinctByIdRDV(id));
        model.addAttribute("medecins", medecins);
        model.addAttribute("patients", patients);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);

        return "rdv/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid RendezVous rendezVous, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) {
            System.err.println("kayna erreur f update !");
            return "rdv/save";
        }
        System.out.println("Id transmis >" + id);
        RendezVous rendezVous1 = rendezVousService.findDistinctByIdRDV(id);
        rendezVous.setIdRDV(rendezVous1.getIdRDV());
        System.out.println("RendezVous à modifier => " + rendezVous);
        rendezVousService.save(rendezVous);

        return "redirect:/rdv/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/validate/{id}")
    public String validate(String keyword, Principal principal, int page, @PathVariable("id") Long id) {
        if (rendezVousService.findDistinctByIdRDV(id) == null) throw new RuntimeException("Page introuvable a sadi9");

        RendezVous rendezVous = rendezVousService.findDistinctByIdRDV(id);
        System.out.println("Rendez Vous recherché => " + rendezVous.getMotif());
        rendezVous.setStatut("Accepté");
        rendezVousService.save(rendezVous);

        for (Role role : currentUser(principal).getRoles()) {
            if (role.getNom().equals("Médecin")) return "redirect:/rdv/?page=" + page + "&keyword=" + keyword;
        }
        return "redirect:/rdv/index?page=" + page + "&keyword=" + keyword;

    }
    @GetMapping(path = "/annuler/{id}")
    public String annuler(String keyword, Principal principal, int page, @PathVariable("id") Long id) {
        if (rendezVousService.findDistinctByIdRDV(id) == null) throw new RuntimeException("Page introuvable a sadi9");

        RendezVous rendezVous = rendezVousService.findDistinctByIdRDV(id);
        System.out.println("Rendez Vous recherché => " + rendezVous.getMotif());
        rendezVous.setStatut("Annulé");
        rendezVousService.save(rendezVous);

        for (Role role : currentUser(principal).getRoles()) {
            if (role.getNom().equals("Médecin")) return "redirect:/rdv/?page=" + page + "&keyword=" + keyword;
        }
        return "redirect:/rdv/index?page=" + page + "&keyword=" + keyword;

    }
}
 