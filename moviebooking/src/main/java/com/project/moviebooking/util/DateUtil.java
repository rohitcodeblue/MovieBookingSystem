package com.project.moviebooking.util;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.project.moviebooking.model.DateModel;


/**
 *
 * @author uday
 */
public class DateUtil {

	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");

	public final static String EXPORT_FORMAT = "dd/MM/yyyy";
	public final static String FILE_FORMAT = "yyyy-MM-dd";


	/**
	 * Description - Method to Get Today's day
	 *
	 * @param dayFormat
	 * @param timeZone
	 * @return
	 */
	public static String getTodaysDay(String dayFormat, TimeZone timeZone) {
		Date date = new Date();
		/* Specifying the format */
		DateFormat requiredFormat = new SimpleDateFormat(dayFormat);
		/* Setting the Timezone */
		requiredFormat.setTimeZone(timeZone);
		/* Picking the day value in the required Format */
		String strCurrentDay = requiredFormat.format(date).toUpperCase();
		return strCurrentDay;
	}

	/**
	 * Description - Method to Get Current time
	 *
	 * @param timeFormat
	 * @param timeZone
	 * @return
	 */
	public static String getCurrentTime(String timeFormat, TimeZone timeZone) {
		/* Specifying the format */
		DateFormat dateFormat = new SimpleDateFormat(timeFormat);
		/* Setting the Timezone */
		Calendar cal = Calendar.getInstance(timeZone);
		dateFormat.setTimeZone(cal.getTimeZone());
		/* Picking the time value in the required Format */
		String currentTime = dateFormat.format(cal.getTime());
		return currentTime;
	}

	/**
	 * Description - Method to Get Today's date
	 *
	 * @param dateFormat
	 * @param timeZone
	 * @return
	 */
	public static String getTodayDate(String dateFormat, TimeZone timeZone) {
		Date todayDate = new Date();
		/* Specifying the format */
		DateFormat todayDateFormat = new SimpleDateFormat(dateFormat);
		/* Setting the Timezone */
		todayDateFormat.setTimeZone(timeZone);
		/* Picking the date value in the required Format */
		String strTodayDate = todayDateFormat.format(todayDate);
		return strTodayDate;
	}

	public static Date getDateChangedByMinutes(int minutes, TimeZone timeZone) {
		Calendar calendar = Calendar.getInstance(timeZone);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}

	public static List<String> getYearList() {
		List<String> yearList = new ArrayList<>();
		for (int year = 2000; year <= 2100; year++) {
			yearList.add(String.valueOf(year));
		}
		return yearList;
	}

	public static List<String> getMonthList() {
		List<String> monthList = new ArrayList<>();
		String[] months = new DateFormatSymbols().getMonths();
		for (int i = 0; i < months.length; i++) {
			String month = months[i];
			if (!month.trim().isEmpty()) {
				monthList.add(months[i]);
			}
		}
		return monthList;
	}

	public static TimeZone getTimeZone(String timezoneId) {
		if (timezoneId == null) {
			timezoneId = "UTC";
		}
		return TimeZone.getTimeZone(timezoneId);
	}
//
//    public static TimeZone getUserTimeZone(User user) {
//        String timezoneId;
//        if (user.getTimezoneId() != null && !user.getTimezoneId().isEmpty()) {
//            timezoneId = user.getTimezoneId();
//        } else {
//            timezoneId = "UTC";
//        }
//        return TimeZone.getTimeZone(timezoneId);
//    }
//
//    public static boolean compareDatesEqual(String firstDateString, String secondDateString, String timeZoneId) {
//        Date firstDate = getUserTimezoneDateFromUTC(new Date(Long.parseLong(firstDateString)), getTimeZone(timeZoneId));
//        Date secondDate = getUserTimezoneDateFromUTC(new Date(Long.parseLong(secondDateString)), getTimeZone(timeZoneId));
//        return JsfUtil.isEqualDate(firstDate, secondDate);
//    }
//
//    public static Date getUTCDateFRomUserTimezoneDate(String dateString, TimeZone userTimeZone) {
////        simpleDateFormat.setTimeZone(userTimeZone);
//        Date date;
//        try {
//            date = simpleDateFormat.parse(dateString);
//            return new Date(date.getTime() - userTimeZone.getOffset(date.getTime()));
//        } catch (ParseException ex) {
//            Logger.getLogger(JsfUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }

	public static Date getUTCDateFRomUserTimezoneDate(Date date, TimeZone userTimeZone) {
		return new Date(date.getTime() - userTimeZone.getOffset(date.getTime()));
	}

	public static Date getUserTimezoneDateFromUTC(Date date, TimeZone userTimeZone) {
		return new Date(date.getTime() + userTimeZone.getOffset(date.getTime()));
	}

	public static Date getDateFromString(String date) {
		try {
			return simpleDateFormat.parse(date);
		} catch (ParseException ex) {
			Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public static Date getDateByLongString(String date) {
		return new Date(Long.parseLong(date));
	}

	public static String getUserTimezoneStringDateFromUTC(Date date, TimeZone userTimeZone) {
		Date date1 = new Date(date.getTime() + userTimeZone.getOffset(date.getTime()));
		return simpleDateFormat.format(date1);
	}

	public static String getFormattedTimeInHoursAndMins(Date date, TimeZone userTimeZone) {
		Date date1 = new Date(date.getTime() + userTimeZone.getOffset(date.getTime()));
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a", Locale.ENGLISH);
		return timeFormat.format(date1);
	}

	public static Date getThisWeekFirstDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		return cal.getTime();
	}

	public static Date getThisWeekLastDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
		cal.add(Calendar.DATE, 6);
		return cal.getTime();
	}

	public static Date getStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static Date getEndDate(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static Date getUTCDate() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		return calendar.getTime();
	}

	public static Date convertLongToUTC(long date) {
		try {
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			calendar.setTimeInMillis(date);
			return calendar.getTime();
		} catch (Exception e) {
		}
		return null;
	}

	public static int diff(Date endDate, Date startDate) {
		long diff = endDate.getTime() - startDate.getTime();
		int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		return diffDays;
	}

	public static Long getDifferenceInMinutes(Long startDate, Long endDate) {
		long diff = endDate - startDate;
		return TimeUnit.MILLISECONDS.toMinutes(diff);
	}

//	public static int getNoOfWeeksBetween(Date startDate, Date endDate) {
//		DateTime dateTime1 = new DateTime(startDate);
//		DateTime dateTime2 = new DateTime(endDate);
//		return Weeks.weeksBetween(dateTime1, dateTime2).getWeeks();
//	}
//
//	public static int getNoOfMonthsBetween(Date startDate, Date endDate) {
//		DateTime dateTime1 = new DateTime(startDate);
//		DateTime dateTime2 = new DateTime(endDate);
//		return Months.monthsBetween(dateTime1, dateTime2).getMonths();
//	}
//
//	public static int getNoOfYearsBetween(Date startDate, Date endDate) {
//		DateTime dateTime1 = new DateTime(startDate);
//		DateTime dateTime2 = new DateTime(endDate);
//		return Years.yearsBetween(dateTime1, dateTime2).getYears();
//	}

	public static Date addDay(Date date, int count) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + count);
		return calendar.getTime();
	}

	public static Date subtractDay(Date date, int count) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - count);
		return calendar.getTime();
	}

	public static Date addMonth(Date date, int count) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + count);
		return calendar.getTime();
	}

//	public static List<DropdownModel> daysList() {
//		List<DropdownModel> days = new ArrayList<>();
//		daysMap().keySet().forEach((id) -> {
//			days.add(new DropdownModel(id.longValue(), daysMap().get(id)));
//		});
//		return days;
//	}

	public static Map<Integer, String> daysMap() {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "Sunday");
		map.put(2, "Monday");
		map.put(3, "Tuesday");
		map.put(4, "Wednesday");
		map.put(5, "Thursday");
		map.put(6, "Friday");
		map.put(7, "Saturday");
		return map;
	}

	public static void applyCustomTime(Integer time, Calendar calendar) {
		if (time != null && calendar != null) {
			if (time < 24) {
				calendar.set(Calendar.HOUR_OF_DAY, time);
				calendar.set(Calendar.MINUTE, 00);
				calendar.set(Calendar.SECOND, 00);
			} else if (time == 24) {
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
			}
		}
	}

	public static int getTimeFormat24(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour == 23) {
			if (calendar.get(Calendar.MINUTE) == 59) {
				hour = 24;
			}
		}
		return hour;
	}

	public static int getWeekCodeFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		if (week == 7) {
			if ((calendar.get(Calendar.DATE) >= 1 && calendar.get(Calendar.DATE) <= 7)
					|| (calendar.get(Calendar.DATE) >= 15 && calendar.get(Calendar.DATE) <= 21)) {
				week = 8;
			}
			if (calendar.get(Calendar.DATE) >= 8 && calendar.get(Calendar.DATE) >= 14
					|| (calendar.get(Calendar.DATE) >= 22 && calendar.get(Calendar.DATE) >= 31)) {
				week = 9;
			}
		}
		return week;
	}

	public static int getMonthCodeFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		return month;
	}

	public static int getDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int d = calendar.get(Calendar.DATE);
		int maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (maxDate == d) {
			return 0;
		} else if (maxDate - 1 == d) {
			return -1;
		} else {
			return d;
		}
	}

	public static int getYearFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}

	public static void applyCustomDate(Integer date, Calendar calendar) {
		if (date != null && calendar != null) {
			if (date == 0) {
				calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
			} else if (date == -1) {
				calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE) - 1);
			} else if (date <= 28) {
				calendar.set(Calendar.DATE, date);
			}
		}
	}

	public static String getDashBoardDateFromString(Date date) {
//        getDayOfMonthSuffix(date.getDate());
		SimpleDateFormat dashboardDateFormat = new SimpleDateFormat("dd MMM yyyy hh:mm a");
		return dashboardDateFormat.format(date);
	}

	public static String getReportDate(Date date) {
//      getDayOfMonthSuffix(date.getDate());
		SimpleDateFormat dashboardDateFormat = new SimpleDateFormat("dd MMM yyyy ");
		return dashboardDateFormat.format(date);
	}

//    public static static String getDayOfMonthSuffix(final int n) {
//        if (n >= 1 && n <= 31) {
//            if (n >= 11 && n <= 13) {
//                return "th";
//            }
//            switch (n % 10) {
//                case 1:
//                    return "st";
//                case 2:
//                    return "nd";
//                case 3:
//                    return "rd";
//                default:
//                    return "th";
//            }
//        }
//        return null;
//    }
//}

	/*
	 * public static DateModel today() { Calendar calendar = Calendar.getInstance();
	 * DateModel todayDate = new DateModel();
	 * todayDate.setStartDate(calendar.getTime());
	 * todayDate.setEndDate(calendar.getTime()); return todayDate; }
	 * 
	 * public static DateModel lastSeventDays() { DateModel lastWeekDate = new
	 * DateModel(); Calendar startCalendar = Calendar.getInstance();
	 * startCalendar.set(Calendar.DAY_OF_MONTH,
	 * startCalendar.get(Calendar.DAY_OF_MONTH) - 6);
	 * lastWeekDate.setStartDate(DateUtil.getStartDate(startCalendar.getTime()));
	 * 
	 * Calendar endCalendar = Calendar.getInstance();
	 * lastWeekDate.setEndDate(DateUtil.getEndDate(endCalendar.getTime())); return
	 * lastWeekDate; }
	 * 
	 * public static DateModel thisWeek() { DateModel thisWeekDate = new
	 * DateModel(); thisWeekDate.setStartDate(getWeekStartDate());
	 * thisWeekDate.setEndDate(getWeekEndDate()); return thisWeekDate; }
	 * 
	 * public static DateModel thisMonth() { DateModel thisMonthDate = new
	 * DateModel(); Calendar calendar = Calendar.getInstance();
	 * calendar.set(Calendar.DAY_OF_MONTH,
	 * calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
	 * calendar.set(Calendar.HOUR, 0); calendar.set(Calendar.MINUTE, 0);
	 * calendar.set(Calendar.SECOND, 0);
	 * thisMonthDate.setStartDate(calendar.getTime());
	 * 
	 * calendar.set(Calendar.DAY_OF_MONTH,
	 * calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	 * calendar.set(Calendar.HOUR, 23); calendar.set(Calendar.MINUTE, 59);
	 * calendar.set(Calendar.SECOND, 59);
	 * thisMonthDate.setEndDate(calendar.getTime()); return thisMonthDate; }
	 * 
	 * public static DateModel thisYear() { Calendar calendar =
	 * Calendar.getInstance(); DateModel thisYearDate = new DateModel();
	 * calendar.set(Calendar.MONTH, Calendar.JANUARY);
	 * calendar.set(Calendar.DAY_OF_MONTH, 1); calendar.set(Calendar.HOUR, 0);
	 * calendar.set(Calendar.MINUTE, 0); calendar.set(Calendar.SECOND, 0);
	 * thisYearDate.setStartDate(calendar.getTime());
	 * 
	 * calendar.set(Calendar.MONTH, Calendar.DECEMBER);
	 * calendar.set(Calendar.DAY_OF_MONTH,
	 * calendar.getActualMaximum(Calendar.DATE)); calendar.set(Calendar.HOUR, 23);
	 * calendar.set(Calendar.MINUTE, 59); calendar.set(Calendar.SECOND, 59);
	 * thisYearDate.setEndDate(calendar.getTime()); return thisYearDate; }
	 * 
	 * public static DateModel yesterday() { Calendar calendar =
	 * Calendar.getInstance(); DateModel yesterdayDate = new DateModel();
	 * yesterdayDate = new DateModel(); calendar = Calendar.getInstance();
	 * calendar.add(Calendar.DATE, -1);
	 * yesterdayDate.setStartDate(calendar.getTime());
	 * yesterdayDate.setEndDate(calendar.getTime()); return yesterdayDate; }
	 * 
	 * public static DateModel previousWeek() { DateModel previousWeekDate = new
	 * DateModel(); Calendar calendar = Calendar.getInstance();
	 * previousWeekDate.setStartDate(firstDayOfLastWeek(calendar).getTime());
	 * previousWeekDate.setEndDate(lastDayOfLastWeek(calendar).getTime()); return
	 * previousWeekDate; }
	 * 
	 * public static DateModel previousMonth() { DateModel previousMonthDate = new
	 * DateModel(); Calendar calendar = Calendar.getInstance(); previousMonthDate =
	 * new DateModel(); calendar = Calendar.getInstance();
	 * calendar.add(Calendar.MONTH, -1); calendar.set(Calendar.DAY_OF_MONTH, 1);
	 * calendar.set(Calendar.HOUR, 0); calendar.set(Calendar.MINUTE, 0);
	 * calendar.set(Calendar.SECOND, 0);
	 * previousMonthDate.setStartDate(calendar.getTime());
	 * 
	 * calendar.set(Calendar.DAY_OF_MONTH,
	 * calendar.getActualMaximum(Calendar.DATE)); calendar.set(Calendar.HOUR, 23);
	 * calendar.set(Calendar.MINUTE, 59); calendar.set(Calendar.SECOND, 59);
	 * previousMonthDate.setEndDate(calendar.getTime()); return previousMonthDate; }
	 * 
	 * public static DateModel previousYear() { DateModel previousYearDate = new
	 * DateModel(); Calendar calendar = Calendar.getInstance(); previousYearDate =
	 * new DateModel(); calendar = Calendar.getInstance();
	 * calendar.add(Calendar.YEAR, -1); calendar.set(Calendar.MONTH,
	 * Calendar.JANUARY); calendar.set(Calendar.DAY_OF_MONTH, 1);
	 * calendar.set(Calendar.HOUR, 0); calendar.set(Calendar.MINUTE, 0);
	 * calendar.set(Calendar.SECOND, 0);
	 * previousYearDate.setStartDate(calendar.getTime());
	 * 
	 * calendar.set(Calendar.MONTH, Calendar.DECEMBER);
	 * calendar.set(Calendar.DAY_OF_MONTH,
	 * calendar.getActualMaximum(Calendar.DATE)); calendar.set(Calendar.HOUR, 23);
	 * calendar.set(Calendar.MINUTE, 59); calendar.set(Calendar.SECOND, 59);
	 * previousYearDate.setEndDate(calendar.getTime()); return previousYearDate; }
	 */
	public static Date getWeekStartDate() {
		Calendar calendar = Calendar.getInstance();
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, -1);
		}
		return calendar.getTime();
	}

	public static Date getWeekEndDate() {
		Calendar calendar = Calendar.getInstance();
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DATE, 1);
		}
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	public static Calendar firstDayOfLastWeek(Calendar c) {
		c = (Calendar) c.clone();
		// last week
		c.add(Calendar.WEEK_OF_YEAR, -1);
		// first day
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return c;
	}

	public static Calendar lastDayOfLastWeek(Calendar c) {
		c = (Calendar) c.clone();
		// first day of this week
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		// last day of previous week
		c.add(Calendar.DAY_OF_MONTH, -1);
		return c;
	}

	public static String getDefaultDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dashboardDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dashboardDateFormat.format(date);
	}

	public static String getDefaultDateFormat(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dashboardDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dashboardDateFormat.format(date);// MMMMM yyyy
	}

	public static String getDefaultDateFormat_MonthInChars(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dashboardDateFormat = new SimpleDateFormat("dd MMMMM yyyy");
		return dashboardDateFormat.format(date);
	}

	public static String getDefaultDateTime(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dashboardDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		return dashboardDateFormat.format(date);
	}

	public static String getDefaultDateTime24hr(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dashboardDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return dashboardDateFormat.format(date);
	}

	public static String getWeekDayName(int weekDay) {
		String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
				"Even Saturday", "Odd Saturday" };
		return strDays[weekDay];
	}

	public static Integer getUnixTime(Date date) {
		Long ut3 = date.getTime() / 1000L;
		return Integer.parseInt(ut3.toString());
	}

	public static DateModel thisFinancialYear() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		DateModel thisYearDate = new DateModel();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		thisYearDate.setStartDate(calendar.getTime());
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(year + 1, Calendar.MARCH, 31);
		calendar1.set(Calendar.HOUR, 23);
		calendar1.set(Calendar.MINUTE, 59);
		calendar1.set(Calendar.SECOND, 59);
		thisYearDate.setEndDate(calendar1.getTime());
		return thisYearDate;
	}

	public static DateModel quarter(int month, boolean isPrevYear) {
		DateModel Quarter = new DateModel();
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		if (isPrevYear) {
			year -= 1;
		}
		if (1 <= month && month <= 3) {
			calendar.set(calendar.YEAR, year);
			calendar.set(calendar.MONTH, Calendar.JANUARY);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DATE));
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Quarter.setStartDate(calendar.getTime());
			calendar.set(calendar.YEAR, year);
			calendar.set(calendar.MONTH, Calendar.MARCH);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Quarter.setEndDate(calendar.getTime());
		} else if (4 <= month && month <= 6) {
			calendar.set(calendar.YEAR, year);
			calendar.set(calendar.MONTH, Calendar.APRIL);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DATE));
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Quarter.setStartDate(calendar.getTime());
			calendar.set(calendar.YEAR, year);
			calendar.set(calendar.MONTH, Calendar.JUNE);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Quarter.setEndDate(calendar.getTime());
		} else if (7 <= month && month <= 9) {
			calendar.set(calendar.YEAR, year);
			calendar.set(calendar.MONTH, Calendar.JULY);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DATE));
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Quarter.setStartDate(calendar.getTime());
			calendar.set(calendar.YEAR, year);
			calendar.set(calendar.MONTH, Calendar.SEPTEMBER);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Quarter.setEndDate(calendar.getTime());
		} else if (10 <= month && month <= 12) {
			calendar.set(calendar.YEAR, year);
			calendar.set(calendar.MONTH, Calendar.OCTOBER);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DATE));
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Quarter.setStartDate(calendar.getTime());
			calendar.set(calendar.YEAR, year);
			calendar.set(calendar.MONTH, Calendar.DECEMBER);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DATE));
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Quarter.setEndDate(calendar.getTime());
		}
		return Quarter;
	}

	public static Date addMinutes(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}

	public static String getDefaultDateTimeNotYear(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dashboardDateFormat = new SimpleDateFormat("dd/MM hh:mm a");
		return dashboardDateFormat.format(date);
	}

	public static String getDefaultFileDate() {
		return getFormattedDate(new Date(), FILE_FORMAT);
	}
	
	public static String getFormattedDate(Object date, String pattern) {
		return getFormattedDate(date != null ? (Date) date : null, pattern);
	}

	public static synchronized String getFormattedDate(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		TimeZone timeZone = TimeZone.getTimeZone("IST");
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setTimeZone(timeZone);
		return dateFormat.format(date);
	}
	public static Date getTommorowDate(Date date) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, 1);
		date = c.getTime();
		return date;
	}
	

	public static String localTimeFromater(LocalTime localTime) {
		return localTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
	}
}
