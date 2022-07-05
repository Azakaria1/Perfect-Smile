package ma.perfectsmile.projetpfa.Model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
@Table(name = "SituationFinanciere")
public class SituationFinanciere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_situation", nullable = false)
    private Long idSituation;

    @NotNull
    @NotBlank
    @Length(min = 20)
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "paye", nullable = false)
    private Long paye;

    @Column(name = "reste", nullable = false)
    private Long reste;

    @Column(name = "total", nullable = false)
    private Long total;

    @OneToOne
    private Patient patient;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "situationFinanciere")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Facture> factures;
}