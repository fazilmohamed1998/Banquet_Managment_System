package lk.elevenzcode.bms.common.dao;

import lk.elevenzcode.bms.common.exception.DataAccessException;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by hashan on 3/8/19 12:06 PM
 */
public interface GenericDao<T> {
  void insert(T model) throws DataAccessException;

  void update(T model) throws DataAccessException;

  T get(Integer id) throws DataAccessException;

  List<T> getAll() throws DataAccessException;

  void delete(T model) throws DataAccessException;

  void detach(T object) throws DataAccessException;

  Session getCurrentSession();

  void initialize(Object list);
}