package lk.elevenzcode.bms.common.util;

import org.springframework.http.MediaType;

public interface Constant {
  String TRANSACTION_MANAGER = "transactionManager";
  String ACCEPT = "Accept";
  String REQ_HEADER_ACCEPT_JSON = ACCEPT + "=" + MediaType.APPLICATION_JSON_VALUE;
  String REQ_HEADER_ACCEPT_HTML = ACCEPT + "=" + MediaType.TEXT_HTML_VALUE;
  String LOGO_PATH = "/static/app/img/logo.png";
  String REFUND_IMG_PATH = "/static/app/img/refund.png";
}
