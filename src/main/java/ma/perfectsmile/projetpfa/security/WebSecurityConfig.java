package ma.perfectsmile.projetpfa.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("static/**" , "/webjars/**").permitAll();
        http.authorizeRequests().antMatchers("/rdv/index").hasAnyAuthority("Secrétaire", "Assistant");

        http.authorizeRequests().antMatchers("/rdv/", "/rdv/add").hasAnyAuthority("Patient", "Médecin");

        http.authorizeRequests().antMatchers("/dossier/**", "specialite/**", "acte/**", "/patient/**",
                        "/secretaire/**", "/assistant/**", "/rdv/add","/rdv/edit", "/medecin/add", "/medecin/delete/**", "/medecin/edit/**",
                        "/role/**", "/permission/**").hasAuthority("Secrétaire")

                .and().authorizeRequests().antMatchers("/intervention/**","/acte/**","/specialite/**").hasAnyAuthority("Médecin", "Secrétaire", "Assistant")
                .and().formLogin().defaultSuccessUrl("/").permitAll() // to permit all the users to see the login page
                .and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").permitAll();
    }
}
