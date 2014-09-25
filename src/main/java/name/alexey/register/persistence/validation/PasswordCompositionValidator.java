package name.alexey.register.persistence.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Validates password composition rules
 */
public class PasswordCompositionValidator implements ConstraintValidator<PasswordComposition, String> {
    @Override
    public void initialize(PasswordComposition passwordComposition) {
        // nothing form annotation
    }

    private final static Pattern aDigit = Pattern.compile(".*\\d.*");
    private final static Pattern aLowercaseLetter = Pattern.compile(".*[a-z].*");
    private final static Pattern aUppercaseLetter = Pattern.compile(".*[A-Z].*");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        if( aDigit.matcher(s).matches() && aLowercaseLetter.matcher(s).matches() && aUppercaseLetter.matcher(s).matches()) {
            return true;
        }
        context.buildConstraintViolationWithTemplate( context.getDefaultConstraintMessageTemplate()).addConstraintViolation();

        return false;
    }
}
