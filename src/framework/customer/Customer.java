package framework.customer;

import java.util.ArrayList;
import java.util.List;

import framework.account.Account;
import framework.interfaces.ICustomer;

import javax.swing.*;

public abstract class Customer implements ICustomer {
	
	private String name;
	private String stret;
	private String city;
	private String state;
	private String zip;
	private String email;
	
	private List<Account> accounts = new ArrayList<>();
	
	public Customer() {}

	
	Customer(String name, String city, Account account) {
		super();
		this.name = name;
		this.city = city;
		this.accounts.add(account);
	}



	Customer(String name) {
		super();
		this.name = name;
	}



	public Customer(String name, String stret, String city, String state, String zip, String email,
			List<Account> accounts) {
		super();
		this.name = name;
		this.stret = stret;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.email = email;
		this.accounts = accounts;
	}

	
	public void addAccount(Account account) {
		this.accounts.add(account);
		System.out.println("Account added");
		
	}
	
	public void removeAccount(Account account) {
		this.accounts.remove(account);
		System.out.println("Account removed");
	}
	
	
	public void sendEmailToCustomer() {

		String message = "Email sent to " + this.name;

		JOptionPane.showMessageDialog(null, message,
				"Email Notification", JOptionPane.WARNING_MESSAGE);

	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStret() {
		return stret;
	}


	public void setStret(String stret) {
		this.stret = stret;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getZip() {
		return zip;
	}


	public void setZip(String zip) {
		this.zip = zip;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Account> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	@Override
	public String toString() {
		return "Customer [name=" + name + ", stret=" + stret + ", city=" + city + ", state=" + state + ", zip=" + zip
				+ ", email=" + email + ", accounts=" + accounts + "]";
	}
	
	

	

}
