package ma.perfectsmile.projetpfa.Annotations;

import ma.perfectsmile.projetpfa.Constraints.BirthDateValidator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BirthDateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Past
@NotNull
@DateTimeFormat(pattern = "yyyy-MM-dd")
public @interface DOBValidator {
    String message() default "Date de naissance invalide !";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
