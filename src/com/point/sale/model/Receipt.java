package com.point.sale.model;

import java.text.DecimalFormat;
import java.util.Date;

import com.point.sale.helper.CalendarHelper;

public class Receipt {
	
	/*
	 * This will have all the values that need to be returned to the user once calculated the sale.
	 */
	private String itemCode;
	private String itemType;
	private String brand;
	private String rentalDays;
	private String checkoutDate;
	private String dueDate;
	private String dailyRentalCharge;
	private String chargeDays;
	private String preDiscountCharge;
	private String discountPercent;
	private String discountAmount;
	private String finalCharge;
	
	/**
	 * Print Receipt
	 */
	public void print() {
		System.out.println("===========================================================");
		System.out.println("===========================================================");
		System.out.println("Tool Code: " + itemCode);
		System.out.println("Tool Type: " + itemType);
		System.out.println("Tool Brand: " + brand);
		System.out.println("Rental Days: " + rentalDays);
		System.out.println("Check out Date: " + checkoutDate);
		System.out.println("Due Date: " + dueDate);
		System.out.println("Daily Rental Charge: $" + dailyRentalCharge);
		System.out.println("Charge Days: " + chargeDays);
		System.out.println("Pre-Discount Charge: $" + preDiscountCharge) ;
		System.out.println("Discount Percent: " + discountPercent + "%");
		System.out.println("Discount Amount: $" + discountAmount);
		System.out.println("Final Charge: $" + finalCharge);
		System.out.println("===========================================================");
		System.out.println("===========================================================");
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(Integer rentalDays) {
		this.rentalDays = rentalDays.toString();
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = CalendarHelper.formatDate(checkoutDate);
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = CalendarHelper.formatDate(dueDate);
	}

	public String getDailyRentalCharge() {
		return dailyRentalCharge;
	}

	public void setDailyRentalCharge(Double dailyRentalCharge) {
		this.dailyRentalCharge = String.format("%,.2f", dailyRentalCharge);
	}

	public String getChargeDays() {
		return chargeDays;
	}

	public void setChargeDays(Integer chargeDays) {
		this.chargeDays = chargeDays.toString();
	}

	public String getPreDiscountCharge() {
		return preDiscountCharge;
	}

	public void setPreDiscountCharge(Double preDiscountCharge) {
		this.preDiscountCharge = String.format("%,.2f", preDiscountCharge);
	}

	public String getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent.toString();
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = String.format("%,.2f", discountAmount);
	}

	public String getFinalCharge() {
		return finalCharge;
	}

	public void setFinalCharge(Double finalCharge) {
		this.finalCharge = String.format("%,.2f", finalCharge);
	}
	
}
