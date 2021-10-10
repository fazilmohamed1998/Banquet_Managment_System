package lk.elevenzcode.bms.payment.dto;

import lk.elevenzcode.bms.common.util.DateUtil;
import lk.elevenzcode.bms.payment.model.PaymentRefund;

/**
 * @author HaShaN on 8/31/2019 10:55 AM.
 */
public class PaymentRefundInfoDto {
  private String refundBy;
  private String refundOn;

  public static PaymentRefundInfoDto parse(PaymentRefund refund) {
    PaymentRefundInfoDto refundInfoDto = new PaymentRefundInfoDto();
    refundInfoDto.setRefundBy(refund.getRefundBy().getName());
    refundInfoDto.setRefundOn(DateUtil.formatToWebDateTimeMMM(refund.getRefundOn()));
    return refundInfoDto;
  }

  public String getRefundBy() {
    return refundBy;
  }

  public void setRefundBy(String refundBy) {
    this.refundBy = refundBy;
  }

  public String getRefundOn() {
    return refundOn;
  }

  public void setRefundOn(String refundOn) {
    this.refundOn = refundOn;
  }
}
