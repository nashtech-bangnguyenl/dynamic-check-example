import dto.DocumentA;

import java.util.Arrays;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Filter filter = new Filter();
        filter.setType(DocumentA.class.getSimpleName());

        Criteria criteria1 = new Criteria();
        criteria1.setProperty("username");
        criteria1.setOperation(Operation.IS_NOT_EMPTY);
        criteria1.setMessage("Username is missing");

        Criteria criteria2 = new Criteria();
        criteria2.setProperty("firstname");
        criteria2.setOperation(Operation.IS_NOT_EMPTY);
        criteria2.setMessage("First name is missing");

        Criteria criteria3 = new Criteria();
        criteria3.setProperty("lastname");
        criteria3.setOperation(Operation.IS_NOT_EMPTY);
        criteria3.setMessage("Last name is missing");

        Criteria criteria4 = new Criteria();
        criteria4.setProperty("age");
        criteria4.setOperation(Operation.IS_NOT_NULL);
        criteria4.setMessage("Age is missing");

        Criteria criteria5 = new Criteria();
        criteria5.setProperty("enabled");
        criteria5.setOperation(Operation.IS_NOT_NULL);
        criteria5.setMessage("Enable is missing");

        Criteria criteria6 = new Criteria();
        criteria6.setProperty("enabled");
        criteria6.setOperation(Operation.EQUAL);
        criteria6.setValue("true");
        criteria6.setMessage("Enabled not equal true");

        filter.setCriterias(Arrays.asList(criteria1, criteria2, criteria3, criteria4, criteria6));

        FilterService filterService = new FilterService();
        filterService.create(filter);

        DocumentA documentA = new DocumentA();
        documentA.setAge(8);
        documentA.setEnabled(true);

        Set<ValidationConstrain> constrains = filterService.validate(documentA);
        constrains.forEach(c -> {
            System.out.println("Error field: " + c.getProperty());
            System.out.println("Error message: " + c.getErrorMessage());
        });
    }
}
