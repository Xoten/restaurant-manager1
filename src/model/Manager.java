package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import exceptions.NullCodeException;
import exceptions.WrongIdException;
import exceptions.WrongNitException;
import java.lang.Comparable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.File;


public class Manager implements Comparable<Client> {

	

	public final static String SAVE_PATH_FILE_RESTAURANTS = "data/restaurants.ap2";
	public final static String SAVE_PATH_FILE_CLIENTS = "data/clients.ap2";
	public final static String SAVE_PATH_FILE_PRODUCTS = "data/products.ap2";
	public final static String SAVE_PATH_FILE_ORDERS = "data/orders.ap2";

	public List<Restaurant> restaurants;
	public List<Product> products;
	public List<Client> clients;
	public List<Delivery> orders;

	private final static String SEPARATOR = ",";


	
	public Manager() {
		restaurants = new ArrayList<Restaurant>();
		products = new ArrayList<Product>();
		clients = new ArrayList<Client>();
		orders = new ArrayList<Delivery>();
	}
	
	public List<Restaurant> getRestaurants(){
		return restaurants;
	}
	
	public List<Product> getProducts(){
		return products;
	}
	
	public List<Client> getClients(){
		return clients;
	}
	
	public List<Delivery> getOrders(){
		return orders;
	}


	
	public void saveData(String type) throws IOException{
		if(type.equalsIgnoreCase("rest")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_RESTAURANTS));
			oos.writeObject(restaurants);
			oos.close();
		} 
		if(type.equalsIgnoreCase("client")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_CLIENTS));
			oos.writeObject(clients);
			oos.close();
		}
		if(type.equalsIgnoreCase("products")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_PRODUCTS));
			oos.writeObject(products);
			oos.close();
		}	
		if(type.equalsIgnoreCase("orders")) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE_ORDERS));
			oos.writeObject(orders);
			oos.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadData(String type) throws IOException, ClassNotFoundException{
		File r = new File(SAVE_PATH_FILE_RESTAURANTS);
		File c = new File(SAVE_PATH_FILE_CLIENTS);
		File p = new File(SAVE_PATH_FILE_PRODUCTS);
		File o = new File(SAVE_PATH_FILE_ORDERS);
		boolean loaded = false;
		if(r.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(r));
			if(type.equalsIgnoreCase("rest")) {
				restaurants = (List<Restaurant>)ois.readObject();
				loaded = true;
			}
			ois.close();	
		}
		if(c.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(c));
			if(type.equalsIgnoreCase("client")) {
				clients = (List<Client>)ois.readObject();
				loaded = true;
			}
			ois.close();		
		}
		if(p.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(p));
			if(type.equalsIgnoreCase("products")) {
				products = (List<Product>)ois.readObject();
				loaded = true;
			}
			ois.close();
		}
		if(o.exists()){
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(o));
			if(type.equalsIgnoreCase("orders")) {
				orders = (List<Delivery>)ois.readUnshared();
				loaded = true;
			}
			ois.close();	
		}
		else {
			loaded = false;
		}
		return loaded;
	}

	
	public void importRestaurants(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			String name = parts[0];
			String nit = parts[1];
			String manager = parts[2];
			addRestaurant(name,nit,manager);
			line = br.readLine();
		}
		br.close();
	}
	
	public void importClients(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			String name = parts[0];
			String lastName = parts[1];
			String idNum = parts[2];
			int idType = Integer.parseInt(parts[3]);
			String telephone = parts[4];
			String address= parts[5];
			addClient(name, lastName, idNum, idType, telephone, address);
			line = br.readLine();
		}
		br.close();
	}

	public void importProducts(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();
		String line = br.readLine();
		while(line!=null) {
			String[] parts = line.split(SEPARATOR);
			String name = parts[0];
			String code = parts[1];
			String info = parts[2];
			double cost = Double.parseDouble(parts[3]);
			String restNit = parts[4];
			addProduct(name, code, info, cost, restNit);
			line = br.readLine();
		}
		br.close();
	}

	public void exportDeliveries(String separator) throws FileNotFoundException {
		sortDeliveries();
		PrintWriter pw = new PrintWriter("data/orders.csv");
		pw.println("Code"+separator+"Date"+separator+"Client ID"+separator+"Status"+separator+"Restaurant Nit"+separator+"Product code"+separator+"Product quantity");
		for (int i = 0; i < orders.size(); i++)  {
			if (orders.get(i).getOrdersProductList().size()==1) 
				pw.println(orders.get(i).getCode()+separator+orders.get(i).getDate()+separator+orders.get(i).getClientIdNum()+separator+orders.get(i).getDeliveryStat()+separator+orders.get(i).getRestaurantNit()+separator+orders.get(i).getOrdersProductList().get(0).getInfoToExport(separator));
			if(orders.get(i).getOrdersProductList().size()>1) {	
				for (int j = 0; j < orders.get(i).getOrdersProductList().size(); j++) {
					pw.println(orders.get(i).getCode()+separator+orders.get(i).getDate()+separator+orders.get(i).getClientIdNum()+separator+orders.get(i).getDeliveryStat()+separator+orders.get(i).getRestaurantNit()+separator+orders.get(i).getOrdersProductList().get(j).getInfoToExport(separator));
				}					
			}
		}
		pw.close();
	}




	public void sortClientsCorrect() {
		Comparator<Client> ca = new Comparator<Client>() {		
			public int compare(Client c1, Client c2) {
				int comp;
				String nom1 = c1.getFullName();
				String nom2 = c2.getFullName();
				comp = nom2.compareToIgnoreCase(nom1);
				return comp;
			}
		};
		Collections.sort(clients,ca);
	}

	
	
	
	public void sortDeliveries() {
		class Sortdeliveries implements Comparator<Delivery>{
			@Override
			public int compare(Delivery o1, Delivery o2) {
				int value1 = 0, value2 = 0;
				value1 = o1.getRestaurantNit().compareTo(o2.getRestaurantNit());
				if(value1 == 0) {
					value2 = o2.getClientIdNum().compareTo(o1.getClientIdNum());
					if(value2 == 0) {
						return o1.getDate().compareTo(o2.getDate());
					}
					else {
						return value2;
					}
				}
				return value1;
			}
		}
		Collections.sort(orders, new Sortdeliveries());
	}


	public void sortByRestaurantNameBubble() {	
		Restaurant temp;
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < restaurants.size()-1; i++) {
				if (restaurants.get(i).getName().compareTo(restaurants.get(i + 1).getName()) > 0) {
					temp = restaurants.get(i);
					restaurants.set(i, restaurants.get(i + 1));
					restaurants.set(i + 1, temp);
					sorted = false;
				}
			}
		}
	}


	public void SortbyClientTelephoneInsertion() {
		for (int i = 1; i < clients.size(); i++) {
			Client c1 = clients.get(i);
			int j = i;
			Client c2 = clients.get(j-1);
			while(j>0 && c2.getTelephone().compareTo(c1.getTelephone())<0) {
				clients.set(j,c2);
				j--;
				if(j>0) c2 = clients.get(j-1);
			}
			clients.set(j,c1);
		}
	}



	public int searchRestaurantNit(String nit) throws WrongNitException{
		int position = 0;
		boolean found = !false;
		for(int i=0; i<restaurants.size() && found; i++){
			if(restaurants.get(i).getNit().equalsIgnoreCase(nit)){
				found = true;
				position = i;
			}
		} if(found == false) {
			throw new WrongNitException();
		}
		return position;
	}
	

	public int searchProductByCode(String code) throws NullCodeException{
		int position = 0;
		boolean found = !false;
		for(int i=0; i<products.size() && found; i++){
			if(products.get(i).getCode().equalsIgnoreCase(code)){
				found = true;
				position = i;
			}
		} if(found == false) {
			throw new NullCodeException();
		}
		return position;
	}

	public int searchClientId(String idNum) throws WrongIdException{
		int position = 0;
		boolean found = !false;
		for(int i=0; i<clients.size() && found; i++){
			if(clients.get(i).getIdNum().equalsIgnoreCase(idNum)){
				found = true;
				position = i;
			}
		}
		if(found = false) {
			throw new WrongIdException();
		}
		return position;
	}

	public boolean searchClientName(String name, String lastName) {
		String fullName = "";
		fullName = name+" "+lastName;
		boolean found = false;
		int start = 0;
		int end = clients.size()-1;
		while (start <= end && !found) {
			int middle = (start + end)/2;
			if (clients.get(middle).getFullName().equalsIgnoreCase(fullName)) {
				found = true;
			} else if(clients.get(middle).getFullName().compareToIgnoreCase(fullName) < 1){
				end = middle -1;
			} else {
				start = middle +1;
			}			
		}
		return found;
	}
	
	public int searchDeliveries(String orderCode) throws NullCodeException{
		int position = 0;
		boolean found = !false;
		for(int i=0; i<orders.size() && found; i++){
			if(orders.get(i).getCode().equalsIgnoreCase(orderCode)){
				found = true;
				position = i;
			}
		} if(found == false) {
			throw new NullCodeException();
		}
		return position;
	}
	public String searchProductByRestaurant(String nit) {
		String info = "";
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getRestaurantNit().equalsIgnoreCase(nit)) {
				info += products.get(i).getInfoProducts();
			}
		}
		return info;
	}

	public void updateNitProducts(String OldNit, String NewNit) {
		for (int i = 0; i < products.size(); i++) {
			if(products.get(i).getRestaurantNit().equalsIgnoreCase(OldNit)) {
				products.get(i).setRestaurantNit(NewNit);
			}
		}
	}
	
	public void updateDeliveriesNit(String OldNit, String NewNit) {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getRestaurantNit().equalsIgnoreCase(OldNit)) {
				orders.get(i).setRestaurantNit(NewNit);
			}
		}
	}
	
	public void updateClientIdDeliveries(String OldId, String NewId) {
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getClientIdNum().equalsIgnoreCase(OldId)) {
				orders.get(i).setClientIdNum(NewId);
			}
		}
	}
	
	public void updateProductDeliveryCodeFromProduct(String oldCode, String newCode) {
		for (int i = 0; i < orders.size(); i++) {
			orders.get(i).updateProductsCode(oldCode, newCode);
		}
	}

	
	public String addRestaurant(String name, String nit, String manager) throws IOException {
		Restaurant R = new Restaurant(name, nit, manager);
		String info = "";
		boolean added = false;
		if(restaurants.isEmpty()) {
			restaurants.add(R);
			added = true;
			info += "\n**Added!**\n";
			saveData("rest");
		}
		else if(!added){
			boolean unique = uniqueRestaurantNit(R.getNit());
			if(unique) {
				restaurants.add(R);
				info += "\n**Added!**\n";
				saveData("rest");
			}
			else
				info += "**\nRestaurant alredy exists\n**";
		}	
		return info;
	}
	
	public boolean uniqueRestaurantNit(String nit){
		boolean unique = true;
		for(int i=0; i<restaurants.size() && unique; i++){
			if(restaurants.get(i).getNit().equalsIgnoreCase(nit)){
				unique = false;
			}
		}
		return unique;
	}
	
	public String showRestaurants() {
		String info = "";
		if (restaurants.isEmpty()) {
			info = "**\nThere no restaurants in list***\n";
		}
		else {
			sortByRestaurantNameBubble();
			info += getRestaurants()+"\n";		
		}
		return info;
	}

	public String addClient(String name, String lastName, String idNum, int choice, String tel, String address) throws IOException {
		String info = "";
		String idType = ""; 
		switch (choice) {
		case 1:
			idType = "CC";
			break;

		case 2:
			idType = "PP";
			break;

		case 3:
			idType = "CE";
			break;

		case 4:
			idType = "TI";
			break;

		default:
			info += "Choice not valid";
			break;					
		}
		Client c = new Client(name, lastName, idNum, idType, tel, address);
		boolean unique = uniqueClientId(c.getIdNum());
		if(clients.isEmpty()) {
			clients.add(c);
			info += "**Added!**";
			saveData("client");

		} else if(!unique) {
			info += "**Client alredy exists **";
		} else {
			//method sorting called
			clients.add(compareTo(c),c);
			info += "**Added!**";
			saveData("client");
		} 
		return info;
	}
	
	@Override	
	public int compareTo(Client c) {
		int r = 0;
		boolean added = false;
		for (int i = 0; i < clients.size() && !added; i++) {
			int S = c.getFullName().compareToIgnoreCase(clients.get(i).getFullName());
			if (S > 0) {
				r = i;
				added = true;
			} else if(!added && i == clients.size()-1) {
				i++;
				r = i;
				added = true;
			}
		}
		return r;			
	}  
	
	public boolean uniqueClientId(String idNum){
		boolean unique = true;
		for(int i=0; i<clients.size() && unique; i++){
			if(clients.get(i).getIdNum().equalsIgnoreCase(idNum)){
				unique = false;
			}
		}
		return unique;
	}
	
	public String showClients() {
		String info = "";
		if (clients.isEmpty()) {
			info = "There no clients in list\n";
		}
		else {
			for (int i = 0; i < clients.size(); i++) {
				info += clients.get(i).getInfo()+"\n";
				info += i+"\n";
			}	
		}
		return info;
	}

	
	public String addProduct(String name, String code,String infoP, double cost, String restNit) throws IOException {
		String info = "";
		if(!uniqueRestaurantNit(restNit)) {
			Product p = new Product(name, code, infoP, cost, restNit);
			boolean unique = uniqueProductCode(p.getCode());
			if(unique) {
				products.add(p);
				info += "Added!";
				saveData("products");
			}
			else
				info += "** Product alredy exists **";	
		} else {
			info += "** Restaurant NIT doesn�t exists, product can�t be added! **";
		}
		return info;
	}
	
	public boolean uniqueProductCode(String code){
		boolean unique = true;
		for(int i=0; i<products.size() && unique; i++){
			if(products.get(i).getCode().equalsIgnoreCase(code)){
				unique = false;
			}
		} 
		return unique;
	}
	public String showProducts() {
		String info = "";
		if (products.isEmpty()) {
			info = "There no products in list\n";
		}
		else {
			for (int i = 0; i < products.size(); i++) {
				info += products.get(i).getInfoProducts()+"\n";
			}	
		}
		return info;
	}
	
	public String addOrder(String code, String idNum, String Nit) throws IOException {
		String info = "";
		Delivery order = new Delivery(code, idNum, Nit); 
		boolean unique = uniqueOrderCode(order.getCode());
		if(unique) {
			orders.add(order);
			saveData("orders");
			info += "Added!";
		}
		else
			info += "** Order alredy exists **";

		return info;
	}

	public boolean uniqueOrderCode(String code){
		boolean unique = true;
		for(int i=0; i<orders.size() && unique; i++){
			if(orders.get(i).getCode().equalsIgnoreCase(code)){
				unique = false;
			}
		}
		return unique;
	}
	
	public String showOrders() {
		String info = "";
		if (orders.isEmpty()) {
			info = "There no orders in list\n";
		}
		else {
			for (int i = 0; i < orders.size(); i++) {
				info += orders.get(i).getInfo()+"\n";
			}	
		}
		return info;
	}
}