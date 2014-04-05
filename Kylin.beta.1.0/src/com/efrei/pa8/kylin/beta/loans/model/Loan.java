package com.efrei.pa8.kylin.beta.loans.model;

public class Loan {
	private String object;
	private int idBorrower;
	private String nameBorrower;
	private int idLender;
	private String nameLender;
	
	
	public Loan(String object, int idBorrower, String nameBorrower,
			int idLender, String nameLender) {
		super();
		this.object = object;
		this.idBorrower = idBorrower;
		this.nameBorrower = nameBorrower;
		this.idLender = idLender;
		this.nameLender = nameLender;
	}

	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public int getIdBorrower() {
		return idBorrower;
	}
	public void setIdBorrower(int idBorrower) {
		this.idBorrower = idBorrower;
	}
	public String getNameBorrower() {
		return nameBorrower;
	}
	public void setNameBorrower(String nameBorrower) {
		this.nameBorrower = nameBorrower;
	}
	public int getIdLender() {
		return idLender;
	}
	public void setIdLender(int idLender) {
		this.idLender = idLender;
	}
	public String getNameLender() {
		return nameLender;
	}
	public void setNameLender(String nameLender) {
		this.nameLender = nameLender;
	}
}
