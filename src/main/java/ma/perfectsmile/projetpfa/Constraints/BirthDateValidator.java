package ma.perfectsmile.projetpfa.Constraints;

import ma.perfectsmile.projetpfa.Annotations.DOBValidator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class BirthDateValidator implements ConstraintValidator<DOBValidator, Date> {
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        Calendar dateInCalendar = Calendar.getInstance();
        dateInCalendar.setTime(date);

        return ( ( Calendar.getInstance().get(Calendar.YEAR) - dateInCalendar.get(Calendar.YEAR) >= 18 ) &&
                ( Calendar.getInstance().get(Calendar.YEAR) - dateInCalendar.get(Calendar.YEAR) <= 70 ) );
    }
}
