package lk.elevenzcode.bms.restaurant.dto;

public class SearchCriteriaDto {
    private Integer typeId, statusId;

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


