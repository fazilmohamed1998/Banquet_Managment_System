package lk.elevenzcode.bms.common.util;

import lk.elevenzcode.bms.common.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by hashan on 5/19/19 11:20 AM
 */
public class ValidationUtil {
  private static final String NIC_REGEX = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$";

  public static void validateNIC(String nic) throws ValidationException {
    if (StringUtils.isNotEmpty(nic)) {
      if (!Pattern.matches(NIC_REGEX, nic)) {
        throw new ValidationException("Invalid NIC");
      }
    } else {
      throw new ValidationException("Invalid NIC");
    }
  }
}
