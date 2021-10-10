package lk.elevenzcode.bms.payment.service;

import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.dto.ResultAdditionalData;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.GenericService;
import lk.elevenzcode.bms.payment.dto.ReportCriteriaDto;
import lk.elevenzcode.bms.payment.model.Payment;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;

public interface PaymentService extends GenericService<Payment> {
  List<Payment> getList(Pageable pageable, ResultAdditionalData additionalData, String criteria) throws ServiceException;

  long getCount(String criteria) throws ServiceException;

  void save(Payment payment) throws ServiceException;

  void saveWithNotif(Payment payment) throws ServiceException;

  void refund(int id) throws ServiceException;

  JasperPrint generateReceipt(Payment payment) throws ServiceException;

  JasperPrint generateReport(ReportCriteriaDto reportCriteria) throws ServiceException;

  void sendReceipt(Payment payment) throws ServiceException;

  void insertDemoData() throws ServiceException;
}
