package model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Manager {

	
	
	public final static String SAVE_RESTAURANTS = "data/restaurantes.rt";
	public final static String SAVE_CLIENTS = "data/clientes.c";
	public final static String SAVE_PRODUCTS = "data/productos.p";
	public final static String SAVE_DELIVERIES = "data/pedidos.pd";
	
	private final static String SEPARATOR = ",";
    Restaurant r1 = new Restaurant();

	private List<Restaurant> restaurant;

	public Manager() {
		restaurant = new ArrayList<>();
	}

	public List<Restaurant> getRestaurants() {
		return restaurant;

	}
	
	public void sortRestaurantByName() {
		
		RestaurantNameComparator nc = new RestaurantNameComparator();
		Collections.sort(restaurant,nc);
		
	}
	
	public void showRestaurant() {
		for(int i=0;i<restaurant.size();i++) {
			System.out.println(restaurant.get(i).getNit()+"  "+restaurant.get(i).getName()+" "+restaurant.get(i).getAdminName());
		}
	}
	
	
	
	
	public boolean usedNit(int nit) {
		boolean usedNit = false;

		for (int i = 0; i < restaurant.size() && !usedNit ; i++){
			
			if(restaurant.get(i).getNit() == nit) {
				
				usedNit = true;
			}
			
		}
		return usedNit;
	}
	
	public boolean registeredId(String id) {
		boolean registeredId = false;
		
		for (int i = 0; i < r1.getClients().size() && !registeredId; i++){
			if ( r1.getClients().get(i).getIdNumber().equals(id) ) {
				registeredId = true;
			}
		}
		
		return registeredId;		
	}
	
	@SuppressWarnings("unchecked")
	public Restaurant getRestaurant (int resNit) {
		Restaurant r1 = new Restaurant();
		for (int i = 0; i < restaurant.size(); i++) {
			if (restaurant.get(i).getNit() == resNit) {
				restaurant = (List<Restaurant>) restaurant.get(i);
			}
		}
		
		return r1;
		
		
		
	}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	


	public void addRestaurant(String name, int nit ,String adminName) {
		Restaurant r = new Restaurant(name, nit, adminName);
		restaurant.add(r);

	}

	public void saveRestaurants() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_RESTAURANTS));
		oos.writeObject(restaurant);
		oos.close();
	}
	public void saveClients() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_CLIENTS));
		oos.writeObject(r1.getClients());
		oos.close();
	}
	public void saveProducts() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PRODUCTS));
		oos.writeObject(r1.getProduct());
		oos.close();
	}
	
	
	public void SaveDeliveries() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_DELIVERIES));
		oos.writeObject(r1.getDeliveries());
		oos.close();
	}
}
