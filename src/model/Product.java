package model;

import java.io.Serializable;

public class Product implements Serializable {
	public final static long serialVersionUID = 1;
	private int code;
	private String name;
	private String description;
	private double price;
	private int restaurantNit;
	private int quantities;
	
	public Product() {
		
		
		
	}
	
	public Product(int code, String name, String description, double price, int restaurantNit) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.price = price;
		this.restaurantNit = restaurantNit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}

	public int getRestaurantNit() {
		return restaurantNit;
	}
	
	public int getQuantities() {
		
		return quantities;
	}
	
	public void setRestaurantNit(int restaurantNit) {
		
		this.restaurantNit = restaurantNit;
	}
	
	public void setCode(int code) {
		
		this.code = code;
	}
	
	@Override
	public String toString() {
		
		String info = "name" + getName() + "description" + getDescription() + "price" + getPrice() + "restaurantNit"
		 + getRestaurantNit();
		return info;
	}
     public String getInfo() {
		
		String info = "name" + getName() + "description" + getDescription() + "price" + getPrice() + "restaurantNit"
		 + getRestaurantNit();
		return info;
	}
	
	
	
	
	

}
