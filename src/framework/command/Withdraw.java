package framework.command;

import framework.exceptions.WithdrawException;
import framework.interfaces.IAccount;
import framework.interfaces.ICommand;

public class Withdraw implements ICommand {

	IAccount account;
	
	public Withdraw(IAccount account) {
		this.account = account;
	}
	
	@Override
	public void execute(double value) throws WithdrawException {
		this.account.withdraw(value);
		
	}

}
