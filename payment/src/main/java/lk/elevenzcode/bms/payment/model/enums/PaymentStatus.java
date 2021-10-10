package lk.elevenzcode.bms.payment.model.enums;

/**
 * @author HaShaN on 8/31/2019 9:34 AM.
 */
public enum PaymentStatus {
  SUCCESS(1), REFUND(2);

  private int id;

  PaymentStatus(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
