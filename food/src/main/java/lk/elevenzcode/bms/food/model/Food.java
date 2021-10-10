package lk.elevenzcode.bms.food.model;

import lk.elevenzcode.bms.common.model.EntityBase;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "food", schema = "sch_food_mgmt")
@NamedQueries({
  @NamedQuery(
    name = "Food.search",
    query = "SELECT f FROM Food f JOIN f.cuisine cu JOIN f.category c"
  )
})
public class Food extends EntityBase implements Serializable {
  @Transient
  MultipartFile image;

  /*this will be the food name column in food table*/
  /*in here length is maximum no of characters that column(when column is a string(varchar)) will contain*/
  /*nullable = false means whether column allows null values or not*/
  @Column(name = "name", nullable = false, unique = true, length = 50)
  private String name;

  /*this will be the food price column in food table*/
  @Column(name = "price", nullable = false)
  /*since price is a decimal value here we define how many integer numbers & floating point numbers, column contain*/
  @Digits(integer = 11, fraction = 2)
  private BigDecimal price;

  /*this is the many to one mapping with food cuisine(Food cuisine has many foods) since we keep cuisine in a separate table,
   need to map food table like below. so in food table has column named as cuisine_id*/
  @ManyToOne
  @JoinColumn(name = "cuisine_id", nullable = false)
  private FoodCuisine cuisine;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private FoodCategory category;

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

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public FoodCuisine getCuisine() {
    return cuisine;
  }

  public void setCuisine(FoodCuisine cuisine) {
    this.cuisine = cuisine;
  }

  public FoodCategory getCategory() {
    return category;
  }

  public void setCategory(FoodCategory category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "Food{" +
      "image=" + image +
      ", name='" + name + '\'' +
      ", price=" + price +
      ", cuisine=" + cuisine +
      ", category=" + category +
      '}';
  }
}
