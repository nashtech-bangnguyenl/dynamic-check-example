public enum Operation {
    IS_NOT_NULL("is not null"), IS_NOT_EMPTY("is not empty"), EQUAL("is not equal");

    private String defaultMessage;

    Operation(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
