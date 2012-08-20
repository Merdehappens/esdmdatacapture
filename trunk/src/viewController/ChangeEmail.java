package viewController;

import javax.swing.JLabel;
import javax.swing.JTextField;
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

	public ChangeEmail(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	public void initialise() {
		setLayout(null);

		super.setTitle("Change Email");

		JLabel lblOldPassword = new JLabel("New Email Address:");
		lblOldPassword.setBounds(352, 77, 114, 30);
		add(lblOldPassword);

		txtNewEmailAddress = new JTextField();
		txtNewEmailAddress.setBounds(462, 77, 209, 30);
		add(txtNewEmailAddress);

		btnChangeEmail = new JButton("Change Email");
		btnChangeEmail.setBounds(352, 171, 139, 30);
		add(btnChangeEmail);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(532, 171, 139, 30);
		add(btnCancel);

	}

	// Takes in an ActionListener and adds it to the change Email button

	public void changeEmail(ActionListener al) {
		btnChangeEmail.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the cancel button

	public void cancel(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	public String getEmail() {
		return txtNewEmailAddress.getText();
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		txtNewEmailAddress.setText("");
	}

}