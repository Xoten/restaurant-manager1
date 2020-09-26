package ui;
import model.*;

import java.util.ArrayList;
import java.util.Scanner;
import exceptions.DifferentRestaurantException;
import exceptions.InvalidCliendException;
import exceptions.InvalidNitException;

import java.io.IOException;


public class Menu {

	private final static int EXIT_OPTION = 30;
	private Scanner sc;
	private Restaurant r1;
	private Manager m1;
	
	public Menu() {
		sc = new Scanner(System.in);
		r1 = new Restaurant();
		m1 = new Manager();
		
		
	}
	
	public void startMenu() throws InvalidCliendException, InvalidNitException, IOException {
		String menu = getMenuText();
		int option;
		do {
			System.out.print(menu);
			option = readOption();
			executeOperation(option);
		}while(option!=EXIT_OPTION);
	}
	
	private String getMenuText() {
		String menu="=====================================================\n";
		menu +=     "               RESTAURANT MANAGER SYSTEM\n";
		menu +=     "=====================================================\n";
		menu +=     "1.Register restaurants\n" ;
		menu +=     "2.Register clients\n" ;
		menu +=     "3.Register products\n" ;
		menu +=     "4.Register deliveries\n" ;
		menu +=     "5.Update Restaurant data\n" ;
		menu +=     "6 Update Client data\n" ;
		menu +=     "7.Update Product data\n" ;
		menu +=     "8 Update Deliveries data\n" ;
		menu +=     "9.Modify state of a delivery\n" ;
		menu +=     "10.Import data\nE" ;
		menu +=     "11.Search Client\n" ;
		menu +=     "12. Show Restaurants\n" ;
		menu +=     "13.Show Clients\n" ;
		menu +=     "14. Load Data\\n";
		menu +=     "30.EXIT\n" ;
		
		return menu;
	}
	
	private int readOption() {
		int op;
		op = Integer.parseInt(sc.nextLine());
		return op;
	}
	
	private void executeOperation(int option) throws InvalidCliendException, InvalidNitException, IOException {
		switch(option) {
			case 1: 
				
				registerRestaurant();
				m1.saveRestaurants();
				break;
			case 2:
				registerClient();
				m1.saveClients();
				break;
			case 3: 
				
				registerProduct();
				m1.saveProducts();
			 break;
			 
			case 4:
				
				 registerDelivery();
				 m1.SaveDeliveries();
				break;
				
			case 5:
				
				 editRestaurant();
				m1.saveRestaurants();
				
				
				
				 
				
			 break;
			 
			case 6 :
				
			     editClient();
			     m1.saveClients();
			break;
			
			case 7:
				
			     editProduct();
			     m1.saveProducts();
			
			
			
			
			break;
			
			case 10:
				importData();
				
			case 9 :
				ModifyStateofDelivery();
				m1.SaveDeliveries();
			case 11:
				
				SearchClientByName();
				break;
				
			case 14:
				
				loadProgram();
				break;
			
			case 20:
				
				
				exitProgram();
				
			
				
			case 12:
				
				if(m1.getRestaurants().isEmpty()) {
					
					System.out.println("The list of resstaurants is empty");
					
						 
						 
					 }else {
						 
						 m1.sortRestaurantByName();
						 
						 m1.showRestaurant();
						
							
						}
				break;
				
			case 13:
				
				if(r1.getClients().isEmpty()) {
					System.out.println("The list of clients is empty");
					
				} else {
					
					r1.sortClientsdByphoneNumber();
					
					for(int i = 0; i< r1.getClients().size();i++) {
						
						
				System.out.println(" Client No. " +(i+1)+" \n"+ " Id Number: " +r1.getClients().get(i).getIdNumber()+" \n"+   " Name: " + r1.getClients().get(i).getName()+" \n" +  " Last Name:  "
								+ r1.getClients().get(i).getLastName()+" \n" + " Adress: " + r1.getClients().get(i).getAdress()+" \n" + " TypeDocument:  "
								+ r1.getClients().get(i).getType()+" \n" + " phone Number:  " + r1.getClients().get(i).getPhoneNumber()+ " \n \n \n");
				}            
					 
					
				}	
				
			default: break;
		}
	}

	private void exitProgram() {
		sc.close();
	}
	
	private void registerClient() throws NumberFormatException, IOException {
		
		System.out.println("Adding Client ...");
		System.out.println("Please enter your type of document: ");
		String type = sc.nextLine();
		System.out.println("Please enter your id number ");
		String idNumber = sc.nextLine();
		System.out.println("Please enter your name ");
		String Name = sc.nextLine();
		System.out.println("Please enter your Lastname ");
		String Lastname = sc.nextLine();
		System.out.println("Please enter your phoneNumber ");
		int phoneNumber = sc.nextInt();
		sc.nextLine();
		System.out.println("Please enter your adress");
		String adress = sc.nextLine();
		
		
		r1.addClient(type,idNumber,Name,Lastname,phoneNumber,adress);
		
		System.out.println("The client has been registered succesfully");
			
		
	}
	
	    private void registerRestaurant() throws InvalidNitException, IOException {
		
		System.out.println("Adding Restaurant ...");
		System.out.println("Please enter your restaurant name: ");
	     String name = sc.nextLine();
		System.out.println("Please enter the nit of the restaurant ");
		int nit = Integer.parseInt(sc.nextLine());
		
		if (m1.usedNit(nit)) {
			throw new InvalidNitException(nit);
		}
		System.out.println("Please enter the admin name ");
		String adminName = sc.nextLine();
		
		
		
		m1.addRestaurant(name,nit,adminName);
		
		System.out.println("The restaurant has been registered succesfully");
		 m1.saveRestaurants();
	    }
		
		private void registerProduct()  {
			
			System.out.println("Adding Product ...");
			
			System.out.println("Please enter the product code");
			int code = sc.nextInt();
			sc.nextLine();
			System.out.println("Please enter the product name ");
			String namep = sc.nextLine();
			System.out.println("Please enter the product description");
			String description = sc.nextLine();
			System.out.println("Please enter the product price: ");
			double price = Double.parseDouble(sc.nextLine());
			System.out.println("Please enter the restaurant nit: ");
			int restaurantNit = Integer.parseInt(sc.nextLine());
			
			r1.addProduct(code,namep,description,price,restaurantNit);
			
			
			System.out.println("The product has been registered succesfully");
			
			
			
			
		}
		
		private void registerDelivery() throws  InvalidCliendException, InvalidNitException, IOException {
			
			
			
			
			
			System.out.println("Add a new delivery order (the entered info can be changed later on):");
			System.out.println("-Enter the client idNumber:");
			
			System.out.println("\nRegistred id list:");
			for (Client client : r1.getClients()) {
				System.out.println(client.getName() + ": " + client.getIdNumber());
			}
		
			
			System.out.println("-Enter the id number:");
			String clientId = sc.nextLine();
			if (!m1.registeredId(clientId)) {
				throw new InvalidCliendException(clientId);
			}
					
			ArrayList<Product> products = new ArrayList<>();
			ArrayList<Integer> quantities =  new ArrayList<>();
			
			boolean selectAnOther = false;
			int resNit = 0;
			do {
				if (products.size() != 0 ) {
					resNit = products.get(0).getRestaurantNit();
				}
				Product product = selectProduct(resNit);
				System.out.println("- Enter the quantitie of this product to add to the delivery:");
				int quantity = Integer.parseInt(sc.nextLine());
				if (quantity < 0) {
					throw new NumberFormatException();
				}
				
				products.add(product);
				quantities.add(quantity);
				
				System.out.println("- Add nore products:");
				System.out.println("1. Yes");
				System.out.println("2. No");
				int option = Integer.parseInt(sc.nextLine());
				switch (option) {
				case 1:
					selectAnOther = true;
					break;
				case 2:
					selectAnOther = false;
					break;
				default:
					throw new NumberFormatException();
				}
				
				
				
			} while (selectAnOther);
			
			
			int deliveryCode = (int)(Math.random()*1000);
			Delivery delivery = new Delivery(deliveryCode, clientId, products.get(0).getRestaurantNit() , products, quantities);
			r1.getDeliveries().add(delivery);
	
				
			
		}
		
		
		
		
		private Product selectProduct(int resNit) {
			boolean error = false;
			int option = 0;
			Product finalProduct = new Product(); 
			do {
				
				
				System.out.println("you can only order from one restaurant.");
				System.out.println("if a product is unavailable, its because its from a different restaurant");
				int productIndex = 0;
				for (Product product : r1.getProduct()) {
					productIndex++;
					if (resNit != 0 && product.getRestaurantNit() == resNit) {
						System.out.print("unavailable- ");
					}
					System.out.println(productIndex + ". " + product.getName() + ": " + product.getPrice() + ". Restaurant: " + m1.getRestaurant(product.getRestaurantNit()).getName() );
				}
				
				try {
					option = Integer.parseInt(sc.nextLine());
					if (option < 1 || option > productIndex) {
						System.out.print("Invalid Option");
					}
					
					if (resNit == 0 ) {
						finalProduct = r1.getProduct().get(option-1);
					}else {
						if (r1.getProduct().get(option-1).getRestaurantNit()==(resNit)) {
							finalProduct = r1.getProduct().get(option-1);
						}else {
							throw new DifferentRestaurantException();
						}
					}
					error = false;
				} catch (NumberFormatException numberFormatException) {
					System.err.println("the entered option was invalid. please only enter the number next to the option");
					System.out.println("Press any key to continue.");
					sc.nextLine();
					error = true;
				}catch (DifferentRestaurantException diferentRestaurantException) {
					System.err.println(diferentRestaurantException.getMessage());
					System.out.println("Press any key to continue.");
					sc.nextLine();
					error = true;
				}
				
			} while (error);
			
			
			
			return finalProduct;
							
		}
		
		public void editClient() {
			
			System.out.println("Please enter the client id");
			String number = sc.nextLine();
			int index = r1.UpdateClientbyId(number);
			
			System.out.println("¿What do you want to update?");
			System.out.println("1.Name");
			System.out.println("2.Lastname");
			System.out.println("3.phoneNumber");
			System.out.println("4.Adress");
			System.out.println("5.Type");
			
			
			
			int option = sc.nextInt();
			sc.nextLine();
			
			switch(option){
			
			
			case 1:
				
				System.out.println("Please enter the new Name");
				
				String newname = sc.nextLine();
				
				r1.getClients().get(index).setName(newname);
				
				System.out.println("Name updated!!!");
				
			case 2:
              System.out.println("Please enter the new Lastname");
				
				String newlastname = sc.nextLine();
				
				r1.getClients().get(index).setLastName(newlastname);
				
				System.out.println("Lastname updated!!!");
				
				break;
				
			case 3:
				
				
                 System.out.println("Please enter the new phoneNumber");
				
				int phoneNumber = sc.nextInt();
				sc.nextLine();
				
				r1.getClients().get(index).setPhoneNumber(phoneNumber);
				
				System.out.println("phone Number updated!!!");
				
				
				
				break;
				
			case 4:
				
				 System.out.println("Please enter the new Adress");
					
					String newAdress = sc.nextLine();
					
					r1.getClients().get(index).setAdress(newAdress);
					
					System.out.println("Adress updated!!!");
					
				
				
			    break;
			    
			case 5: 
			
				 System.out.println("Please enter the new typeofDocument");
					
					String newType = sc.nextLine();
					
					r1.getClients().get(index).setType(newType);
					
					System.out.println("type of document updated!!!");
					
			break;
			}
			
			
			
		}
		
		public void editProduct() {
			
			
			System.out.println("Please enter the product code");
			int number = sc.nextInt();
			sc.nextLine();
			int productindex = r1.UpdateProductsByCode(number);
			
			System.out.println("¿What do you want to update?");
			System.out.println("1.Name");
			System.out.println("2.description");
			System.out.println("3.price");
			System.out.println("4.restaurantNit");
			
			
			
			
			int option = sc.nextInt();
			sc.nextLine();
			
			switch(option){
			
			
			case 1:
				
				System.out.println("Please enter the new product name");
				
				String pnewname = sc.nextLine();
				
				r1.getProduct().get(productindex).setName(pnewname);
				
				System.out.println("Product Name updated!!!");
				
			case 2:
              System.out.println("Please enter the new description");
				
				String pdescription = sc.nextLine();
				
				r1.getProduct().get(productindex).setDescription(pdescription);
				
				System.out.println("description updated!!!");
				
				break;
				
			case 3:
				
				
                 System.out.println("Please enter the new price");
				
				double pprice = sc.nextDouble();
				sc.nextLine();
				
				r1.getProduct().get(productindex).setPrice(pprice);
				
				System.out.println("price updated!!!");
				
				
				
				break;
				
			case 4:
				
				 System.out.println("Please enter the new restaurantNit");
					
					int prestnit = sc.nextInt();
					sc.nextLine();
					
					r1.getProduct().get(productindex).setRestaurantNit(prestnit);
					
					System.out.println("restaurant nit updated!!!");
				
			    break;
			    
	
			
			}
			
			
	
			
		}
		
		public void editRestaurant() {
			
			
			System.out.println("Please enter the restaurant nit");
			int rnit = sc.nextInt();
			sc.nextLine();
			int rindex = r1.UpdateProductsByCode(rnit);
			
			System.out.println("¿What do you want to update?");
			System.out.println("1. Name");
			System.out.println("2. Nit");
			System.out.println("3. Admin Name");
			
			
			
			
			
			int option = sc.nextInt();
			sc.nextLine();
			
			switch(option){
			
			
			case 1:
				
				System.out.println("Please enter the new name of the restaurant");
				
				String rnewname = sc.nextLine();
				
				m1.getRestaurants().get(rindex).setName(rnewname);
				
				System.out.println("Restaurant Name updated!!!");
				
			case 2:
              System.out.println("Please enter the new nit of the restaurant");
				
				int rnewnit = sc.nextInt();
				
				sc.nextLine();
				
				m1.getRestaurants().get(rindex).setNit(rnewnit);
				
				System.out.println("restaurant nit updated!!!");
				
				break;
				
			case 3:
				
				
                 System.out.println("Please enter the new admin name");
				
				String radminname = sc.nextLine();
				
				
				m1.getRestaurants().get(rindex).setAdminName(radminname);
				
				System.out.println("admin name updated!!!");
				
				
				
				break;
				
			
			
			
			
	
			
		}
			
			
			
			
			
			
			
			
		}
	
		
		
		
		 /** This method request a client name and Lastname, then call a method in Restaurants Manager to execute the binary search, finally show the searching time.
	     * <b>pre:</b>A client must be created.<br>
	     *
	     * <b>post:</b>The data requested in .csv file is imported to the program<br>
	     */
	    private void SearchClientByName() {
	        System.out.println("FINDING CLIENT**");
	        System.out.println("Enter the first name of client ");
	        String name = sc.nextLine();
	        System.out.println( r1.binarySearchClient(name));
	    
	    }
	    private void importData() {
			boolean error = false;
			int option = 0;
			do {
				
				
				System.out.println("1. import Clients data");
				System.out.println("2. import Restaurants data");
				System.out.println("3. import products data");
				
				try {
					option = Integer.parseInt( sc.nextLine());
					if (option < 1 || option > 6 ) {
						
						System.out.println(" Invalid Option");
					
				
					
					error = true;
					}
					}catch (NumberFormatException numberFormatException) {
					System.err.println("the entered option was invalid. please only enter the number next to the option");
					System.out.println("Pres any key to continue");
					error = true;
				}
				
				
			} while (error);
			
			switch (option) {
			case 1:
				System.out.println("enter the destiny path. like: data/CLIENT_DATA.csv");
				String fileName = sc.nextLine();
				try {
					m1.importClient(fileName);
				} catch (IOException ioException) {
					System.err.println("couldnt import the info");
				}
				break;
			
			case 2:
				System.out.println("enter the destiny path. like: data/RESTAURANT_DATA.csv");
				 fileName = sc.nextLine();
				try {
					m1.importRestaurants(fileName);
				} catch (IOException ioException) {
					System.err.println("couldnt import the report");
				}
				break;
			
			case 3:
				System.out.println("enter the destiny path. like: data/PRODUCT_DATA.csv");
				 fileName = sc.nextLine();
				try {
					m1.importProduct(fileName);
				} catch (IOException ioException) {
					System.err.println("couldnt import the info.");
				}
				break;

			}
			
			 
		}
	    
	    private void ModifyStateofDelivery(){
			boolean error = false;
			int deliveryIndex = 0;
			
			do {
				int cont = 0;
				
				System.out.println("Select a delivery to change status:");
				for (Delivery delivery: r1.getDeliveries()) {
					System.out.println(cont + ". "  + String.valueOf( delivery.getDeliveryCode() )+ "." + delivery.getOrderState());
				}
				
				try {
					deliveryIndex = Integer.parseInt( sc.nextLine());
					if (deliveryIndex < 1 || deliveryIndex >  cont ) {
						System.out.println("Invalid Option");
					}

					error = true;
					System.out.println("Please press any key to continue");
				}catch (NumberFormatException numberFormatException) {
					System.err.println("the entered option was invalid. please only enter the number next to the option");
					error = true;
					System.out.println("Please press any key to continue");
				}
				
				
				
			} while (error);
			
			int orderStateNum = r1.getDeliveries().get(deliveryIndex).getOrderState().ordinal() + 1;
			if (orderStateNum <= 3) {
				r1.getDeliveries().get(deliveryIndex).setOrderState(orderStateNum);
			}
			
			System.out.println("the new status is: " + String.valueOf( r1.getDeliveries().get(deliveryIndex).getOrderState() ));
			
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
		
		
		
		
		
		
		
		
	
	

	

	
		
	

}
