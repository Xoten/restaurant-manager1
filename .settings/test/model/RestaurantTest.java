package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;


class RestaurantTest {

	private Manager restaurantsManager = new Manager();

	public void setupScenary1() throws IOException {
		restaurantsManager.addRestaurant("Burger King", "12345", "Holly");
		restaurantsManager.addRestaurant("Focio", "12345", "Sebastian");
	}
	
	@Test
	public void testSameRestaurantsNit_1() throws IOException {
		setupScenary1();
		assertEquals("can't duplicate restqaurant nit", 1, restaurantsManager.getRestaurants().size());
	}
	@Test
	public void testSameRestaurantsNit_2() throws IOException {
		setupScenary1();
		assertEquals("can't duplicate restaurant NIT", "Burger King", restaurantsManager.getRestaurants().get(0).getName());
	}
}
