package finco;

import java.io.File;

import javax.swing.UIManager;

import java.util.Scanner;

import framework.account.FincoManager;
import framework.exceptions.WithdrawException;
import framework.interfaces.IFactory;

public class Finco {
	protected static FincoGui gui=null;
	private FincoManager manager;

	
	protected Finco( FincoGui gui, IFactory factory){
		this.gui=gui;
		manager = new FincoManager();
		manager.setFactory(factory);
		gui.setManager(manager);
		gui.setVisible(true);
		
		
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws WithdrawException, InterruptedException {
		IFactory f=null;
		Class instance=null;
		try {
			Scanner scanner = new Scanner(new File("fincoConfig.txt"));
			
			String instanceName = scanner.nextLine();
			 instance = Class.forName(instanceName);
			 System.out.println(instanceName);
			 f= (IFactory) instance.newInstance();

			 
			 instanceName = scanner.nextLine();
			 System.out.println(instanceName);
			 instance = Class.forName(instanceName);
			 
			 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			try {
		    // Add the following code if you want the Look and Feel
		    // to be set to the Look and Feel of the native system.
		    
		    try {
		        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    } 
		    catch (Exception e) { 
		    }
		    
			//Create a new instance of our application's frame, and make it visible.
		    new Finco((FincoGui) instance.newInstance(), f);
			
		} 
		catch (Throwable t) {
			t.printStackTrace();
			//Ensure the application exits with an error condition.
			System.exit(1);
		}
		
	
		
	}
}
