package ui;
import model.*;

import java.util.Date;
import java.util.Scanner;

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
	
	public void startMenu() {
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
	
	private void executeOperation(int option) {
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
			 
			case 22:
				exitProgram();
			default: break;
		}
	}

	private void exitProgram() {
		sc.close();
	}
	
	private void registerClient() {
		
		System.out.println("Adding Client ...");
		System.out.print("Please enter your type of document: ");
		String type = sc.nextLine();
		System.out.print("Please enter your id number ");
		String idNumber = sc.nextLine();
		System.out.print("Please enter your name ");
		String Name = sc.nextLine();
		System.out.print("Please enter your Lastname ");
		String Lastname = sc.nextLine();
		System.out.print("Please enter your phoneNumber ");
		int phoneNumber = Integer.parseInt(sc.nextLine());
		System.out.print("Please enter your adress");
		String adress = sc.nextLine();
		
		
		r1.addClient(type,idNumber,Name,Lastname,phoneNumber,adress);
		
		System.out.println("The client has been registered succesfully");
			
		
	}
	
	    private void registerRestaurant() {
		
		System.out.println("Adding Restaurant ...");
		System.out.print("Please enter your restaurant name: ");
	     String name = sc.nextLine();
		System.out.print("Please enter the nit of the restaurant ");
		int nit = Integer.parseInt(sc.nextLine());
		System.out.print("Please enter the admin name ");
		String adminName = sc.nextLine();
		
		
		
		m1.addRestaurant(name,nit,adminName);
		
		System.out.println("The restaurant has been registered succesfully");
		
	    }
		
		private void registerProduct() {
			
			System.out.println("Adding Product ...");
			int code = (int)(1000000*Math.random());
			System.out.print("Please enter the product name ");
			String namep = sc.nextLine();
			System.out.print("Please enter the product description");
			String description = sc.nextLine();
			System.out.print("Please enter the product price ");
			double price = Double.parseDouble(sc.nextLine());
			System.out.print("Please enter the restaurant nit ");
			int restaurantNit = Integer.parseInt(sc.nextLine());
			
			r1.addProduct(code,namep,description,price,restaurantNit);
			
			
			System.out.println("The prodcut has been registered succesfully");
			
			
			
			
		}
		
		private void registerDelivery() {
			
			System.out.println("Adding Delivery ...");
			
			int deliveryCode = (int)(100000*Math.random());
			System.out.print("Please enter the restaurant nit ");
			int restaurantnit = Integer.parseInt(sc.nextLine());
			System.out.print("Please enter the cliend id ");
			int clientId = Integer.parseInt(sc.nextLine());
			
			 int date = d1.getDate();
			 int hour = d1.getHours();
			 
			 
			
			r1.addDelivery(deliveryCode, date, hour, clientId, restaurantnit);
			System.out.print("Please enter the amount of products ");
			 int amount = Integer.parseInt(sc.nextLine());
			
			for(int i = 0; i<amount; i++) {
				System.out.print("Please enter the code of the product ");
				
				int code = Integer.parseInt(sc.nextLine());
				
				System.out.print("Please enter the amount of the product ");
				
				int quantities = Integer.parseInt(sc.nextLine());
				 dv1.addProductToOrder(code, quantities);
			}
			   
			   
			
	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	
	

	

	
		
	

}
