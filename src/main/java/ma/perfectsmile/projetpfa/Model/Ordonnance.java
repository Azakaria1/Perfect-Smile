package ma.perfectsmile.projetpfa.Model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Table(name = "Ordonnance")
public class Ordonnance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordonnance", nullable = false)
    private Long idOrdonnance;

    @NotNull
    @NotBlank
    @Length(min = 5)
    private String description;

    @ManyToOne(
            cascade = CascadeType.ALL) // chaque patient peut avoir plsr ordonnances
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Medicament> medicaments = new ArrayList<>();
}