package lk.elevenzcode.bms.reservation.model.enums;

/**
 * @author Harin on 8/31/2019.
 */
public enum ReservationStatus {
  RESEREVED(1), CONFIRM(2), CANCELED(3);

  private int id;

  ReservationStatus(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
