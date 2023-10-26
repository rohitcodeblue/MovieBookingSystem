///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.daynilgroup.mycity.rest.controller.inf;
//
//import com.daynilgroup.mycity.model.PageModel;
//import com.daynilgroup.mycity.model.inf.ListResponse;
//import com.daynilgroup.mycity.rest.controller.inf.embedded.DefaultInf;
//import com.daynilgroup.mycity.rest.exception.DataNotFoundRestException;
//import java.util.List;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//
///**
// *
// * @author Uday
// */
//public interface FindByCityIdRest<E> extends DefaultInf{
//
//
//    @GetMapping(path = "/findByCityId/{cityId}")
//    default ResponseEntity<Object> findByCityId(@PathVariable Long cityId,
//            @RequestParam(name = "pageNumber", required = false) Integer pageNumber,
//            @RequestParam(name = "pageSize", required = false) Integer pageSize,
//            @RequestParam(name = "sortBy", required = false) String sortBy,
//            @RequestParam(name = "sortDirection", required = false) String sortDirection
//    ) {
//        Sort sort = null;
//        if (sortBy != null) {
//            if (sortDirection == null || Sort.Direction.fromString(sortDirection) == null) {
//                sort = Sort.by(Sort.Direction.DESC, sortBy);
//            } else {
//                sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
//            }
//        }
//        Pageable pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, pageSize != null ? pageSize : 10);
//        if (sort != null) {
//            pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, pageSize != null ? pageSize : 10, sort);
//        }
//        try {
//            Page<E> page = getService().findByCity(cityId, pageable);
//            List<ListResponse> list = getHelper().getListItemTypeResponse(page.getContent());
//            PageModel.PageModelBuilder builder = PageModel.builder()
//                    .data(list)
//                    .pageCount(Long.valueOf(page.getTotalPages()))
//                    .totalCount(page.getTotalElements());
//            return new ResponseEntity<Object>(builder.build(), HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<Object>(new DataNotFoundRestException(e.getMessage()),
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}
