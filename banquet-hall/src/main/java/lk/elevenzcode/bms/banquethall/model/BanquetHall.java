package lk.elevenzcode.bms.banquethall.model;

import lk.elevenzcode.bms.common.model.EntityBase;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "banquet_hall", schema = "sch_banquet_hall_mgmt")
@NamedQueries( {
    @NamedQuery(
        name = "BanquetHall.search",
        query = "SELECT bh FROM BanquetHall bh JOIN bh.type t JOIN bh.status s"
    )
})
public class BanquetHall extends EntityBase implements Serializable {

  @Transient
  MultipartFile image;
  @Column(name = "name", nullable = false, unique = true, length = 50)
  private String name;
  @Column(name = "capacity", nullable = false)
  private short capacity;
  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false)
  private BanquetHallType type;
  @ManyToOne
  @JoinColumn(name = "status", nullable = false)
  private BanquetHallStatus status;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public short getCapacity() {
    return capacity;
  }

  public void setCapacity(short capacity) {
    this.capacity = capacity;
  }

  public BanquetHallType getType() {
    return type;
  }

  public void setType(BanquetHallType type) {
    this.type = type;
  }

  public BanquetHallStatus getStatus() {
    return status;
  }

  public void setStatus(BanquetHallStatus status) {
    this.status = status;
  }

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "BanquetHall{" +
        "name='" + name + '\'' +
        ", capacity=" + capacity +
        ", type=" + type +
        ", status=" + status +
        ", image=" + (image != null ? image.getOriginalFilename() : "") +
        '}';
  }


}
