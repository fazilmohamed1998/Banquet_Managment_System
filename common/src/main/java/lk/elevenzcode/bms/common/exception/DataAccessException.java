package lk.elevenzcode.bms.common.exception;

public class DataAccessException extends Exception {
  public static final int DATA_ACCESS_FAILED = 1;
  public static final int VALIDATION_FAILED = 2;
  public static final int PRIMARY_KEY_NOT_FOUND = 22;
  public static final int NO_PRIMARY_KEY_VALUE = 23;
  private int code;

  public DataAccessException(int code, String message) {
    super(message);
    this.code = code;
  }

  public DataAccessException(int code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
