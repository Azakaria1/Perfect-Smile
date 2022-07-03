package ma.perfectsmile.projetpfa.Model;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@DiscriminatorValue("Patient")
public class Patient extends Utilisateur{

    @OneToOne
    private DossierMedical dossierMedical;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient") // chaque patient a plsr RDV
    @LazyCollection(LazyCollectionOption.FALSE)
    List<RendezVous> rendezVousList = new ArrayList<>();

    @OneToOne
    private SituationFinanciere situationFinanciere;

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "patient")
    private List<Ordonnance> ordonnances;
}