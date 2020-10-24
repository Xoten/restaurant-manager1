package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import org.junit.jupiter.api.Test;

class DeliveryTest {
	Random random = new Random();
	private Manager restaurantsManager = new Manager();
	public void setupScenary1() throws IOException{

		restaurantsManager.addRestaurant("Burger King", "12345", "Sandra");
		restaurantsManager.addRestaurant("Domino pizza", "1234", "Camilo");
		restaurantsManager.addProduct("Sandwich", "4673", "sandwich cubano", 12000, "1234");
		restaurantsManager.addProduct("Churrasco", "4675", "carne a la plancha", 8500, "1234");
		restaurantsManager.addClient("Esteban", "Dominguez", "2154531341", 1, "31254867624", "Cali");
		restaurantsManager.addOrder((String)new BigInteger(50, random).toString(32), "2154531341", "12345");
		restaurantsManager.getOrders().get(0).addProductInOrderList("4673", 2);
		restaurantsManager.addOrder((String)new BigInteger(50, random).toString(32), "2154531341", "12345");
		restaurantsManager.getOrders().get(1).addProductInOrderList("2153", 2);
		restaurantsManager.getOrders().get(1).addProductInOrderList("4673", 3);
	}
	@Test
	public void testOrderAdded_1() throws IOException {
		setupScenary1();
		assertEquals("Fail test in order added", 2, restaurantsManager.getOrders().size());

	}
	@Test
	public void testOrderSearchProductCode_1() throws IOException {
		setupScenary1();
		assertEquals("Fail test in order to search", 1, restaurantsManager.getOrders().get(1).searchProductInList("4673"));
	}
}
