package viewController;


import javax.swing.JButton;

import system.model.ESDMModel;

import java.awt.event.ActionListener;

public class AccountView extends PanelView {



	/**
	 * 
	 */
	private static final long serialVersionUID = -7002494511986322397L;
	private JButton btnChangePassword;
	private JButton btnChangeEmailAddress;
	private JButton btnNewUserAccount;

	public AccountView() {
		super();
		initialise();

	}

	public AccountView(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	/*
	 * Initialises all the items on the screen.
	 */


	public void initialise()
	{
		setLayout(null);

		/*
		 * Adds the title to the top of the screen. then adds 3 buttons
		 */
		
		super.setTitle("Account Management");

		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(20, 70, 200, 40);
		add(btnChangePassword);

		btnChangeEmailAddress = new JButton("Change Email Address");
		btnChangeEmailAddress.setBounds(20, 120, 200, 40);
		add(btnChangeEmailAddress);
		
		btnNewUserAccount = new JButton("Add New User Account");
		btnNewUserAccount.setBounds(20, 170, 200, 40);
		add(btnNewUserAccount);
		
	}
	
	/*
	 * Takes in an ActionListener and adds it to the change password button
	 */
	
	public void changePassword(ActionListener al)
	{
		btnChangePassword.addActionListener(al);
	}

	/*
	 * Takes in an ActionListener and adds it to the change email address button
	 */
	
	public void changeEmailAddress(ActionListener al)
	{
		btnChangeEmailAddress.addActionListener(al);
	}
	
	/*
	 * Takes in an ActionListener and adds it to the new user button
	 */
	
	public void newUserAccount(ActionListener al)
	{
		btnNewUserAccount.addActionListener(al);
	}
	
	/*
	 * (non-Javadoc)
	 * @see viewController.PanelView#refreshView()
	 */
	
	public void refreshView()
	{
	}
	
	

}
