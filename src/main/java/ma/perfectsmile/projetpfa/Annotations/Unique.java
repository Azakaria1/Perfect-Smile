package ma.perfectsmile.projetpfa.Annotations;

import ma.perfectsmile.projetpfa.Constraints.UserUniqueValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Length(min = 15)
@NotBlank
@Email(message = "Veuillez entrez une adresse email valide !")
@NotNull
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = UserUniqueValidator.class)
@Retention(RUNTIME)
public @interface Unique {
    String message() default "Email already exists a sadi9 !";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
