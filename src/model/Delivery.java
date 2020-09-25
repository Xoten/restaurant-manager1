package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Delivery implements Serializable {
	public final static long serialVersionUID = 1;
	
	private int deliveryCode;
	private int date;
	private int clientId;
	private int restaurantNit;
	private ArrayList<Product> products;
	private int hour;
	
	public enum status{REQUESTED,IN_PROCESS,SENT,DELIVERED}
	public status OrderState;
	
	
	
	public Delivery(int deliveryCode, int date, int hour, int clientId,  int restaurantNit) {
		this.restaurantNit = restaurantNit;
		this.deliveryCode = deliveryCode;
		this.date= date;
		this.clientId = clientId;
		this.hour = hour;
		this.OrderState = status.REQUESTED;
		
		products = new ArrayList<Product>();
	}
	
	public void addProductToOrder(int code, int quantities) {
		
		Product p1 = new Product(code, quantities);
		
		products.add(p1);
		
	}
	
	

	public String getOrderState() {
		return OrderState.name();
	}

	@SuppressWarnings("static-access")
	public String setOrderStatebyValue(String status) {
		String info = "";
		
		if(getOrderState().equalsIgnoreCase("REQUESTED")) {
			
			setOrderState(OrderState.IN_PROCESS);
			
			info+= "Order status setted IN_PROCESS";
			
		}else if(getOrderState().equalsIgnoreCase("IN_PROCESS")) {
			
			setOrderState(OrderState.SENT);
			
			info+= "Order status setted in SENT";
		
	    }else  if(getOrderState().equalsIgnoreCase("SENT")) {
			
			setOrderState(OrderState.DELIVERED);
			
			info+= "Order status setted in SENT";

	    }
		
		return info;
	}
	

	private void setOrderState(status orderState) {
		
		this.OrderState = status.REQUESTED;
		
	}

	

	public int getDeliveryCode() {
		return deliveryCode;
	}

	public int getDate() {
		return date;
	}

	public int getClientId() {
		return clientId;
	}

	public int getRestaurantNit() {
		return restaurantNit;
	}

	public ArrayList<Product> getProducts() {
		
		return products;
	}
	
	public int getHour() {
		
		return hour;
	}
	



	
	
	
	
	
	
	
	
	
	

}
