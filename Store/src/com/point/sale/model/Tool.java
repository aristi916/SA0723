package com.point.sale.model;

public class Tool implements Item {
	
	public enum ToolType {Jackhammer, Chainsaw, Ladder}
	
	private String code;
	private ToolType type;
	private String brandName;

	
	public Tool(String code, ToolType type, String brandName) {
		this.code = code;
		this.type = type;
		this.brandName = brandName;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ToolType getType() {
		return type;
	}
	public void setType(ToolType type) {
		this.type = type;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}
