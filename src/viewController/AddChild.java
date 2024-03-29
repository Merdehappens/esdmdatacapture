package viewController;

import javax.swing.JButton;

import system.model.ESDMModel;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.awt.BorderLayout;

public class AddChild extends PanelView {

	private static final long serialVersionUID = 5863857435197571726L;
	private JTextField txtName;
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JDateChooser dateJoinedChooser;
	private JDateChooser dobChooser;


	public AddChild() {
		super();
		initialise();

	}

	public AddChild(ESDMModel model) {
		super(model);
		initialise();

	}

	// Initialises all the graphical components on the page.

	private void initialise() {

		setLayout(null);
		// Sets the title of the screen
		super.setTitle("Add New Child");

		// Adds labels to the screen
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 66, 46, 30);
		add(lblName);

		// Adds text fields to screen
		txtName = new JTextField();
		txtName.setBounds(43, 66, 357, 30);
		add(txtName);
		txtName.setColumns(10);

		dobChooser = new JDateChooser();
		dobChooser.setBounds(10, 107, 390, 30);
		add(dobChooser);
		
				JLabel lblDateOfBirth = new JLabel("Date Of Birth");
				dobChooser.add(lblDateOfBirth, BorderLayout.WEST);

		dateJoinedChooser = new JDateChooser();
		dateJoinedChooser.setBounds(10, 146, 390, 30);
		add(dateJoinedChooser);
		
				JLabel lblDateJoined = new JLabel("Date Joined");
				dateJoinedChooser.add(lblDateJoined, BorderLayout.WEST);
		
		// Adds submit button to screen
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 241, 89, 30);
		add(btnSubmit);

		// Creates a reset button and adds an action listener to it. that when clicked it resets all fields.
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshView();
			}
		});
		btnReset.setBounds(109, 242, 105, 29);
		add(btnReset);

		// Creates and adds a cancel button to screen
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(224, 242, 94, 29);
		add(btnCancel);
		


	}

	// Returns the text inside the text box for name
	public String getChildName() {
		return txtName.getText();
	}

	// Returns the DOB that is chosen by the date chooser
	public Calendar getDob() {
		return dobChooser.getCalendar();
	}

	// Returns the Date joined that is chosen by the date chooser
	public Calendar getDateJoined() {
		return dateJoinedChooser.getCalendar();
	}

	// Takes in an ActionListener and adds it to the submit button
	public void submitListener(ActionListener al) {
		btnSubmit.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the cancel button
	public void cancelListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	// Resets all the text fields to default
	public void resetTextField() {
		txtName.setText("");
		Date tempDate = null;
		dateJoinedChooser.setDate(tempDate);
		dobChooser.setDate(tempDate);
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		resetTextField();
	}
}
