package lk.elevenzcode.bms.user.dto;

public class SearchCriteriaDto {
    private Integer roleId, statusId;

    public SearchCriteriaDto() {
    }

    public SearchCriteriaDto(Integer roleId, Integer statusId) {
        this.roleId = roleId;
        this.statusId = statusId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
                "roleId=" + roleId +
                ", statusId=" + statusId +
                '}';
    }
}
