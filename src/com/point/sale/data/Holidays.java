package com.point.sale.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.point.sale.model.Holiday;
import com.point.sale.model.NonStaticHoliday;
import com.point.sale.model.StaticHoliday;

public class Holidays {
	
	public static HashMap<Integer, Set<Holiday>> holidays;
	
	/**
	 * This class is a for storing a list of holidays.
	 * It represents a DB with the holiday list.
	 * NOTE: for the number of the month, it starts at 0, not at 1 (i.e. Jan = 0, Dec = 11)
	 */
	
	public static void initialize() {
//		System.out.println("Initializing Holidays");
		
		if (holidays == null || holidays.isEmpty()) {
			holidays = new HashMap();
		}
		try {
			holidays.put(6, new HashSet<Holiday>());
			holidays.get(6).add(new StaticHoliday("July Fourth", 4, 6));
			
			holidays.put(8, new HashSet<Holiday>());
			holidays.get(8).add(new NonStaticHoliday("Labor Day", 8, NonStaticHoliday.DayOfWeek.Monday, NonStaticHoliday.NumberDayOfWeekMonth.First));
		} catch (Exception e) {
			System.out.println("There was an error starting up holidays");
		}
		
//		System.out.println("Finish initializing holidays!");
	}

}
