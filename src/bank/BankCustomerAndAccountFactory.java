package bank;
import framework.account.Account;
import framework.customer.Company;
import framework.customer.Personal;
import framework.interfaces.IAccount;
import framework.interfaces.ICustomer;
import framework.interfaces.IFactory;

public class BankCustomerAndAccountFactory implements IFactory {

	@Override
	public ICustomer createCustomer(String type, String clientName, String city, Account account) {
		switch(type) {
		case "P": return new Personal(clientName,city,account);
		case "C": return new Company(clientName,city,account);
		}
		return null;
	}

	@Override
	public IAccount createAccount(String type, String accNumber) {
		System.out.println("type of account : "+ type);
		switch(type) {
		case "Ch": return new Cheking(accNumber) ;
		case "S": return new Saving(accNumber);
		}
		return null;
	}

	

}
