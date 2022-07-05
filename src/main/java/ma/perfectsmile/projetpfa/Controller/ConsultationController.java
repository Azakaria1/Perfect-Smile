package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.*;
import ma.perfectsmile.projetpfa.Service.*;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("consultation")
public class ConsultationController {

    @Autowired
    private ConsultationServiceImpl consultationService;

    @Autowired
    private ActeServiceImpl acteService;

    @Autowired
    private FactureServiceImpl factureService;

    @Autowired
    private UtilisateurServiceImpl utilisateurService;

    @Autowired
    private InterventionServiceImpl interventionService;

    @Autowired
    private RendezVousServiceImpl rendezVousService;

    public Utilisateur currentUser(Principal principal) {

        String[] arrOfStr = principal.getName().split(" ");
        return utilisateurService.findByUsername(arrOfStr[0], arrOfStr[1]);
    }

    @GetMapping(path = "/index")
    public String consultations(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Consultation> consultations = consultationService.findByMotifContains(keyword, PageRequest.of(page, size));
/*
        Page<Consultation> consultationsPrix = consultationService.findByPrixContains(keyword, PageRequest.of(page, size));
*/
        System.out.println("Consultations : " + consultations.getContent());

        model.addAttribute("consultations", consultations.getContent());
        model.addAttribute("pages", new int[consultations.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "consultation/index";
    }

    @GetMapping(path = "/")
    public String consultationsPatient(Model model,Principal principal, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        List<Consultation> consultationList = new ArrayList<>();
        for (Consultation consultation : consultationService.getConsultations()) {
            if (consultation.getRendezVous().getPatient().equals(currentUser(principal)))
                consultationList.add(consultation);
            if (consultation.getMedecin().equals(currentUser(principal)))
                consultationList.add(consultation);

        }
        Page<Consultation> pageconsultations = new PageImpl<>(consultationList);


        model.addAttribute("consultations", pageconsultations.getContent());
        model.addAttribute("pages", new int[pageconsultations.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "consultation/index";
    }
    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        consultationService.deleteByIdConsultation(id);
        return "redirect:/consultation/index?page=" + page + "&keyword=" + keyword;
    }
    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("consultation", new Consultation());
        model.addAttribute("intervention", new Intervention());
        model.addAttribute("actes", acteService.findAll());
        model.addAttribute("rdvs", rendezVousService.findAll());

        return "consultation/save";
    }


    @PostMapping(path = "/save")
    public String save(@Valid Consultation consultation, @Valid Intervention intervention, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page, Principal principal) {
        if (bindingresult.hasErrors()) {
            bindingresult.getAllErrors().forEach(System.err::println);
            return "consultation/save";
        }

        System.out.println(consultation.getMotif() + " => id: " + consultation.getIdConsultation()); // id = null ???!!!

        consultation.setMedecin(currentUser(principal));

        intervention.setPrix(intervention.getNbDent() * 60L);

        consultation.setIntervention(intervention);
        consultation.setPrix(consultation.getIntervention().getPrix() +
                consultation.getActes().stream().mapToLong(Acte::getPrix).sum());

        System.out.println("intervention ajoutée => " + intervention.getIdIntervention() + " , Dents: " + intervention.getNbDent() + " prix = " + intervention.getPrix() + " DH");

        interventionService.save(consultation.getIntervention());

        Facture facture= new Facture();
        facture.setPrixTotal(consultation.getPrix());
        facture.setConsultation(consultation);

        consultationService.save(consultation);

        consultationService.ajouteFacture(
                facture,consultation.getIdConsultation(),
                consultation.getRendezVous().getPatient());

        consultationService.ajouterSituationFinanciere( new SituationFinanciere(),
                consultation.getIdConsultation(), consultation.getRendezVous().getPatient());

        return "redirect:/consultation/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String editConsultation(Model model, @PathVariable("id") Long id, String keyword, int page) {
        //Consultation consultation= consultationService.findById(id).get();
        if (consultationService.findByIdConsultation(id) == null) throw new RuntimeException("Page introuvable a sadi9");

        model.addAttribute("consultation", consultationService.findByIdConsultation(id));
        model.addAttribute("intervention", consultationService.findByIdConsultation(id).getIntervention());
        model.addAttribute("actes", acteService.findAll());
        model.addAttribute("rdvs", rendezVousService.findAll());
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);

        return "consultation/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid Consultation consultation, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (bindingresult.hasErrors()) return "consultation/save";
        Consultation consultation2 = consultationService.findByIdConsultation(id);
        consultation.setIdConsultation(consultation2.getIdConsultation());
        consultationService.save(consultation);
        return "redirect:/consultation/index?page=" + page + "&keyword=" + keyword;

    }

}
 