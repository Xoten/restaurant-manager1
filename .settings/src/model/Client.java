package model;
import java.io.Serializable;

public class Client implements Serializable{



	private static final long serialVersionUID = 1;
	private String idType;
	private String idNum;
	private String name;
	private String lastName;
	private String telephone;
	private String address;


	
	public Client(String name, String lastName, String idNum, String idType, String telephone, String address) {
		this.idType = idType;
		this.idNum = idNum;
		this.name = name;
		this.lastName = lastName;
		this.telephone = telephone;
		this.address = address;

	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public String getFullName() {
		String fullName = "";
		fullName = name+" "+lastName;
		return fullName;
	}

	public String getInfo() {
		String info = "";
		info += "Name: "+getName()+"\nLast name: "+lastName+"\nID number: "+getIdNum()+"\nID type:"+getIdType()+"\nTelephone: "+getTelephone()+"\nAdress: "+getAddress()+"\n";
		return info;
	}
	
	@Override
	public String toString() {
		String concat = "";
		concat += "\n"+"Name: "+getName()+"\nLast name: "+lastName+"\nID number: "+getIdNum()+"\nID type:"+getIdType()+"\nTelephone: "+getTelephone()+"\nAdress: "+getAddress()+"\n";
		return concat;
	}
}