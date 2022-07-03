package ma.perfectsmile.projetpfa.Constraints;

import ma.perfectsmile.projetpfa.Annotations.DateRDValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class AppointmentDateValidator implements ConstraintValidator<DateRDValidator, Date> {

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        Calendar dateInCalendar = Calendar.getInstance();
        System.out.println("Date parameter =>" + date);  // null !
        dateInCalendar.setTime(date);
        System.out.println("Date in calendar value => "+ dateInCalendar);

        return ((Calendar.getInstance().get(Calendar.YEAR) == dateInCalendar.get(Calendar.YEAR) ) &&
                ( dateInCalendar.get(Calendar.MONTH)  -  Calendar.getInstance().get(Calendar.MONTH) >= 0 ) &&
                ( dateInCalendar.get(Calendar.MONTH)  -  Calendar.getInstance().get(Calendar.MONTH) <= 5) );
    }
}
