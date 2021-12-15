package framework.Util;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import framework.account.Account;
import framework.customer.Customer;
import framework.interfaces.IAccount;

import java.util.*;

public abstract class FunctionUtil {
	


	public static Function<List<Customer>, List<Account>> getAllAccounts = (customers) ->
		customers.stream()
		.flatMap(customer -> customer.getAccounts().stream())
		.collect(Collectors.toList());
		
		
		
		
	public static BiFunction<List<Customer>, String, Account> getSelectedAccount = (customers, accountNumber) ->
		getAllAccounts.apply(customers).stream()
		.filter(account -> ((Account) account).getAccNumber().equals(accountNumber))
		.collect(Collectors.toList()).get(0);

}