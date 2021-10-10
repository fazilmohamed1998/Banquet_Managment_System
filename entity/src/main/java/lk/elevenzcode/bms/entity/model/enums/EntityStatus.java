package lk.elevenzcode.bms.entity.model.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by hashan on 5/19/19 3:00 PM
 */
public enum EntityStatus {
  ACTIVE(1), INACTIVE(2), BLOCKED(3), DELETED(4);

  private int id;

  EntityStatus(int id) {
    this.id = id;
  }

  public static int getId(String name) {
    for (EntityStatus value : values()) {
      if (StringUtils.equalsIgnoreCase(value.name(), name)) {
        return value.getId();
      }
    }
    return 0;
  }

  public int getId() {
    return id;
  }
}
