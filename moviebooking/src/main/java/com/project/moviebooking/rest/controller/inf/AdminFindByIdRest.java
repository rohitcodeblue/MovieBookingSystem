//package com.project.moviebooking.rest.controller.inf;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.project.moviebooking.exception.DataNotFoundRestException;
//import com.project.moviebooking.rest.controller.inf.embedded.AbstractInf;
//
//
///**
// *
// * @author Uday
// */
//public interface AdminFindByIdRest extends AbstractInf {
//
//    @GetMapping(path = "/findById/{id}")
//    default ResponseEntity<Object> findById(@PathVariable Long id) {
//        try {
//            Object response = getHelper().getDetailForAdminResponse(getService().findById(id));
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(new DataNotFoundRestException(e.getMessage()),
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}
