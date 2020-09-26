package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
			System.out.println("Restaurant No." +(i+1) + "\n\n" + "NIT: "+ restaurant.get(i).getNit() +"\n"+ "Name"+restaurant.get(i).getName()+"\n "+ " Admin Name " +restaurant.get(i).getAdminName()+ "\n\n");
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
	
	/**
	 * 
	 * @param resNit
	 */
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
	public int UpdateRestByNit(int nit) {
        
		int index = 0;
		boolean found = false;
		int start = 0;
		int end = restaurant.size() - 1;
		Restaurant rt = restaurant.get(0);
		while (start <= end && !found) {
			int middle = (start + end) / 2;
			rt = restaurant.get(middle);
			if (rt.getNit()==(nit)) {
				found = true;
			} else if (rt.getNit() > nit ) {
				end = middle - 1;

			} else {
				start = middle + 1;
			}

		}

		return index;

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
	
	public void importClient(String c) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(c));
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			String type = parts[0];
			String idNumber = parts[1];
			String Name = parts[2];
			String LastName = parts[3];
			int phoneNumber = Integer.parseInt(parts[4]);
			String adress = parts[5];
			
			
			
		r1.addClient(type, idNumber, Name, LastName, phoneNumber , adress);
		line = br.readLine();
		}
		saveClients();
		br.close();
	}
	public void importProduct(String p) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(p));
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			int code = Integer.parseInt(parts[0]);
			String name = parts[1];
			String description = parts[2];
			double price = Double.parseDouble(parts[3]);
			int restaurantNit = Integer.parseInt(parts[4]);
			r1.addProduct(code,name,description,price,restaurantNit);
			
			
			line = br.readLine();
		}
		saveProducts();
		br.close();
	}
	
	public void importRestaurants(String fileName) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(fileName));
		String line = bReader.readLine();
		int cent = 1;
		while (line != null ) {
			if (cent != 1) {
				String[] parts = line.split(SEPARATOR);
				String name = parts[0];
				String adminName = parts[2];
				int nit = Integer.parseInt(parts[1]);
				addRestaurant(name,nit, adminName);
			}
			cent ++;
			
			line = bReader.readLine();
		}
		
		saveRestaurants();
		bReader.close();
	}
	public void exportDeliveryReport(String fileName, String separator) throws FileNotFoundException {
		PrintWriter pWriter = new PrintWriter(fileName);
		
		pWriter.println("[DelyveryCode]" + separator + "[delivery date and time]"  + separator + "[Delivery State]" + separator + 
						"[Restaurant Name]" + separator + "[Restaurant Nit]" + separator +
						"[Client Name]" + separator + "[Client id]"  + separator + "[Client address]" + separator + 
						"[Product Number]" + separator + "[Product Name]" + separator + "[Product price]" + separator + "[Product code]" );
		
		
		for (Delivery delivery : r1.getDeliveries()) {
			
			//delivery
			pWriter.print(delivery.getDeliveryCode() + separator);
			pWriter.print(delivery.getDate() + separator);
			pWriter.print(delivery.getOrderState() + separator);
			//restaurant
			Restaurant restaurant = getRestaurant( delivery.getRestaurantNit() );
			pWriter.print(restaurant.getName() + separator);
			pWriter.print(restaurant.getNit() + separator);
			//client
			Client client = r1.getClients().get( Integer.parseInt(delivery.getClientId()) );
			pWriter.print(client.getIdNumber() + separator);
			pWriter.print(client.getIdNumber() + separator);
			pWriter.print(client.getAdress() + separator);
			//Product
			int cent = 0;
			for (Product product: delivery.getProducts()) {
				cent++;
				pWriter.print(cent + separator);
				pWriter.print(product.getName() + separator);
				pWriter.print(product.getPrice() + separator);
				pWriter.print(product.getCode() + "\n");
				
			}
			
		}
		pWriter.close();
	}
	
	
	

	
}
