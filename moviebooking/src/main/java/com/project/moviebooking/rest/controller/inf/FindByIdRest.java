///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.daynilgroup.mycity.rest.controller.inf;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.daynilgroup.mycity.rest.controller.inf.embedded.AbstractInf;
//import com.daynilgroup.mycity.rest.exception.DataNotFoundRestException;
//
///**
// *
// * @author Uday
// * @param <E>
// */
//public interface FindByIdRest<E> extends AbstractInf {
//
//    @GetMapping(path = "/findById/{id}")
//    default ResponseEntity<Object> findByIdPublic(@PathVariable Long id) {
//        try {
//            Object response = getHelper().getDetailResponse(getService().findById(id));
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(new DataNotFoundRestException(e.getMessage()),
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
