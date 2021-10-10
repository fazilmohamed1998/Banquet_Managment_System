package lk.elevenzcode.bms.common.service.impl;

import lk.elevenzcode.bms.common.dao.GenericDao;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.GenericService;
import lk.elevenzcode.bms.common.util.Constant;
import lk.elevenzcode.bms.entity.remote.dto.EntityDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Transactional(value = Constant.TRANSACTION_MANAGER, propagation = Propagation.REQUIRED,
  rollbackFor = ServiceException.class)
public class GenericServiceImpl<T> implements GenericService<T> {
  private GenericDao<T> genericDao;
  @Qualifier("messageSource")
  @Autowired
  protected MessageSource messageSource;
  private Locale locale = LocaleContextHolder.getLocale();

  public static EntityDetail getLoggedEntity() {
    final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return principal != null && principal instanceof EntityDetail ? (EntityDetail) principal : null;
  }

  protected void init(GenericDao<T> dao) {
    genericDao = dao;
  }

  @Override
  public void insert(T model) throws ServiceException {
    try {
      genericDao.insert(model);
    } catch (DataAccessException e) {
      throw new ServiceException(e.getMessage(), e.getCause());
    }
  }

  @Override
  public void update(T model) throws ServiceException {
    try {
      genericDao.update(model);
    } catch (DataAccessException e) {
      throw new ServiceException(e.getMessage(), e.getCause());
    }
  }

  @Override
  public T get(Integer id) throws ServiceException {
    try {
      return genericDao.get(id);
    } catch (DataAccessException e) {
      throw new ServiceException(e.getMessage(), e.getCause());
    }
  }

  @Override
  public List<T> getAll() throws ServiceException {
    try {
      return genericDao.getAll();
    } catch (DataAccessException e) {
      throw new ServiceException(e.getMessage(), e.getCause());
    }
  }

  @Override
  public void delete(T model) throws ServiceException {
    try {
      genericDao.delete(model);
    } catch (DataAccessException e) {
      throw new ServiceException(e.getMessage(), e.getCause());
    }
  }

  public String getMessage(String msg, String... args) {
    return messageSource.getMessage(msg, args, locale);
  }
}
