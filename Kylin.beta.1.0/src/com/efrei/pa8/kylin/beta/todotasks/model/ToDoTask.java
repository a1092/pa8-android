package com.efrei.pa8.kylin.beta.todotasks.model;

import java.util.ArrayList;
import java.util.Date;

import com.efrei.pa8.kylin.beta.tools.User;

public class ToDoTask {
	private int id;
	private String name;
	private Date deadline;
	private String description;
	private ArrayList<User> users;

	public ToDoTask(int id, String name, Date deadline, String description) {
		super();
		this.id = id;
		this.name = name;
		this.deadline = deadline;
		this.description = description;
		this.users = new ArrayList<User>();
	}
	
	public ToDoTask(String name, Date deadline, String description,
			ArrayList<User> users) {
		super();
		this.name = name;
		this.deadline = deadline;
		this.description = description;
		this.users = users;
	}
	
	public ToDoTask(int id, String name, Date deadline, String description,
			ArrayList<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.deadline = deadline;
		this.description = description;
		this.users = users;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void addUser(User user) {
		this.users.add(user);
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
}



//id name deadline description users