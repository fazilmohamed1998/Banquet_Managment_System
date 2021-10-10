package lk.elevenzcode.bms.reservation.dto;

import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.reservation.model.Reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Harin on 8/30/2019
 */
public class ReservationDataDto {
    public static final Map<String, String> sortMap = new HashMap<>();

    static {
        sortMap.put("id", "id");
        sortMap.put("reservationMethod", "reservationMethod.id");
        sortMap.put("payOn", "paidOn");
        sortMap.put("status", "status");
    }

    private int id;
    private String customer, reservationNo, event, reservedOn, reservedBy, user, status, type;

    public static List<ReservationDataDto> parse(List<Reservation> reservations) {
        final List<ReservationDataDto> reservationDataDtoList = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationDataDtoList.add(parse(reservation));
        }
        return reservationDataDtoList;
    }

    public static ReservationDataDto parse(Reservation reservation) {
        ReservationDataDto reservationDataDto = new ReservationDataDto();
        reservationDataDto.setId(reservation.getId());
        reservationDataDto.setReservationNo(reservation.getReservationNo());
        reservationDataDto.setCustomer(reservation.getCustomer().getName());
        reservationDataDto.setEvent(reservation.getEvent().getEventType().getType());
        reservationDataDto.setReservedOn(DateUtil.formatToWebDateTimeMMM(reservation.getReservedOn()));
        reservationDataDto.setReservedBy(reservation.getUser().getName());
        reservationDataDto.setUser(reservation.getUser().getName());
        reservationDataDto.setStatus(reservation.getStatus().getName());
        reservationDataDto.setType(reservation.getType().getType());
        return reservationDataDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getReservedOn() {
        return reservedOn;
    }

    public void setReservedOn(String reservedOn) {
        this.reservedOn = reservedOn;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReservationNo() {
        return reservationNo;
    }

    public void setReservationNo(String reservationNo) {
        this.reservationNo = reservationNo;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public void setReservedBy(String reservedBy) {
        this.reservedBy = reservedBy;
    }
}
