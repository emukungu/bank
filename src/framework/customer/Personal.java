package framework.customer;

import java.util.ArrayList;

import framework.account.Account;
import framework.interfaces.IAccount;
import framework.interfaces.ICompany;
import framework.interfaces.IPersonal;

public class Personal extends Customer implements IPersonal {

	ICompany company;
	
	String birthdate;
	
	public Personal(String name) {
		this.setName(name);
		
	}
	
	public Personal(String name, String stret, String city, String state, String zip, String email,
			ArrayList<Account> accounts, String birthdate) {
		super(name, stret, city, state, zip, email, accounts);
		
		this.birthdate = birthdate;
	}

	public Personal() {
		// TODO Auto-generated constructor stub
		System.out.println("personal instancited");

	}
	

	public Personal(String clientName, String city, Account account) {
	super(clientName,city,account);
	}

	@Override
	public void sendEmail() {
		this.sendEmailToCustomer();	
	}
	
	
	
	
}
