package lk.elevenzcode.bms.common.dto;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Response<T> {
  private boolean hasError;
  private T result;
  private List<String> errors = new ArrayList<>();

  public Response() {
  }

  public Response(T result) {
    this.result = result;
  }

  public Response(T result, String... errors) {
    this.result = result;

    if (ArrayUtils.isNotEmpty(errors)) {
      hasError = true;
      this.errors.addAll(Arrays.asList(errors));
    }
  }

  public boolean isHasError() {
    return hasError;
  }

  public void setHasError(boolean hasError) {
    this.hasError = hasError;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    if (!hasError) {
      hasError = true;
    }
    this.errors = errors;
  }

  public void addErrors(String... errors) {
    if (!hasError) {
      hasError = true;
    }
    this.errors.addAll(Arrays.asList(errors));
  }
}
