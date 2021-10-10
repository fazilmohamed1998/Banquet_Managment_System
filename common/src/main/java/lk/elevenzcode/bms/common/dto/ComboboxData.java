package lk.elevenzcode.bms.common.dto;

public class ComboboxData {
  private String value;
  private String text;

  public ComboboxData(int value, String text) {
    this.value = String.valueOf(value);
    this.text = text;
  }

  public ComboboxData(String value, String text) {
    this.value = value;
    this.text = text;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "ComboboxData{" +
        "value='" + value + '\'' +
        ", text='" + text + '\'' +
        '}';
  }
}
