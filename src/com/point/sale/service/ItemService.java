package com.point.sale.service;

import java.util.Date;

import com.point.sale.model.Item;

public interface ItemService {
	
	
	public Integer getDaysCountToCharge(Item item, Integer days, Date checkout);

}
