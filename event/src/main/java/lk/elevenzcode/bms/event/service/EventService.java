package lk.elevenzcode.bms.event.service;

import lk.elevenzcode.bms.common.dto.Pageable;
import lk.elevenzcode.bms.common.exception.ServiceException;
import lk.elevenzcode.bms.common.service.GenericService;
import lk.elevenzcode.bms.event.model.Event;

import java.util.List;

public interface EventService extends GenericService<Event> {
    List<Event> getList(Pageable pageable, String criterial) throws ServiceException;

    void delete(int id)throws ServiceException;

    List<Event> getList(int excludeStatus)throws ServiceException;
}