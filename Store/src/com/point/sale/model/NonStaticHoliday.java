package com.point.sale.model;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class NonStaticHoliday implements Holiday {
	
	public enum DayOfWeek {
		Monday(2), Tuesday(3), Wednesday(4), Thursday(5), Friday(6), Saturday(7), Sunday(1);
		public final int numDay;
		
		private DayOfWeek(Integer num) {
			this.numDay = num;
		}
	}
	public enum NumberDayOfWeekMonth {
		First(1), Second(2), Third(3), Fourth(4), Fifth(5);
		public final int num;
		private NumberDayOfWeekMonth(Integer num) {
			this.num = num;
		}
	}
	
	private String name;
	private Integer month;
	private DayOfWeek dayOfWeek;
	private NumberDayOfWeekMonth numberDayOfWeekMonth;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public NumberDayOfWeekMonth getNumberDayOfWeekMonth() {
		return numberDayOfWeekMonth;
	}

	public void setNumberDayOfWeekMonth(NumberDayOfWeekMonth numberDayOfWeekMonth) {
		this.numberDayOfWeekMonth = numberDayOfWeekMonth;
	}

	public NonStaticHoliday(String name, Integer month, DayOfWeek dayOfWeek, NumberDayOfWeekMonth numDay) throws IOException {
		this.name = name;
		this.dayOfWeek = dayOfWeek;
		this.numberDayOfWeekMonth = numDay;
		if (month > 0 && month < 13)
			this.month = month;
		else
			throw new IOException("Invalid number of month");
	}
	
	public static void calculateHolidayDay(Integer year, NonStaticHoliday holiday) {
		
		/*
		 * get the first day of the month for this holiday
		 * find the day of week at the numberdayofweekmonth
		 * i.e FIrst Monday of the month of september
		 */
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.MONTH, holiday.getMonth()-1);
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.DATE, 1);
		
		System.out.println(cal.getTime());
		cal.get(Calendar.DAY_OF_WEEK);
	}
	
//	public static void main(String[] args) {
//		try {
//			NonStaticHoliday holiday = new NonStaticHoliday("Labor Day", 9, DayOfWeek.Monday, NumberDayOfWeekMonth.First);
//			calculateHolidayDay(2020, holiday);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
