package framework.interfaces;

import framework.exceptions.WithdrawException;

public interface IAccount extends ISubject {
	 void deposit(double value);
	 void withdraw(double value) throws WithdrawException;
}
