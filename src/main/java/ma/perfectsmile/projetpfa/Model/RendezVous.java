package ma.perfectsmile.projetpfa.Model;

import lombok.*;
import ma.perfectsmile.projetpfa.Annotations.DateRDValidator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RendezVous")
@Component
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRDV;

    //@DateRDValidator
    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRDV;

    @NotNull
    @DateTimeFormat(pattern="HH:mm")
    private String HeureRDV;

    @NotNull
    private String motif;

    private String statut;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_patient")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_medecin")
    private Medecin medecin;

    @OneToOne(mappedBy = "rendezVous")
    private Consultation consultation;

}