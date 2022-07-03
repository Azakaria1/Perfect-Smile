package ma.perfectsmile.projetpfa.Controller;

import ma.perfectsmile.projetpfa.Model.Patient;
import ma.perfectsmile.projetpfa.Service.PatientServiceImpl;
import ma.perfectsmile.projetpfa.Service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("patient")
public class PatientController {

    PasswordEncoder passwordEncoder = encoder();
    @Autowired
    private PatientServiceImpl patientService;
    @Autowired
    private RoleServiceImpl roleService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping(path = "/")
    public String patients(Model model, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> pagepatientsParNom = patientService.findByNomContains(keyword, PageRequest.of(page, size));
        Page<Patient> pagepatientsParPrenom = patientService.findByPrenomContains(keyword, PageRequest.of(page, size));

        Page<Patient> patients = pagepatientsParNom.isEmpty() ? pagepatientsParPrenom : pagepatientsParNom;

        System.out.println("Patients : " + patients.getContent());

        model.addAttribute("patient", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patient/index";
    }

    @GetMapping(path = "/delete")
    public String delete(Long id, String keyword, int page) {
        patientService.deleteById(id);
        return "redirect:/patient/?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/add")
    public String add(Model model) {
        model.addAttribute("patient", new Patient());

        return "patient/save";
    }

    @PostMapping(path = "/save")
    public String save(@Valid Patient patient, BindingResult bindingresult, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) return "patient/save";
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.getRoles().add(roleService.findDistinctByNom("Patient"));
        patientService.ajouterPatient(patient);

        return "redirect:/patient?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/edit/{id}")
    public String edit(Model model, @PathVariable Long id, String keyword, int page) {

        if (patientService.findDistinctByIdUtilisateur(id) == null) throw new RuntimeException("Page introuvable !");

        model.addAttribute("patient", patientService.findDistinctByIdUtilisateur(id));
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);

        return "patient/edit";
    }

    @PostMapping(path = "/update/{id}")
    public String update(@Valid Patient patient, BindingResult bindingresult, @PathVariable("id") Long id, @RequestParam(name = "keyword", defaultValue = "") String keyword, @RequestParam(name = "page", defaultValue = "0") int page) {

        if (bindingresult.hasErrors()) return "patient/save";

        System.out.println("Id transmis >" + id);
        Patient patient1 = patientService.findDistinctByIdUtilisateur(id);
        patient.setIdUtilisateur(patient1.getIdUtilisateur());
        patient.setVersion(patient1.getVersion());
        System.out.println("Patient à modifier => " + patient);

        patientService.ajouterPatient(patient);

        return "redirect:/patient?page=" + page + "&keyword=" + keyword;

    }
}
