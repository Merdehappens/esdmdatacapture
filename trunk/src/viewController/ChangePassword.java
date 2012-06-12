	package viewController;


import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

import system.model.ESDMModel;

import java.awt.event.ActionListener;
import javax.swing.JPasswordField;

//testing

public class ChangePassword extends PanelView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2762847607634692026L;
	private JPasswordField txtOld;
	private JPasswordField txtNew;
	private JPasswordField txtConfirm;
	private JButton btnChangePassword;
	private JButton btnCancel;


	public ChangePassword() {
		initialise();

	}

	public ChangePassword(ESDMModel model)
	{
		super(model);
		initialise();
	}


	public void initialise()
	{
		setLayout(null);

		JLabel lblTitle = new JLabel("Change Password");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 25, 504, 34);
		add(lblTitle);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setBounds(10, 77, 114, 30);
		add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setBounds(10, 118, 114, 30);
		add(lblNewPassword);
		
		JLabel lblConfirmNewPassword = new JLabel("Confirm New Password");
		lblConfirmNewPassword.setBounds(10, 159, 114, 30);
		add(lblConfirmNewPassword);
		
		txtOld = new JPasswordField();
		txtOld.setBounds(134, 77, 209, 30);
		add(txtOld);
		
		txtNew = new JPasswordField();
		txtNew.setBounds(134, 118, 209, 30);
		add(txtNew);
		
		txtConfirm = new JPasswordField();
		txtConfirm.setBounds(134, 159, 209, 30);
		add(txtConfirm);
		
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(10, 230, 139, 30);
		add(btnChangePassword);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(160, 230, 139, 30);
		add(btnCancel);

	}
	
	public void changePassword(ActionListener al)
	{
		btnChangePassword.addActionListener(al);
	}
	
	public void cancel(ActionListener al)
	{
		btnCancel.addActionListener(al);
	}
	
	@SuppressWarnings("deprecation")
	public boolean newPasswordMatch()
	{
		return txtNew.getText().equals(txtConfirm.getText());
	}
	
	@SuppressWarnings("deprecation")
	public String[] getNewPassword()
	{
		String[] temp = new String[2];
		temp[0] = txtNew.getText();
		temp[1] = txtConfirm.getText();
		
		return temp;
	}

	@SuppressWarnings("deprecation")
	public String getOldPassword() {
		return txtOld.getText();
	}
	
}
