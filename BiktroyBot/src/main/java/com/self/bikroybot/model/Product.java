package com.self.bikroybot.model;

public class Product {
	private String name;
	private int postAmount;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPostAmount() {
		return postAmount;
	}
	public void setPostAmount(int postAmount) {
		this.postAmount = postAmount;
	}
	
	@Override
	public String toString() {
		return "Product [name=" + name + ", postAmount=" + postAmount + "]";
	}
	
	
	
}
