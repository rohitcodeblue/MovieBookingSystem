package com.project.moviebooking.util;

import java.awt.Menu;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.amazonaws.util.Base64;
import com.google.gson.Gson;
import com.project.moviebooking.constant.UserTypeEnum;
import com.project.moviebooking.exception.MovieException;
import com.project.moviebooking.security.model.CustomUserDetail;

import liquibase.repackaged.org.apache.commons.text.WordUtils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommonUtil {

	public static boolean isStringEmpty(Object object) {
		try {
			boolean isEmpty = false;
			if (object == null || ((String) object).trim().length() == 0)
				isEmpty = true;
			return isEmpty;
		} catch (NullPointerException e) {
			return true;
		}
	}

	public static boolean isEmpty(String string) {
		return StringUtils.isBlank(string);
	}

	public static boolean isEmpty(Object string) {
		return ObjectUtils.isEmpty(string);
	}

	public static boolean isValid(Long value) {
		return value != null && value > 0;
	}

	public static boolean isValid(Integer value) {
		return value != null && value > 0;
	}

	public static boolean isTrue(Boolean value) {
		return value != null && value;
	}

	public static boolean isValid(BigDecimal value) {
		return value != null && value.doubleValue() > 0;
	}

	public static boolean isValid(Double value) {
		return value != null && value > 0;
	}

	public static boolean isListEmpty(List<?> list) {
		boolean isEmpty = false;
		if (list == null || list.isEmpty())
			isEmpty = true;
		return isEmpty;
	}

	/**
	 * URL encode String
	 * 
	 * @param String url
	 * @return encoded string
	 * @throws UnsupportedEncodingException
	 */
	public static String encodeUrl(String url) {
		try {
			return URLEncoder.encode(url, Charset.defaultCharset().toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}

	/**
	 * URL decode String
	 * 
	 * @param String encodedUrl
	 * @return decoded string
	 * @throws UnsupportedEncodingException
	 */
	public static String decodeUrl(String encodedUrl) {
		try {
			return URLDecoder.decode(encodedUrl, Charset.defaultCharset().toString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodedUrl;
	}

	public static Boolean getIsDateExpired(Date validUptoDate2) {
		Date currentDate = new Date();
		long diff = DateUtil.diff(currentDate, validUptoDate2);
		if (diff > 0) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static String getEnvVariableByName(String name) {
		Map<String, String> env = System.getenv();
		String envName = env.get(name);
		return envName;
	}

	public static String generateRandomPassword() {
		// ASCII range - alphanumeric (0-9, a-z, A-Z)
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int randomIndex = random.nextInt(chars.length());
			sb.append(chars.charAt(randomIndex));
		}
		return sb.toString();
	}

	public static Long getCurrentUserId() {
		try {
			CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			return customUserDetail.getId();
		} catch (Exception e) {
//			return EntityUtil.getAnonymousUserID();
			return 3l;
		}
	}
	
	public static Long getCurrentUserIdWithoutAnonymousUserId() {
		try {
			CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			return customUserDetail.getId();
		} catch (Exception e) {
			return null;
		}
	}


	public static Object convertStringToJsonObject(Object string) {
		try {
			if (string instanceof String && !isStringEmpty(string) && !"null".equals(string)) {
				JSONParser parser = new JSONParser(string.toString());
				return parser.parse();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BigDecimal getAmount(Double percentage, BigDecimal amount) {
		BigDecimal percentageAmount = new BigDecimal(percentage);
		percentageAmount = percentageAmount.divide(new BigDecimal(100));
		return amount.multiply(percentageAmount);

	}

	public static String convertByteArrayToBase64(byte[] byteArray) throws UnsupportedEncodingException {
		if (byteArray == null) {
			return "";
		}
		return new String(Base64.encode(byteArray), "UTF-8");
	}

	public static String formatAmount(BigDecimal amount) {
		if (amount != null) {
			return rupeeFormat(String.valueOf(amount.doubleValue()));
		}
		return "";
	}

	public static String rupeeFormat(String value) {
		value = value.replace(",", "");
		String fractionValue = "";
		if (value.indexOf(".") > 0) {
			fractionValue = value.substring(value.indexOf(".") + 1);
//			Long fractionIntValue = Long.parseLong(fractionValue);
			BigDecimal fractionIntValue = new BigDecimal(fractionValue);
			if (BigDecimal.ZERO.equals(fractionIntValue)) {
				fractionValue = "";
			}
			value = value.substring(0, value.indexOf("."));
		}
		char lastDigit = value.charAt(value.length() - 1);
		String result = "";
		int len = value.length() - 1;
		int nDigits = 0;

		for (int i = len - 1; i >= 0; i--) {
			result = value.charAt(i) + result;
			nDigits++;
			if (((nDigits % 2) == 0) && (i > 0)) {
				result = "," + result;
			}
		}
		if (!fractionValue.isEmpty()) {
			String output = result + lastDigit + "." + fractionValue;
			return output;
		}
		return (result + lastDigit);
	}

	@SuppressWarnings("rawtypes")
	public static List<String> getCapitalizeFullyList(Enum... enums) {
		List<String> list = new ArrayList<>();
		for (Enum enumString : enums) {
			if(getCapitalizeFully(enumString.toString()).equals("Upi")) {
				list.add("UPI");
			} else {
				list.add(getCapitalizeFully(enumString.toString()));
			}
		}
		return list;
	}

	public static String getCapitalizeFully(String string) {
		return WordUtils.capitalizeFully(string.replace("_", " "));
	}

	public static String generateRandomDigit(Integer digit) {
		String no = "4";
		if (null != digit) {
			no = digit.toString();
		}
		return String.format("%0" + no + "d", new Random().nextInt(10000));
	}

	private static Double deg2rad(Double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static Double rad2deg(Double rad) {
		return (rad * 180.0 / Math.PI);
	}

	public static String convertObjToJsonString(Object obj) {
		return new Gson().toJson(obj);
	}

	public static Object convertJsonToObject(String json, Class<?> clazz) {
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}

	public static List<String> longToString(List<Long> longs) {
		if (!isListEmpty(longs))
			return longs.stream().map(Object::toString).collect(Collectors.toList());
		return null;
	}

	public static String getStaticFieldName(String displayNameStartsWith, String name) {
		if (isEmpty(displayNameStartsWith)) {
			return name;
		}
		return displayNameStartsWith + " " + name;
	}

	public static Object cast(String classSimpleName, Object value, Boolean isDisplay) {
		if (null == value) {
			return null;
		} else if (Long.class.getSimpleName().equals(classSimpleName)) {
			return Long.parseLong(value.toString());
		} else if (String.class.getSimpleName().equals(classSimpleName)) {
			return value.toString();
		} else if (Integer.class.getSimpleName().equals(classSimpleName)) {
			return Integer.parseInt(value.toString());
		} else if (Double.class.getSimpleName().equals(classSimpleName)) {
			return Double.parseDouble(value.toString());
		} else if (Boolean.class.getSimpleName().equals(classSimpleName)) {
			return Boolean.parseBoolean(value.toString());
		} else if (Date.class.getSimpleName().equals(classSimpleName)) {
			if (isDisplay) {
				return DateUtil.getDefaultDateTime((Date) value);
			}
			return (Date) value;
		}
		return value;
	}

	public static List<String> convertStringArrayToList(String[] strings) {
		List<String> list = new ArrayList<>();
		for (String string : strings) {
			list.add(string);
		}
		return list;
	}

	public static boolean isEmpty(Collection<?> list) {
		return list == null || list.isEmpty();
	}

	public static BigDecimal getBigDecimal(Double value) {
		return value != null ? new BigDecimal(value) : null;
	}
	

	public static String generateUPIQRCodeUrl(String upiId, String name, Double amount, String purpose)
			throws MovieException {
		if (CommonUtil.isEmpty(upiId) || CommonUtil.isEmpty(name) || CommonUtil.isEmpty(amount)
				|| CommonUtil.isEmpty(purpose))
			throw new MovieException(409, "UPIID, name, amount, pirpose not be null");
		return UriComponentsBuilder.fromUriString("upi://pay").queryParam("pa", upiId).queryParam("pn", name)
				.queryParam("am", amount).queryParam("cu", "INR").queryParam("purpose", purpose).build().encode()
				.toUriString();
	}
	
		
	private static Double getDouble(BigDecimal value) {
		if(value != null){
			return value.doubleValue();
		}else{
			return null;
		}
	}

	public static String getAddress(String addressLine, String areaOrLocality, String blockName, String wardNo, Long pinNo) {
		StringJoiner stringJoiner = new StringJoiner(", ");
		if (!CommonUtil.isEmpty(areaOrLocality)) 
			stringJoiner = stringJoiner.add(areaOrLocality);
		
		if (!CommonUtil.isEmpty(addressLine)) 
			stringJoiner = stringJoiner.add(addressLine);
		
		if (!CommonUtil.isEmpty(blockName)) 
			stringJoiner = stringJoiner.add(blockName);
		
		if (!CommonUtil.isEmpty(wardNo)) 
			stringJoiner = stringJoiner.add(wardNo);
		
		if(pinNo != null)
			stringJoiner = stringJoiner.add(pinNo.toString());
		
		return stringJoiner.toString();
	}
	
	public static BigDecimal getGstAmountToPercentage(BigDecimal amount, BigDecimal gstPercentage, boolean isTaxInclusive) {
		if(isTaxInclusive) {
			//	GstAmount = Original Cost – [Original Cost x {100/(100+GST%)}]
			return amount.subtract(amount.multiply(BigDecimal.valueOf(100).divide(gstPercentage.add(BigDecimal.valueOf(100)), 5, RoundingMode.CEILING)));
		} else {
			// GstAmount = [Original Cost x (GST%/100)]
			return gstPercentage.divide(BigDecimal.valueOf(100)).multiply(amount);
		}
	}
	

	public static String getFormatedBigDecimal(BigDecimal value) {
		String result = "";
		if(isValid(value)) 
			result = value.toPlainString().replace(".00", "");
		
		return result;	
	}
	
	public static String getUserNameOrNumberFormatted(String name, String number) {
		StringJoiner stringJoiner = new StringJoiner("/");
		if (!CommonUtil.isEmpty(name)) 
			stringJoiner = stringJoiner.add(name);
		
		if (!CommonUtil.isEmpty(number)) 
			stringJoiner = stringJoiner.add(number);
		return stringJoiner.toString();
	}
	
	public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	public static String removeSpecialCharacters(String string) {
		if (isEmpty(string))
			return null;
		return string.replaceAll("[^a-zA-Z0-9]", " ");
	}
	
	public static Double unitConverter(Double allocatedStock, String groupUnit) {
		switch (groupUnit) {
			case "kg":
				allocatedStock = allocatedStock / 1000;
			break;
			case "gram":
				allocatedStock = allocatedStock * 1000;
			break;
			case "litre":
				allocatedStock = allocatedStock / 1000;
			break;
			case "ml":
				allocatedStock = allocatedStock * 1000;
			break;
			default:
				break;
		}
		return  allocatedStock;
	}

}
