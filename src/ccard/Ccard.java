package ccard;

import java.io.File;
import java.util.Scanner;

import javax.swing.UIManager;

import finco.Finco;
import framework.interfaces.IFactory;

public class Ccard extends Finco{

	Ccard(CcardGui gui, IFactory abstractFactory) {
		super(gui, abstractFactory);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		

		IFactory f=null;
		Class instance=null;
		try {
			Scanner scanner = new Scanner(new File("ccardConfig.txt"));
			
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
		    new Ccard((CcardGui) instance.newInstance(), f);
			
		} 
		catch (Throwable t) {
			t.printStackTrace();
			//Ensure the application exits with an error condition.
			System.exit(1);
		}
		
	
		
}
}
