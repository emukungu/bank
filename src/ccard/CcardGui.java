package ccard;

import javax.swing.table.DefaultTableModel;

import finco.FincoGui;
import framework.Util.FunctionUtil;
import framework.account.Account;

public class CcardGui extends FincoGui{
	String expdate, ccnumber;
	javax.swing.JButton JButton_NewCCAccount = new javax.swing.JButton();
	javax.swing.JButton JButton_GenBill = new javax.swing.JButton();
	
	public CcardGui() {
		super();
		myframe=this;
		
		
		setTitle("Credit-Card Processing Application.");
		lSymAction = new CcardSymAction();
		JPanel1.remove(JButton_PerAC);
		JButton_Withdraw.setText("Charge");
		
		JButton_NewCCAccount.setText("Add Credit-card account"); 
		JPanel1.add(JButton_NewCCAccount);
		JButton_NewCCAccount.setActionCommand("jbutton");
		JButton_NewCCAccount.setBounds(24,20,192,33);
		
		JButton_GenBill.setText("Generate Monthly bills");
		JPanel1.add(JButton_GenBill);
		JButton_GenBill.setActionCommand("jbutton");
		JButton_GenBill.setBounds(240,20,192,33);
		
		JButton_NewCCAccount.addActionListener(lSymAction);
		JButton_GenBill.addActionListener(lSymAction); // diff

	}
	
	
	
	@Override
	protected DefaultTableModel createModel() {
		DefaultTableModel model1 = new DefaultTableModel();
		   model1.addColumn("Name");
	        model1.addColumn("CC number");
	        model1.addColumn("Exp date"); // we dont have this on Bank
	        model1.addColumn("Type");
	        model1.addColumn("Balance");

		return model1;
	}
	
	class CcardSymAction extends SymAction {
		public void actionPerformed(java.awt.event.ActionEvent event) {
			super.actionPerformed(event);
			Object object = event.getSource();
			 if (object == JButton_NewCCAccount)
				JButtonNewCCAC_actionPerformed(event);
			else if (object == JButton_GenBill)
				JButtonGenerateBill_actionPerformed(event);

		}
	}
	
	
	
	
	void JButtonNewCCAC_actionPerformed(java.awt.event.ActionEvent event)
	{
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object 
		 set the boundaries and show it 
		*/
		
		JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(myframe);
		ccac.setBounds(450, 20, 300, 380);
		ccac.show();

		if (newaccount){
            // add row to table
			this.manager.createNewCustomer(ccnumber, clientName, expdate, "", accountType);

			rowdata[0] = clientName;
			rowdata[1] = ccnumber;
			rowdata[2] = expdate;
			rowdata[3] = accountType;
			rowdata[4] = "0";
			model.addRow(rowdata);
			JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
			newaccount=false;
           
        }

    }
	
	void JButtonGenerateBill_actionPerformed(java.awt.event.ActionEvent event)
	{

		// get selected name
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String accnr = getAccountNumber(selection);

			Account acc =  FunctionUtil.getSelectedAccount.apply(manager.getCustomerList(), accnr);
			JDialogGenBill billFrm = new JDialogGenBill(acc.generateReport());
			billFrm.setBounds(450, 20, 400, 350);
			billFrm.show();



		}
	    
	}
	@Override
	protected String getAccountNumber(int selection) {
		return (String) model.getValueAt(selection, 1);
	}
	


}
