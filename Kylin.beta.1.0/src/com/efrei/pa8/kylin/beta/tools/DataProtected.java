package com.efrei.pa8.kylin.beta.tools;

import java.util.ArrayList;

import com.efrei.pa8.kylin.beta.contacts.model.Contact;
import com.efrei.pa8.kylin.beta.loans.model.Loan;
import com.efrei.pa8.kylin.beta.shoppingList.model.ShoppingList;
import com.efrei.pa8.kylin.beta.todotasks.model.ToDoTask;

public class DataProtected {

	private ArrayList<ShoppingList> shoppingListList;
	private ArrayList<Contact> contactList;
	private ArrayList<Loan> loanList;
	private ArrayList<User> userList;
	private ArrayList<ToDoTask> todoTaskList;
	private String username;
	private String password;
	private int id;
	private int listPosition;
	private int shoppingItemPosition;
	
	public void addShoppingList(ShoppingList shoppingList){
		this.shoppingListList.add(shoppingList);
	}
	public ArrayList<ShoppingList> getShoppingListList() {
		return shoppingListList;
	}
	public void setShoppingListList(ArrayList<ShoppingList> shoppingListList) {
		this.shoppingListList = shoppingListList;
	}
	public ArrayList<Contact> getContactList() {
		return contactList;
	}
	public void setContactList(ArrayList<Contact> contactList) {
		this.contactList = contactList;
	}
	public void addContact(Contact contact) {
		this.contactList.add(contact);
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getListPosition() {
		return listPosition;
	}
	public void setListPosition(int listPosition) {
		this.listPosition = listPosition;
	}
	public int getShoppingItemPosition() {
		return shoppingItemPosition;
	}
	public void setShoppingItemPosition(int shoppingItemPosition) {
		this.shoppingItemPosition = shoppingItemPosition;
	}
	public void addloan(Loan loan) {
		this.loanList.add(loan);
	}
	public ArrayList<Loan> getLoanList() {
		return loanList;
	}
	public void setLoanList(ArrayList<Loan> loanList) {
		this.loanList = loanList;
	}
	public void addUser(User user) {
		this.userList.add(user);
	}
	public ArrayList<User> getUserList() {
		return userList;
	}
	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}
	public void addToDoTaks(ToDoTask toDoTask) {
		this.todoTaskList.add(toDoTask);
	}
	public ArrayList<ToDoTask> getTodoTaskList() {
		return todoTaskList;
	}
	public void setTodoTaskList(ArrayList<ToDoTask> todoTaskList) {
		this.todoTaskList = todoTaskList;
	}
}
