package lk.elevenzcode.bms.entity.service.impl;

import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.entity.listener.EntityRoleListener;
import lk.elevenzcode.bms.entity.model.Entity;
import lk.elevenzcode.bms.entity.model.Role;
import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
import lk.elevenzcode.bms.entity.service.EntityService;
import lk.elevenzcode.bms.entity.service.RolePermissionService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class EntityDetailsServiceImpl implements org.springframework.security.core.userdetails
        .UserDetailsService {
  private static final Logger logger = LoggerFactory.getLogger(EntityDetailsServiceImpl.class);

  @Autowired
  private EntityService entityService;

  @Autowired
  private EntityRoleListener[] roleListeners;

  @Autowired
  private RolePermissionService rolePermissionService;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    Entity entity = null;
    try {
      entity = entityService.getActiveEntity(username);
    } catch (ServiceException e) {
      throw new UsernameNotFoundException(username);
    }

    if (entity != null) {
      return buildUserForAuthentication(entity);
    } else {
      throw new UsernameNotFoundException(username);
    }
  }

  // Converts User user to
  // org.springframework.security.core.userdetails.User
  private User buildUserForAuthentication(Entity entity) {
    final List<GrantedAuthority> authorities = new ArrayList<>();
    Role role = null;
    try {
      if (ArrayUtils.isNotEmpty(roleListeners)) {
        for (EntityRoleListener roleListener : roleListeners) {
          role = roleListener.getRole(entity.getId());
          if (role != null) {
            authorities.addAll(CollectionUtils.collect(rolePermissionService.getByRole(role.getId()),
                    rolePermission -> new SimpleGrantedAuthority("ROLE_" + rolePermission
                            .getPermission().getCode())));
            break;
          }
        }
      }

      if (role != null) {
        authorities.add(new SimpleGrantedAuthority("ROLE_" + StringUtils.upperCase(role.getName())));
      }

      for (GrantedAuthority authority : authorities) {
        if (logger.isDebugEnabled()) {
          logger.debug("{}", authority.getAuthority());
        }
      }

    } catch (ServiceException e) {
      logger.error("Error ", e);
    }

    return new EntityDetail(entity.getId(), entity.getName(), entity.getUserName(),
            entity.getPassword(), true, true, true, true, authorities);
  }
}