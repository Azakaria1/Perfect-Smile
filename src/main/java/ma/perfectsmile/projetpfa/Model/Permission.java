package ma.perfectsmile.projetpfa.Model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(
        name = "Permission",
        uniqueConstraints = @UniqueConstraint( name = "permission_nom_unique", columnNames = "nom")
)
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permission", nullable = false)
    private Long id_Permission;

    @NotNull
    @NotBlank
    @Length(min = 5)
    @Column(unique = true)
    private String nom;

}
