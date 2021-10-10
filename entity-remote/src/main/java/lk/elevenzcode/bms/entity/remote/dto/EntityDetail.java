package lk.elevenzcode.bms.entity.remote.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

public class EntityDetail extends User implements Serializable {
  private Integer id;
  private String name;

  public EntityDetail(Integer id, String name, String username,
                      String password, boolean enabled, boolean accountNonExpired, boolean
                              credentialsNonExpired, boolean accountNonLocked, Collection<? extends
          GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired,
            accountNonLocked, authorities);
    this.id = id;
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
