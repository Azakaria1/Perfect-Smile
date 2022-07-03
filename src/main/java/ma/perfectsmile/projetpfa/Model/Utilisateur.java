package ma.perfectsmile.projetpfa.Model;

import lombok.*;
import ma.perfectsmile.projetpfa.Annotations.DOBValidator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@DiscriminatorColumn(name = "role")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Utilisateur", uniqueConstraints = {@UniqueConstraint(name = "utilisateur_email_unique", columnNames = "email"), @UniqueConstraint(name = "utilisateur_nom_unique", columnNames = "nom"), @UniqueConstraint(name = "utilisateur_cin_unique", columnNames = "cin")})
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Utilisateur", nullable = false)
    private Long idUtilisateur;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;

    @Column(name = "prenom", length = 45, nullable = false)
    @NotNull
    private String prenom;

    @Column(name = "nom", length = 45, nullable = false)
    @NotBlank
    @NotNull
    private String nom;

    @Column(name = "date_naissance", nullable = false)
    @Temporal(TemporalType.DATE)
    @DOBValidator
    private Date date_naissance;

    @Column(name = "cin", length = 45, nullable = false)
    @NotBlank
    @NotNull
    private String cin;

    @Column(name = "email", length = 60, nullable = false)
    @Email
    @NotBlank
    @NotNull
    private String email;

    @Column(name = "tel", nullable = false)
    @Pattern(regexp = "^((06)|(07))[0-9]{8}", message = "Not a valid number !!")
    @NotBlank
    @NotNull
    private String tel;

    @Column(name = "sexe", length = 45, nullable = false)
    @NotBlank
    @NotNull
    private String sexe;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_Creation", nullable = false)
    private Date dateCreation = new Date();

    @Column(name = "password", nullable = false)
    private String password;

    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Role> roles = new ArrayList<>();

    private String adresse;

    public String getUsername() {
        return nom + " " + prenom;
    }
}