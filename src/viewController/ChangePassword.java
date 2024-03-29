package viewController;

import javax.swing.JLabel;
import javax.swing.JButton;

import system.model.ESDMModel;

import java.awt.event.ActionListener;
import javax.swing.JPasswordField;

//testing

public class ChangePassword extends PanelView {

	private static final long serialVersionUID = -2762847607634692026L;
	private JPasswordField txtOld;
	private JPasswordField txtNew;
	private JPasswordField txtConfirm;
	private JButton btnChangePassword;
	private JButton btnCancel;

	public ChangePassword() {
		super();
		initialise();
	}

	public ChangePassword(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	public void initialise() {
		setLayout(null);

		super.setTitle("Change Password");

		JLabel lblOldPassword = new JLabel("Old Password:");
		lblOldPassword.setBounds(10, 77, 114, 30);
		add(lblOldPassword);

		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setBounds(10, 118, 114, 30);
		add(lblNewPassword);

		JLabel lblConfirmNewPassword = new JLabel("Confirm New Password:");
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

	// Takes in an ActionListener and adds it to the change password button

	public void changePassword(ActionListener al) {
		btnChangePassword.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the cancel button

	public void cancel(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	@SuppressWarnings("deprecation")
	public boolean newPasswordMatch() {
		return txtNew.getText().equals(txtConfirm.getText());
	}

	@SuppressWarnings("deprecation")
	public String[] getNewPassword() {
		String[] temp = new String[2];
		temp[0] = txtNew.getText();
		temp[1] = txtConfirm.getText();

		return temp;
	}

	@SuppressWarnings("deprecation")
	public String getOldPassword() {
		return txtOld.getText();
	}

	public void resetText() {
		txtNew.setText("");
		txtOld.setText("");
		txtConfirm.setText("");
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		resetText();
	}

}
