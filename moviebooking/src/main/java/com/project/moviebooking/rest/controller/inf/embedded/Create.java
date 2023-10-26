package com.project.moviebooking.rest.controller.inf.embedded;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.project.moviebooking.entity.mappedclass.BaseEntity;
import com.project.moviebooking.exception.SaveRestException;
import com.project.moviebooking.rest.helper.AbstractHelper;
import com.project.moviebooking.service.AbstractService;

/**
 *
 * @author Rohit
 * @param <E>
 * @param <C>
 */
public interface Create<E, C> extends AbstractInf {

	public AbstractService getService();

    public AbstractHelper getHelper();

	@Transactional
    default public ResponseEntity<Object> create(C request) throws Exception {
        try {
            Object entity = getHelper().getEntity(request);
            entity = getService().save(entity);
            getService().save(entity);
            Long entityId = null;
            if (entity instanceof BaseEntity) {
                entityId = ((BaseEntity) entity).getId();
            }
            return new ResponseEntity<>(entityId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new SaveRestException(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}