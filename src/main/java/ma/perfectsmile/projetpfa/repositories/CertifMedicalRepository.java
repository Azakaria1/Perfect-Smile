package ma.perfectsmile.projetpfa.repositories;

import ma.perfectsmile.projetpfa.Model.CertifMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertifMedicalRepository extends JpaRepository<CertifMedical,Integer> {

    CertifMedical findDistinctByIdCertificat(Integer id);

    void deleteByIdCertificat(Integer id);
}
