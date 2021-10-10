package lk.elevenzcode.bms.reservation.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "reservation_status", schema = "sch_reservation")
@NamedQueries({
        //TODO add HQL queries here
})
public class ReservationStatus extends EntityBase implements Serializable {
    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    public ReservationStatus() {
    }

    public ReservationStatus(Integer id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
