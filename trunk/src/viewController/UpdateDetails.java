package viewController;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import system.model.ESDMModel;

import java.awt.event.ActionListener;

public class UpdateDetails extends PanelView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9133299148971810165L;
	private JTextField txtNewEmailAddress;
	private JButton btnSave;
	private JButton btnCancel;

	public UpdateDetails() {
		initialise();

	}

	public UpdateDetails(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	public void initialise() {
		setLayout(null);

		super.setTitle("Update Details");

		JLabel lblEmail = new JLabel("Email Address:");
		lblEmail.setBounds(352, 77, 114, 30);
		add(lblEmail);

		txtNewEmailAddress = new JTextField();
		txtNewEmailAddress.setBounds(462, 77, 209, 30);
		add(txtNewEmailAddress);

		btnSave = new JButton("Save");
		btnSave.setBounds(352, 171, 139, 30);
		add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(532, 171, 139, 30);
		add(btnCancel);

	}

	// Takes in an ActionListener and adds it to the change Email button

	public void saveListener(ActionListener al) {
		btnSave.addActionListener(al);
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
