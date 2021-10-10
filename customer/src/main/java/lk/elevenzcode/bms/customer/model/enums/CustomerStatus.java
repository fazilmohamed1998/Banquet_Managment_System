package lk.elevenzcode.bms.customer.model.enums;

/**
 * @author Gayan on 9/1/2019 3:54 PM.
 */
public enum CustomerStatus {
  ACTIVE(1), INACTIVE(2);

  private int id;

  CustomerStatus(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
