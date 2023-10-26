///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.daynilgroup.mycity.rest.controller.inf;
//
//import com.daynilgroup.mycity.rest.controller.inf.embedded.FindAll;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// *
// * @author Uday
// */
//public interface FindAllRest extends FindAll {
//
//    @GetMapping(path = "/findAll")
//    @Override
//    default ResponseEntity<Object> findAll(@RequestParam(name = "pageNumber", required = false) Integer pageNumber,
//            @RequestParam(name = "pageSize", required = false) Integer pageSize,
//            @RequestParam(name = "sortBy", required = false) String sortBy,
//            @RequestParam(name = "sortDirection", required = false) String sortDirection) {
//        return FindAll.super.findAll(pageNumber, pageSize, sortBy, sortDirection);
//    }
//
//}
