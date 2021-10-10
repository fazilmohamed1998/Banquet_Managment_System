package lk.elevenzcode.bms.customer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author HaShaN on 8/31/2019 12:14 PM.
 */
@Entity
@Table(name = "customer_contact", schema = "sch_customer_mgmt")
@NamedQueries({
  //TODO add HQL queries here
})
public class CustomerContact implements Serializable {
  @Id
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = false)
  private Customer customer;

  @Id
  @Column(name = "contact_number", length = 15, nullable = false)
  private String contactNumber;

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerContact that = (CustomerContact) o;
    return customer.equals(that.customer) &&
      contactNumber.equals(that.contactNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customer, contactNumber);
  }
}
