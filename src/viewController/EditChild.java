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

public class EditChild extends PanelView {

	// test

	/**
	 * 
	 */
	private static final long serialVersionUID = -3129950176718443795L;
	private JTextField txtName;
	private Child child;
	private JDateChooser dateJoinedChooser;
	private JDateChooser dobChooser;
	private JLabel txtId;
	private JButton btnCancel;
	private JButton btnSave;
	private MyJTable tblGuardian;
	private DefaultTableModel tblGuardianModel;
	private MyJTable tblObjective;
	private DefaultTableModel tblObjectiveModel;
	private JButton btnSetMastered;
	private JButton btnRemoveObjective;
	private JButton btnIncrementStep;
	private JButton btnDecrementStep;
	private JButton btnAddObjective;
	private JButton btnAddExistingGuardian;
	private JButton btnAddNewGuardian;
	private JButton btnRemoveGuardian;
	private ArrayList<Guardian> guardians;

	/**
	 * Create the panel.
	 */
	public EditChild() {
		super();
		initialise();

	}

	public EditChild(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {
		setLayout(null);

		super.setTitle("Edit Child");

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 66, 46, 30);
		add(lblName);

		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setBounds(10, 107, 73, 30);
		add(lblDateOfBirth);

		JLabel lblDateJoined = new JLabel("Date Joined:");
		lblDateJoined.setBounds(10, 148, 73, 30);
		add(lblDateJoined);

		txtName = new JTextField();
		txtName.setBounds(66, 63, 331, 30);
		add(txtName);
		txtName.setColumns(10);

		dobChooser = new JDateChooser();
		dobChooser.setDateFormatString("dd/MM/yyyy");
		dobChooser.setBounds(87, 107, 310, 30);
		add(dobChooser);

		dateJoinedChooser = new JDateChooser();
		dateJoinedChooser.setDateFormatString("dd/MM/yyyy");
		dateJoinedChooser.setBounds(87, 148, 310, 30);
		add(dateJoinedChooser);

		btnSave = new JButton("Save");

		btnSave.setBounds(46, 389, 89, 30);
		add(btnSave);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshView();
			}
		});
		btnReset.setBounds(145, 389, 105, 30);
		add(btnReset);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(260, 389, 105, 30);
		add(btnCancel);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 33, 46, 30);
		add(lblId);

		txtId = new JLabel("");
		txtId.setBounds(40, 33, 216, 30);
		add(txtId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 189, 387, 150);
		add(scrollPane);
		
		tblGuardian = new MyJTable();
		tblGuardianModel = new DefaultTableModel();
		tblGuardian.setModel(tblGuardianModel);
		scrollPane.setViewportView(tblGuardian);
		
		JScrollPane scrlTable = new JScrollPane();
		scrlTable.setBounds(411, 63, 426, 316);
		add(scrlTable);
		
		tblObjective = new MyJTable();
		scrlTable.setViewportView(tblObjective);
		
		tblObjectiveModel = new DefaultTableModel();
		tblObjective.setModel(tblObjectiveModel);
		
		
		String[] colIdentifiers = {"Objective", "Type", "Step", "Mastered?"};
		tblObjectiveModel.setColumnIdentifiers(colIdentifiers);
		
		tblObjective.getColumnModel().getColumn(0).setPreferredWidth(250);
		tblObjective.getColumnModel().getColumn(1).setPreferredWidth(120);
		tblObjective.getColumnModel().getColumn(2).setPreferredWidth(50);
		tblObjective.getColumnModel().getColumn(3).setPreferredWidth(70);
		
		btnAddObjective = new JButton("Add Objective");
		btnAddObjective.setBounds(857, 66, 132, 30);
		add(btnAddObjective);

		
		btnSetMastered = new JButton("Set Mastered");
		btnSetMastered.setBounds(857, 152, 132, 30);
		add(btnSetMastered);
		
		btnRemoveObjective = new JButton("Remove Objective");
		btnRemoveObjective.setBounds(857, 111, 132, 30);
		add(btnRemoveObjective);
		
		btnIncrementStep = new JButton("Increment Step");
		btnIncrementStep.setBounds(857, 273, 132, 30);
		add(btnIncrementStep);
		
		btnDecrementStep = new JButton("Decrement Step");
		btnDecrementStep.setBounds(857, 316, 132, 30);
		add(btnDecrementStep);
		
		btnAddExistingGuardian = new JButton("<HTML><FONT SIZE=\"2\">Add Existing Guardian</FONT></HTML>");
		btnAddExistingGuardian.setBounds(10, 350, 140, 30);
		add(btnAddExistingGuardian);
		
		btnAddNewGuardian = new JButton("<HTML><FONT SIZE=\"2\">Add New Guardian</FONT></HTML>");
		btnAddNewGuardian.setBounds(150, 350, 119, 30);
		add(btnAddNewGuardian);
		
		btnRemoveGuardian = new JButton("<HTML><FONT SIZE=\"2\">Remove Guardian</FONT></HTML>");
		btnRemoveGuardian.setBounds(270, 350, 127, 30);
		add(btnRemoveGuardian);
		String[] guardianColIdentifiers = {"Name", "Phone Number", "Email Address"};
		tblGuardianModel.setColumnIdentifiers(guardianColIdentifiers);
	}
	
	// Takes in an ActionListener and adds it to the Add New Guardian button
	public void addNewGuardianListener(ActionListener al) {
		btnAddNewGuardian.addActionListener(al);
	}
	
	// Takes in an ActionListener and adds it to the Add Guardian button
	public void addExistingGuardianListener(ActionListener al) {
		btnAddExistingGuardian.addActionListener(al);
	}
	
	// Takes in an ActionListener and adds it to the Remove Guardian button
	public void removeGuardianListener(ActionListener al) {
		btnRemoveGuardian.addActionListener(al);
	}	

	// Takes in an ActionListener and adds it to the Save Child button
	public void saveChildListener(ActionListener al) {
		btnSave.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the Cancel button
	public void cancelListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}
	
	// Takes in an ActionListener and adds it to the remove objective button
	public void removeObjectiveListener(ActionListener al) {
		btnRemoveObjective.addActionListener(al);
	}
	
	// Takes in an ActionListener and adds it to the increment step button
	public void incrementStepListener(ActionListener al) {
		btnIncrementStep.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the decrement step button
	public void decrementStepListener(ActionListener al) {
		btnDecrementStep.addActionListener(al);
	}
	
	// Takes in an ActionListener and adds it to the add objective button
	public void addObjectiveListener(ActionListener al) {
		btnAddObjective.addActionListener(al);
	}
	
	// Takes in an ActionListener and adds it to the Set Mastered button
	public void setMasteredListener(ActionListener al) {
		btnSetMastered.addActionListener(al);
	}
	
	public Objective getSelectedObjective() throws Exception
	{
		if(tblObjective.getSelectedRow() == -1)
		{
			throw new Exception("30010: There is no row selected");
		}
		return (Objective)tblObjectiveModel.getValueAt(tblObjective.getSelectedRow(), 0);
		
	}

	public void setId(int childId) {
		try {
			setChild(this.getModel().viewChild(childId));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public Child getChild() {
		return child;
	}

	public void setChild(Child child) {
		this.child = child;
		refreshView();
	}

	public Object[] getInformation() {
		Object[] temp = new Object[3];
		temp[0] = txtName.getText();
		temp[1] = dobChooser.getCalendar();
		temp[2] = dateJoinedChooser.getCalendar();

		return temp;
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		dobChooser.setCalendar(child.getDob());
		dateJoinedChooser.setCalendar(child.getDateJoined());
		txtId.setText(child.getId()+"");
		txtName.setText(child.getName());
		
		populateTable();
	}

	private void populateTable() {
		guardians = new ArrayList<Guardian>(child.getGuardians());
		
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
		
	}

	public Guardian getSelectedGuardian() {
		Guardian g = guardians.get(tblGuardian.getSelectedRow());
		return g;
	}
}
