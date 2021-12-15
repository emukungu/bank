package framework.command;

import framework.interfaces.IAccount;
import framework.interfaces.ICommand;

public class Deposit implements ICommand {

	IAccount account;
	
	public Deposit(IAccount account) {
		this.account = account;
	}

	@Override
	public void execute(double value) {
		this.account.deposit(value);
		
	}

}
