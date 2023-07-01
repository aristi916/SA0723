package com.point.sale.data;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.point.sale.model.Holiday;
import com.point.sale.model.HolidayDate;
import com.point.sale.model.NonStaticHoliday;
import com.point.sale.model.StaticHoliday;
import com.point.sale.model.NonStaticHoliday.DayOfWeek;
import com.point.sale.model.NonStaticHoliday.NumberDayOfWeekMonth;

public class HolidaysDates {
	
	/**
	 * HolidayDates will store static holidaysDates that will be fulfilled on a need basis if not part of the map
	 * Parent map's key will be year, and within that key's value will be a map whose key will be month which will contain list of holidaydates
	 * 
	 * example:
	 * 		key 	| value
	 * 		2023   -> key   | value
	 * 					7  -> HolidayDate(Holiday("July Fourth" , 4, 7), 2023)
	 * 					...
	 * 		.
	 * 		.
	 * 		. 
	 * 		2024   -> 	7  -> HolidayDate(Holiday("July Fourth" , 4, 7), 2023)
	 * 		.			...
	 * 		.
	 * 		.
	 */
	
	public static HashMap<Integer, HashMap<Integer, Set<HolidayDate>>> holidaysDates;
	
	public static void addHolidayDate(HolidayDate holiday) {
		
		// if it has not been created before, create it.
		if (holidaysDates == null) {
			holidaysDates = new HashMap<Integer, HashMap<Integer, Set<HolidayDate>>>();
		}
		
		// set the time of the passed holiday
		Calendar cal = Calendar.getInstance();
		cal.setTime(holiday.getDate());
		// get the year of the passed holiday
		Integer yearKey = cal.get(Calendar.YEAR); 
		
		// get the month of the year in this holiday
		Integer monthKey = cal.get(Calendar.MONTH); 
		
		// insert into year and into month
		HashMap<Integer, Set<HolidayDate>> holidaysInYear;
		if (holidaysDates.containsKey(yearKey)) { // year has been initialized
			// get holidays in that year
			holidaysInYear = holidaysDates.get(yearKey); 
			HashSet<HolidayDate> holidaysInMonth;
			
			if (holidaysInYear.containsKey(monthKey)) { // month also has been filled with holiday before
				// get the existing holidays added for the month and the year
				holidaysInMonth = (HashSet<HolidayDate>) holidaysInYear.get(monthKey); 
				
				// if holiday does not exist in this year's month, add
				if (!holidaysInMonth.contains(holiday)) holidaysInMonth.add(holiday); 
				
			} else {// no holidays added for this month and year, add month into the year
				holidaysInMonth = new HashSet<>(); 
				holidaysInMonth.add(holiday);
				holidaysInYear.put(monthKey, holidaysInMonth);
			}
		} else { // year has not been added before, add both year and month
			holidaysInYear = new HashMap<>();
			HashSet<HolidayDate> holidaysInMonth = new HashSet<>(); 
			holidaysInMonth.add(holiday);
			holidaysInYear.put(monthKey, holidaysInMonth);
			holidaysDates.put(yearKey, holidaysInYear);
		}
		
	}
	
	public static void fulfillHolidaysDatesByYear(Set<Holiday> holidays, Integer year) {
		
		/*
		 * loop thru the set of holidays
		 * create a new HolidayDate passing the year and the holiday
		 * use addHolidayDate(Holiday, year)
		 */
		
		if (holidays != null && !holidays.isEmpty()) {
			for (Holiday holiday : holidays) {
				addHolidayDate(new HolidayDate(holiday, year));
			}
		}
	}
	
	
	public static void main(String[] args) {
		try {
			NonStaticHoliday nonStatic = new NonStaticHoliday("Labor Day", 9, DayOfWeek.Monday, NumberDayOfWeekMonth.First);
			HolidayDate nonStaticHolidayDate = new HolidayDate(nonStatic, 2020);
			
			addHolidayDate(nonStaticHolidayDate);
			
			StaticHoliday statc = new StaticHoliday("Fourth Of July", 10, 7);
			HolidayDate staticHoliday = new HolidayDate(statc, 2023);
			System.out.println(staticHoliday.getDate());
			System.out.println(staticHoliday.isObserved());
			System.out.println(staticHoliday.getObservedDate());
			
			addHolidayDate(staticHoliday);
			
//			System.out.println("Is holidaysDates empty? " + (holidaysDates == null));
			
			if (holidaysDates != null) {
				System.out.println(holidaysDates.keySet());
				for (Integer holidayYear : holidaysDates.keySet()) {
					System.out.println(holidaysDates.get(holidayYear));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
