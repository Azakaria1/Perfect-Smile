package ma.perfectsmile.projetpfa.Constraints;

import ma.perfectsmile.projetpfa.Annotations.Unique;
import ma.perfectsmile.projetpfa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UserUniqueValidator implements ConstraintValidator<Unique,String> {

    @Autowired
    private PatientRepository patientRepository;


    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return patientRepository == null || !patientRepository.existsByEmail(email);
    }

}
