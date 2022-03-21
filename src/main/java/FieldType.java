import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public enum FieldType {
    STRING(String.class) {
        public boolean isValid(Object value, Criteria criteria) {
            String strValue = (String) value;
            final Operation operation = criteria.getOperation();
            switch (operation) {
                case IS_NOT_NULL:
                    return strValue != null;
                case IS_NOT_EMPTY:
                    return StringUtils.isNotEmpty(strValue);
                case EQUAL:
                    return StringUtils.equals(strValue, criteria.getValue());
                default:
                    throw new IllegalArgumentException("Don't support " + operation + " with " + this);
            }
        }
    },
    BOOLEAN(Boolean.class) {
        @Override
        public boolean isValid(Object value, Criteria criteria) {
            Boolean booleanValue = (Boolean) value;
            final Operation operation = criteria.getOperation();
            switch (operation) {
                case IS_NOT_NULL:
                    return booleanValue != null;
                case EQUAL:
                    return Boolean.parseBoolean(criteria.getValue()) == booleanValue;
            }

            throw new IllegalArgumentException("Don't support " + operation + " with " + this);
        }
    },
    INTEGER(Integer.class) {
        @Override
        public boolean isValid(Object value, Criteria criteria) {
            final Operation operation = criteria.getOperation();
            Integer integerValue = (Integer) value;
            switch (operation) {
                case IS_NOT_NULL:
                    return integerValue != null;
                case EQUAL:
                    return integerValue == Integer.parseInt(criteria.getValue());
            }
            throw new IllegalArgumentException("Don't support " + operation + " with " + this);
        }
    };

    private final Class<?> type;

    FieldType(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    public abstract boolean isValid(Object value, Criteria criteria);
}
