package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.DossierMedical;
import ma.perfectsmile.projetpfa.Model.Patient;
import ma.perfectsmile.projetpfa.Model.Role;
import ma.perfectsmile.projetpfa.Model.Utilisateur;
import ma.perfectsmile.projetpfa.Service.DossierMedicalServiceImpl;
import ma.perfectsmile.projetpfa.Service.RoleServiceImpl;
import ma.perfectsmile.projetpfa.Service.UtilisateurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    ApplicationEventPublisher eventPublisher;
    @Autowired
    private UtilisateurServiceImpl utilisateurService;

    @Autowired
    private DossierMedicalServiceImpl dossierMedicalService;

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

    public Utilisateur currentUser(Principal principal) {

        String[] arrOfStr = principal.getName().split(" ");
        return utilisateurService.findByUsername(arrOfStr[0], arrOfStr[1]);
    }
    @RequestMapping("signUp")
    public String registerForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "auth/register";
    }

    // Inscription ==> Patient

    @RequestMapping("register")
    public String register(@Valid Patient patient, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page, HttpServletRequest request) throws MessagingException {

        if (bindingresult.hasErrors()) {
            bindingresult.getAllErrors().forEach(System.err::println);
            return "auth/register";
        }
        utilisateurService.register(patient);

        return "redirect:/login";
    }
    @GetMapping(path = "/profil")
    public String profilPage(Model model, Principal principal) {

        String[] arrOfStr = principal.getName().split(" ");

        System.err.println(utilisateurService.findByUsername(arrOfStr[0],arrOfStr[1]));
        List<Role> roles = utilisateurService.findByNom(arrOfStr[0]).getRoles();

        List<DossierMedical> dossierMedicals = dossierMedicalService.findAll();
        for (DossierMedical dossier : dossierMedicals ) {
            if (dossier.getPatient().equals(currentUser(principal)) && ( dossier.getGrpSanguin() != null) )
                model.addAttribute("dossier",dossier);

        }

        model.addAttribute("utilisateur", utilisateurService.findByNom(arrOfStr[0]));
        model.addAttribute("roles", roles);

        return "auth/profile";
    }

  /*  @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (utilisateurService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }
*/
}