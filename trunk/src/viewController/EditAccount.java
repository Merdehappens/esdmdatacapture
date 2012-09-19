package viewController;

import javax.swing.JButton;

import system.individuals.Child;
import system.individuals.ChildObjective;
import system.individuals.Guardian;
import system.marking.Objective;
import system.model.ESDMModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	//private Child child;
	private JLabel txtId;
	private JButton btnCancel;
	private JButton btnSave;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblPhoneNumber;
	private JTextField txtPhoneNumber;
	private JLabel lblEmailAddress;
	private JTextField txtEmailAddress;
	private JLabel lblCurrentPassword;
	private JTextField txtCurrentPassword;
	private JLabel lblMarksLogged;
	private JLabel lblNewPassword;
	private JTextField txtNewPassword;
	private JLabel lblRetypeNewPassword;
	private JButton btnReset;
	private JScrollPane scrlTable;
	private MyJTable tblMarks;
	private DefaultTableModel tableModel;
	private JTextField txtRetypeNewPassword;

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
		lblUserName.setBounds(10, 86, 66, 30);
		add(lblUserName);

		btnSave = new JButton("Save");

		btnSave.setBounds(10, 442, 89, 30);
		add(btnSave);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshView();
			}
		});
		btnReset.setBounds(109, 442, 105, 30);
		add(btnReset);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(224, 442, 105, 30);
		add(btnCancel);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 53, 46, 30);
		add(lblId);

		txtId = new JLabel("");
		txtId.setBounds(31, 53, 222, 30);
		add(txtId);
		
		scrlTable = new JScrollPane();
		scrlTable.setBounds(379, 157, 610, 316);
		add(scrlTable);
		
		// Creates a new JTable
		tblMarks = new MyJTable();		
		
		// Sets the name of the columns
		tableModel = new DefaultTableModel();
		tblMarks.setModel(tableModel);
		scrlTable.setViewportView(tblMarks);
		String[] columnNames = new String[] { "Child", "Time/Date", "Step", "Mark", "Comment" };
		tableModel.setColumnIdentifiers(columnNames);
		
		JLabel txtUserName = new JLabel("");
		txtUserName.setBounds(76, 86, 208, 30);
		add(txtUserName);
		
		JLabel lblAccountType = new JLabel("Account Type:");
		lblAccountType.setBounds(10, 119, 89, 30);
		add(lblAccountType);
		
		JLabel txtAccountType = new JLabel("");
		txtAccountType.setBounds(91, 119, 216, 30);
		add(txtAccountType);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(10, 152, 46, 30);
		add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(51, 153, 278, 26);
		add(txtName);
		txtName.setColumns(10);
		
		lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(10, 185, 89, 30);
		add(lblPhoneNumber);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(101, 186, 228, 26);
		add(txtPhoneNumber);
		
		lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setBounds(10, 218, 89, 30);
		add(lblEmailAddress);
		
		txtEmailAddress = new JTextField();
		txtEmailAddress.setColumns(10);
		txtEmailAddress.setBounds(101, 223, 228, 26);
		add(txtEmailAddress);
		
		lblCurrentPassword = new JLabel("Current Password:");
		lblCurrentPassword.setBounds(10, 251, 112, 30);
		add(lblCurrentPassword);
		
		txtCurrentPassword = new JTextField();
		txtCurrentPassword.setColumns(10);
		txtCurrentPassword.setBounds(10, 275, 319, 26);
		add(txtCurrentPassword);
		
		lblMarksLogged = new JLabel("Marks Logged:");
		lblMarksLogged.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMarksLogged.setBounds(633, 126, 121, 20);
		add(lblMarksLogged);
		
		lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setBounds(10, 305, 112, 30);
		add(lblNewPassword);
		
		txtNewPassword = new JTextField();
		txtNewPassword.setColumns(10);
		txtNewPassword.setBounds(10, 329, 319, 26);
		add(txtNewPassword);
		
		lblRetypeNewPassword = new JLabel("Re-type New Password:");
		lblRetypeNewPassword.setBounds(10, 359, 148, 30);
		add(lblRetypeNewPassword);
		
		txtRetypeNewPassword = new JTextField();
		txtRetypeNewPassword.setColumns(10);
		txtRetypeNewPassword.setBounds(10, 383, 319, 26);
		add(txtRetypeNewPassword);
		
		/*txtTest_1 = new JTextField();
		txtTest_1.setColumns(10);
		txtTest_1.setBounds(10, 383, 319, 26);
		add(txtTest_1);
		String[] guardianColIdentifiers = {"Name", "Phone Number", "Email Address"};
		tblGuardianModel.setColumnIdentifiers(guardianColIdentifiers);*/
	}

	// Takes in an ActionListener and adds it to the Save Child button
	public void saveAccountListener(ActionListener al) {
		btnSave.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the Cancel button
	public void cancelListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}
	
	/*public Objective getSelectedObjective() throws Exception
	{
		if(tblObjective.getSelectedRow() == -1)
		{
			throw new Exception("10001: There is no row selected");
		}
		return (Objective)tblObjectiveModel.getValueAt(tblObjective.getSelectedRow(), 0);
		
	}*/

	/*public void setId(int childId) {
		try {
			setChild(this.getModel().viewChild(childId));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}*/

	/*public Child getChild() {
		return child;
	}*/

	/*public void setChild(Child child) {
		this.child = child;
		refreshView();
	}*/

	/*public Object[] getInformation() {
		Object[] temp = new Object[3];
		temp[0] = txtName.getText();
		temp[1] = dobChooser.getCalendar();
		temp[2] = dateJoinedChooser.getCalendar();

		return temp;
	}*/

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		/*dobChooser.setCalendar(child.getDob());
		dateJoinedChooser.setCalendar(child.getDateJoined());
		txtId.setText(child.getId()+"");
		txtName.setText(child.getName());
		
		populateTable();*/
	}

	/*private void populateTable() {
		ArrayList<Guardian> guardians = new ArrayList<Guardian>(child.getGuardians());
		
		while(tblGuardianModel.getRowCount() > 0)
		{
			tblGuardianModel.removeRow(0);
		}
		while(tblObjectiveModel.getRowCount() > 0)
		{
			tblObjectiveModel.removeRow(0);
		}
		
		int size = guardians.size();
		for(int i = 0; i < size; i++)
		{
			Guardian g = guardians.get(i);
			
			Object[] temp = new Object[3];
			temp[0] = g.getName();
			temp[1] = g.getPhoneNo();
			temp[2] = g.getEmailAddress();
			
			tblGuardianModel.addRow(temp);
		}
		
		ArrayList<ChildObjective> obj = new ArrayList<ChildObjective>(child.getChildObjectives());
		size = obj.size();
		for(int i = 0; i < size; i++)
		{
			Object[] o = new Object[4];
			
			ChildObjective co = obj.get(i);
			Objective objective = co.getObjective();
			
			o[0] = objective;
			o[1] = objective.getType();
			o[2] = co.getStep().getNo();
			o[3] = co.getMastered();
			
			tblObjectiveModel.addRow(o);
		}
		
	}*/
}
