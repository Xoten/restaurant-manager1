package model;

import java.io.Serializable;

public class Client implements Serializable, Comparable<Client> {
	
	public final static long serialVersionUID = 1;
	private String type;
	private String idNumber;
	private String Name;
	private int phoneNumber;
	private String adress;
	private String Lastname;
	private static final String TI= "TI";
	private static final String CC = "CC";
	private static final String PP = "PP";
	private static final String CE = "CE";
	
	
	
    
			
	
	public Client(String type, String idNumber, String Name,String Lastname, int phoneNumber, String adress) {
		this.type = type;
		this.idNumber = idNumber;
		this.Name = Name;
		this.Lastname = Lastname;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		
	}
	
	public void setType(String type) {
		
		
		if(type.equalsIgnoreCase("TI")) {
			
			this.type = TI;
		}else if(type.equalsIgnoreCase("CE")) {
			
			this.type = CE;
		}else if(type.equalsIgnoreCase("PP")) {
			
			this.type = PP;
		}else if(type.equalsIgnoreCase("CC")) {
			
			this.type = CC;
		}
		
		
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

	public String getType() {
		
		return type;
		
		
		
		
		
		
		
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
	
	public String toString() {
		
		return "type:"+type+","+ "IdNumber:" + idNumber+","+ "Name:"+Name+","+"LastName:"+Lastname+","+"PhoneNumber:"+phoneNumber+","+"Adress:"+adress;
		
	}
	
		
	

}
