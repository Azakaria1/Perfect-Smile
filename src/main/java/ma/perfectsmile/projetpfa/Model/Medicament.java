package ma.perfectsmile.projetpfa.Model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "Medicament"
        , uniqueConstraints = @UniqueConstraint(name = "medicament_nom_unique", columnNames = "nom")
)
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medicament", nullable = false)
    private Long idMedicament;

    @NotNull
    @NotBlank
    @Length(min = 20)
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @NotBlank
    @Length(min = 20)
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @NotBlank
    @DecimalMin("0")
    @Column(name = "prix", nullable = false)
    private double prix;

}
