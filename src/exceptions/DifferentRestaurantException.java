package exceptions;

public class DifferentRestaurantException extends Exception{
	private static final long serialVersionUID = 1;
	
	 public DifferentRestaurantException() {
		super("----------------\n the option selected is invalid."
				+ "\n please select a product that belongs to the same restaurant." );
	}
}
