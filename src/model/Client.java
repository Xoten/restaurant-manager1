package model;

public class Client implements Comparable<Client> {
	
	private int type;
	private String idNumber;
	private String Name;
	private String phoneNumber;
	private String adress;
	private String Lastname;
	static int TI =1;
	static int CC = 2;
    static int PP = 3;
    static int CE = 4;
			
	
	public Client(int type, String idNumber, String Name,String Lastname, String phoneNumber, String adress) {
		this.type = type;
		this.idNumber = idNumber;
		this.Name = Name;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.Lastname = Lastname;
	}
	
	public void setType(int type) {
		this.type = type;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void setName(String Name) {
		this.Name = Name;
	}
	
	public String getLastName() {
		
		return Lastname;
	}
	public void setLastName(String Lastname) {
		
		this.Lastname = Lastname;
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
	
	public String getName() {
		return Name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getAdress() {
		return adress;
	}

	@Override
	public int compareTo(Client cl1) {
		int comp;
		comp = Name.compareTo(cl1.getName());
		
			
			
			
		return comp;
		
		
	}
	
	
	
	
	

}
