package com.point.sale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.point.sale.helper.InputValidationHelper;
import com.point.sale.model.Receipt;
import com.point.sale.model.Sale;
import com.point.sale.model.ValidationResponse;
import com.point.sale.service.impl.SaleService;

public class Tests {
	
	public static final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
	public SaleService saleService = new SaleService();
	
	@Test
	public void test1() throws ParseException {
		Sale sale = new Sale("JAKR", format.parse("9/3/15"), 101, 5);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.FALSE, valid.getValidInput());
		assertEquals(InputValidationHelper.INVALID_DISCOUNT_VALUE, valid.getValidationError());
	}
	@Test
	public void test2() throws ParseException {
		Sale sale = new Sale("LADW", format.parse("7/2/20"), 10, 3);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.TRUE, valid.getValidInput());
		assertNull(valid.getValidationError());
		Receipt receipt = saleService.calculateRentalAgreement(sale);
		String validDayToCharge = "2";
		assertEquals(validDayToCharge, receipt.getChargeDays());
		String validPreDiscountCharge = "3.98";
		assertEquals(validPreDiscountCharge, receipt.getPreDiscountCharge());
		String validFinalCharge = "3.58";
		assertEquals(validFinalCharge, receipt.getFinalCharge());
		String validDueDate = "07/05/20";
		assertEquals(validDueDate, receipt.getDueDate());
	}
	@Test
	public void test3() throws ParseException {
		Sale sale = new Sale("CHNS", format.parse("7/2/15"), 25, 5);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.TRUE, valid.getValidInput());
		assertNull(valid.getValidationError());
		Receipt receipt = saleService.calculateRentalAgreement(sale);
		String validDayToCharge = "3";
		assertEquals(validDayToCharge, receipt.getChargeDays());
		String validPreDiscountCharge = "4.47";
		assertEquals(validPreDiscountCharge, receipt.getPreDiscountCharge());
		String validFinalCharge = "3.35";
		assertEquals(validFinalCharge, receipt.getFinalCharge());
		String validDueDate = "07/07/15";
		assertEquals(validDueDate, receipt.getDueDate());
	}
	@Test
	public void test4() throws ParseException {
		Sale sale = new Sale("JAKD", format.parse("9/3/15"), 0, 6);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.TRUE, valid.getValidInput());
		assertNull(valid.getValidationError());
		Receipt receipt = saleService.calculateRentalAgreement(sale);
		String validDayToCharge = "3";
		assertEquals(validDayToCharge, receipt.getChargeDays());
		String validPreDiscountCharge = "8.97";
		assertEquals(validPreDiscountCharge, receipt.getPreDiscountCharge());
		String validFinalCharge = "8.97";
		assertEquals(validFinalCharge, receipt.getFinalCharge());
		String validDueDate = "09/09/15";
		assertEquals(validDueDate, receipt.getDueDate());
	}
	@Test
	public void test5() throws ParseException {
		Sale sale = new Sale("JAKR", format.parse("7/2/15"), 0, 9);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.TRUE, valid.getValidInput());
		assertNull(valid.getValidationError());
		Receipt receipt = saleService.calculateRentalAgreement(sale);
		String validDayToCharge = "5";
		assertEquals(validDayToCharge, receipt.getChargeDays());
		String validPreDiscountCharge = "14.95";
		assertEquals(validPreDiscountCharge, receipt.getPreDiscountCharge());
		String validFinalCharge = "14.95";
		assertEquals(validFinalCharge, receipt.getFinalCharge());
		String validDueDate = "07/11/15";
		assertEquals(validDueDate, receipt.getDueDate());
	}
	@Test
	public void test6() throws ParseException {
		Sale sale = new Sale("JAKR", format.parse("7/2/20"), 50, 4);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.TRUE, valid.getValidInput());
		assertNull(valid.getValidationError());
		Receipt receipt = saleService.calculateRentalAgreement(sale);
		String validDayToCharge = "1";
		assertEquals(validDayToCharge, receipt.getChargeDays());
		String validPreDiscountCharge = "2.99";
		assertEquals(validPreDiscountCharge, receipt.getPreDiscountCharge());
		String validFinalCharge = "1.50";
		assertEquals(validFinalCharge, receipt.getFinalCharge());
		String validDueDate = "07/06/20";
		assertEquals(validDueDate, receipt.getDueDate());
	}
	@Test
	public void test7() throws ParseException {
		Sale sale = new Sale("CHNS", format.parse("9/3/23"), -1, 5);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.FALSE, valid.getValidInput());
		assertEquals(InputValidationHelper.INVALID_DISCOUNT_VALUE, valid.getValidationError());
	}
	@Test
	public void test8() throws ParseException {
		Sale sale = new Sale("CHNS", format.parse("9/3/23"), 20, -10);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.FALSE, valid.getValidInput());
		assertEquals(InputValidationHelper.INVALID_DAYS_VALUE, valid.getValidationError());
	}
	@Test
	public void test9() throws ParseException {
		Sale sale = new Sale("CHNSSS", format.parse("11/24/20"), 10, 10);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.FALSE, valid.getValidInput());
		assertEquals(InputValidationHelper.INVALID_CODE_VALUE, valid.getValidationError());
	}
	
	@Test
	public void test10() throws ParseException {
		Sale sale = new Sale("CHNS", format.parse("7/2/21"), 5, 5);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.TRUE, valid.getValidInput());
		assertNull(valid.getValidationError());
		Receipt receipt = saleService.calculateRentalAgreement(sale);
		String validDayToCharge = "3";
		assertEquals(validDayToCharge, receipt.getChargeDays());
		String validPreDiscountCharge = "4.47";
		assertEquals(validPreDiscountCharge, receipt.getPreDiscountCharge());
		String validFinalCharge = "4.25";
		assertEquals(validFinalCharge, receipt.getFinalCharge());
		String validDueDate = "07/07/21";
		assertEquals(validDueDate, receipt.getDueDate());
	}
	
	@Test
	public void test11() throws ParseException {
		Sale sale = new Sale("LADW", format.parse("7/2/21"), 5, 5);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.TRUE, valid.getValidInput());
		assertNull(valid.getValidationError());
		Receipt receipt = saleService.calculateRentalAgreement(sale);
		String validDayToCharge = "4";
		assertEquals(validDayToCharge, receipt.getChargeDays());
		String validPreDiscountCharge = "7.96";
		assertEquals(validPreDiscountCharge, receipt.getPreDiscountCharge());
		String validFinalCharge = "7.56";
		assertEquals(validFinalCharge, receipt.getFinalCharge());
		String validDueDate = "07/07/21";
		assertEquals(validDueDate, receipt.getDueDate());
	}
	
	@Test
	public void test12() throws ParseException {
		Sale sale = new Sale("JAKD", format.parse("7/2/21"), 5, 5);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.TRUE, valid.getValidInput());
		assertNull(valid.getValidationError());
		Receipt receipt = saleService.calculateRentalAgreement(sale);
		String validDayToCharge = "2";
		assertEquals(validDayToCharge, receipt.getChargeDays());
		String validPreDiscountCharge = "5.98";
		assertEquals(validPreDiscountCharge, receipt.getPreDiscountCharge());
		String validFinalCharge = "5.68";
		assertEquals(validFinalCharge, receipt.getFinalCharge());
		String validDueDate = "07/07/21";
		assertEquals(validDueDate, receipt.getDueDate());
	}
	
	@Test
	public void test13() throws ParseException {
		Sale sale = new Sale("JAKR", format.parse("9/2/21"), 5, 5);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.TRUE, valid.getValidInput());
		assertNull(valid.getValidationError());
		Receipt receipt = saleService.calculateRentalAgreement(sale);
		String validDayToCharge = "2";
		assertEquals(validDayToCharge, receipt.getChargeDays());
		String validPreDiscountCharge = "5.98";
		assertEquals(validPreDiscountCharge, receipt.getPreDiscountCharge());
		String validFinalCharge = "5.68";
		assertEquals(validFinalCharge, receipt.getFinalCharge());
		String validDueDate = "09/07/21";
		assertEquals(validDueDate, receipt.getDueDate());
	}
	
	@Test
	public void test14() throws ParseException {
		Sale sale = new Sale("LADW", format.parse("9/2/21"), 5, 5);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.TRUE, valid.getValidInput());
		assertNull(valid.getValidationError());
		Receipt receipt = saleService.calculateRentalAgreement(sale);
		String validDayToCharge = "4";
		assertEquals(validDayToCharge, receipt.getChargeDays());
		String validPreDiscountCharge = "7.96";
		assertEquals(validPreDiscountCharge, receipt.getPreDiscountCharge());
		String validFinalCharge = "7.56";
		assertEquals(validFinalCharge, receipt.getFinalCharge());
		String validDueDate = "09/07/21";
		assertEquals(validDueDate, receipt.getDueDate());
	}
	
	@Test
	public void test15() throws ParseException {
		Sale sale = new Sale("CHNS", format.parse("9/2/21"), 5, 5);
		ValidationResponse valid = InputValidationHelper.validateSaleInput(sale);
		assertEquals(Boolean.TRUE, valid.getValidInput());
		assertNull(valid.getValidationError());
		Receipt receipt = saleService.calculateRentalAgreement(sale);
		String validDayToCharge = "3";
		assertEquals(validDayToCharge, receipt.getChargeDays());
		String validPreDiscountCharge = "4.47";
		assertEquals(validPreDiscountCharge, receipt.getPreDiscountCharge());
		String validFinalCharge = "4.25";
		assertEquals(validFinalCharge, receipt.getFinalCharge());
		String validDueDate = "09/07/21";
		assertEquals(validDueDate, receipt.getDueDate());
	}

}
