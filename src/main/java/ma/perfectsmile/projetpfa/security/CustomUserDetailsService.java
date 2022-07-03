package ma.perfectsmile.projetpfa.security;

import ma.perfectsmile.projetpfa.Model.Utilisateur;
import ma.perfectsmile.projetpfa.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UtilisateurRepository utilisateurRepository ;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        System.err.println("Utilisateur authentifié => " + utilisateur.getUsername() + " ayant comme email => "+ utilisateur.getEmail());
        return new CustomUserDetails(utilisateur);
    }
}
