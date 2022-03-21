package dto;

import javax.validation.constraints.NotBlank;

public class DocumentB {
  @NotBlank(message = "name is missing")
  private String name;

  @NotBlank(message = "address is missing")
  private String address;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
