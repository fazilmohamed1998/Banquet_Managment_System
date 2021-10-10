package lk.elevenzcode.bms.user.dao;

import lk.elevenzcode.bms.common.dao.GenericDao;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.user.dto.SearchCriteriaDto;
import lk.elevenzcode.bms.user.model.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
  List<User> search(SearchCriteriaDto searchCriteriaDto) throws DataAccessException;
}