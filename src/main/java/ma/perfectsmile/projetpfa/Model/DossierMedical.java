package ma.perfectsmile.projetpfa.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DossierMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDossierMedical ;

    @NotNull
    private GrpSanguin grpSanguin;

    private String allergie;

    @Length(min = 5)
    private String maladie;

    @Length(min = 5)
    private String typeMaladie;

    private String traitement;

    @OneToOne
    @NotNull
    private Utilisateur patient;

}
