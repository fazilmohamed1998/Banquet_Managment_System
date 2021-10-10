package lk.elevenzcode.bms.restaurant.dto;

import lk.elevenzcode.bms.restaurant.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantReportData {
    private String  type, status;

    public static List<RestaurantReportData> parse(List<Restaurant> table) {
        final List<RestaurantReportData> reportDataList = new ArrayList<>();
        RestaurantReportData reportData;
        for (Restaurant restaurant : table) {
            reportData = new RestaurantReportData();
            reportData.setType(restaurant.getType().getType());
            reportData.setStatus(restaurant.getStatus().getName());
            reportDataList.add(reportData);
        }
        return reportDataList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}



