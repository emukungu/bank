package bank;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import finco.FincoGui;

public class BankGui extends FincoGui {

	javax.swing.JButton JButton_CompAC = new javax.swing.JButton();
	javax.swing.JButton JButton_Addinterest = new javax.swing.JButton();

	public BankGui() {
		super();

		setTitle("Bank Application");

		lSymAction = new BankSymAction();
		JButton_CompAC.setText("Add company account"); // button different
		JButton_CompAC.setActionCommand("jbutton");// different
		JButton_CompAC.setBounds(240, 20, 190, 33); // diffe
		JPanel1.add(JButton_CompAC);
		JButton_CompAC.addActionListener(lSymAction);// diff

		JButton_Addinterest.setBounds(448, 20, 106, 33);
		JButton_Addinterest.setText("Add interest"); // doesnt exist on CC card
		JPanel1.add(JButton_Addinterest);// doesnt exist on CC card
		JButton_Addinterest.addActionListener(lSymAction);// diff

	}

	@Override
	protected DefaultTableModel createModel() {
		DefaultTableModel model1 = new DefaultTableModel();
		model1.addColumn("AcctNr"); // name is different
		model1.addColumn("Name");
		model1.addColumn("City");
		model1.addColumn("P/C"); // we dont have this on CC card
		model1.addColumn("Ch/S"); // type is defferent
		model1.addColumn("Amount");

		return model1;
	}

	@Override
	protected void changeColumnForSelectedRow(String amount, int selectedRow, int targetColumn) {
		model.setValueAt(amount, selectedRow, 5);
	}

	class BankSymAction extends SymAction {
		public void actionPerformed(java.awt.event.ActionEvent event) {
			super.actionPerformed(event);
			Object object = event.getSource();
			if (object == JButton_CompAC)
				JButtonCompAC_actionPerformed(event);
			else if (object == JButton_Addinterest)
				JButtonAddinterest_actionPerformed(event);

		}
	}

	void JButtonCompAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 * construct a JDialog_AddCompAcc type object set the boundaries and show it
		 */

		JDialog_AddCompAcc pac = new JDialog_AddCompAcc(myframe);
		pac.setBounds(450, 20, 300, 330);
		pac.show();

		if (newaccount) {
			this.manager.createNewCustomer(accountnr, clientName, city, "C", accountType);

			rowdata[0] = accountnr;
			rowdata[1] = clientName;
			rowdata[2] = city;
			//rowdata[3] = type;
			rowdata[4] = accountType;
			rowdata[5] = "0";
			model.addRow(rowdata);
			JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
			newaccount = false;

		}

	}

	void JButtonAddinterest_actionPerformed(java.awt.event.ActionEvent event) {
		JOptionPane.showMessageDialog(JButton_Addinterest, "Add interest to all accounts",
				"Add interest to all accounts", JOptionPane.WARNING_MESSAGE);

			manager.addInterest(5,model);
	}


}
