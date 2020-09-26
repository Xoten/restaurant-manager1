package ui;
import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import exceptions.DifferentRestaurantException;
import exceptions.InvalidCliendException;
import exceptions.InvalidNitException;

import java.io.IOException;


public class Menu {

	private final static int EXIT_OPTION = 3;
	private Scanner sc;
	private Restaurant r1;
	private Manager m1;
	private Date d1;
	private Delivery dv1;
	public Menu() {
		sc = new Scanner(System.in);
		r1 = new Restaurant();
		m1 = new Manager();
		d1 = new Date();
		
	}
	
	public void startMenu() throws InvalidCliendException, InvalidNitException {
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
		menu +=     "               RESTAURANTES\n";
		menu +=     "=====================================================\n";
		menu +=     "1.REGISTRAR RESTAURANTES\n" ;
		menu +=     "2.REGISTRAR  CLIENTES\n" ;
		menu +=     "3.REGISTRAR PRODUCTOS\n" ;
		menu +=     "4.REGISTRAR PEDIDOS\n" ;
		menu +=     "5.ACTUALIZAR DATOS RESTAURANTES\n" ;
		menu +=     "6.ACTUALIZAR DATOS CLIENTES\n" ;
		menu +=     "7.ACTUALIZAR DATOS PRODUCTO\n" ;
		menu +=     "8 ACTUALIZAR DATOS PEDIDOS\n" ;
		menu +=     "9.CAMBIAR EL ESTADO DE UN PEDIDO\n" ;
		menu +=     "10. IMPORTAR DATOS RESTAURANT\nE" ;
		menu +=     "11. IMPORTAR DATOS CLIENTE\n" ;
		menu +=     "12. IMPORTAR DATOS PRODUCTO\n";
		menu +=     "13. IMPORTAR DATOS PEDIDO\n" ;
		menu +=     "14. EXPORTAR DATOS RESTAURANT\n" ;
		menu +=     "15. EXPORTAR DATOS CLIENTE\n" ;
		menu +=     "16. EXPORTAR DATOS PRODUCTO\n";
		menu +=     "17. EXPORTAR DATOS PEDIDO\n" ;
		menu +=     "18.BUSCAR UN CLIENTE EFICIENTEMENTE\n" ;
		menu +=     "19. LISTAR EN PANTALLA RESTAURANTES\n" ;
		menu +=     "20.LISTAR EN PANTALLA CLIENTES\n" ;
		menu +=     "21.GUARDAR INFORMACION\n" ;
		menu +=     "POR FAVOR INGRESE LA OPCION\n" ;
		
		return menu;
	}
	
	private int readOption() {
		int op;
		op = Integer.parseInt(sc.nextLine());
		return op;
	}
	
	private void executeOperation(int option) throws InvalidCliendException, InvalidNitException {
		switch(option) {
			case 1: 
				
				registerRestaurant();
				
				break;
			case 2:
				registerClient();
				break;
			case 3: 
				
				registerProduct();
				
			 break;
			 
			case 4:
				
				registerDelivery();
				break;
				
			case 5:
				
				
			 
			case 22:
				exitProgram();
				
			case 19:
				
				if(m1.getRestaurants().isEmpty()) {
					
					System.out.println("La lista de restaurantes esta vacia");
					
						 
						 
					 }else {
						 
						 m1.sortRestaurantByName();
						 
						 m1.showRestaurant();
						
							
						}
				
			case 20:
				
				if(r1.getClients().isEmpty()) {
					System.out.println("La lista de clientes esta vacia");
					
				} else {
					
					r1.sortClientsdByphoneNumber();
					System.out.println(r1.getClients());
				}
					 
					
				
				
			default: break;
		}
	}

	private void exitProgram() {
		sc.close();
	}
	
	private void registerClient() throws NumberFormatException {
		
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
		int phoneNumber = Integer.parseInt(sc.nextLine());
		System.out.println("Please enter your adress");
		String adress = sc.nextLine();
		
		
		r1.addClient(type,idNumber,Name,Lastname,phoneNumber,adress);
		
		System.out.println("The client has been registered succesfully");
			
		
	}
	
	    private void registerRestaurant() {
		
		System.out.println("Adding Restaurant ...");
		System.out.println("Please enter your restaurant name: ");
	     String name = sc.nextLine();
		System.out.println("Please enter the nit of the restaurant ");
		int nit = Integer.parseInt(sc.nextLine());
		System.out.println("Please enter the admin name ");
		String adminName = sc.nextLine();
		
		
		
		m1.addRestaurant(name,nit,adminName);
		
		System.out.println("The restaurant has been registered succesfully");
		
	    }
		
		private void registerProduct() {
			
			System.out.println("Adding Product ...");
			int code = (int)(1000000*Math.random());
			System.out.println("Please enter the product name ");
			String namep = sc.nextLine();
			System.out.println("Please enter the product description");
			String description = sc.nextLine();
			System.out.println("Please enter the product price: ");
			double price = Double.parseDouble(sc.nextLine());
			System.out.println("Please enter the restaurant nit: ");
			int restaurantNit = Integer.parseInt(sc.nextLine());
			
			r1.addProduct(code,namep,description,price,restaurantNit);
			
			
			System.out.println("The prodcut has been registered succesfully");
			
			
			
			
		}
		
		private void registerDelivery() throws  InvalidCliendException, InvalidNitException {
			
			
			
			
			
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
				System.out.println("if a product is unavailable, its because its from a diferent restaurant, please press 0 to exit");
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
						System.out.print("Opcion invalida");
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
	
		
		
		
		
		
		
		
		
		
		
		
		
	
	

	

	
		
	

}
