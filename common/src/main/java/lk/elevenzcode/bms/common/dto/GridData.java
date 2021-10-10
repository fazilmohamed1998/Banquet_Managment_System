package lk.elevenzcode.bms.common.dto;

import java.util.List;

/**
 * @author HaShaN on 8/26/2019 9:45 PM.
 */
public class GridData<T> {
  private List<T> rows;
  private long total;

  public List<T> getRows() {
    return rows;
  }

  public void setRows(List<T> rows) {
    this.rows = rows;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }
}
