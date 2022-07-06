package ma.perfectsmile.projetpfa;

import ma.perfectsmile.projetpfa.Model.Patient;
import ma.perfectsmile.projetpfa.Model.Role;
import ma.perfectsmile.projetpfa.Model.Secretaire;
import ma.perfectsmile.projetpfa.Model.Specialite;
import ma.perfectsmile.projetpfa.Service.PatientServiceImpl;
import ma.perfectsmile.projetpfa.Service.RoleServiceImpl;
import ma.perfectsmile.projetpfa.Service.SecretaireServiceImpl;
import ma.perfectsmile.projetpfa.Service.SpecialiteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class ProjetPfaApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ProjetPfaApplication.class, args);
    }

    /*@Bean
    @Transactional*/
    CommandLineRunner commandLineRunner(SecretaireServiceImpl secretaireService, SpecialiteServiceImpl specialiteService, PatientServiceImpl patientService, RoleServiceImpl roleService) {
        return args -> {
            // On ajoute un compte Secrétaire (administrateur de l'app)
            Role role = new Role(null, "Secrétaire", new ArrayList<>());
            Role role2 = new Role(null, "Patient", new ArrayList<>());
            roleService.save(role);
            roleService.save(role2);

            Secretaire secretaire = new Secretaire();
            secretaire.setNom("Benzakour");
            secretaire.setPrenom("Ilham");
            secretaire.setEmail("ilhamBen@gmail.com");
            secretaire.setAdresse("Rabat, Maroc");
            secretaire.setPassword(passwordEncoder.encode("123456789"));
            secretaire.setTel("0612345678");
            secretaire.setDate_naissance(new Date(99, Calendar.DECEMBER,5));
            secretaire.setCin("AA98257");
            secretaire.getRoles().add(roleService.findDistinctByNom("Secrétaire"));
            secretaire.setSexe("F");

             secretaireService.ajouterSecretaire(secretaire);


            Patient patient = new Patient();
            patient.setNom("Achour");
            patient.setPrenom("Zakaria");
            patient.setEmail("achourzakaria@gmail.com");
            patient.setAdresse("Casablanca, Maroc");
            patient.setPassword(passwordEncoder.encode("123456789"));
            patient.setTel("0698765432");
            patient.setDate_naissance(new Date(100, Calendar.MAY,15));
            patient.setCin("AA9916");
            patient.getRoles().add(roleService.findDistinctByNom("Patient"));
            patient.setSexe("Homme");

            patientService.ajouterPatient(patient);
             Specialite specialite = new Specialite();
            specialite.setNom("Chirurgie");
            specialiteService.save( specialite );
        };
    }
}
