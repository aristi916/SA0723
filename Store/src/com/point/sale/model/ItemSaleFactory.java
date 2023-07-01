package com.point.sale.model;

import com.point.sale.service.ItemService;
import com.point.sale.service.impl.ToolService;

public class ItemSaleFactory {
	
	
	private ToolService toolService = new ToolService();
	
	
	public ItemService getItemSaleFactory(Item item) {
		
		if (item instanceof Tool) {
			return toolService;
		}
		
		
		return null;
		
	}

}
