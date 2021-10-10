package lk.elevenzcode.bms.customer.model;

import lk.elevenzcode.bms.common.model.EntityBase;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer", schema = "sch_customer_mgmt")
@NamedQueries({
  //TODO add HQL queries here
})
public class Customer extends EntityBase implements Serializable {
  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "address", nullable = false, length = 200)
  private String address;

  @Column(name = "email", nullable = false, length = 140)
  private String email;

  @Column(name = "nic", nullable = false, length = 12)
  private String nic;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CustomerContact> contactList;

  @ManyToOne
  @JoinColumn(name = "status", nullable = false)
  private CustomerStatus status;

  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private CustomerType type;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNic() {
    return nic;
  }

  public void setNic(String nic) {
    this.nic = nic;
  }

  public List<CustomerContact> getContactList() {
    return contactList;
  }

  public void setContactList(List<CustomerContact> contactList) {
    this.contactList = contactList;
  }

  public CustomerStatus getStatus() {
    return status;
  }

  public void setStatus(CustomerStatus status) {
    this.status = status;
  }

  public CustomerType getType() {
    return type;
  }

  public void setType(CustomerType type) {
    this.type = type;
  }
}
