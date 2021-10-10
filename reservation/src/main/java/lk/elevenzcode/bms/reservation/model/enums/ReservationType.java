package lk.elevenzcode.bms.reservation.model.enums;

/**
 * @author Harin on 8/31/2019.
 */
public enum ReservationType {
    BANQUET_HALL(1), RESTAURANT(2);

    private int id;

    ReservationType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
