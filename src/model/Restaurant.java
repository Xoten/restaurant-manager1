package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class Restaurant implements Serializable  {

	private String name;
	private int nit;
	private String adminName;
	private List<Client> clients;
	private List<Product> products;
	private List<Delivery> deliveries;
	private final static String SEPARATOR = ";";

	public Restaurant(String name, int nit, String adminName) {

		this.name = name;
		this.nit = nit;
		this.adminName = adminName;
	}
	
	public Restaurant() {
		
		clients = new ArrayList<Client>();
		products = new ArrayList<Product>();
		deliveries = new ArrayList<Delivery>();
		
		
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getName() {
		return name;
	}

	public int getNit() {
		return nit;
	}

	public String getAdminName() {
		return adminName;
	}	
	
	public void addProduct(int code, String name, String description, double price, int restaurantNit) {
		
		
		Product pd = new Product(code, name, description, price , restaurantNit);
		products.add(pd);
	}
	
	public void addClient(int type, String idNumber, String Name,String Lastname, int phoneNumber, String adress) {
		
		Client cl = new Client(type,idNumber, Name, Lastname, phoneNumber, adress);
		if(clients.isEmpty()) {
			
			clients.add(cl);
			
			
		}else {
			
			int i = 0;
			while(cl.getLastName().compareTo(clients.get(i).getLastName())<0) {
				i++;
				if(cl.getLastName().compareTo(clients.get(i).getLastName()) == 0) {
					int j = 0;
					while(cl.getName().compareTo(clients.get(j).getName())<0) 
					
				{
					j++;	
						
						
				}  
					clients.add(j,cl);
				}
				
				else {
					
					clients.add(i,cl);
				}
			}
			
			
		}
		
	}
	
	public void addDelivery(int deliveryCode, Date dateandTime, int clientId, int restaurantNit, Product[] products, int[] quantities) {
		
		Delivery dv1 = new Delivery(deliveryCode, dateandTime, clientId, restaurantNit, products, quantities);
		deliveries.add(dv1);
	}
	
	public List<Product> getProduct(){
	
	
      return products;
	

	}
	public List<Client> getClients(){
		
		
		return clients;
		
		
		}
	public List<Delivery> getDeliveries(){
		
		
		return deliveries;
		
	
		
		}
	
	public void importClient(String c) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(c));
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			String name = parts[0];
			double amount = Double.parseDouble(parts[1]);
		addClient(name, name, nit, nit, nit, name);
		line = br.readLine();
		}
		br.close();
	}
	public void importarProducto(String p) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(p));
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			String name = parts[0];
			double amount = Double.parseDouble(parts[1]);
		    addProduct(nit, name, name, amount, name);
			
			line = br.readLine();
		}
		br.close();
	}
    
	public void importarPedido(String pd) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(pd));
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			String name = parts[0];
			double amount = Double.parseDouble(parts[1]);
		    addPedido(nit, nit, nit, nit, nit);
			
			line = br.readLine();
		}
		br.close();
	}

	public void sortClientByLastNameAndName() {
		
		Comparator<Client> ca = new Comparator<Client>() {
			public int compare(Client c1, Client c2) {
				int comp;
				String l1 = c1.getLastName();
				String l2= c2.getLastName();
				String n1 = c1.getName();
				String n2 = c2.getName();
				
				comp = l1.compareTo(l2);
				
				if(comp==0) {
					comp = n1.compareTo(n2);
				}
				
				return comp;
			}
		};
		
		Collections.sort(clients,ca);
		
		
		
		
	}

	
		
		
		
		
	



	
	
	
	
	
	
	
}
