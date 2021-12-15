package framework.interfaces;

import framework.account.Account;

public interface IFactory {
	ICustomer createCustomer(String string, String clientName, String city, Account account);
	IAccount createAccount(String type, String accNumber);

}
