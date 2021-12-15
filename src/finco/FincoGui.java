package finco;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bank.JDialog_AddPAcc;
import bank.JDialog_Deposit;
import bank.JDialog_Withdraw;
import framework.Util.FunctionUtil;
import framework.account.Account;
import framework.account.FincoManager;
import framework.command.Deposit;
import framework.command.Withdraw;
import framework.exceptions.WithdrawException;

public class FincoGui extends javax.swing.JFrame {
	/****
	 * init variables in the object
	 ****/
	public String accountnr;
	public String clientName;
	public String street;
	public String city;
	public String zip;
	public String state;
	public String accountType;
	public String amountDeposit;
	public boolean newaccount;
	protected DefaultTableModel model=null;
	protected JTable JTable1;
	protected JScrollPane JScrollPane1;
	protected FincoGui myframe;
	protected Object[] rowdata;
	protected SymAction lSymAction;

	protected FincoManager manager;


	
	public FincoGui() {
		myframe = this;
		setTitle("Finco Application");
		setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(675, 410);
		setVisible(false);
		JPanel1.setLayout(null);
		getContentPane().add(BorderLayout.CENTER, JPanel1);
		JPanel1.setBounds(0, 0, 580, 320);
		/*
		 * /Add five buttons on the pane /for Adding personal account, Adding company
		 * account /Deposit, Withdraw and Exit from the system
		 */
		JScrollPane1 = new JScrollPane();
		model = createModel();
		JTable1 = new JTable(model);
		rowdata = new Object[8]; // different
		newaccount = false;

		JPanel1.add(JScrollPane1);
		JScrollPane1.setBounds(12, 92, 444, 160);
		JScrollPane1.getViewport().add(JTable1);
		JTable1.setBounds(0, 0, 420, 0);
//      rowdata = new Object[8];

		JButton_PerAC.setText("Add account"); // name is defferent
		JPanel1.add(JButton_PerAC); // name different
		JButton_PerAC.setBounds(24, 20, 192, 33);
		JButton_PerAC.setActionCommand("jbutton");

		//JButton_CompAC.setText("Add company account"); // button different
		//JButton_CompAC.setActionCommand("jbutton");//different
		//JPanel1.add(JButton_CompAC);	//diff
		//JButton_CompAC.setBounds(240, 20, 190, 33);	//diffe
		JButton_Deposit.setText("Deposit");
		JPanel1.add(JButton_Deposit);
		JButton_Deposit.setBounds(468, 104, 96, 33);
		JButton_Withdraw.setText("Withdraw");
		JPanel1.add(JButton_Withdraw);
		JButton_Withdraw.setBounds(468, 164, 96, 33);// name diff
		
		//JButton_Addinterest.setBounds(448, 20, 106, 33);
		//JButton_Addinterest.setText("Add interest"); // doesnt exist on CC card
		//JPanel1.add(JButton_Addinterest);// doesnt exist on CC card
		
		JButton_Exit.setText("Exit");
		JPanel1.add(JButton_Exit);
		JButton_Exit.setBounds(468, 248, 98, 32);
		// lineBorder1.setRoundedCorners(true);
		// lineBorder1.setLineColor(java.awt.Color.green);
		// $$ lineBorder1.move(24,312);


		SymWindow aSymWindow = new SymWindow();
		this.addWindowListener(aSymWindow);
		lSymAction = new SymAction();
		JButton_Exit.addActionListener(lSymAction);
		JButton_PerAC.addActionListener(lSymAction);//diff
		//JButton_CompAC.addActionListener(lSymAction);//diff
		JButton_Deposit.addActionListener(lSymAction);
		JButton_Withdraw.addActionListener(lSymAction);
		//JButton_Addinterest.addActionListener(lSymAction);//diff
	}

	public void setManager(FincoManager manager){
		this.manager = manager;
	}

	
	protected DefaultTableModel createModel() {
		DefaultTableModel model1 = new DefaultTableModel();
		
		model1.addColumn("AcctNr"); // name is different
		model1.addColumn("Name");
		model1.addColumn("City");
		 // we dont have this on CC card
		model1.addColumn("Type"); // type is defferent
		model1.addColumn("Amount");
		return model1;
	}

	protected javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
	protected javax.swing.JButton JButton_PerAC = new javax.swing.JButton();
	//javax.swing.JButton JButton_CompAC = new javax.swing.JButton();
	javax.swing.JButton JButton_Deposit = new javax.swing.JButton();//same
	protected javax.swing.JButton JButton_Withdraw = new javax.swing.JButton();//same
	//javax.swing.JButton JButton_Addinterest = new javax.swing.JButton();
	javax.swing.JButton JButton_Exit = new javax.swing.JButton();

	void exitApplication() {
		try {
			this.setVisible(false); // hide the Frame
			this.dispose(); // free the system resources
			System.exit(0); // close the application
		} catch (Exception e) {
		}
	}

	class SymWindow extends java.awt.event.WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent event) {
			Object object = event.getSource();
			if (object == FincoGui.this)
				BankFrm_windowClosing(event);
		}
	}
	

	void BankFrm_windowClosing(java.awt.event.WindowEvent event) {
		// to do: code goes here.

		BankFrm_windowClosing_Interaction1(event);
	}

	void BankFrm_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
		try {
			this.exitApplication();
		} catch (Exception e) {
		}
	}

	protected class SymAction implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent event) {
			Object object = event.getSource();
			if (object == JButton_Exit)
				JButtonExit_actionPerformed(event);
			else if (object == JButton_PerAC)
				JButtonPerAC_actionPerformed(event);
			else if (object == JButton_Deposit)
				JButtonDeposit_actionPerformed(event);
			else if (object == JButton_Withdraw)
				JButtonWithdraw_actionPerformed(event);

		}
	}

	// When the Exit button is pressed this code gets executed
	// this will exit from the system
	void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
		System.exit(0);
	}

	void JButtonPerAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 * JDialog_AddPAcc type object is for adding personal information construct a
		 * JDialog_AddPAcc type object set the boundaries and show it
		 */
//		if (this.manager.factory == null) {
//			System.out.println("no implementation of the factory provided");
//			return;
//		}
		JDialog_AddPAcc pac = new JDialog_AddPAcc(myframe);
		pac.setBounds(450, 20, 300, 330);
		pac.show();

		if (newaccount) {

			manager.createNewCustomer(accountnr, clientName, city, "P", accountType);
			rowdata[0] = accountnr;
			rowdata[1] = clientName;
			rowdata[2] = city;
			//rowdata[3] = type;
			rowdata[3] = accountType;
			rowdata[4] = "0";
			model.addRow(rowdata);
			JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
			newaccount = false;

		}

	}


	

	void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
		// get selected name
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String accnr = getAccountNumber(selection);

			// Show the dialog for adding deposit amount for the current mane
			JDialog_Deposit dep = new JDialog_Deposit(myframe, accnr);
			dep.setBounds(430, 15, 275, 140);
			dep.show();
			

		    Account acc =  FunctionUtil.getSelectedAccount.apply(manager.getCustomerList(), accnr);

			try {
				manager.doTransaction(new Deposit(acc),Integer.parseInt(amountDeposit));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WithdrawException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    changeColumnForSelectedRow(String.valueOf(acc.getCurrentBalance()),selection, 4);
		}

	}
	protected String getAccountNumber(int selection) {
		return (String) model.getValueAt(selection, 0);
	}

	void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
		// get selected name
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String accnr = getAccountNumber(selection);

			// Show the dialog for adding withdraw amount for the current mane
			JDialog_Withdraw wd = new JDialog_Withdraw(myframe, accnr);
			wd.setBounds(430, 15, 275, 140);
			wd.show();

			 Account acc = FunctionUtil.getSelectedAccount.apply(manager.getCustomerList(), accnr);

	    		
				try {
					manager.doTransaction(new Withdraw(acc),Integer.parseInt(amountDeposit));
				} catch (NumberFormatException | WithdrawException e) {
					// TODO Auto-generated catch block
					 JOptionPane.showMessageDialog(null, "You don't have enough money to do this transaction.", e.getMessage(),JOptionPane.WARNING_MESSAGE);
					e.getMessage();
				}
			    // compute new amount
				
	           
			    double newamount= acc.getCurrentBalance();
			    if (newamount < 0) {
					JOptionPane.showMessageDialog(JButton_Withdraw,
							" Account " + accnr + " : balance is negative: $" + String.valueOf(newamount) + " !",
							"Warning: negative balance", JOptionPane.WARNING_MESSAGE);
				}
			    changeColumnForSelectedRow(String.valueOf(newamount),selection, 4);
			   
			    
			
		}

	}
	
	protected void changeColumnForSelectedRow(String amount, int selectedRow, int targetColumn ) {
		 model.setValueAt(amount,selectedRow, targetColumn);
	}

}
