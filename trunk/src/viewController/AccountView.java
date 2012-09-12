package viewController;

import javax.swing.JButton;

import system.model.ESDMModel;

import java.awt.event.ActionListener;

public class AccountView extends PanelView {


	private static final long serialVersionUID = -7002494511986322397L;
	private JButton btnChangePassword;
	private JButton btnChangeEmailAddress;
	private JButton btnNewUserAccount;
	private JButton btnRooms;
	private JButton btnSetting;
	private JButton btnObjectiveType;
	private JButton btnUserAccounts;

	public AccountView() {
		super();
		initialise();
	}

	public AccountView(ESDMModel model) {
		super(model);
		initialise();
	}

	
	// Initialises all the graphical items on the screen.
	public void initialise() {
		setLayout(null);

		// Adds the title to the top of the screen.

		super.setTitle("Administration");

		// Adds the change password button to the screen
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(20, 70, 200, 40);
		add(btnChangePassword);

		// Adds the change email address button to the screen
		btnChangeEmailAddress = new JButton("Change Email Address");
		btnChangeEmailAddress.setBounds(20, 120, 200, 40);
		add(btnChangeEmailAddress);

		// Adds the New user account button to the screen
		btnNewUserAccount = new JButton("Create New User Account");
		btnNewUserAccount.setBounds(20, 170, 200, 40);
		add(btnNewUserAccount);
		
		btnRooms = new JButton("Rooms");
		btnRooms.setBounds(277, 170, 165, 40);
		add(btnRooms);
		
		btnSetting = new JButton("Settings");
		btnSetting.setBounds(277, 230, 165, 40);
		add(btnSetting);
		
		
		btnObjectiveType = new JButton("Objective Types");
		btnObjectiveType.setBounds(277, 280, 165, 40);
		add(btnObjectiveType);
		/*
		JButton btnViewAccount = new JButton("View Account");
		btnViewAccount.setBounds(20, 221, 200, 40);
		add(btnViewAccount);
		 */
		
		btnUserAccounts = new JButton("User Accounts");
		btnUserAccounts.setBounds(277, 330, 165, 40);
		add(btnUserAccounts);
	}

	// Takes in an ActionListener and adds it to the change password button
	public void changePassword(ActionListener al) {
		btnChangePassword.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the change email address button
	public void changeEmailAddress(ActionListener al) {
		btnChangeEmailAddress.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the new user button
	public void newUserAccount(ActionListener al) {
		btnNewUserAccount.addActionListener(al);
	}
	
	public void roomsListener(ActionListener al) {
		btnRooms.addActionListener(al);
	}
	
	public void settingListener(ActionListener al) {
		btnSetting.addActionListener(al);
	}
	
	public void objectiveTypeListener(ActionListener al) {
		btnObjectiveType.addActionListener(al);
	}
	
	public void userAccountsListener(ActionListener al) {
		btnUserAccounts.addActionListener(al);
	}
	

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
	}

	public void setAccess(String string) {
		if(!string.equals("a"))
		{
			btnNewUserAccount.setEnabled(false);
		}
	}
}
