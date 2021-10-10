package lk.elevenzcode.bms.common.service;

import lk.elevenzcode.bms.common.exception.ServiceException;

import java.util.List;

public interface GenericService<T> {
  void insert(T model) throws ServiceException;

  void update(T model) throws ServiceException;

  T get(Integer id) throws ServiceException;

  List<T> getAll() throws ServiceException;

  void delete(T model) throws ServiceException;
}
