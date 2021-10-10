package lk.elevenzcode.bms.reservation.dto;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.event.model.Event;
import lk.elevenzcode.bms.reservation.model.Reservation;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Harin on 10/3/2019 3:00 PM.
 */
public class ReservationReportData {
  private String reservationNo;
  private String customer;
  private String event;
  private String type;
  private String reservedOn;
  private String from;
  private String to;
  private String user;
  private String status;


  public static List<ReservationReportData> parse(List<Reservation> reservations) throws ServiceException{
      final List<ReservationReportData> reportDataList = new ArrayList<>();
      ReservationReportData reportData = new ReservationReportData();
      for (Reservation reservation : reservations) {
        Event event = reservation.getEvent();
        reportData.setReservationNo(reservation.getReservationNo());
        reportData.setCustomer(reservation.getCustomer().getName());
        reportData.setEvent(reservation.getEvent().getEventType().getType());
        reportData.setType(reservation.getEvent().getLocation().getName());
        reportData.setReservedOn(DateUtil.formatToWebDateMMM(reservation.getReservedOn()));
        reportData.setFrom(DateUtil.formatToWebDateMMM(event.getFrom()));
        reportData.setTo(DateUtil.formatToWebDateMMM(event.getTo()));
        reportData.setUser(reservation.getUser().getName());
        reportData.setStatus(reservation.getStatus().getName());
        reportDataList.add(reportData);
      }
      return reportDataList;
  }

  public String getReservationNo() {
    return reservationNo;
  }

  public void setReservationNo(String reservationNo) {
    this.reservationNo = reservationNo;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getReservedOn() {
    return reservedOn;
  }

  public void setReservedOn(String reservedOn) {
    this.reservedOn = reservedOn;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
