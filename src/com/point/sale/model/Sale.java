package com.point.sale.model;

import java.util.Date;

public class Sale {
	
	private String toolCode;
	private Date checkoutDate;
	private Integer discountPercent;
	private Integer rentalDays;
	
	
	public Sale(String toolCode, Date checkoutDate, Integer discount, Integer rentDays) {
		this.toolCode = toolCode;
		this.checkoutDate = checkoutDate;
		this.discountPercent = discount;
		this.rentalDays = rentDays;
	}

	public Sale() { }

	public String getToolCode() {
		return toolCode;
	}

	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public Integer getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}

	public Integer getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(Integer rentalDays) {
		this.rentalDays = rentalDays;
	}
	

}
