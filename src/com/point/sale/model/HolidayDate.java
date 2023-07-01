package com.point.sale.model;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import com.point.sale.model.NonStaticHoliday.DayOfWeek;
import com.point.sale.model.NonStaticHoliday.NumberDayOfWeekMonth;


public class HolidayDate {
	
	private String name;
	private Date date;
	private Boolean isObserved = false;
	private Date observedDate;
	
	public HolidayDate(Holiday holiday, Integer year) {
		try {
			if (holiday instanceof NonStaticHoliday) {
				this.name = ((NonStaticHoliday) holiday).getName();
				this.isObserved = false;
				this.observedDate = null;
				this.date = calculateNonStaticHolidayDay(year, (NonStaticHoliday) holiday);
			}
			
			if (holiday instanceof StaticHoliday) {
				calculateStaticHolidayDay(year, (StaticHoliday) holiday);
			}
		} catch (Exception e) {
			System.out.println("There was an error with holiday creation" + e.getMessage());
		}
	}
	
	private void calculateStaticHolidayDay(Integer year, StaticHoliday holiday) {
		this.name = holiday.getName();
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, holiday.getMonth());
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.DATE, holiday.getDay());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
//		System.out.println(cal.getTime());
		this.date = cal.getTime();
		
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) { // Holiday is observed falling on Saturday
			this.isObserved = true;
			cal.roll(Calendar.DATE, false);
			this.observedDate = cal.getTime();
		}
		
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) { // Holiday is observed falling on Sunday
			this.isObserved = true;
			cal.roll(Calendar.DATE, true);
			this.observedDate = cal.getTime();
		}
	}
	
	private Date calculateNonStaticHolidayDay(Integer year, NonStaticHoliday holiday) throws IOException {
		
		/*
		 * get the first day of the month for this holiday
		 * find the day of week at the numberdayofweekmonth
		 * i.e FIrst Monday of the month of september
		 */
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, holiday.getMonth());
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		int firstDayWeekOfMonth = cal.get(Calendar.DAY_OF_WEEK); // i.e num value of day week (1 = Sunday to 7 = Saturday)
		int dayOfWeekDesired = holiday.getDayOfWeek().numDay;
		
		
		/*
		 * logic here:
		 * 	if the day of week desired is greater than the first day of month's day of week
		 * 		then we need to rotate back to day 1 and add the day of week desired
		 * 			ex:	first day of the month is 4 (Wed) and desired day of week is 2 (Mon)
		 * 					then we need to substract number of days per week (7) minus first day of month day (4)
		 * 						so then we add desired day of week 2 (Mon) because that's where we want to be
		 * 							(7 - 4) + 2 = 5
		 * 								 this means, the first day of our desired week day is the fifth (5th) day of the month
		 * 
		 * 		then we need to get whichever nth day of week month we want
		 * 			ex: third monday of the month
		 * 				we take the nth day we want for holiday (3rd), 
		 * 				substract 1, because we already have the first one of the month from prior logic
		 * 				we multiply it by 7
		 * 				and the resulting number we add to the calendar we have and we get the final resulting date for the holiday
		 * 					7 * 3 = 21
		 * 					21 + 5 = 26th day of the month is the third monday of the month
		 */
		int numOfMovesToDesiredDayOfweek = 0;
		if (firstDayWeekOfMonth > dayOfWeekDesired) {
			numOfMovesToDesiredDayOfweek = (7 - firstDayWeekOfMonth) + dayOfWeekDesired;
		} else if (firstDayWeekOfMonth < dayOfWeekDesired) {
			numOfMovesToDesiredDayOfweek = dayOfWeekDesired - firstDayWeekOfMonth;
		} else { // it is equal
			// nothing to do
		}
		
		// need to move to that desired day of week the nth times NumberDayOfWeekMonth
		int nthDayOfWeekMonth = (holiday.getNumberDayOfWeekMonth().num-1) * 7;
		
		// add the nth number of week moves to final number of moves to make
		numOfMovesToDesiredDayOfweek += nthDayOfWeekMonth;
		
		// if the final number of move to desired day is greater then maximum day of the month, then it is an invalid entry.
		if (cal.getActualMaximum(Calendar.DATE) < (cal.get(Calendar.DATE) + numOfMovesToDesiredDayOfweek)) {
			throw new IOException("Invalid number of days for this holiday.");
		}
		
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + numOfMovesToDesiredDayOfweek);
		
		return cal.getTime();
		
	}
	
//	public static void main(String[] args) {
//		try {
//			NonStaticHoliday nonStatic = new NonStaticHoliday("Labor Day", 9, DayOfWeek.Monday, NumberDayOfWeekMonth.Fifth);
//			HolidayDate nonStaticHolidayDate = new HolidayDate(nonStatic, 2020);
//			
//			StaticHoliday statc = new StaticHoliday("Fourth Of July", 10, 7);
//			HolidayDate staticHoliday = new HolidayDate(statc, 2023);
//			System.out.println(staticHoliday.getDate());
//			System.out.println(staticHoliday.isObserved());
//			System.out.println(staticHoliday.getObservedDate());
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Boolean isObserved() {
		return isObserved;
	}
	public void setIsObserved(Boolean isObserved) {
		this.isObserved = isObserved;
	}
	public Date getObservedDate() {
		return observedDate;
	}
	public void setObservedDate(Date observedDate) {
		this.observedDate = observedDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
