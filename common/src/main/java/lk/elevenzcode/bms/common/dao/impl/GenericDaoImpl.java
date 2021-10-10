package lk.elevenzcode.bms.common.dao.impl;

import lk.elevenzcode.bms.common.dao.GenericDao;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hashan on 3/8/19 1:02 PM
 */
@SuppressWarnings("unchecked")
public class GenericDaoImpl<T> implements GenericDao<T> {
  private static final Logger logger = LoggerFactory.getLogger(GenericDaoImpl.class);
  private final Class<? extends T> type;
  @Autowired
  private SessionFactory sessionFactory;

  public GenericDaoImpl(Class<? extends T> type) {
    this.type = type;
  }

  @Override
  public void insert(T model) throws DataAccessException {
    try {
      getCurrentSession().persist(model);
    } catch (Exception e) {
      throw new DataAccessException(DataAccessException.DATA_ACCESS_FAILED, e.getMessage(), e);
    }
  }

  @Override
  public void update(T model) throws DataAccessException {
    try {
      getCurrentSession().merge(model);
    } catch (Exception e) {
      throw new DataAccessException(DataAccessException.DATA_ACCESS_FAILED, e.getMessage(), e);
    }
  }

  @Override
  public T get(Integer id) throws DataAccessException {
    try {
      return (T) getCurrentSession().get(type, id);
    } catch (Exception e) {
      throw new DataAccessException(DataAccessException.DATA_ACCESS_FAILED, e.getMessage(), e);
    }
  }

  @Override
  public List<T> getAll() throws DataAccessException {
    try {
      return (List<T>) getCurrentSession().createQuery("SELECT o FROM " + type.getName() + " o")
        .list();
    } catch (Exception e) {
      throw new DataAccessException(DataAccessException.DATA_ACCESS_FAILED, e.getMessage(), e);
    }
  }

  @Override
  public void delete(T model) throws DataAccessException {
    try {
      getCurrentSession().delete(model);
    } catch (Exception e) {
      throw new DataAccessException(DataAccessException.DATA_ACCESS_FAILED, e.getMessage(), e);
    }
  }

  @Override
  public void detach(T object) throws DataAccessException {
    try {
      getCurrentSession().evict(object);
    } catch (Exception e) {
      throw new DataAccessException(DataAccessException.DATA_ACCESS_FAILED, e.getMessage(), e);
    }
  }

  @Override
  public Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

  @Override
  public void initialize(Object list) {
    Hibernate.initialize(list);
  }
}
