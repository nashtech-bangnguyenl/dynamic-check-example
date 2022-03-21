
import dto.FilterAware;

import java.lang.reflect.Field;
import java.util.*;

public class FilterService {

    private final Map<String, Filter> filterMap = new HashMap<>();

    public void create(Filter filter) {
        this.filterMap.put(filter.getType(), filter);
    }

    public boolean validate(FilterAware filterAware) throws NoSuchFieldException, IllegalAccessException {
        final String type = filterAware.getType();
        final Filter filter = this.filterMap.get(type);
        boolean check = true;
        for (Criteria criteria : filter.getCriterias()) {
            check = check && isValid(criteria, filterAware);
        }
        return check;
    }

    private boolean isValid(Criteria criteria, FilterAware filterAware) throws NoSuchFieldException, IllegalAccessException {
        final Field field = filterAware.getClass().getDeclaredField(criteria.getProperty());
        final Class<?> type = field.getType();
        final FieldType fieldType = this.getFieldType(type);
        field.setAccessible(true);
        final Object value = field.get(filterAware);
        return fieldType.isValid(value, criteria);
    }

    public FieldType getFieldType(Class<?> type) {
        final Optional<FieldType> optional = Arrays.stream(FieldType.values()).filter(f -> f.getType() == type).findFirst();
        return optional.orElseThrow(() -> new IllegalArgumentException("Don't support type " + type));
    }
}
