package com.efrei.pa8.kylin.beta.contacts.model;


public class Contact {
	private int id;
	private String name;
	private String email;
	private String adress;
	private String homePhoneNumber;
	private String mobilePhoneNumber;
	private String otherPhoneNumber;
	private String category;
	private String remark;
	
	public Contact(int id, String name, String email, String adress,
			String homePhoneNumber, String mobilePhoneNumber,
			String otherPhoneNumber, String category, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.adress = adress;
		this.homePhoneNumber = homePhoneNumber;
		this.mobilePhoneNumber = mobilePhoneNumber;
		this.otherPhoneNumber = otherPhoneNumber;
		this.category = category;
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}

	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	public String getOtherPhoneNumber() {
		return otherPhoneNumber;
	}

	public void setOtherPhoneNumber(String otherPhoneNumber) {
		this.otherPhoneNumber = otherPhoneNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getId() {
		return id;
	}
}
