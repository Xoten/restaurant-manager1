package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Product;

class ProductTest {

	public void setUpScenary1() {
		 
	 }
	
		 public void setUpScenary2() {
			 
		 }
	

		 @Test
			void testaddProducto() {
				setUpScenary1();
				String Name= "hamburguesa";
				int code= 345435435;
				String Descp="pizza de pepperoni";
				double Price= 20000;
				int  RestaurantNit=12345;
				
				
				
				Product r= new Product(code ,Name,Descp, Price, RestaurantNit);
				assertEquals(Name, r.getName());
				assertEquals(code, r.getCode());
				assertEquals(Descp, r.getDescription());
				assertEquals(RestaurantNit, r.getRestaurantNit());
				assertEquals(Price, r.getPrice());
				
				
			
				
			}
		 
		 @Test
			void testaddProducto2() {
				setUpScenary1();
				String nam= "Cheese";
				int  cod= 2234334;
				String descrip=" italian Cheese";
				double cost=2000;
				int nit= 1234567;
				
				
				
				Product r= new Product(cod,nam,descrip, cost, nit);
				assertEquals(nam, r.getName());
				assertEquals(cod, r.getCode());
				assertEquals(descrip, r.getDescription());
				
				
			
				
			}
}
