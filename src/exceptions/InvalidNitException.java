package exceptions;

public class InvalidNitException extends Exception{

private static final long serialVersionUID = 1;
	
	private int nit;
	
	public InvalidNitException(int nit2) { 
		super("----------------\nThe entered NIT: " + nit2 + " is invalid");
		this.nit = nit2;
	}
	
	public int getNit() {
		return nit;
	}
	
	
	
	
}
