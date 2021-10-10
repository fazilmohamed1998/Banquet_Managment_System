package lk.elevenzcode.bms.banquethall.dto;

import lk.elevenzcode.bms.banquethall.model.BanquetHall;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hashan on 9/27/19 4:22 PM
 */
public class BanquetHallReportData {
  private String hallName, capacity, type, status;

  public static List<BanquetHallReportData> parse(List<BanquetHall> banquetHalls) {
    final List<BanquetHallReportData> reportDataList = new ArrayList<>();
    BanquetHallReportData reportData;
    for (BanquetHall banquetHall : banquetHalls) {
      reportData = new BanquetHallReportData();
      reportData.setHallName(banquetHall.getName());
      reportData.setCapacity(String.format("%d Tables", banquetHall.getCapacity()));
      reportData.setType(banquetHall.getType().getType());
      reportData.setStatus(banquetHall.getStatus().getName());
      reportDataList.add(reportData);
    }
    return reportDataList;
  }

  public String getHallName() {
    return hallName;
  }

  public void setHallName(String hallName) {
    this.hallName = hallName;
  }

  public String getCapacity() {
    return capacity;
  }

  public void setCapacity(String capacity) {
    this.capacity = capacity;
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
