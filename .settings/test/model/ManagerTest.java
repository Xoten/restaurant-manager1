package model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import exceptions.WrongNitException;

class ManagerTest {
	private Manager restaurantsManager = new Manager();

	public void setupScenary1() throws IOException, WrongNitException {

		restaurantsManager.addRestaurant("Burger King", "12345", "Holly");
		restaurantsManager.addRestaurant("Focio", "1234", "Sebastian");


	}
	public void setupScenary2() throws IOException  {

		restaurantsManager.addClient("Alejandro", "Coronel", "1548225645", 2, "3124865794", "Candelaria");
		restaurantsManager.addClient("Dario", "Lopez", "2154531341", 1, "3154867624", "Cali");

	}
	@Test
	public void testSearchRestaurant_1() throws IOException, WrongNitException {
		setupScenary1();
		assertEquals("Fail test NIT", 1, restaurantsManager.searchRestaurantNit("1234"));
	}
	@Test
	public void testSearchClient_2() throws IOException {
		setupScenary2();
		assertTrue("Fail test search client", restaurantsManager.searchClientName("Dario","Lopez"));
	}
}