package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.Role;
import ma.perfectsmile.projetpfa.Model.Utilisateur;
import ma.perfectsmile.projetpfa.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

    private final PasswordEncoder passwordEncoder = encoder();
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RoleServiceImpl roleService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }

    @Override
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        // utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.saveAndFlush(utilisateur);
    }

    @Override
    public void supprimerUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.delete(utilisateur);
    }

    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) {
        //    utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateurRepository.saveAndFlush(utilisateur);
    }

    @Override
    public Role ajouterRole(Role role) {
        return roleService.save(role);
    }

    @Override
    public void supprimerRole(Role role) {
        roleService.delete(role);
    }

    @Override
    public void modifierRole(Role role) {
        roleService.save(role);
    }

    @Override
    public void addRoleToUser(Long idUtilisateur, Long idRole) {
        List<Role> roles = findDistinctByIdUtilisateur(idUtilisateur).getRoles();
        roles.add(getRole(idRole));
        findDistinctByIdUtilisateur(idUtilisateur).setRoles(roles);
    }

    @Override
    public void addRolesToUser(Long idUtilisateur, List<Role> rolesList) {
        List<Role> roles = findDistinctByIdUtilisateur(idUtilisateur).getRoles();
        roles.addAll(rolesList);
        findDistinctByIdUtilisateur(idUtilisateur).setRoles(roles);
    }

    @Override
    public void updateUserRole(Long idUtilisateur, Long idRole) {
        List<Role> roleList = findDistinctByIdUtilisateur(idUtilisateur).getRoles();
        Role role = new Role();
        for (Role r : roleList) {
            role = r.getIdRole().equals(idRole) ? r : null;
        }
        modifierRole(role);
    }

    @Override
    public void deleteRoleFromUser(Long idUtilisateur, Long idRole) {
        Utilisateur utilisateur = findDistinctByIdUtilisateur(idUtilisateur);
        List<Role> roles = utilisateur.getRoles();
        roles.removeIf(role -> role.getIdRole().equals(idRole));
    }

    public List<Role> getUserRoles(Utilisateur utilisateur) {
        return utilisateur.getRoles();
    }

    @Override
    public Role getRole(Long idRole) {
        return roleService.findById(idRole);
    }

    @Override
    public List<Role> getRoles() {
        return roleService.getRoles();
    }


    @Override
    public Page<Utilisateur> findByNomContains(String mot, Pageable pageable) {
        return utilisateurRepository.findByNomContains(mot, pageable);
    }

    @Override
    public Page<Utilisateur> findByPrenomContains(String mot, Pageable pageable) {
        return utilisateurRepository.findByPrenomContains(mot, pageable);
    }

    @Override
    public Utilisateur findDistinctByIdUtilisateur(Long id) {
        return utilisateurRepository.findDistinctByIdUtilisateur(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return utilisateurRepository.existsByEmail(email);
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public void save(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur register(Utilisateur utilisateur) {

        utilisateur.getRoles().add(roleService.findDistinctByNom("Patient"));
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateurRepository.save(utilisateur);

        return utilisateur;
    }/*
    @Override
    public void sendVerificationEmail(Utilisateur utilisateur, String siteURL) throws MessagingException {

        String toAddress = utilisateur.getEmail();
        String fromAddress = "azakariaachour@gmail.com";
        String senderName = "Perfect Smile";
        String subject = "Please verify your registration";
        String content = "Dear " + utilisateur.getUsername() + ",<br>" + "Please click the link below to verify your registration:<br>" + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>" + senderName;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom(fromAddress);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        helper.setTo(toAddress);
        helper.setSubject(subject);

        String verifyURL = siteURL + "/verify?code=" + utilisateur.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content);

        mailSender.send(message);
    }

    public boolean verify(String verificationCode) {
        Utilisateur utilisateur = utilisateurRepository.findByVerificationCode(verificationCode);

        if (utilisateur == null || utilisateur.isEnabled()) {
            return false;
        } else {
            utilisateur.setVerificationCode(null);
            utilisateur.setEnabled(true);
            utilisateurRepository.save(utilisateur);

            return true;
        }
    }
*/

    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public Utilisateur findByNom(String name) {
        return utilisateurRepository.findByNom(name);
    }

    @Override
    public Utilisateur findByUsername(String first, String second) {
        return utilisateurRepository.findByUsername(first, second);
    }
}