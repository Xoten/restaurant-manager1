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
    public final static long serialVersionUID = 1;
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
	
	public void addClient(String type, String idNumber, String Name,String Lastname, int phoneNumber, String adress) {
		
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
	
	public void addDelivery(int deliveryCode, int dateandTime,int hour, int clientId, int restaurantNit){
		
		Delivery dv1 = new Delivery(deliveryCode, dateandTime,hour, clientId, restaurantNit);
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
			String type = parts[0];
			String idNumber = parts[1];
			String Name = parts[2];
			String LastName = parts[3];
			int phoneNumber = Integer.parseInt(parts[4]);
			String adress = parts[5];
			
			
			
		addClient(type, idNumber, Name, LastName, phoneNumber , adress);
		line = br.readLine();
		}
		br.close();
	}
	public void importarProduct(String p) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(p));
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			int code = Integer.parseInt(parts[0]);
			String name = parts[1];
			String description = parts[2];
			double price = Double.parseDouble(parts[3]);
			int restaurantNit = Integer.parseInt(parts[4]);
			addProduct(code,name,description,price,restaurantNit);
			
			
			line = br.readLine();
		}
		br.close();
	}
    
	public void importDelivery(String pd) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(pd));
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			int deliveryCode = Integer.parseInt(parts[0]);
			int date = Integer.parseInt(parts[1]);
			int hour = Integer.parseInt(parts[2]);
			int clientId = Integer.parseInt(parts[3]);
			int restaurantNit = Integer.parseInt(parts[4]);
			
			addDelivery(deliveryCode, date, hour, clientId, restaurantNit);
			
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
