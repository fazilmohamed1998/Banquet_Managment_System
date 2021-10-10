package lk.elevenzcode.bms.customer.service.impl;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.impl.GenericServiceImpl;
import lk.elevenzcode.bms.customer.dao.CustomerDao;
import lk.elevenzcode.bms.customer.model.Customer;
import lk.elevenzcode.bms.customer.model.CustomerContact;
import lk.elevenzcode.bms.customer.model.enums.CustomerStatus;
import lk.elevenzcode.bms.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer> implements CustomerService {
  @Autowired
  private CustomerDao customerDao;

  @PostConstruct
  void init() {
    init(customerDao);
  }

  @Override
  public void save(Customer customer) throws ServiceException {
    if (customer != null) {
      if (customer.getId() != null && customer.getId() > 0) {
        update(customer);
      } else {
        for (CustomerContact customerContact : customer.getContactList()) {
          customerContact.setCustomer(customer);
        }
        customer.setStatus(new lk.elevenzcode.bms.customer.model.CustomerStatus(CustomerStatus.ACTIVE.getId()));
        insert(customer);
      }
    } else {
      throw new ServiceException(ServiceException.VALIDATION_FAILURE, "err.customer.details.required");
    }
  }
}
