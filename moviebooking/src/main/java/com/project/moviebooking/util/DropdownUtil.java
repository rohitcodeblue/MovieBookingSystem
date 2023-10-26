package com.project.moviebooking.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.project.moviebooking.annotation.EnumLabel;
import com.project.moviebooking.constant.Constants;
import com.project.moviebooking.exception.DataNotFoundRestException;
import com.project.moviebooking.model.DropdownEnumModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@SuppressWarnings(value = { "unchecked", "rawtypes" })
public class DropdownUtil {

	public ResponseEntity<Object> getResponseEntity(Class enumClass) {
		return getResponseEntity(enumClass, false);
	}

	public ResponseEntity<Object> getResponseEntity(Class enumClass, boolean noSort) {
		try {
			return new ResponseEntity(getResponse(enumClass, noSort), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity(new DataNotFoundRestException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<DropdownEnumModel> getResponse(Class enumClass, boolean noSort) {
		EnumLabel enumLabel = (EnumLabel) enumClass.getAnnotation(EnumLabel.class);
		Field labelField = FieldUtils.getField(enumClass, enumLabel != null ? enumLabel.name() : Constants.DISPLAY_NAME,
				true);
		List<DropdownEnumModel> responses = new ArrayList<>();
		List<Enum> enums = EnumUtils.getEnumList(enumClass);
		enums.forEach(value -> {
			try {
				String label;
				if (labelField != null) {
					label = FieldUtils.readField(value, labelField.getName(), true).toString();
				} else {
					label = WordUtils.capitalizeFully(value.toString().replace("_", " "));
				}
				if (!CommonUtil.isEmpty(label)) {
					responses.add(new DropdownEnumModel(label, value.toString()));
				}
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
			}
		});
		return responses;
	}

	public ResponseEntity<Object> getResponseEntity(Class enumClass, List<String> enumStringList, Boolean isRequired) {
		return getResponseEntity(enumClass, false, enumStringList, isRequired);
	}

	public ResponseEntity<Object> getResponseEntity(Class enumClass, boolean noSort, List<String> enumStringList,
			Boolean isRequired) {
		try {
			return new ResponseEntity(getResponse(enumClass, noSort, enumStringList, isRequired), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return new ResponseEntity(new DataNotFoundRestException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public List<DropdownEnumModel> getResponse(Class enumClass, boolean noSort, List<String> enumStringList,
			Boolean isRequired) {
		EnumLabel enumLabel = (EnumLabel) enumClass.getAnnotation(EnumLabel.class);
		Field labelField = FieldUtils.getField(enumClass, enumLabel != null ? enumLabel.name() : Constants.DISPLAY_NAME,
				true);
		List<DropdownEnumModel> responses = new ArrayList<>();
		List<Enum> enums = EnumUtils.getEnumList(enumClass);
		enums.forEach(value -> {
			try {
				String label;
				if (labelField != null) {
					label = FieldUtils.readField(value, labelField.getName(), true).toString();
				} else {
					label = WordUtils.capitalizeFully(value.toString().replace("_", " "));
				}
				if (!CommonUtil.isEmpty(label) && validateEnum(label, isRequired, enumStringList)) {
					responses.add(new DropdownEnumModel(label, value.toString()));
				}
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
			}
		});
		return responses;
	}

	private boolean validateEnum(String label, Boolean isRequired, List<String> enumStringList) {
		if (!enumStringList.isEmpty() && enumStringList.contains(label)) {
			return isRequired;
		}
		return !isRequired;
	}

	public List<Enum> getEnumList(Class enumClass) {
		try {
			return EnumUtils.getEnumList(enumClass);
		} catch (Exception e) {
			return null;
		}
	}

}
