package ma.perfectsmile.projetpfa.Model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "Consutation")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consultation", nullable = false)
    private Long idConsultation;

    @Column(name = "motif", nullable = false)
    private String motif;

    @Column(name = "prix", nullable = false)
    private Long prix;

    @OneToOne
    @NotNull
    private RendezVous rendezVous;

    @OneToOne
    private CertifMedical certifMedical;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Facture> factures;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "id_intervention")
    private Intervention intervention;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "id_medecin")
    private Utilisateur medecin;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Acte> actes;
}