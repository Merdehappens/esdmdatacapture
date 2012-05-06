package viewController;


import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

import systemModel.ESDMModel;

import java.awt.event.ActionListener;

public class AccountView extends PanelView {



	private JButton btnChangePassword;
	private JButton btnChangeEmailAddress;

	public AccountView() {
		initialise();

	}

	public AccountView(ESDMModel model)
	{
		super(model);
		initialise();
	}


	public void initialise()
	{
		setLayout(null);

		JLabel lblTitle = new JLabel("Account Management");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 25, 504, 34);
		add(lblTitle);

		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(20, 70, 200, 30);
		add(btnChangePassword);

		btnChangeEmailAddress = new JButton("Change Email Address");
		btnChangeEmailAddress.setBounds(20, 115, 200, 30);
		add(btnChangeEmailAddress);

	}
	
	public void changePassword(ActionListener al)
	{
		btnChangePassword.addActionListener(al);
	}
	
	public void changeEmailAddress(ActionListener al)
	{
		btnChangeEmailAddress.addActionListener(al);
	}
	
	

}
