package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Client;
import model.Restaurant;

class ClientTest {
public void setUpScenary1() {
		
	}
	public void setUpScenary2() {
		
	}

	@Test
	
		
		void testCliente() {
			setUpScenary1();
			String idNumber="124560";
			String name="Francisco";
			String LastName="Coronel";
			String Adress="Calleseptima";
			 String type ="TI";
			int phoneNumber= 31136505;
			
			
			Client r= new Client( type ,idNumber, name ,LastName,phoneNumber,Adress);
			assertEquals(name, r.getName());
			assertEquals(type, r.getType());
			assertEquals(LastName, r.getLastName());
			assertEquals(Adress, r.getAdress());
			assertEquals(idNumber, r.getIdNumber());
			assertEquals(phoneNumber, r.getPhoneNumber());
			
			
			
			
			
			
			}
@Test
	
	void testEmptyClients() {
		setUpScenary2();
		
		Restaurant r = new Restaurant();
		
		assertNotNull(r.getClients());
		assertEquals(0,r.getClients().size(),"");
		
		
		}
	
}
