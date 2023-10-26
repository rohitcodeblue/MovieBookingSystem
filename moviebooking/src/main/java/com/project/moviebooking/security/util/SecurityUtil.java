package com.project.moviebooking.security.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.project.moviebooking.constant.Constants;
import com.project.moviebooking.constant.LocaleEnum;
import com.project.moviebooking.security.model.CustomUserDetail;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Rohit
 */
@Slf4j
public class SecurityUtil {

    public static LocaleEnum getLocaleEnum() {
        LocaleEnum locale = Constants.DEFAULT_LOCALE;
        try {
			if (SecurityContextHolder.getContext().getAuthentication() != null) {
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (principal instanceof CustomUserDetail) {
					CustomUserDetail customUserDetail = (CustomUserDetail) principal;
					locale = customUserDetail.getLocale();
				}
			}
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return locale;
    }

}
