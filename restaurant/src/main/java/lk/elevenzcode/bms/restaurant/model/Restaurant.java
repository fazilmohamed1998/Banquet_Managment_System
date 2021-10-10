package lk.elevenzcode.bms.restaurant.model;

import lk.elevenzcode.bms.common.model.EntityBase;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "restaurant", schema = "sch_restaurant_mgmt")
@NamedQueries({
        @NamedQuery(
  //TODO add HQL queries here
        name = "Restaurant.search",
        query = "SELECT bh FROM Restaurant bh JOIN bh.type t JOIN bh.status s"
)
})
public class Restaurant extends EntityBase implements Serializable {
  @Column(name = "number", nullable = false)
  private Integer number;

  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private RestaurantType type;

  @ManyToOne
  @JoinColumn(name = "status", nullable = false)
  private RestaurantStatus status;

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public RestaurantType getType() {
    return type;
  }

  public void setType(RestaurantType type) {
    this.type = type;
  }

  public RestaurantStatus getStatus() {
    return status;
  }

  public void setStatus(RestaurantStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Restaurant{" +
            "name='" + number + '\'' +
            ", type=" + type +
            ", status=" + status +
            '}';
  }
}