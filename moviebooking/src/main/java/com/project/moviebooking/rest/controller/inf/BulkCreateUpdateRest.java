///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.daynilgroup.mycity.rest.controller.inf;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.daynilgroup.mycity.annotation.CreateRequestValidator;
//import com.daynilgroup.mycity.model.request.inf.CreateRequest;
//import com.daynilgroup.mycity.repository.event.PostSave;
//import com.daynilgroup.mycity.rest.controller.inf.embedded.AbstractInf;
//import com.daynilgroup.mycity.rest.exception.SaveRestException;
//import com.daynilgroup.mycity.rest.exception.ValidationRestException;
//import com.daynilgroup.mycity.rest.validator.RequestValidator;
//
///**
// *
// * @author Uday
// */
//public interface BulkCreateUpdateRest<E, C extends CreateRequest> extends AbstractInf {
//
//    @PostMapping(path = "/bulkcreate")
//    default ResponseEntity<Object> bulkCreateAdmin(@RequestBody List<C> requestList) throws Exception {
//        try {
//            Map<Object, C> entityRequestMap = new HashMap();
//            List<Object> entityList = new ArrayList();
//            for (C request : requestList) {
//                Object entity = getHelper().getEntity(request);
//                entityList.add(entity);
//                entityRequestMap.put(entity, request);
//                List<String> errorMsgList = new ArrayList();
//                boolean createRequestValidator = request.getClass().isAnnotationPresent(CreateRequestValidator.class);
//                if (createRequestValidator && !RequestValidator.validate(request, errorMsgList)) {
//                    return new ResponseEntity<Object>(new ValidationRestException(errorMsgList),
//                            HttpStatus.INTERNAL_SERVER_ERROR);
//                }
//            }
//            getService().saveAll(entityList);
//            if (getHelper() instanceof PostSave) {
//                for (Map.Entry<Object, C> object : entityRequestMap.entrySet()) {
//                    if (((PostSave) getHelper()).postSaveAction(object.getKey(), object.getValue())) {
//                        getService().save(object.getKey());
//                    }
//                }
//            }
//            return new ResponseEntity<Object>(HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<Object>(new SaveRestException(e.getMessage()),
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}
