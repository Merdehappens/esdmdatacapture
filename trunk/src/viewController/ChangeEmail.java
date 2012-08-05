package viewController;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

import system.model.ESDMModel;

import java.awt.event.ActionListener;

public class ChangeEmail extends PanelView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9133299148971810165L;
	private JTextField txtNewEmailAddress;
	private JButton btnChangeEmail;
	private JButton btnCancel;


	public ChangeEmail() {
		initialise();

	}

	public ChangeEmail(ESDMModel model)
	{
		super(model);
		initialise();
	}


	public void initialise()
	{
		setLayout(null);

		super.setTitle("Change Email");
		
		JLabel lblOldPassword = new JLabel("New Email Address:");
		lblOldPassword.setBounds(10, 77, 114, 30);
		add(lblOldPassword);
		
		txtNewEmailAddress = new JTextField();
		txtNewEmailAddress.setBounds(134, 77, 209, 30);
		add(txtNewEmailAddress);
		
		btnChangeEmail = new JButton("Change Email");
		btnChangeEmail.setBounds(10, 230, 139, 30);
		add(btnChangeEmail);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(160, 230, 139, 30);
		add(btnCancel);

	}
	
	public void changeEmail(ActionListener al)
	{
		btnChangeEmail.addActionListener(al);
	}
	
	public void cancel(ActionListener al)
	{
		btnCancel.addActionListener(al);
	}

	public String getEmail() {
		return txtNewEmailAddress.getText();
	}
	
	public void refreshView(){
		txtNewEmailAddress.setText("");
	}
	

	
}
