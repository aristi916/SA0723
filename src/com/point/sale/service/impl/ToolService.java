package com.point.sale.service.impl;

import java.util.Calendar;
import java.util.Date;

import com.point.sale.helper.CalendarHelper;
import com.point.sale.model.Item;
import com.point.sale.model.Tool;
import com.point.sale.model.ToolSaleRules;
import com.point.sale.service.ItemService;

public class ToolService implements ItemService {

	@Override
	public Integer getDaysCountToCharge(Item item, Integer days, Date checkout) {
		
		/*
		 * iterate over dates
		 * if (weekend && !chargeWeekend) days--; continue;
		 * if (holiday && !chargeHoliday) days--; continue;
		 */
		
		ToolSaleRules toolRules = new ToolSaleRules((Tool)item);
		
		Integer daysToCharge = 0;
		
		Calendar date = Calendar.getInstance();  
		date.setTime(checkout);
		date.set(Calendar.HOUR, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		date = CalendarHelper.rollDay(date); // start the day after the checkout
		
		while (days > 0) {
//			System.out.println("Days " + days);
			if (CalendarHelper.isWeekend(date) && !toolRules.getWeekendCharge()) {
//				System.out.println("Date is weekend and not chargable " + date.getTime());
				days--;
				date = CalendarHelper.rollDay(date);
				continue;
			}
			if (CalendarHelper.isHoliday(date) && !toolRules.getHolidayCharge()) {
//				System.out.println("Date is holiday and not chargable " + date.getTime());
				days--;
				date = CalendarHelper.rollDay(date);
				continue;
			}
//			System.out.println("charging date " + date.getTime());
			days--;
			daysToCharge++;
			date = CalendarHelper.rollDay(date);
//			System.out.println("Days to charge " + daysToCharge);
		}
		
		
		return daysToCharge;
	}

}
