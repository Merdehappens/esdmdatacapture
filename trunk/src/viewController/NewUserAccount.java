package viewController;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JButton;

import system.model.ESDMModel;

import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JRadioButton;


public class NewUserAccount extends PanelView {


	private static final long serialVersionUID = -2762847607634692026L;
	private JButton btnSubmit;
	private JButton btnCancel;
	private JTextField txtName;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JRadioButton rdbtnAdminTherapist;
	private JRadioButton rdbtnGuardian;
	private JRadioButton rdbtnTherapist;

	public NewUserAccount() {
		initialise();
	}

	public NewUserAccount(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	public void initialise() {
		setLayout(null);

		super.setTitle("New Account");

		// Creates and adds 2 new buttons to panel
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 359, 139, 30);
		add(btnSubmit);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(160, 359, 139, 30);
		add(btnCancel);

		// creates and adds labels & Text fields to the panel
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 70, 73, 30);
		add(lblName);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 110, 86, 30);
		add(lblUsername);
		
		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setBounds(10, 150, 94, 30);
		add(lblEmailAddress);

		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(10, 190, 110, 30);
		add(lblPhoneNumber);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(106, 110, 252, 30);
		add(txtUsername);
		txtUsername.setColumns(10);

		txtName = new JTextField();
		txtName.setBounds(93, 70, 265, 30);
		add(txtName);
		txtName.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(116, 150, 242, 30);
		add(txtEmail);
		txtEmail.setColumns(10);

		txtPhone = new JTextField();
		txtPhone.setBounds(116, 191, 242, 30);
		add(txtPhone);
		txtPhone.setColumns(10);
		
		rdbtnTherapist = new JRadioButton("Therapist");
		rdbtnTherapist.setBounds(115, 249, 109, 23);
		add(rdbtnTherapist);
		
		rdbtnGuardian = new JRadioButton("Guardian");
		rdbtnGuardian.setBounds(10, 249, 109, 23);
		add(rdbtnGuardian);
		
		rdbtnAdminTherapist = new JRadioButton("Admin Therapist");
		rdbtnAdminTherapist.setBounds(226, 249, 109, 23);
		add(rdbtnAdminTherapist);
		
		ButtonGroup b = new ButtonGroup();
		b.add(rdbtnGuardian);
		b.add(rdbtnTherapist);
		b.add(rdbtnAdminTherapist);

	}
	
	public String getSelectedAccess()
	{
		if(rdbtnAdminTherapist.isSelected())
		{
			return "a";
		}
		else if(rdbtnTherapist.isSelected())
		{
			return "n";
		}
		else
		{
			return "g";
		}
		
	}

	// Takes in an ActionListener and adds it to the submit button
	public void submit(ActionListener al) {
		btnSubmit.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the cancel button
	public void cancel(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	// Returns the value in the name text field
	public String getUsersName() {
		return txtName.getText();
	}

	// Returns the value in the username text field
	public String getUsername() {
		return txtUsername.getText();
	}

	// Returns the value in the email text field
	public String getEmailAddress() {
		return txtEmail.getText();
	}

	// Returns the value in the phone text field
	public String getPhoneNo() {
		return txtPhone.getText();
	}

	// Resets all text fields to empty
	public void resetTextFields() {
		txtName.setText("");
		txtUsername.setText("");
		txtEmail.setText("");
		txtPhone.setText("");
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		resetTextFields();
	}
}
