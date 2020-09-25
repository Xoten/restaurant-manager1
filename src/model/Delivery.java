package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Delivery implements Serializable {
	public final static long serialVersionUID = 1;
	private final static int  REQUESTED = 0 ; 
	private final static int  IN_PROCESS = 1 ; 
	private final static int  SENT = 2 ; 
	private final static int  DELIVERED = 3 ; 
	private String orderState;
	private int deliveryCode;
	private int date;
	private int clientId;
	private int restaurantNit;
	private ArrayList<Product> products;
	private int hour;
	
	
	
	public Delivery(int deliveryCode, int date, int hour, int clientId,  int restaurantNit) {
		this.restaurantNit = restaurantNit;
		this.deliveryCode = deliveryCode;
		this.date= date;
		this.clientId = clientId;
		this.hour = hour;
		
		products = new ArrayList<Product>();
	}
	
	public void addProductToOrder(int code, int quantities) {
		
		Product p1 = new Product(code, quantities);
		
		products.add(p1);
		
	}
	
	

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(int orderStateNumber) {
		switch (orderStateNumber) {
		case REQUESTED:
			orderState = "Requested";
			break;
			
		case IN_PROCESS:
			orderState = "In process";
			break;

		case SENT:
			orderState = "Sent";
			break;
			
		case DELIVERED:
			orderState = "Delivered";
			break;
		}
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
