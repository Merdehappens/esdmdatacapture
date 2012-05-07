package viewController;

import javax.swing.JButton;

import systemModel.Child;
import systemModel.ESDMModel;
import systemModel.Guardian;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AddChild extends PanelView {
	private JTextField txtName;
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JDateChooser dateJoinedChooser;
	private JDateChooser dobChooser;
	private ArrayList<Guardian> guardians;
	private JTable tblGuardian;
	private DefaultTableModel tblGuardianModel;

	/**
	 * Create the panel.
	 */
	public AddChild() {
		initialise();
		guardians = new ArrayList<Guardian>();
	}
	
	public AddChild(ESDMModel model)
	{
		super(model);
		guardians = new ArrayList<Guardian>();
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
		
		txtName = new JTextField();
		txtName.setBounds(66, 40, 271, 30);
		add(txtName);
		txtName.setColumns(10);
		
		
		dobChooser = new JDateChooser();
		dobChooser.setBounds(87, 81, 250, 30);
		add(dobChooser);
		
		dateJoinedChooser = new JDateChooser();
		dateJoinedChooser.setBounds(93, 120, 244, 30);
		add(dateJoinedChooser);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 343, 89, 23);
		add(btnSubmit);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTextField();
			}
		});
		btnReset.setBounds(109, 344, 105, 21);
		add(btnReset);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(224, 344, 94, 21);
		add(btnCancel);
		
		JButton btnAddGuardianAlready = new JButton("Add Guardian already in system");
		btnAddGuardianAlready.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addExistingGuardian();
			}
		});
		btnAddGuardianAlready.setBounds(10, 161, 327, 23);
		add(btnAddGuardianAlready);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 237, 342, 95);
		add(scrollPane);
		
		
		
		
		
		tblGuardian = new JTable();
		scrollPane.setViewportView(tblGuardian);
		String[] columnNames = new String[] {"Guardian", "Name", "Phone Number"};
		
		tblGuardianModel = new DefaultTableModel();

		tblGuardianModel.setColumnIdentifiers(columnNames);
		
		tblGuardian.setModel(tblGuardianModel);
		
		JLabel lblChildsGuardians = new JLabel("Childs Guardians");
		lblChildsGuardians.setHorizontalAlignment(SwingConstants.CENTER);
		lblChildsGuardians.setBounds(10, 212, 342, 14);
		add(lblChildsGuardians);
		
		Object[] tempRow = new Object[3];

		Guardian guardian = new Guardian();
		
		tempRow[0] = guardian;
		tempRow[1] = "String 1";
		tempRow[2] = "String 2";
		
		
		tblGuardianModel.addRow(tempRow);
		
		
		
		
		
		
		
		
	}
	
	private void addExistingGuardian() {


		
	}
	
	private void refreshView()
	{
		tblGuardianModel = new DefaultTableModel();
		
		String[][] temp = new String[guardians.size()][2];
		
		for(int i = 0; i < guardians.size(); i++)
		{
			temp[i][0] = guardians.get(i).getName();
			temp[i][1] = guardians.get(i).getPhoneNo();
			tblGuardianModel.addRow(temp[i]);
		}
		
	}

	public Child addChild()
	{
		return super.getModel().addChild(txtName.getText(), dobChooser.getDate(), guardians);
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
		Date tempDate = null;
		guardians = new ArrayList<Guardian>();
		dateJoinedChooser.setDate(tempDate);
		dobChooser.setDate(tempDate);
	}
}
