public class ValidationConstrain {
  private String property;
  private String errorMessage;

  public ValidationConstrain(String property, String errorMessage) {
    this.property = property;
    this.errorMessage = errorMessage;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
