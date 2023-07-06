package com.point.sale;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.point.sale.data.Holidays;
import com.point.sale.data.Tools;
import com.point.sale.helper.InputValidationHelper;
import com.point.sale.model.Receipt;
import com.point.sale.model.Sale;
import com.point.sale.model.ValidationResponse;
import com.point.sale.service.impl.SaleService;

public class StartTool {
	
	/* NOTES
	 * Initialize list of tools into HasMap(String, Tool) String is the code
	 * Initialize list of holidays
	 * 	Receive input for tool checkout
	 * 		Take Code
	 * 		Take Checkout Date
	 * 		Take Discount
	 * 		Take Rental Day count
	 * 			Validate the inputs according to params
	 * 			if Invalid, throw user friendly message
	 * 			return to entry again
	 * 			
	 * 		if Valid, feed into an Object Sale
	 * 			Find the tool from the HashMap
	 * 			Calculate the number of days to charge
	 * 				calculate if there were any holidays
	 * 				calculate if there were any days not chargeable
	 * 				with the remaining charge days multiply with the value of the tool per day
	 * 				calculate total charge with discount if any
	 * 				
	 * 				once all done, have all the values ready to print
	 * 				
	 * 
	 * 
	 */
	
	public SaleService saleService = new SaleService();
	
	static {
		// Setup list of tools and holidays
		Tools.initialize();
		Holidays.initialize();
	}
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(Tests.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
	  	}
		if (result.wasSuccessful()) {
			new StartTool().startUpStore();
		}
	}
	
	
	public void startUpStore() {
		
		System.out.println("Hello! Welcome to Simon's Store!");
		
		// start input
		Sale input = new Sale(); 
		while (true) {
			if (getInput(input)) break;
		}
		
		// valid entries, compute the receipt
		Receipt receipt = saleService.calculateRentalAgreement(input);
		
		// print receipt
		receipt.print();
		
		// continue?
		if (continueStore()) {
			startUpStore();
		}
		
	}
	
	public ValidationResponse validateInput(Sale input) {
		return InputValidationHelper.validateSaleInput(input);
	}
	
	public Boolean getInput(Sale sale) {
		
		receiveInput(sale);
		
		// validate input
		ValidationResponse validation = validateInput(sale);
		
		// error handling
		if (!validation.getValidInput()) {
			System.out.println(validation.getValidationError());
		}
		return validation.getValidInput();
		
	}
	
	public void receiveInput(Sale sale) {
		// receive the fields
		try {
			Scanner scin = new Scanner(System.in);
			System.out.println("===========================================================");
			System.out.println("===========================================================");
			System.out.println("Please Enter the Item's code: ");
			sale.setToolCode(scin.nextLine());
			System.out.println("How many days would you like this item?");
			sale.setRentalDays(scin.nextInt());
			System.out.println("How much discount are you receiving?");
			sale.setDiscountPercent(scin.nextInt());
			System.out.println("When is the checkout date? Please use the format MM/DD/YY (numbers)");
			String dateStr = scin.next();
			if (dateStr != null) {
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
				sale.setCheckoutDate(format.parse(dateStr));
			}
		} catch (Exception e) {
			System.out.println("There was an unexpected error while reading your entries. Please try again.");
			receiveInput(sale);
		}
		
	}
	
	public Boolean continueStore() {
		Scanner scin = new Scanner(System.in);
		System.out.println("Would you like to generate another receipt? (Y/N)");
		String input = scin.nextLine();
		if (input.equalsIgnoreCase("Y")) return true;
		if (input.equalsIgnoreCase("N")) return false;
		else {
			System.out.println("Invalid entry, please try again"); return continueStore();
		}
	}
	
	
	
}
