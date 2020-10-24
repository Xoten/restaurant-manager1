package model;

import java.io.Serializable;

public class Product implements Serializable {
	
	
	
	public final static long serialVersionUID = 1;

	private String code;
	private String name;
	private String info;
	private double price;
	private String restaurantNit;
	private int quantity;

	
	public Product(String name, String code, String info, double price, String restNit) {
		this.code = code;
		this.name = name;
		this.info = info;
		this.price = price;
		this.restaurantNit = restNit;
	}
	
	public Product(String code, int quantity) {
		this.code = code;
		this.quantity = quantity;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setCost(double cost) {
		this.price = cost;
	}
	
	public String getRestaurantNit() {
		return restaurantNit;
	}
	
	public void setRestaurantNit(String restaurantNit) {
		this.restaurantNit = restaurantNit;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getInfoProducts() {
		String allInfo = "";
		allInfo += "\nName: "+name+"\nCode: "+code+"\nInformation: "+info+"\nCost: "+"$"+price+"\nRestaurant NIT: "+restaurantNit+"\n";
		return allInfo;
	}
	
	public String toString() {
		String concat = "";
		concat += "\nName: "+name+"\nCode: "+code+"\nInformation: "+info+"\nCost: "+"$"+price+"\nRestaurant NIT: "+restaurantNit+"\n";
		return concat;
	}
	
    public String getInfoToExport(String separator) {
        String info = "";
        info += code + separator + quantity;
        return info;
    }
}