import dto.DocumentB;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationService {

  public static void validate(DocumentB documentB) {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<DocumentB>> constraintViolations = validator.validate(documentB);
    for (ConstraintViolation<DocumentB> constraintViolation : constraintViolations) {
      System.out.println("Error field: " + constraintViolation.getPropertyPath());
      System.out.println("Error message: " + constraintViolation.getMessage());
    }
  }

}
