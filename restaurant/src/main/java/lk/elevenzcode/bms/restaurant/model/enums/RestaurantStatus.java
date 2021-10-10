package lk.elevenzcode.bms.restaurant.model.enums;

/**
 * @author Nilan on 9/1/2019 3:54 PM.
 */
public enum RestaurantStatus {
  AVAILABLE(1), RESERVED(2);

  private int id;

  RestaurantStatus(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
