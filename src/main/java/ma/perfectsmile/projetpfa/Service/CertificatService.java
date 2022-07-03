package ma.perfectsmile.projetpfa.Service;

import ma.perfectsmile.projetpfa.Model.CertifMedical;

import java.util.List;

public interface CertificatService {

    CertifMedical addCertifMedical(CertifMedical certificat);

    List<CertifMedical> getAllCertificats();

    CertifMedical getCertificat(Integer id);

    CertifMedical updateCertifMedical(CertifMedical certificat);

    Boolean deleteCertifMedical(Integer id);
}
