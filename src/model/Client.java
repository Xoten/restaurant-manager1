package model;

import java.io.Serializable;

public class Client implements Serializable, Comparable<Client> {
	
	public final static long serialVersionUID = 1;
	private int type;
	private String idNumber;
	private String Name;
	private int phoneNumber;
	private String adress;
	private String Lastname;
	static int TI =1;
	static int CC = 2;
    static int PP = 3;
    static int CE = 4;
			
	
	public Client(int type, String idNumber, String Name,String Lastname, int phoneNumber, String adress) {
		this.type = type;
		this.idNumber = idNumber;
		this.Name = Name;
		this.Lastname = Lastname;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		
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

	public void setPhoneNumber(int phoneNumber) {
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
	
	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getAdress() {
		return adress;
	}

	@Override
	public int compareTo(Client cl1) {
		
		int comp;
		if(phoneNumber<cl1.getPhoneNumber()) {
			comp=-1;
		}else if(phoneNumber>cl1.getPhoneNumber()) {
			comp=1;
		}else {
			comp=0;
		}return comp;
		
		
		
	}
	
	
	
	
	

}
