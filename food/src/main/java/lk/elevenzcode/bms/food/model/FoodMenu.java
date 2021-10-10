package lk.elevenzcode.bms.food.model;

import lk.elevenzcode.bms.common.model.EntityBase;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "food_menu", schema = "sch_food_mgmt")
@NamedQueries({
        //TODO add HQL queries here
})
public class FoodMenu extends EntityBase implements Serializable {
  @Transient
  MultipartFile image;

  /*this will be the foodmenu name column in foodmenu table*/
  /*in here length is maximum no of characters that column(when column is a string(varchar)) will contain*/
  /*nullable = false means whether column allows null values or not*/
  @Column(name = "name", nullable = false, unique = true, length = 50)
  private String name;

  @ManyToOne
  @JoinColumn(name = "status_id", nullable = false)
  private FoodMenuStatus status;

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public FoodMenuStatus getStatus() {
    return status;
  }

  public void setStatus(FoodMenuStatus status) {
    this.status = status;
  }
}

