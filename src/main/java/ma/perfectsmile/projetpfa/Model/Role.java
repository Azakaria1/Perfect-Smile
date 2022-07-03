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
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "Role", uniqueConstraints = @UniqueConstraint(name = "role_nom_unique", columnNames = "nom"))
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role", nullable = false)
    private Long idRole;

    @NotNull
    @NotBlank
    @Length(min = 5)
    @Column(unique = true)
    private String nom;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Permission> permissions;

}