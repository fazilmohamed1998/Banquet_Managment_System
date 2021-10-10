package lk.elevenzcode.bms.event.dao;

import lk.elevenzcode.bms.common.dao.GenericDao;
import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.exception.DataAccessException;
import lk.elevenzcode.bms.event.model.Event;

import java.util.List;

public interface EventDao extends GenericDao<Event> {
    List<Event> getList(Pageable pageable, String criterial)throws DataAccessException;

    List<Event> getList(int excludeStatus)throws DataAccessException;
}
