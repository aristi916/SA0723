package com.point.sale.service.impl;

import java.text.DecimalFormat;
import java.util.Date;

import com.point.sale.data.Tools;
import com.point.sale.model.Item;
import com.point.sale.model.ItemSaleFactory;
import com.point.sale.model.Receipt;
import com.point.sale.model.Sale;
import com.point.sale.model.SaleRules;
import com.point.sale.model.Tool;
import com.point.sale.model.ToolSaleRules;
import com.point.sale.service.ItemService;

public class SaleService {
	
	private static final Long DAY_IN_MILLIS = 86400000l;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	private ItemSaleFactory itemSaleFactory = new ItemSaleFactory();
	
	/**
	 * this method calculates the sale item receipt of the item passed.
	 * This does not validate input, it assumes the values are already validated.
	 * @param saleItem
	 * @return
	 */
	public Receipt calculateRentalAgreement(Sale saleItem) {
		
		Item item = Tools.getToolByCode(saleItem.getToolCode());
		Tool toolItem = ((Tool)item);
		SaleRules rules = new ToolSaleRules(toolItem);
		
		Integer daysToCharge = calculateDaysToCharge(saleItem.getCheckoutDate(), saleItem.getRentalDays(), item);
		Date dueDate = calculateDueDate(saleItem.getCheckoutDate(), saleItem.getRentalDays());
		Double preDiscountCharge = calculatePreDiscountCharge(rules, daysToCharge);
		Double discountAmount = (preDiscountCharge * saleItem.getDiscountPercent())/100;
		Double finalCharge = preDiscountCharge - discountAmount;
		
		Receipt receipt = new Receipt();
		receipt.setBrand(toolItem.getBrandName());
		receipt.setToolType(toolItem.getType());
		receipt.setItemCode(toolItem.getCode());
		receipt.setRentalDays(saleItem.getRentalDays());
		receipt.setCheckoutDate(saleItem.getCheckoutDate());
		receipt.setDueDate(dueDate);
		receipt.setDailyRentalCharge(((ToolSaleRules)rules).getPricePerDay());
		receipt.setChargeDays(daysToCharge);
		receipt.setPreDiscountCharge(preDiscountCharge);
		receipt.setDiscountPercent(saleItem.getDiscountPercent());
		receipt.setDiscountAmount(discountAmount);
		receipt.setFinalCharge(finalCharge);
		
		return receipt;
	}
	
	private Integer calculateDaysToCharge(Date checkoutDate, Integer days, Item item) {
		ItemService itemService = itemSaleFactory.getItemSaleFactory(item);
		Integer daysToCharge = itemService.getDaysCountToCharge(item, days, checkoutDate);
		return daysToCharge;
	}
	
	private Date calculateDueDate(Date checkoutDate, Integer days) {
		Long checkoutDateInMillis = checkoutDate.getTime();
		return new Date(checkoutDateInMillis + (DAY_IN_MILLIS * days));
	}
	
	private Double calculatePreDiscountCharge(SaleRules rules, Integer daysToCharge) {
		Double itemChargeDaily = 0d;
		if (rules instanceof ToolSaleRules) {
			itemChargeDaily = ((ToolSaleRules) rules).getPricePerDay();
		}
		
		if (itemChargeDaily > 0) {
			return itemChargeDaily * daysToCharge.doubleValue();
		}
		
		return 0d;
	}
	
}
