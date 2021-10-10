package lk.elevenzcode.bms.event.model.enums;

/**
 * @author Harin on 8/31/2019.
 */
public enum EventStatus {
    PENDING(1), DONE(2), CANCELED(3), DELETED(4);

    private int id;

    EventStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
