package viewController;


import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

import system.model.ESDMModel;

import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

//testing

public class NewUserAccount extends PanelView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2762847607634692026L;
	private JButton btnSubmit;
	private JButton btnCancel;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JTextField txtPhone;


	public NewUserAccount() {
		initialise();

	}

	public NewUserAccount(ESDMModel model)
	{
		super(model);
		initialise();
	}


	public void initialise()
	{
		setLayout(null);

		JLabel lblTitle = new JLabel("New User Account");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 25, 504, 34);
		add(lblTitle);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 359, 139, 30);
		add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(160, 359, 139, 30);
		add(btnCancel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 70, 56, 30);
		add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(63, 70, 236, 30);
		add(txtName);
		txtName.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 110, 56, 30);
		add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(73, 110, 226, 30);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setBounds(10, 150, 76, 30);
		add(lblEmailAddress);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(83, 150, 218, 30);
		add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(10, 190, 76, 30);
		add(lblPhoneNumber);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(93, 190, 206, 30);
		add(txtPhone);
		txtPhone.setColumns(10);

	}
	
	public void submit(ActionListener al)
	{
		btnSubmit.addActionListener(al);
	}
	
	public void cancel(ActionListener al)
	{
		btnCancel.addActionListener(al);
	}

	public String getUsersName() {
		return txtName.getText();
	}

	public String getUsername() {
		return txtUsername.getText();
	}

	public String getEmailAddress() {
		return txtEmail.getText();
	}

	public String getPhoneNo() {
		return txtPhone.getText();
	}
	
}
