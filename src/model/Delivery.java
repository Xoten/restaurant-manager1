package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Delivery implements Serializable {
	

	
	public final static long serialVersionUID = 1;

	private String code;
	private String date;
	private String clientIdNum;
	private String restaurantNit;
	public enum status {REQUESTED, IN_PROCESS, SENT, DELIVERED}
	public status deliveryStat;
	Random random = new Random();
	private List<Product> deliveries;

	
	public Delivery(String code, String ClientIdNum, String restNit) {
		this.code = code;
		this.date = getDate();
		this.clientIdNum = ClientIdNum;
		this.restaurantNit = restNit;
		this.deliveryStat = status.REQUESTED;
		deliveries = new ArrayList<Product>() ;

	}

	public String getDeliveryStat() {
		return deliveryStat.name();
	}

	@SuppressWarnings({ "static-access" })
	public String setOrderStatByCondition(String status) {
		String info = "";
		if(getDeliveryStat().equalsIgnoreCase("REQUESTED")) {
			setDeliveryStat(deliveryStat.IN_PROCESS);
			info += "Order status setted IN PROCESS";
		} else if (getDeliveryStat().equalsIgnoreCase("IN_PROCESS")) {
			setDeliveryStat(deliveryStat.SENT);
			info += "Order status setted SENT";
		} else if(getDeliveryStat().equalsIgnoreCase("SENT")){
			setDeliveryStat(deliveryStat.DELIVERED);
			info += "Order status setted DELIVERED";
		} else if(getDeliveryStat().equalsIgnoreCase("DELIVERED")) {
			info += "Order status is DELIVERED, Order closed!";
		}
		return info;
	}

	public void setDeliveryStat(status orderStat) {
		this.deliveryStat = orderStat;
	}
	
	public int searchProductInList(String code) {
		int position = 0;
		boolean found = !false;
		for(int i=0; i<deliveries.size() && found; i++){
			if(deliveries.get(i).getCode().equalsIgnoreCase(code)){
				found = true;
				position = i;
			}
		}
		return position;
	}

	public String getDate() {
		String date = "";
		date += ""+(LocalDate.now().getDayOfMonth())+"/"+LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
		return date;
	}
	
	public String getRestaurantNit() {
		return restaurantNit;
	}

	public String getClientIdNum() {
		return clientIdNum;
	}
	
	public void setClientIdNum(String clientIdNum) {
		this.clientIdNum = clientIdNum;
	}

	public void setRestaurantNit(String restaurantNit) {
		this.restaurantNit = restaurantNit;
	}
	
	public void updateProductsCode(String oldCode, String newCode) {
		for(int i = 0; i < deliveries.size(); i++) {
			deliveries.get(i).getCode().equalsIgnoreCase(oldCode);
			deliveries.get(i).setCode(newCode);
		}
	}
	
	public String getCode() {
		return code;
	}

	public List<Product> getOrdersProductList(){
		return deliveries;
	}

	public String addProductInOrderList(String code, int quantity) {
		String info = "";
		Product p = new Product(code, quantity);
		deliveries.add(p);
		info += "Added!";
		return info;
	}

	public String getOrdersList(){
		String info = "";
		for (int i = 0; i < deliveries.size(); i++) {
			info += "\nProduct code: "+deliveries.get(i).getCode()+"\nQuantity: "+deliveries.get(i).getQuantity();
		}
		return info;
	}

	public String toString() {
		String concat = "";
		concat += "Code: "+code+"\nDate: "+date+"\nClient ID: "+clientIdNum+"\nRestaurant NIT: "+restaurantNit+"\nStatus: "+deliveryStat+getOrdersList();
		return concat;
	}

	public String getInfo() {
		String info = "";
		info += "\nCode: "+code+"\nDate: "+date+"\nClient ID: "+clientIdNum+"\nRestaurant NIT: "+restaurantNit+"\nStatus: "+deliveryStat+getOrdersList()+"\n";
		return info;
	}
}