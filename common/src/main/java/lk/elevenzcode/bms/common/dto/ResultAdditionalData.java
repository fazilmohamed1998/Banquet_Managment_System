package lk.elevenzcode.bms.common.dto;

/**
 * @author HaShaN on 8/31/2019 3:53 PM.
 */
public class ResultAdditionalData {
  private Long count = new Long(0);

  public Long getCount() {
    return count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public void addCount(Long count) {
    this.count += count != null ? count : 0;
  }
}
