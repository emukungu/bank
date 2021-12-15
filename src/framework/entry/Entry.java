package framework.entry;

import java.util.Date;

import framework.interfaces.IEntry;

public class Entry implements IEntry {
	private double amount;
	Date transactionDate;
	

	String name;
	
	public Entry(double amount, Date transactionDate, String name) {
		super();
		this.amount = amount;
		this.transactionDate = transactionDate;
		this.name = name;
	}
	
	public Date getTransactionDate() {
		return transactionDate;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getAmout() {
		return this.amount;
	}

	@Override
	public String toString() {
		return "Entry [amount=" + amount + ", transactionDate=" + transactionDate + ", name=" + name + "]";
	}
	
	

}
