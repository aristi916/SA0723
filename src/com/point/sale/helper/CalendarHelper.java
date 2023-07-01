package com.point.sale.helper;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.point.sale.data.Holidays;
import com.point.sale.data.HolidaysDates;
import com.point.sale.model.HolidayDate;

public class CalendarHelper {
	
	public static Boolean isHoliday(Calendar calendar) {
		
		/*
		 *
		 * get year and month of this calendar
		 * check if the holidays contains the month
		 * 		if not
		 * 			return false
		 * 		if yes
		 * 			check if holidaysDates map has the year and month
		 * 				if not
		 * 					need to fulfill using HolidaysDates.fulfillHolidaysDatesByYear() passing holidays set for the month and the year
		 * 					once done, call isHoliday() again
		 * 				if yes
		 * 					turn this calendar into a date
		 * 						go thru set of holidayDates
		 *	 					match this calendar's date with holidayDates date's
		 *							if found
		 *								check if holiday is observed
		 *									if true continue, not a holiday yet
		 *									else return true
		 *							if not found, 
		 *								check if observed date 
		 *									if yes, check if it matches this calendars date
		 *										if yes
		 *											return true
		 *										else continue
		 *									if not, continue
		 */
		
		Integer year = calendar.get(Calendar.YEAR);
		Integer month = calendar.get(Calendar.MONTH);
		
		if (Holidays.holidays == null || !Holidays.holidays.containsKey(month)) {
			return false;
		} else {
			if (HolidaysDates.holidaysDates == null || !HolidaysDates.holidaysDates.containsKey(year) || 
					(HolidaysDates.holidaysDates.containsKey(year) && !HolidaysDates.holidaysDates.get(year).containsKey(month))) {
				HolidaysDates.fulfillHolidaysDatesByYear(Holidays.holidays.get(month), year);
				return isHoliday(calendar);
			} else {
				Date calDate = calendar.getTime();
				for (HolidayDate holidayDate : HolidaysDates.holidaysDates.get(year).get(month)) {
//					Date holiday = holidayDate.getDate();
					if (calDate.compareTo(holidayDate.getDate()) == 0) {
						if (!holidayDate.isObserved()) return true;
					} else {
						if (holidayDate.isObserved()) {
							if (calDate.compareTo(holidayDate.getObservedDate()) == 0) return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	public static Boolean isWeekday(Calendar calendar) {
		
		if (!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) ||
				!(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) 
			return true;
			
		return false;
	}
	
	public static Boolean isWeekend(Calendar calendar) {
		
		if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) ||
				(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) 
			return true;
			
		return false;
	}
	
	public static Calendar rollDay(Calendar cal) {
		long nextDay = cal.getTime().getTime() + 86400000; // add one day (day in milliseconds)
		cal.setTimeInMillis(nextDay);
		return cal;
	}
	
	public static String formatDate(Date date) {
		Format f = new SimpleDateFormat("MM/dd/yy"); 
		return f.format(date);
	}
	
//	public static void main(String args[]) {
//		Date today = new Date();
//		System.out.println("Today =" + today);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(today);
//		System.out.println("Tomorrow = " + rollDay(cal));
//	}
	

}
