package lk.elevenzcode.bms.customer.dao.impl;

import lk.elevenzcode.bms.common.dao.impl.GenericDaoImpl;
import lk.elevenzcode.bms.customer.dao.CustomerDao;
import lk.elevenzcode.bms.customer.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao {
  public CustomerDaoImpl() {
    super(Customer.class);
  }
}
