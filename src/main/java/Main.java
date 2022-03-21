import dto.DocumentA;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Filter filter = new Filter();
        filter.setType(DocumentA.class.getSimpleName());

        Criteria criteria1 = new Criteria();
        criteria1.setProperty("username");
        criteria1.setOperation(Operation.IS_NOT_EMPTY);

        Criteria criteria2 = new Criteria();
        criteria2.setProperty("firstname");
        criteria2.setOperation(Operation.IS_NOT_EMPTY);

        Criteria criteria3 = new Criteria();
        criteria3.setProperty("lastname");
        criteria3.setOperation(Operation.IS_NOT_EMPTY);

        Criteria criteria4 = new Criteria();
        criteria4.setProperty("age");
        criteria4.setOperation(Operation.IS_NOT_NULL);

        Criteria criteria5 = new Criteria();
        criteria5.setProperty("enabled");
        criteria5.setOperation(Operation.IS_NOT_NULL);

        Criteria criteria6 = new Criteria();
        criteria6.setProperty("enabled");
        criteria6.setOperation(Operation.EQUAL);
        criteria6.setValue("true");

        filter.setCriterias(Arrays.asList(criteria1, criteria2, criteria3, criteria4, criteria6));

        FilterService filterService = new FilterService();
        filterService.create(filter);

        DocumentA documentA = new DocumentA();
        documentA.setUsername("username");
        documentA.setFirstname("firstname");
        documentA.setLastname("lastname");
        documentA.setAge(8);
        documentA.setEnabled(true);

        final boolean check = filterService.validate(documentA);
        System.out.printf("Valid document: expected true, actual %s", check);
    }
}
