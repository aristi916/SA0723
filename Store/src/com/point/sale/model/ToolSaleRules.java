package com.point.sale.model;

public class ToolSaleRules implements SaleRules {
	
	private Double pricePerDay;
	private Boolean weekdayCharge;
	private Boolean weekendCharge;
	private Boolean holidayCharge;
	
	
	public ToolSaleRules(Tool tool) {
		
		
		switch (tool.getType()) {
		case Ladder:
			this.pricePerDay = 1.99;
			this.weekdayCharge = true;
			this.weekendCharge = true;
			this.holidayCharge = false;
			break;
			
		case Chainsaw:
			this.pricePerDay = 1.49;
			this.weekdayCharge = true;
			this.weekendCharge = false;
			this.holidayCharge = true;
			break;
			
		case Jackhammer:
			this.pricePerDay = 2.99;
			this.weekdayCharge = true;
			this.weekendCharge = false;
			this.holidayCharge = false;
			break;
			
		}
	}


	public Double getPricePerDay() {
		return pricePerDay;
	}


	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}


	public Boolean getWeekdayCharge() {
		return weekdayCharge;
	}


	public void setWeekdayCharge(Boolean weekdayCharge) {
		this.weekdayCharge = weekdayCharge;
	}


	public Boolean getWeekendCharge() {
		return weekendCharge;
	}


	public void setWeekendCharge(Boolean weekendCharge) {
		this.weekendCharge = weekendCharge;
	}


	public Boolean getHolidayCharge() {
		return holidayCharge;
	}


	public void setHolidayCharge(Boolean holidayCharge) {
		this.holidayCharge = holidayCharge;
	}

}
