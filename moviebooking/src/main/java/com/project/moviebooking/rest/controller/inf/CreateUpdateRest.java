package com.project.moviebooking.rest.controller.inf;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.moviebooking.rest.controller.inf.embedded.Create;

/**
 *
 * @author Rohit
 */
public interface CreateUpdateRest<E, C > extends Create<E, C> {

    @PostMapping(path = "/create")
    default ResponseEntity<Object> create(@RequestBody C request) throws Exception {
        return Create.super.create(request);
    }

}
