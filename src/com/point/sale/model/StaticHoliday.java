package com.point.sale.model;

import java.io.IOException;

public class StaticHoliday implements Holiday {
	
	private Integer day;
	private Integer month;
	private String name;
	
	public StaticHoliday(String name, Integer day, Integer month) throws IOException {
		this.name = name;
		
		if (day > 0 && day < 31) 
			this.day = day;
		else
			throw new IOException("Invalid number of day");
		
		
		if (month > 0 && month < 13)
			this.month = month;
		else
			throw new IOException("Invalid number of month");
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
