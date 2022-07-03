package ma.perfectsmile.projetpfa.Model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "Acte", uniqueConstraints = @UniqueConstraint(name = "acte_libelle_unique", columnNames = "libelle"))
public class Acte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idActe", nullable = false)
    private Long idActe;

    @NotBlank
    @NotNull
    @Length(min = 7)
    private String libelle;

    @NotNull
    @Min(0)
    private int prix;
}
