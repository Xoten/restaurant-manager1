package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;


class ProductTest {

	private Manager restaurantsManager = new Manager();

	public void setupScenary1() throws IOException{

		restaurantsManager.addRestaurant("Burger King", "12345", "Holly");
		restaurantsManager.addRestaurant("Focio", "1234", "Teo");
		restaurantsManager.addProduct("Hamburguesa mexicana", "2153", "Hamburgursa con chile, frijoles", 1500, "12345");
		restaurantsManager.addProduct("Arroz con pollo", "2153", "Arroz con pollo estilo suizo", 1500, "12345");
	}
	
	@Test
	public void testProductAddedForEachRestaurant_1() throws IOException {
		setupScenary1();
		assertEquals("Fail test product added to restaurant", "12345",restaurantsManager.getProducts().get(0).getRestaurantNit());

	}
	@Test
	public void testProductSameCode_2() throws IOException {
		setupScenary1();
		assertEquals("Fail test product with same code added to restaurant", 1,restaurantsManager.getProducts().size());
	}
}
