package lk.elevenzcode.bms.event.model.enums;

/**
 * @author Harin on 8/31/2019.
 */
public enum EventType {
    WEDDING(1), CO_OPERATE_EVENT(2), PARTY(3);

    private int id;

    EventType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
