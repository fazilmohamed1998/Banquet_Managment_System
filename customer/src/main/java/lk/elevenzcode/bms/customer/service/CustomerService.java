package lk.elevenzcode.bms.customer.service;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.GenericService;
import lk.elevenzcode.bms.customer.model.Customer;

public interface CustomerService extends GenericService<Customer> {
  void save(Customer customer) throws ServiceException;

}
