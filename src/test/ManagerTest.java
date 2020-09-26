package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Restaurant;

class ManagerTest {

	Restaurant restau;
	 public void setUpScenary1() {
		 
	 }
	
	 public void setUpScenary2() {
			 restau=new Restaurant();
			 
		 }
	 

	@Test
	
	void testRestaurante() {
		setUpScenary1();
		 int n=  1234;
		String nom= " pizzaHut";
		String nomAdmi=" Alejandro";
		
		Restaurant r= new Restaurant(nom,n,nomAdmi);
		assertEquals(n, r.getNit());
		assertEquals(nom, r.getName());
		assertEquals(nomAdmi, r.getAdminName());
	
		
		}
	
	void testRestaurante2() {
		setUpScenary1();
		int n= 567;
		String nom=" dominos";
		String nomAdmi=" martha";
		
		Restaurant r= new Restaurant(nom,n,nomAdmi);
		assertEquals(n, r.getNit());
		assertEquals(nom, r.getName());
		assertEquals(nomAdmi, r.getAdminName());
	
		
		}
	
	/**
	 * Create an empty Contact
	 */
	
	
@Test
	
	void testAsociacionRestaurante() {
		setUpScenary2();
		
		Restaurant rs= new Restaurant();
		
		assertNotNull(rs.getClients());
		assertEquals(0,rs.getClients().size(),"");
		
		
		}
}
