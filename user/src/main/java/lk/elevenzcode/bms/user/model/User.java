package lk.elevenzcode.bms.user.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "[user]", schema = "sch_user")
@PrimaryKeyJoinColumn(name = "entity_id", referencedColumnName = "id")
@NamedQueries({
  @NamedQuery(
    name = "User.search",
    query = "SELECT u FROM User u JOIN u.userRole r JOIN u.status s"
  )
})
public class User extends lk.elevenzcode.bms.entity.model.Entity implements Serializable {


  @Transient
  MultipartFile image;

  @Column(name = "epf_no", unique = true)
  private String epfNo;

  @Column(name = "contact_no", unique = true)
  private String contactNo;

  @Column(name = "join_date", nullable = false)
  private Date joinDate;

  @ManyToOne
  @JoinColumn(name = "user_role_id", referencedColumnName = "role_id")
  private UserRole userRole;

  public User() {
  }

  public User(Integer id) {
    super(id);
  }

  public String getEpfNo() {
    return epfNo;
  }

  public void setEpfNo(String epfNo) {
    this.epfNo = epfNo;
  }

  public String getContactNo() {
    return contactNo;
  }

  public void setContactNo(String contactNo) {
    this.contactNo = contactNo;
  }

  public Date getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(Date joinDate) {
    this.joinDate = joinDate;
  }


  public UserRole getUserRole() {
    return userRole;
  }

  public void setUserRole(UserRole userRole) {
    this.userRole = userRole;
  }

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }


  @Override
  public String toString() {
    return "User{" +
      "epfNo='" + epfNo + '\'' +
      ", contactNo=" + contactNo +
      ", joinDate=" + joinDate +
      ", userRole=" + userRole +
      ", image=" + (image != null ? image.getOriginalFilename() : "") +
      "}\n"
      +super.toString();
  }
}
