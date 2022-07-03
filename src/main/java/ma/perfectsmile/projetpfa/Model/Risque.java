package ma.perfectsmile.projetpfa.Model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Risque")
public class Risque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_risque", nullable = false)
    private Long idRisque;

    @NotNull
    @NotBlank
    @Length(min = 20)
    private String maladie;

    @NotNull
    @NotBlank
    @Length(min = 5)
    private String typeMaladie;

    @NotNull
    @NotBlank
    @Length(min = 20)
    private String traitement;

}
