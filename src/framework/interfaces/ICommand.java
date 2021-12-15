package framework.interfaces;

import framework.exceptions.WithdrawException;

public interface ICommand {
	public void execute(double value) throws WithdrawException;
}
