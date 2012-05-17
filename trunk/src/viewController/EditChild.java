package viewController;

import javax.swing.JButton;

import system.individuals.Child;
import system.model.ESDMModel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

public class EditChild extends PanelView {
	
	//test
	
	private JTextField txtName;
	private Child child;
	private JToggleButton tglbtnToggleEditable;
	private JDateChooser dateJoinedChooser;
	private JDateChooser dobChooser;
	private JLabel txtId;
	
	/**
	 * Create the panel.
	 */
	public EditChild() {
		setLayout(null);
		

		initialise();
	
	}
	
	public EditChild(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		
		JLabel lblTitle = new JLabel("Edit Child");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 430, 21);
		add(lblTitle);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 66, 46, 30);
		add(lblName);
		
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setBounds(10, 107, 73, 30);
		add(lblDateOfBirth);
		
		JLabel lblDateJoined = new JLabel("Date Joined");
		lblDateJoined.setBounds(10, 148, 73, 30);
		add(lblDateJoined);
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setBounds(66, 63, 205, 30);
		add(txtName);
		txtName.setColumns(10);
		
		dobChooser = new JDateChooser();
		dobChooser.setBounds(87, 107, 184, 30);
		add(dobChooser);
		dobChooser.setEnabled(false);
		
		dateJoinedChooser = new JDateChooser();
		dateJoinedChooser.setBounds(87, 148, 184, 30);
		add(dateJoinedChooser);
		dateJoinedChooser.setEnabled(false);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveChild();
			}
		});
		btnSave.setBounds(10, 189, 89, 23);
		add(btnSave);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetTextFields();
			}
		});
		btnReset.setBounds(109, 189, 105, 21);
		add(btnReset);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(224, 189, 94, 21);
		add(btnCancel);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 33, 46, 30);
		add(lblId);
		
		txtId = new JLabel("");
		txtId.setBounds(40, 33, 216, 30);
		add(txtId);
		
		tglbtnToggleEditable = new JToggleButton("Toggle Editable");
		tglbtnToggleEditable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enableTextFields();
			}
		});
		tglbtnToggleEditable.setBounds(281, 63, 121, 33);
		add(tglbtnToggleEditable);
	}
	
	private void resetTextFields() {
		txtName.setText(child.getName());
		dobChooser.setDate(child.getDob());
		dateJoinedChooser.setDate(child.getDateJoined());
		
	}

	private void enableTextFields() {
		
		if(tglbtnToggleEditable.isSelected())
		{
			txtName.setEnabled(true);
			dobChooser.setEnabled(true);
			dateJoinedChooser.setEnabled(true);
		}
		else
		{
			txtName.setEnabled(false);
			dobChooser.setEnabled(false);
			dateJoinedChooser.setEnabled(false);
		}
	}

	private void saveChild() {
		child.setName(txtName.getText());
		child.setDob(dobChooser.getDate());
		child.setDateJoined(dateJoinedChooser.getDate());
	}

	public void setId(String childId)
	{
		setChild(this.getModel().getChild(childId));
	}
	
	
	public void setChild(Child child)
	{
		this.child = child;
		refreshView();
	}
	
	public void refreshView()
	{
		tglbtnToggleEditable.setSelected(false);
		enableTextFields();
		dobChooser.setDate(child.getDob());
		dateJoinedChooser.setDate(child.getDateJoined());
		txtId.setText(child.getId());
		txtName.setText(child.getName());
	}
	
}
