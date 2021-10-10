package lk.elevenzcode.bms.food.dto;

import lk.elevenzcode.bms.common.util.ConversionUtil;
import lk.elevenzcode.bms.food.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodReportData {
  private int id;
  private String price, name, category, cuisine;

  public static List<FoodReportData> parse(List<Food> foods) {
    final List<FoodReportData> reportDataList = new ArrayList<>();
    FoodReportData reportData;
    for (Food food : foods) {
      reportData = new FoodReportData();
      reportData.setId(food.getId());
      reportData.setPrice(ConversionUtil.getMoneyWithThousandSeparator(food.getPrice()));
      reportData.setName(food.getName());
      reportData.setCategory(food.getCategory().getName());
      reportData.setCuisine(food.getCuisine().getType());
      reportDataList.add(reportData);
    }
    return reportDataList;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCuisine() {
    return cuisine;
  }

  public void setCuisine(String cuisine) {
    this.cuisine = cuisine;
  }
}
