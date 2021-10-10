package lk.elevenzcode.bms.common.dto;

/**
 * @author HaShaN on 8/26/2019 9:51 PM.
 */
public class Pageable {
  private String sort, order;
  private int offset, limit;

  public Pageable(String sort, String order, int offset, int limit) {
    this.sort = sort;
    this.order = order;
    this.offset = offset;
    this.limit = limit;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }
}
