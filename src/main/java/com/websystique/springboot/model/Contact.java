package com.websystique.springboot.model;

public class Contact {
	private int contactId;
	private String id;
	private String fullName;
	private String phoneNumber;
	private String email;
	private String address;

	public Contact(String id, String fullName, String phoneNumber, String email, String address) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
	}

	public Contact() {

	}

	
	public Contact(int contactId2, String cid, String cfullName, String cphoneNumber, String cemail, String caddress) {
		// TODO Auto-generated constructor stub
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", fullName=" + fullName + ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", address=" + address + "]";
	}

}
