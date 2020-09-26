package model;




import java.io.Serializable;
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
			while(i<clients.size() && cl.getLastName().compareTo(clients.get(i).getLastName())>0) {
				i++;
				
			}
			clients.add(i,cl);
			
		}
		
	}
	
	public void addDelivery(int deliveryCode, String clientId ,int restaurantNit, ArrayList<Product> products, ArrayList<Integer> quantities){
		
		Delivery dv1 = new Delivery(deliveryCode,clientId,restaurantNit, products, quantities);
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
	
	
    
	
	
	
	public int UpdateProductsByCode(int code)
			 {
        
		int index = 0;
		boolean found = false;
		int start = 0;
		int end = products.size() - 1;
		Product prod = products.get(0);
		while (start <= end && !found) {
			int middle = (start + end) / 2;
			prod = products.get(middle);
			if (prod.getCode() == (code)) {
				index = middle;
				found = true;
			} else if (prod.getCode() > code){
				end = middle - 1;

			} else {
				start = middle + 1;
			}

		}

		return index;

	}
	
	
	public int  UpdateClientbyId(String idNumber
			) {
        int index = 0;
		boolean found = false;
		int start = 0;
		int end = clients.size() - 1;
		Client cl = clients.get(0);
		while (start <= end && !found) {
			int middle = (start + end) / 2;
			cl = clients.get(middle);
			if (cl.getIdNumber().compareTo(idNumber)== 0) {
				index = middle;
				found = true;
				 
				
			} else if (cl.getIdNumber().compareTo(idNumber)>0) {
				end = middle - 1;

			} else {
				start = middle + 1;
			}

		}

		return index;

	}
	
	
	
	
	
	
	public String searchProductByRestaurantNit(int nit) {
		
		String info = "";
			
			for(int i = 0; i<products.size();i++) {
				
				
				if(products.get(i).getRestaurantNit() == nit) {
					
					info += products.get(i).getInfo();
				}
				
				
				}
				
				return info;
			}
	public String binarySearchClient(String name) {
		String msg = "";
		long start = System.currentTimeMillis();
		boolean find = false;
		int in = 0;
		int fin = clients.size();
		while (in <= fin && !find) {
			int pos = (int) Math.floor((in+fin)/2);
			if (pos != clients.size()) {
				String el = clients.get(pos).getName();
				int compar = name.compareToIgnoreCase(el);
				if (compar == 0) {
					msg += clients.get(pos);
					msg += "-------------------\n";
					find = true;
				} else if (compar < 0) {
					fin = pos - 1;
				} else if (compar > 0) {
					in = pos + 1;
				}
			}
		}
		long end = System.currentTimeMillis();
		if (find == false) {
			msg += "A client with this name don't exists\n";
		}
		msg += "Ejecution time: " + (end - start) + "ms";
		return msg;
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
	
	public void sortClientsdByphoneNumber() {
		
		for (int i = 1; i < clients.size(); i++) {
			Client api = clients.get(i);
			int j = i;
			Client apj = clients.get(j-1);
			while(j>0 && api.compareTo(apj)>0) {
				clients.set(j,apj);
				j--;
				if(j>0) apj = clients.get(j-1);
			}
			clients.set(j,api);
		}
		
		
		
		
		
	}
	
	@Override
	public String toString() {
		return "Restaurante [nit=" + getNit() + ", nombre=" + getName() + ", nombreAdministrador=" + getAdminName() + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
		
		
		
		
	



	
	
	
	
	
	
	
}
