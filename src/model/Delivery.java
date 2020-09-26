package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Delivery implements Serializable {
	public final static long serialVersionUID = 1;
	
	private int deliveryCode;
	private Date date;
	private String clientId;
	private int restaurantNit;
	private ArrayList<Product> products;
	private ArrayList<Integer> quantities;
	private OrderState orderState;
	
	
	
	
	
	
	
	public Delivery(int deliveryCode, String clientId,  int restaurantNit, ArrayList<Product> products, ArrayList<Integer> quantities) {
		this.restaurantNit = restaurantNit;
		this.deliveryCode = deliveryCode;
		date = new Date();
		this.clientId = clientId;
		this.quantities = quantities;
		this.products = products;
		
		products = new ArrayList<Product>();
	}
	
	
	
	

	public String getOrderState() {
		return orderState.toString();
	}
	public void setOrderState(int orderStateNum) {
		switch (orderStateNum) {
		case 0:
			orderState = OrderState.valueOf( "REQUESTED" );
			break;
			
		case 1:
			orderState = OrderState.valueOf( "IN_PROCESS" );
			break;

		case 2:
			orderState = OrderState.valueOf( "SENT" );
			break;
			
		case 3:
			orderState = OrderState.valueOf( "DELIVERED" );
			break;
		}
		
	}

	

	public int getDeliveryCode() {
		return deliveryCode;
	}

	public String getDate() {
		return date.toString();
	}

	public String getClientId() {
		return clientId;
	}

	public int getRestaurantNit() {
		return restaurantNit;
	}

	public ArrayList<Product> getProducts() {
		
		return products;
	}
	
 @Override
	public String toString() {
		
	 return "Delivery [orderState=" + orderState + ", deliveryCode=" + deliveryCode + ", dateAndTime=" + date
				+ ", clientId=" + clientId + ", restaurantNit=" + restaurantNit + ", products=" + products
				+ ", quantities=" + quantities + "]";
		
		
		
	}



	
	
	
	
	
	
	
	
	
	

}
