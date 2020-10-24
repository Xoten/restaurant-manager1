package ui;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import exceptions.NullCodeException;
import exceptions.WrongIdException;
import exceptions.WrongNitException;

import java.math.BigInteger;
import model.Manager;


public class Menu {

	

	private Scanner sc;
	private Manager m1 = new Manager();
	final static int EXIT = 17;
	Random random = new Random();

	
	public Menu() {
		sc = new Scanner(System.in);

	}
	
	private void executeOperation(int option) {
		switch (option) {
		case 1:
			addRestaurant();
			break;
		case 2:
			addProduct();
			break;
		case 3:
			addClient();
			break;
		case 4:
			try {
				addDelivery();
			} catch (WrongIdException | WrongNitException | NullCodeException e) {
				System.err.println(e.getMessage());
			}

			break;
		case 5:
			showRestaurants();
			break;
		case 6:
			showProducts();
			break;
		case 7:
			showClients();
			break;
		case 8:
			showDeliveries();

			break;
		case 9:
			showClientsSortedByCellphone();		

			break;
		case 10:
			exportDeliveries();				

			break;
		case 11:
			importData();			

			break;
		case 12:
			try {
				updateRestuarant();
			} catch (WrongNitException e) {
				System.err.println(e.getMessage());
			}

			break;
		case 13:
			try {
				updateProduct();
			} catch (NullCodeException e) {
				System.err.println(e.getMessage());
			}

			break;
		case 14:
			try {
				updateClient();
			} catch (WrongIdException e) {
				System.err.println(e.getMessage());
			}

			break;
		case 15:
			try {
				updateDelivery();
			} catch (IOException | NullCodeException e) {
				System.err.println(e.getMessage());
			}
			break;
		case 16:
			SearchClientByName();

			break;
		case 17:
			exitProgram();
			break;
		default:
			System.out.println("Select a correct option");
			break;
		}

	}
	
	private String getMenu() {
		String menu;
		menu = "============================\n";
		menu = "===========Welcome User==========\n";
		menu = "============================\n";
		menu += "=====Delivery System====\n";
		menu += "===========================\n";
		menu += "1. Add restaurant\n";
		menu += "2. Add product\n";
		menu += "3. Add client\n";
		menu += "4. Add Order\n";
		menu += "5. Show restaurants list\n";
		menu += "6. Show products list\n";
		menu += "7. Show clients list\n";
		menu += "8. Show orders list\n";
		menu += "9. Sort clients list by telephone\n";
		menu += "10. Export Order\n";
		menu += "11. Import Data\n";
		menu += "12. Update restaurants\n";
		menu += "13. Update products\n";
		menu += "14. Update clients\n";
		menu += "15. Update orders\n";
		menu += "16. Search client by name\n";
		menu += "17. Exit\n";
		menu += "Please enter an option\n";
		return menu;
	}


	
	private void loadProgram() {
		System.out.println("Loading data ...");
		try{
			m1.loadData("rest");
			m1.loadData("client");
			m1.loadData("products");
			m1.loadData("orders");
			System.out.println("The program data were loaded succesfully");
		}catch(IOException | ClassNotFoundException e){
			System.out.println("The data can't be load");
		}
	}


	
	private void addRestaurant() {
		System.out.println("ADDING RESTAURANT");
		System.out.println("Please enter the restaurant name: ");
		String name = sc.nextLine();
		System.out.println("Please enter the restaurant NIT: ");
		String nit = sc.nextLine();
		System.out.println("Please enter the restaurant manager name: ");
		String manager = sc.nextLine();
		//SAVING...
		System.out.println("Saving data ...");
		try{
			System.out.println(m1.addRestaurant(name, nit, manager));
			System.out.println("The restaurant was saved succesfully");
		}catch(IOException ioe){
			System.out.println("The data can't be saved");
		}

	}
	
	private void addClient() {
		int choice;
		System.out.println("ADDING CLIENT");
		System.out.println("Please enter the client name: ");
		String name = sc.nextLine();
		System.out.println("Please enter the client last name: ");
		String lastName = sc.nextLine();
		System.out.println("Please enter the client ID number: ");
		String idNum = sc.nextLine();
		System.out.println("Please enter the client ID type: ");
		do {
			System.out.println("Please select the type of ID\n1.CC\n2.PP\n3.CE\n4.TI\n");
			choice = Integer.parseInt(sc.nextLine());
		} while(!((choice == 1) || (choice == 2) || (choice == 3) || (choice == 4)));	
		System.out.println("Please enter the client telephone: ");
		String tel = sc.nextLine();
		System.out.println("Please enter the client address: ");
		String adress = sc.nextLine();
		try {
			System.out.println(m1.addClient(name, lastName, idNum, choice,tel,adress));
			System.out.println("The client was saved succesfully");
		} catch (IOException e) {
			System.out.println("The data can't be saved");
		}
	}

	private void addProduct() {
		System.out.println("ADDING PRODUCT");
		System.out.println("Please enter the product name: ");
		String name = sc.nextLine();
		System.out.println("Please enter the product code: ");
		String code = sc.nextLine();
		System.out.println("Please enter the product information: ");
		String infoP = sc.nextLine();
		System.out.println("Please enter the product cost: ");
		double cost = Double.parseDouble(sc.nextLine());
		System.out.println("Please enter the restaurant NIT");
		showRestaurants();
		String restNit = sc.nextLine();
		try {
			System.out.println(m1.addProduct(name, code, infoP, cost, restNit));
			System.out.println("The product was saved succesfully");
		} catch (IOException e) {
			System.out.println("The data can't be saved");
		}
	}
	
	private void addDelivery() throws WrongIdException, WrongNitException, NullCodeException  {
		boolean done = false;
		if(!m1.clients.isEmpty() && !m1.restaurants.isEmpty() && !m1.products.isEmpty()) {
			System.out.println("**ADDING ORDER**\nEntry the client ID number\n");
			showClients();
			String idNum = sc.nextLine();
			System.out.println("**Entry the restaurant NIT**");
			showRestaurants();
			String restNit = sc.nextLine();
			if(!m1.uniqueClientId(idNum) && !m1.uniqueRestaurantNit(restNit)) {
				int requestedClient = m1.searchClientId(idNum);
				int requestedRestaurant = m1.searchRestaurantNit(restNit);
				String code = new BigInteger(50, random).toString(32);
				try {
					m1.addOrder(code, m1.getClients().get(requestedClient).getIdNum(), m1.getRestaurants().get(requestedRestaurant).getNit());
				} catch (IOException e) {
					System.out.println("Order cant be added!");
				} 
				int requestedOrderPos = m1.searchOrder(code);
				do {
					System.out.println("Enter the code of product for add to order");		
					System.out.println(m1.searchProductByRestaurant(restNit)); 
					String productCode = sc.nextLine();
					System.out.println("Enter quantity of that product");
					int quantity = Integer.parseInt(sc.nextLine());	
					m1.orders.get(requestedOrderPos).addProductInOrderList(productCode, quantity);
					System.out.println("Product added to order list");
					System.out.println("Do you want to add more products?\n1.Yes  /  2.No");
					int option = Integer.parseInt(sc.nextLine());
					if(option == 1)
						done = !false;
					else if(option == 2)
						done = !true;
					System.out.println("Order finalized");
				} while (done);
			} else if(m1.uniqueClientId(idNum) == true) {
				throw new WrongIdException();
			}else if(m1.uniqueRestaurantNit(restNit) == true) {
				throw new WrongNitException();
			}
			try {
				System.out.println("Saving order...");
				m1.saveData("orders");
				System.out.println("Order was saved");
			} catch (IOException e) {
				System.out.println("Data can't be saved");
			}
		}
		else
			System.out.println("Check data of clients, restaurants or products");
	}

	
	private void exportDeliveries() {
		System.out.println("***EXPORTING ORDERS***");
		System.out.println("Please entry the separator to use between columns of the .csv file");
		System.out.println("Suggest comma (,)");
		String separ = sc.nextLine();
		try{
			m1.exportDeliveries(separ);
			System.out.println("The data was exported succesfully");
		}catch(IOException fnfe){
			System.out.println("The data can't be export");
		}
	}

	
	private void importData() {
		System.out.println("IMPORTING DATA");
		boolean condition = true;
		do {
			System.out.println("1.Import restaurants\n2.Import products\n3.Import clients\n4.Import orders");
			int key = Integer.parseInt(sc.nextLine());
			switch (key) {
			case 1:
				try {
					System.out.println("Enter the patch/name of the file to import restaurants");
					String fileName = sc.nextLine();
					System.out.println("Importing data... please wait");
					m1.importRestaurants(fileName);
					System.out.println("The data was imported succesfully");
				} catch (IOException e) {
					System.out.println("The data can't be imported");
				}
				condition = false;
				break;
			case 2:
				try {
					System.out.println("Enter the patch/name of the file to import products");
					String fileName = sc.nextLine();
					System.out.println("Importing data... please wait");
					m1.importProducts(fileName);
					System.out.println("The data was imported succesfully");
				} catch (IOException e) {
					System.out.println("The data can't be imported");
				}
				condition = false;
				break;
			case 3:
				try {
					System.out.println("Enter the patch/name of the file to import clients");
					String fileName = sc.nextLine();
					System.out.println("Importing data... please wait");
					m1.importClients(fileName);
					System.out.println("The data was imported succesfully");
				} catch (IOException e) {
					System.out.println("The data can't be imported");
				}
				condition = false;
				break;
			case 4:
			
				break;
			default:
				System.out.println("Select a correct option");
				break;
			}	
		} while (condition);


	}


	private void SearchClientByName() {
		m1.sortClientsCorrect();
		System.out.println("**FINDING CLIENT**");
		System.out.println("Enter the first name of client ");
		String name = sc.nextLine();
		System.out.println("Enter the last name of client ");
		String lastName = sc.nextLine();
		boolean found;
		long start = System.currentTimeMillis();
		found = m1.searchClientName(name, lastName);
		long end = System.currentTimeMillis();
		if(found) {
			System.out.println("Client :"+name+" "+lastName+" was found!");
		} else {
			System.out.println("Client :"+name+" "+lastName+" was not found!");
		}
		System.out.println("Start: "+start);
		System.out.println("End: "+end);
		System.out.println("Searching time was: "+(end-start));
	}

	
	private void showRestaurants() {
		System.out.println("\n***DEPLOYING RESTAURANTS LIST***\n");
		System.out.println(m1.showRestaurants());
	}
	
	private void showClients() {
		System.out.println("\n***DEPLOYING CLIIENTS LIST***\n");	
		System.out.println(m1.showClients());
	}

	private void showProducts() {
		System.out.println("\n***DEPLOYING PRODUCTS LIST***\n");	
		System.out.println(m1.showProducts());
	}
	
	private void showDeliveries() {
		System.out.println("\n***DEPLOYING ORDERS LIST***\n");	
		System.out.println(m1.showOrders());
	}


	private void showClientsSortedByCellphone() {
		System.out.println("\n***DEPLOYING CLIENTS LIST SORTED BY TELEPHONE***\n");	
		m1.SortbyClientTelephoneInsertion();
		System.out.println("After sorting by telephone");
		System.out.println(m1.getClients());
		m1.sortClientsCorrect();
	}
	

	private void updateRestuarant() throws WrongNitException {
		System.out.println("UPDATING RESTAURANT");
		showRestaurants();
		System.out.println("Please enter the restaurant NIT to be update: ");
		String restNit = sc.nextLine();
		if(!m1.uniqueRestaurantNit(restNit)) {
			int requested = m1.searchRestaurantNit(restNit);
			System.out.println("Please select what you want to update\n1.Name\n2.Manager\n3.Nit");
			int key = Integer.parseInt(sc.nextLine());
			switch (key) {
			case 1:
				System.out.println("Please enter the new restaurant name: ");
				String name = sc.nextLine();
				m1.getRestaurants().get(requested).setName(name);
				System.out.println("Saving data ...");
				try{
					m1.saveData("rest");
					System.out.println("The restaurant was updated succesfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 2:
				System.out.println("Please enter the new manager name: ");
				String manager = sc.nextLine();
				m1.getRestaurants().get(requested).setManager(manager);
				System.out.println("Saving data ...");
				try{
					m1.saveData("rest");
					System.out.println("The restaurant was updated succesfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 3:
				System.out.println("Please enter the new restaurant NIT: ");
				String nit = sc.nextLine();
				if(m1.uniqueRestaurantNit(nit)) {
					m1.getRestaurants().get(requested).setNit(nit);
					m1.updateNitProducts(restNit, nit);
					m1.updateNitOrders(restNit, nit);
					System.out.println("Saving data ...");
					try{
						m1.saveData("rest");
						m1.saveData("products");
						m1.saveData("orders");
						System.out.println("The restaurant was updated succesfully");
					}catch(IOException ioe){
						System.out.println("The data can't be updated");
					}
				} 
				else {
					System.out.println("Nit already exist! Try another");
				}
				break;
			default:
				System.out.println("Select a correct option");
				break;
			}
		} else {
			throw new WrongNitException();
		}
	}
	
	private void updateClient() throws WrongIdException {
		System.out.println("UPDATING CLIENT");
		showClients();
		System.out.println("Please enter the client ID to be update: ");
		String idNum = sc.nextLine();
		if(!m1.uniqueClientId(idNum)) {
			int requested = m1.searchClientId(idNum);
			System.out.println("Please select what you want to update\n1.Name\n2.Last name\n3.ID number\n4.ID type\n5.Telephone\n6.Addres");
			int key = Integer.parseInt(sc.nextLine());
			switch (key) {
			case 1:
				System.out.println("Please enter the new client name: ");
				String name = sc.nextLine();
				m1.getClients().get(requested).setName(name);
				System.out.println("Saving data ...");
				try{
					m1.saveData("client");
					System.out.println("The client was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 2:
				System.out.println("Please enter the new client last name: ");
				String lastName = sc.nextLine();
				m1.getClients().get(requested).setLastName(lastName);
				System.out.println("Saving data ...");
				try{
					m1.saveData("client");
					System.out.println("The client was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 3:
				System.out.println("Please enter the new client ID: ");
				String newId = sc.nextLine();
				if(m1.uniqueClientId(newId)) {
					m1.getClients().get(requested).setIdNum(newId);		
					m1.updateClientIdOrders(idNum,newId);	
					System.out.println("Saving data ...");
					try{
						m1.saveData("client");
						m1.saveData("orders");
						System.out.println("The client was updated successfully");
					}catch(IOException ioe){
						System.out.println("The data can't be updated");
					}
				} else {
					System.out.println("ID already registered, try other!");
				}		
				break;
			case 4:
				System.out.println("Please enter the new client ID type: ");
				int choice;
				do {
					System.out.println("Please select the type of ID\n1.CC\n2.PP\n3.CE\n4.TI\n");
					choice = Integer.parseInt(sc.nextLine());
				} while(!((choice == 1) || (choice == 2) || (choice == 3) || (choice == 4)));	
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
					System.out.println("Choice not valid");
					break;					
				}
				m1.getClients().get(requested).setIdType(idType);		
				System.out.println("Saving data ...");
				try{
					m1.saveData("client");
					System.out.println("The client was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 5:
				System.out.println("Please enter the new client telephone: ");
				String tel = sc.nextLine();
				m1.getClients().get(requested).setTelephone(tel);
				System.out.println("Saving data ...");
				try{
					m1.saveData("client");
					System.out.println("The client was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 6:
				System.out.println("Please enter the new client address: ");
				String address = sc.nextLine();
				m1.getClients().get(requested).setAddress(address);;
				System.out.println("Saving data ...");
				try{
					m1.saveData("client");
					System.out.println("The client was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			default:
				System.out.println("Select a correct option");
				break;
			}
		} else {
			throw new WrongIdException();
		}
	}
	
	private void updateProduct() throws NullCodeException {
		System.out.println("UPDATING PRODUCT");
		showProducts();
		System.out.println("Please enter the product code to be update: ");
		String pCode = sc.nextLine();
		if(!m1.uniqueProductCode(pCode)) {
			int requested = m1.searchProductByCode(pCode);
			System.out.println("Please select what you want to update\n1.Name\n2.Information\n3.Cost\n4.Code");
			int key = Integer.parseInt(sc.nextLine());
			switch (key) {
			case 1:
				System.out.println("Please enter the new product name: ");
				String name = sc.nextLine();
				m1.getProducts().get(requested).setName(name);
				System.out.println("Saving data ...");
				try{
					m1.saveData("products");
					System.out.println("The product was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 2:
				System.out.println("Please enter the new information: ");
				String info = sc.nextLine();
				m1.getProducts().get(requested).setInfo(info);
				System.out.println("Saving data ...");
				try{
					m1.saveData("products");
					System.out.println("The product was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 3:
				System.out.println("Please enter the new product price: ");
				double price = Double.parseDouble(sc.nextLine());
				m1.getProducts().get(requested).setCost(price);
				System.out.println("Saving data ...");
				try{
					m1.saveData("products");
					System.out.println("The product was updated successfully");
				}catch(IOException ioe){
					System.out.println("The data can't be updated");
				}
				break;
			case 4:
				System.out.println("Please enter the new product code: ");
				String code = sc.nextLine();
				if(m1.uniqueProductCode(code)) {
					m1.getProducts().get(requested).setCode(code);
					m1.updateProductOrderCodeFromProduct(pCode,code);
					System.out.println("Saving data ...");
					try{
						m1.saveData("products");
						m1.saveData("orders");
						System.out.println("The product was updated successfully");
					}catch(IOException ioe){
						System.out.println("The data can't be updated");
					}
				} else {
					System.out.println("Code already in use, try other!");
				}
				break;
			default:
				System.out.println("Select a correct option");
				break;
			}	
		} else {
			throw new NullCodeException();
		}
	}
	
	private void updateDelivery() throws IOException, NullCodeException {
		System.out.println("UPDATING ORDER");
		showDeliveries();
		System.out.println("Please enter the order code to be update: ");
		String orderCode = sc.nextLine();
		if (!m1.uniqueOrderCode(orderCode)) {
			int requested = m1.searchOrder(orderCode);
			boolean statusChanged = false;
			do {
				System.out.println("What you want to do?\n1.Delete all order (only for orders when status is requested)\n2.Update order status");
				int key = Integer.parseInt(sc.nextLine());	
				switch (key) {
				case 1:
					if(m1.getOrders().get(requested).getOrderStat().equalsIgnoreCase("REQUESTED")) {
						m1.getOrders().remove(requested);
						System.out.println("Order deleted!");
						statusChanged = true;
						m1.saveData("orders");
					} else {
						System.out.println("Order can't be deleted! Status is not REQUESTED yet");	
						statusChanged = true;
					}
					break;
				case 2:
					System.out.println("Updating status...");
					System.out.println(m1.getOrders().get(requested).setOrderStatByCondition(m1.getOrders().get(requested).getOrderStat()));	
					statusChanged = true;
					m1.saveData("orders");
					break;
				default:
					System.out.println("Enter a correct option");
					break;
				}
			} while(!statusChanged);
		} else {
			throw new NullCodeException();
		}

	}



	private void exitProgram() {
		sc.close();
		System.out.println("Good bye!");
	}
	
	private int readOption() {
		int op;
		op = Integer.parseInt(sc.nextLine());
		return op;
	}

	public void startMenu() {
		String menu = getMenu();
		loadProgram();
		int option;
		do {
			System.out.println(menu);
			option = readOption();
			executeOperation(option);

		} while (option!=EXIT);
	}
}