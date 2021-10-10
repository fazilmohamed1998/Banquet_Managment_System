package lk.elevenzcode.bms.food.dto;

public class SearchCriteriaDto {
  private Integer categoryId, cuisineId;

  public Integer getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(Integer categoryId) {
    this.categoryId = categoryId;
  }

  public Integer getCuisineId() {
    return cuisineId;
  }

  public void setCuisineId(Integer cuisineId) {
    this.cuisineId = cuisineId;
  }

  @Override
  public String toString() {
    return "SearchCriteriaDto{" +
        "typeId=" + categoryId +
        ", statusId=" + cuisineId +
        '}';
  }
}
