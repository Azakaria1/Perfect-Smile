package ma.perfectsmile.projetpfa.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.perfectsmile.projetpfa.Model.CertifMedical;
import ma.perfectsmile.projetpfa.repositories.CertifMedicalRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class CertificatServiceImpl implements CertificatService {

    private final CertifMedicalRepository certifRepo;

    @Override
    public CertifMedical addCertifMedical(CertifMedical certificat) {
        log.info("création du certificat : {}", certificat.getDescription());
        return certifRepo.saveAndFlush(certificat);
    }

    @Override
    public List<CertifMedical> getAllCertificats() {
        log.info("recherche tous les Certificats");
        return certifRepo.findAll();
    }

    @Override
    public CertifMedical getCertificat(Integer id) {
        log.info("recherche CertifMedical : {}", id);
        return certifRepo.findDistinctByIdCertificat(id);
    }

    @Override
    public CertifMedical updateCertifMedical(CertifMedical certificat) {
        log.info("mise à jour du cerificat: {}");
        return certifRepo.saveAndFlush(certificat);
    }

    @Override
    public Boolean deleteCertifMedical(Integer id) {
        log.info("suppression du CertifMedical: {}", id);
        certifRepo.deleteByIdCertificat(id);
        return TRUE;
    }


}
