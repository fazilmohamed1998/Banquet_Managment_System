package lk.elevenzcode.bms.banquethall.dto;

/**
 * Created by hashan on 9/27/19 12:33 PM
 */
public class SearchCriteriaDto {
  private Integer typeId, statusId;

  public SearchCriteriaDto() {
  }

  public SearchCriteriaDto(Integer typeId, Integer statusId) {
    this.typeId = typeId;
    this.statusId = statusId;
  }

  public Integer getTypeId() {
    return typeId;
  }

  public void setTypeId(Integer typeId) {
    this.typeId = typeId;
  }

  public Integer getStatusId() {
    return statusId;
  }

  public void setStatusId(Integer statusId) {
    this.statusId = statusId;
  }

  @Override
  public String toString() {
    return "SearchCriteriaDto{" +
        "typeId=" + typeId +
        ", statusId=" + statusId +
        '}';
  }
}
