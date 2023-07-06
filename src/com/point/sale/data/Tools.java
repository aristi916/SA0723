package com.point.sale.data;

import java.util.HashMap;

import com.point.sale.model.Tool;

public class Tools {
	
	public static HashMap<String, Tool> tools;
	
	/**
	 * This class is a static hashmap that will store tools by code as a key
	 * This represents a sort of DB
	 */
	
	public static void initialize() {
//		System.out.println("Initializing Tools");
		
		if (tools == null || tools.isEmpty()) {
			tools = new HashMap();
		}
		
		tools.put("CHNS", new Tool("CHNS", Tool.ToolType.Chainsaw, "Stihl"));
		tools.put("LADW", new Tool("LADW", Tool.ToolType.Ladder, "Werner"));
		tools.put("JAKD", new Tool("JAKD", Tool.ToolType.Jackhammer, "DeWalt"));
		tools.put("JAKR", new Tool("JAKR", Tool.ToolType.Jackhammer, "Ridgid"));
		
		
//		System.out.println("Finished Initializing tools!");
	}
	
	public static Tool getToolByCode(String code) {
		if (tools != null && !tools.isEmpty()) {
			return tools.get(code);
		}
		
		return null;
	}

}
