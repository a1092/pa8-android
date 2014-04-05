package com.efrei.pa8.kylin.beta.shoppingList.model;

import java.util.ArrayList;
import java.util.Date;

public class ShoppingList {
	String name;
	ArrayList<ShoppingItem> shoppingItems;
	Date deadline;
	int id;
	
	public ShoppingList(int id, String name, Date dealdline){
		super();
		this.id = id;
		this.name = name;
		this.shoppingItems = new ArrayList<ShoppingItem>();
		this.deadline = dealdline;
	}
	public ShoppingList(String name, Date dealdline){
		super();
		this.name = name;
		this.shoppingItems = new ArrayList<ShoppingItem>();
		this.deadline = dealdline;
	}
	
	public ShoppingList(String name, ArrayList<ShoppingItem> shoppingItems,
			Date deadline, Date creationDate, Date modificationDate) {
		super();
		this.name = name;
		this.shoppingItems = shoppingItems;
		this.deadline = deadline;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<ShoppingItem> getShoppingItems() {
		return shoppingItems;
	}
	public void setShoppingItems(ArrayList<ShoppingItem> shoppingItems) {
		this.shoppingItems = shoppingItems;
	}
	public void addShoppingitem(ShoppingItem shoppingItem){
		this.shoppingItems.add(shoppingItem);
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
