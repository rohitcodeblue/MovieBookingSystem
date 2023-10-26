///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.daynilgroup.mycity.rest.controller.inf;
//
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import com.daynilgroup.mycity.constants.LocaleEnum;
//import com.daynilgroup.mycity.rest.exception.DataNotFoundRestException;
//import com.daynilgroup.mycity.rest.helper.abs.LocalizedHelper;
//
//import io.swagger.annotations.ApiOperation;
//
///**
// *
// * @author bhavesh
// * @param <Entity>
// */
//public interface LocalizedRestInf<Entity> {
//
//	LocalizedHelper getLocalizedHelper();
//
//	@PostMapping("localized")
//	@ApiOperation(value = "Saves the localized data")
//	default ResponseEntity<Object> localizedSave(@RequestBody Entity request) {
//		try {
//			Object save = getLocalizedHelper().save(request);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(new DataNotFoundRestException(e.getMessage()),
//					HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
////	@GetMapping("localized/{id}/{locale}")
////	@ApiOperation(value = "Returns localized data based on id and locale")
////	default ResponseEntity<Object> localizedFind(@PathVariable Long id, @PathVariable LocaleEnum locale) {
////		try {
////			Map<Object, Object> localizedFind = getLocalizedHelper().localizedFind(id, locale);
////			return new ResponseEntity<>(localizedFind, HttpStatus.OK);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return new ResponseEntity<>(new DataNotFoundRestException(e.getMessage()),
////					HttpStatus.INTERNAL_SERVER_ERROR);
////		}
////	}
//
////	@PostMapping("localized/bulk/{locale}")
////	@ApiOperation(value = "Returns localized data based on Locale and id")
////	default ResponseEntity<Object> localizedBulkFind(@RequestBody List<Long> ids, @PathVariable LocaleEnum locale) {
////		try {
////			List<Object> localizedFind = getLocalizedHelper().localizedFind(ids, locale);
////			return new ResponseEntity<>(localizedFind, HttpStatus.OK);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return new ResponseEntity<>(new DataNotFoundRestException(e.getMessage()),
////					HttpStatus.INTERNAL_SERVER_ERROR);
////		}
////	}
//
//}
