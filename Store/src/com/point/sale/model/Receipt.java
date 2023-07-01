package com.point.sale.model;

import java.text.DecimalFormat;
import java.util.Date;

import com.point.sale.helper.CalendarHelper;

public class Receipt {
	
	/*
	 * This will have all the values that need to be returned to the user once calculated the sale.
	 */
	private String itemCode;
	private Tool.ToolType toolType;
	private String brand;
	private Integer rentalDays;
	private Date checkoutDate;
	private Date dueDate;
	private Double dailyRentalCharge;
	private Integer chargeDays;
	private Double preDiscountCharge;
	private Integer discountPercent;
	private Double discountAmount;
	private Double finalCharge;
	
	/**
	 * Print Receipt
	 */
	public void print() {
		System.out.println("Tool Code: " + itemCode);
		System.out.println("Tool Type: " + toolType);
		System.out.println("Tool Brand: " + brand);
		System.out.println("Rental Days: " + rentalDays);
		System.out.println("Check out Date: " + CalendarHelper.formatDate(checkoutDate));
		System.out.println("Due Date: " + CalendarHelper.formatDate(dueDate));
		System.out.println("Daily Rental Charge: $" + String.format("%,.2f", dailyRentalCharge));
		System.out.println("Charge Days: " + chargeDays);
		System.out.println("Pre-Discount Charge: $" + String.format("%,.2f", preDiscountCharge)) ;
		System.out.println("Discount Percent: " + discountPercent + "%");
		System.out.println("Discount Amount: $" + String.format("%,.2f", discountAmount));
		System.out.println("Final Charge: $" + String.format("%,.2f", finalCharge));
	}


	public String getItemCode() {
		return itemCode;
	}


	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public Tool.ToolType getToolType() {
		return toolType;
	}


	public void setToolType(Tool.ToolType toolType) {
		this.toolType = toolType;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public Integer getRentalDays() {
		return rentalDays;
	}


	public void setRentalDays(Integer rentalDays) {
		this.rentalDays = rentalDays;
	}


	public Date getCheckoutDate() {
		return checkoutDate;
	}


	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public Double getDailyRentalCharge() {
		return dailyRentalCharge;
	}


	public void setDailyRentalCharge(Double dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
	}


	public Integer getChargeDays() {
		return chargeDays;
	}


	public void setChargeDays(Integer chargeDays) {
		this.chargeDays = chargeDays;
	}


	public Double getPreDiscountCharge() {
		return preDiscountCharge;
	}


	public void setPreDiscountCharge(Double preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
	}


	public Integer getDiscountPercent() {
		return discountPercent;
	}


	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}


	public Double getDiscountAmount() {
		return discountAmount;
	}


	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}


	public Double getFinalCharge() {
		return finalCharge;
	}


	public void setFinalCharge(Double finalCharge) {
		this.finalCharge = finalCharge;
	}

}
