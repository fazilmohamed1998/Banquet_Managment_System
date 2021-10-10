package lk.elevenzcode.bms.user.dto;

import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.user.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserReportData {
  private String name, username, role, joinDate, epfNo, status;

  public static List<UserReportData> parse(List<User> users) {
    List<UserReportData> dataList = new ArrayList<>();
    UserReportData userReportData;
    for (User user : users) {
      userReportData = new UserReportData();
      userReportData.setName(user.getName());
      userReportData.setUsername(user.getUserName());
      userReportData.setRole(user.getUserRole().getName());
      userReportData.setJoinDate(DateUtil.formatToWebDateMMM(user.getJoinDate()));
      userReportData.setEpfNo(user.getEpfNo());
      userReportData.setStatus(user.getStatus().getName());
      dataList.add(userReportData);
    }
    return dataList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(String joinDate) {
    this.joinDate = joinDate;
  }

  public String getEpfNo() {
    return epfNo;
  }

  public void setEpfNo(String epfNo) {
    this.epfNo = epfNo;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
