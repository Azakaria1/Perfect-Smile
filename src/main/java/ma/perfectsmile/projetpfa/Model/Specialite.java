package ma.perfectsmile.projetpfa.Model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "Specialite", uniqueConstraints = @UniqueConstraint(name = "specialite_nom_unique", columnNames = "nom"))
public class Specialite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSpecialite", nullable = false)
    private Long idSpecialite;

    @NotNull
    @Length(min = 7)
    private String nom;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "specialite") // chaque specialite peut avoir plsr médecins
    @LazyCollection(LazyCollectionOption.FALSE)
    List<Medecin> medecins = new ArrayList<>();

}
