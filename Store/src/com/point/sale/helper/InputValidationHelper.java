package com.point.sale.helper;

import com.point.sale.data.Tools;
import com.point.sale.model.Sale;
import com.point.sale.model.ValidationResponse;

public class InputValidationHelper {
	
	public static final String INVALID_DISCOUNT_VALUE = "You have entered an invalid discount number, it needs to be between 0 - 100, please correct it.";
	public static final String INVALID_DAYS_VALUE = "You have entered an invalid number of days, it has to be greater than 1.";
	public static final String INVALID_CODE_VALUE = "You have entered an invalid item code, please check again.";
	
	
	public static ValidationResponse validateSaleInput(Sale sale) {
		
		if (!validateDiscount(sale.getDiscountPercent())) {
			return new ValidationResponse(false, INVALID_DISCOUNT_VALUE);
		}
		
		if (!validateDaysCount(sale.getRentalDays())) {
			return new ValidationResponse(false, INVALID_DAYS_VALUE);
		}
		
		if (!validateItemCode(sale.getToolCode())) {
			return new ValidationResponse(false, INVALID_CODE_VALUE);
		}
		
		return new ValidationResponse(true, null);
	}
	
	private static Boolean validateItemCode(String itemCode) {
		if (Tools.getToolByCode(itemCode) == null) return false;
		
		return true;
	}
	
	private static Boolean validateDiscount(Integer discount) {
		
		if (discount < 0 || discount > 100) return false;
		
		return true;
	}
	
	private static Boolean validateDaysCount(Integer days) {
		
		if (days < 1) return false;
		
		return true;
	}

}
