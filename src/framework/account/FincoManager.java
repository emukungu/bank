package framework.account;

import framework.Util.FunctionUtil;
import framework.command.Deposit;
import framework.customer.Customer;
import framework.exceptions.WithdrawException;
import framework.interfaces.ICommand;
import framework.interfaces.ICustomer;
import framework.interfaces.IFactory;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FincoManager {


	private List<Customer> customerList;
	private Deque<ICommand> commands;

	private IFactory factory;

	public FincoManager(){
		this.customerList = new ArrayList<>();
		this.commands = new ArrayDeque<>();
	}

	public void doTransaction(ICommand command, double value) throws WithdrawException {
		command.execute(value);
		this.commands.push(command);
	}

	public void addCustomer(Customer customer){
		this.customerList.add(customer);
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setFactory(IFactory factory) {
		this.factory = factory;
	}

	public IFactory getFactory() {
		return factory;
	}

	public void createNewCustomer(String accountnr, String clientName, String city, String type, String accountType) {
		// add row to table
		Account account = (Account) this.factory.createAccount(accountType, accountnr);
		ICustomer customer = this.factory.createCustomer(type, clientName, city, account);
		account.setCustomer(customer);
		//((Customer) customer).addAccount(account);
		this.addCustomer((Customer) customer);

	}

//	public void createNewCustomer(String accountnr, String clientName, String city, String type, String accountType) {
//		// add row to table
//		Account account = (Account) this.factory.createAccount(accountType, accountnr);
//		ICustomer customer = this.factory.createCustomer(type, clientName, city, account);
//		account.setCustomer(customer);
//		//((Customer) customer).addAccount(account);
//		this.addCustomer((Customer) customer);
//
//	}

	public void addInterest(double rate, DefaultTableModel model){
		List<Account> accountList = FunctionUtil.getAllAccounts.apply(this.getCustomerList());

		int index = 0;
		for (Account acc : accountList) {
			double value = acc.getCurrentBalance() * rate / 100;

			try {
				this.doTransaction(new Deposit(acc),value);
				model.setValueAt(String.valueOf(acc.getCurrentBalance()), index, 5);
				index++;
			} catch (WithdrawException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
		}
	}



}
