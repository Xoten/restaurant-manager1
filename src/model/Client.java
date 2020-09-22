package model;

public class Client {
	
	private int type;
	private String idNumber;
	private String fullName;
	private String phoneNumber;
	private String adress;
	static int TI =1;
	static int CC = 2;
    static int PP = 3;
    static int CE = 4;
			
	
	public Client(int type, String idNumber, String fullName, String phoneNumber, String adress) {
		this.type = type;
		this.idNumber = idNumber;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
	}
	
	public void setType(int type) {
		this.type = type;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getType() {
		int tipo = 0;
		if (type == TI) {
			tipo= 1;
		}
		if (type == CC) {
			tipo= 2;

		}
		if (type == PP) {
			tipo = 3;

		} else {
			tipo= 4;
		}

		return tipo;
		
		
		
		
		
		
		
	}
	
	public String getIdNumber() {
		return idNumber;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getAdress() {
		return adress;
	}
	
	
	
	
	

}
