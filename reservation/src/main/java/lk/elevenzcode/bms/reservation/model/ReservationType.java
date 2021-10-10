package lk.elevenzcode.bms.reservation.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "reservation_type", schema = "sch_reservation")
@NamedQueries({
        //TODO add HQL queries here
})
public class ReservationType extends EntityBase implements Serializable {
    @Column(name = "type", nullable = false, unique = true, length = 50)
    private String type;

    public ReservationType() {
    }

    public ReservationType(Integer id) {
        super(id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
