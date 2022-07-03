package ma.perfectsmile.projetpfa.Annotations;

import ma.perfectsmile.projetpfa.Constraints.AppointmentDateValidator;
import ma.perfectsmile.projetpfa.Constraints.BirthDateValidator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AppointmentDateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@DateTimeFormat(pattern = "yyyy-MM-dd")
public @interface DateRDValidator {
    String message() default "Date Rendez-Vous ne doit pas dépasser 5 mois !";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
