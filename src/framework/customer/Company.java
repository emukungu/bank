package framework.customer;

import java.util.ArrayList;
import java.util.List;

import framework.account.Account;
import framework.interfaces.IAccount;
import framework.interfaces.ICompany;
import framework.interfaces.ICustomer;
import framework.interfaces.IPersonal;

public class Company extends Customer implements ICompany {
	
	private int nbr_of_employees = 0;
	private ArrayList<IPersonal> personalList;
	
	
	
		
	public Company() {
		super();
		// TODO Auto-generated constructor stub
		System.out.println("company instancited");
	}
	
	public Company(String clientName, String city, Account account) {
		super(clientName,city,account);
		}


	Company(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}


	public Company(String name, String stret, String city, String state, String zip, String email,
			List<Account> accounts) {
		super(name, stret, city, state, zip, email, accounts);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void sendEmail() {
		this.sendEmailToCustomer();	
	}
	
	
	
	
}
