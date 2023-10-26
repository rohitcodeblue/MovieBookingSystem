///**
// * 
// */
//package com.daynilgroup.mycity.rest.controller.inf;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.daynilgroup.mycity.entity.mappedclass.BaseEntity;
//import com.daynilgroup.mycity.rest.controller.inf.embedded.AbstractInf;
//import com.daynilgroup.mycity.rest.exception.DeleteRestException;
//
//import io.swagger.annotations.ApiOperation;
//
///**
// * @author bhavesh
// *
// */
//public interface DeleteByIdRestInf extends AbstractInf {
//
//	@DeleteMapping("/{id}")
//	@ApiOperation(value = "Delete the record based on id")
//	default ResponseEntity<Object> deleteById(@PathVariable Long id) {
//		try {
//			Object entity = getService().findById(id);
//			entity = getService().delete((BaseEntity)entity);
//			return new ResponseEntity<>(((BaseEntity) entity).getDeleted(), HttpStatus.OK);
//		} catch (Exception e) {
//			BASE_LOGGER.error(e.getMessage(), e);
//			return new ResponseEntity<>(new DeleteRestException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//}
