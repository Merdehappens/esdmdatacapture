package viewController;

import javax.swing.JButton;

import system.individuals.Child;
import system.individuals.ChildObjective;
import system.individuals.Guardian;
import system.individuals.UserAccount;
import system.marking.Objective;
import system.model.ESDMModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class EditAccount extends PanelView {

	// test

	/**
	 * 
	 */
	private static final long serialVersionUID = -3129950176718443795L;
	private JButton btnCancel;
	private JButton btnSave;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblPhoneNumber;
	private JTextField txtPhoneNumber;
	private JLabel lblEmailAddress;
	private JTextField txtEmailAddress;
	private JButton btnReset;
	private UserAccount user;
	private JButton btnChangePassword;
	private JLabel txtUserName;
	private JLabel txtAccountType;

	/**
	 * Create the panel.
	 */
	public EditAccount() {
		super();
		initialise();

	}

	public EditAccount(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {
		setLayout(null);

		super.setTitle("View/Edit Account");

		JLabel lblUserName = new JLabel("Username:");
		lblUserName.setBounds(339, 159, 66, 30);
		add(lblUserName);

		btnSave = new JButton("Save");

		btnSave.setBounds(339, 352, 89, 30);
		add(btnSave);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshView();
			}
		});
		btnReset.setBounds(438, 352, 105, 30);
		add(btnReset);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(553, 352, 105, 30);
		add(btnCancel);
		

		
		txtUserName = new JLabel("");
		txtUserName.setBounds(450, 159, 208, 30);
		add(txtUserName);
		
		JLabel lblAccountType = new JLabel("Account Type:");
		lblAccountType.setBounds(339, 192, 89, 30);
		add(lblAccountType);
		
		txtAccountType = new JLabel("");
		txtAccountType.setBounds(442, 192, 216, 30);
		add(txtAccountType);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(339, 225, 46, 30);
		add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(380, 226, 278, 26);
		add(txtName);
		txtName.setColumns(10);
		
		lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(339, 258, 89, 30);
		add(lblPhoneNumber);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(430, 259, 228, 26);
		add(txtPhoneNumber);
		
		lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setBounds(339, 291, 89, 30);
		add(lblEmailAddress);
		
		txtEmailAddress = new JTextField();
		txtEmailAddress.setColumns(10);
		txtEmailAddress.setBounds(430, 296, 228, 26);
		add(txtEmailAddress);
		
		
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(450, 411, 208, 48);
		add(btnChangePassword);
		
	}

	// Takes in an ActionListener and adds it to the Save Child button
	public void saveAccountListener(ActionListener al) {
		btnSave.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the Cancel button
	public void cancelListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		String type;
		if(user.getAccess().equals("a"))
		{
			type = "Admin";
		} else if(user.getAccess().equals("n"))	{
			type = "Therapist";
		} else {
			type = "Guardian";
		}
		txtAccountType.setText(type);
		txtUserName.setText(user.getUsername());
		txtPhoneNumber.setText(user.getPhoneNo());
		txtName.setText(user.getName());
		txtEmailAddress.setText(user.getEmailAddress());
		
		if(user == this.getModel().getCurrentUser())
		{
			btnChangePassword.setVisible(true);
		} else
		{
			btnChangePassword.setVisible(false);
		}
		
	}

	public void changePasswordListener(ActionListener al) {
		btnChangePassword.addActionListener(al);
		
	}

	public void setUser(UserAccount selectedAccount) {
		user = selectedAccount;
	}

	public UserAccount getAccount() {
		return user;
	}

	public String getUserName() {
		return txtName.getText();
	}

	public String getPhoneNumber() {
		return txtPhoneNumber.getText();
	}

	public String getEmail() {
		return txtEmailAddress.getText();
	}
}
