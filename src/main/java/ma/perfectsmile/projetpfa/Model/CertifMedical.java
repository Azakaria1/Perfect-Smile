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
@Table(name = "CertifMedical")
public class CertifMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCertificat", nullable = false)
    private Long idCertificat;

    @NotNull
    @NotBlank
    @Length(min = 20)
    private String description;

    private String signature;

    @OneToOne
    private Consultation consultation;
}
