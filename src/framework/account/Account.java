package framework.account;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import framework.entry.Entry;
import framework.exceptions.WithdrawException;
import framework.interfaces.IAccount;
import framework.interfaces.ICustomer;
import framework.interfaces.IEntry;
import framework.interfaces.IObserver;

public class Account implements IAccount{
	private double balance;
	private String accNumber;
	private ICustomer customer;
	
	private List<IEntry> entries;

	
	
	public Account(String accNumber) {
		this.accNumber = accNumber;
		this.entries = new ArrayList<>();
	}
	
	public void deposit(double value) {
		this.balance += value;
		this.addEntry("Deposit",value);
		
		if(customer.getClass().getSimpleName().equals("Company")) {	
			this.notifyCustomers();
		}else if(customer.getClass().getSimpleName().equals("Personal") && value > 400){
			this.notifyCustomers();
		}
	}
	
	public void withdraw(double value) throws WithdrawException {
		if(this.balance <= 0)
			throw new WithdrawException("No enough money");
		this.balance -= value;

		this.addEntry("Withdraw", value);
		if(customer.getClass().getSimpleName().equals("Company")) {	
			this.notifyCustomers();
		}else if(customer.getClass().getSimpleName().equals("Personal") && (value > 400 || this.balance < 0)){
			this.notifyCustomers();
		}
	}
	
	public void addEntry(String name, double value) {
		this.entries.add(new Entry(value, new Date(), name));
		
	}
	
	public double getCurrentBalance() {
		return this.balance;
	}
	
	public String getAccNumber() {
		return accNumber;
	}
	

	public ICustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ICustomer customer) {
		this.register(customer);
	}
	
	public List<IEntry> getEntries() {
		return entries;
	}
	
	public String generateReport() {
		StringBuffer sb = new StringBuffer();
		sb.append("\nCurrent Balance: ".concat(String.valueOf(this.getCurrentBalance())));
		

		List<IEntry> sortedEntry = this.entries.stream()
		.sorted(Comparator.comparing(IEntry::getTransactionDate).reversed())
		.limit(10)
		.collect(Collectors.toList());
		
		sb.append("\n\n-------------- Last Transactions ----------------\n ");
		
	
		
		for(IEntry entry : sortedEntry) {
			String date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(entry.getTransactionDate());
			sb.append("\nDate: " + date + " Transaction type: " + entry.getName() + " Amount: " + entry.getAmout());
		}
		
		return sb.toString();
	
	}


	@Override
	public void register(IObserver customer) {
		this.customer=(ICustomer) customer;
		
	}

	@Override
	public void unregister(IObserver customer) {
		 this.customer=null;
		
	}

	@Override
	public void notifyCustomers() {   
			 customer.sendEmail();
		 
		
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + ", accNumber=" + accNumber + "]";
	}

	
	
		


}
