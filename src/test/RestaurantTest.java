package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import model.Restaurant;

class RestaurantTest {

	Restaurant restau;
	 public void setUpScenary1() {
		 
	 }
public void setUpScenary3() {
		 
	 }
	
	 public void setUpScenary2() {
			 restau=new Restaurant();
			 
		 }
	 

	@Test
	
	void testRestaurante() {
		setUpScenary1();
		int n= 12345;
		String nom=" Godlike";
		String nomAdmi=" Jhon";
		
		Restaurant r= new Restaurant(nom,n,nomAdmi);
		assertEquals(n, r.getNit());
		assertEquals(nom, r.getName());
		assertEquals(nomAdmi, r.getAdminName());
	
		
		}
	
	@Test
	
	void testRestaurante2() {
		setUpScenary1();
		int n= 899;
		String nom="frijolesVerdes";
		String nomAdmi=" Diego";
		
		Restaurant r= new Restaurant(nom,n,nomAdmi);
		assertEquals(n, r.getNit());
		assertEquals(nom, r.getName());
		assertEquals(nomAdmi, r.getAdminName());
	
		
		}
	
	/**
	 * Create a new contact
	 */
	
	
	
	
@Test
	
	void testAsociacionRestaurante() {
		setUpScenary2();
		
		Restaurant rs= new Restaurant();
		
		assertNotNull(rs.getClients());
		assertEquals(0,rs.getClients().size(),"");
		
		
		}
	
	
	
	
	
	
	
	
	


	}


