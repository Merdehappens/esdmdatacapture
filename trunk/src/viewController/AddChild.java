package viewController;

import javax.swing.JButton;

import systemModel.Child;
import systemModel.ESDMModel;
import systemModel.Guardian;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;

public class AddChild extends PanelView {
	private JTextField txtName;
	private JTextField txtGuardianName;
	private JTextField txtGuardianPhone;
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JDateChooser dateJoinedChooser;
	private JDateChooser dobChooser;

	/**
	 * Create the panel.
	 */
	public AddChild() {
		initialise();
	}
	
	public AddChild(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Add New Child");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 430, 21);
		add(lblTitle);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 40, 46, 30);
		add(lblName);
		
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setBounds(10, 81, 73, 30);
		add(lblDateOfBirth);
		
		JLabel lblDateJoined = new JLabel("Date Joined");
		lblDateJoined.setBounds(10, 120, 73, 30);
		add(lblDateJoined);
		
		JLabel lblGuardianName = new JLabel("Guardian Name");
		lblGuardianName.setBounds(10, 161, 120, 30);
		add(lblGuardianName);
		
		JLabel lblGuardianPhoneNumber = new JLabel("Guardian Phone Number:");
		lblGuardianPhoneNumber.setBounds(10, 202, 120, 30);
		add(lblGuardianPhoneNumber);
		
		txtName = new JTextField();
		txtName.setBounds(66, 40, 271, 30);
		add(txtName);
		txtName.setColumns(10);
		
		txtGuardianName = new JTextField();
		txtGuardianName.setBounds(103, 161, 234, 30);
		add(txtGuardianName);
		txtGuardianName.setColumns(10);
		
		txtGuardianPhone = new JTextField();
		txtGuardianPhone.setBounds(140, 202, 197, 30);
		add(txtGuardianPhone);
		txtGuardianPhone.setColumns(10);
		
		
		dobChooser = new JDateChooser();
		dobChooser.setBounds(87, 81, 250, 30);
		add(dobChooser);
		
		dateJoinedChooser = new JDateChooser();
		dateJoinedChooser.setBounds(93, 120, 244, 30);
		add(dateJoinedChooser);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 263, 89, 23);
		add(btnSubmit);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTextField();
			}
		});
		btnReset.setBounds(109, 263, 105, 21);
		add(btnReset);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(224, 263, 94, 21);
		add(btnCancel);
	}
	
	public Child addChild()
	{
		return super.getModel().addChild(txtName.getText(), dobChooser.getDate(), new Guardian(txtGuardianName.getText(), txtGuardianPhone.getText()));
		
		//testing
	}
	
	
	public void submitListener(ActionListener al)
	{
		btnSubmit.addActionListener(al);	
	}

	
	public void cancelListener(ActionListener al)
	{
		btnCancel.addActionListener(al);	
	}
	
	private void resetTextField()
	{
		txtName.setText("");
		txtGuardianName.setText("");
		txtGuardianPhone.setText("");
		Date tempDate = null;
		dateJoinedChooser.setDate(tempDate);
		dobChooser.setDate(tempDate);
	}
	
}
