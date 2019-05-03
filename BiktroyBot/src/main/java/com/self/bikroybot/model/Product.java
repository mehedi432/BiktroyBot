package com.self.bikroybot.model;

public class Product {
	private String name;
	private String postAmount;

	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPostAmount() {
		return postAmount;
	}



	public void setPostAmount(String postAmount) {
		this.postAmount = postAmount;
	}



	@Override
	public String toString() {
		return "Product [name=" + name + ", postAmount=" + postAmount + "]";
	}
	
	
	
}
