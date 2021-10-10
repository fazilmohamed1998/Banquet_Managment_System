package lk.elevenzcode.bms.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author HaShaN on 9/8/2019 3:20 PM.
 */
public class JsonUtil<T> {
  private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

  final Class<T> classType;

  public JsonUtil(Class<T> classType) {
    this.classType = classType;
  }

  private static final ObjectMapper MAPPER = new ObjectMapper();

  public T readValue(String jsonStr) {
    try {
      return MAPPER.readValue(jsonStr, classType);
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
    }
    return null;
  }
}
