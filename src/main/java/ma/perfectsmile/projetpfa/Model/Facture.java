package ma.perfectsmile.projetpfa.Model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "Facture")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facture", nullable = false)
    private Long idfacture;

    @NotNull
    private Long prixTotal;

    private Date date_paiement ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_facture", nullable = false)
    private Date date_facture = new Date();

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    ) // chaque patient a plsr RDV
    @NotNull
    private Consultation consultation;

    @ManyToOne(
            cascade = CascadeType.ALL) // chaque patient a plsr RDV
    private SituationFinanciere situationFinanciere;

}
