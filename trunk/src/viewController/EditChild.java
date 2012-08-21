package viewController;

import javax.swing.JButton;

import system.individuals.Child;
import system.individuals.Guardian;
import system.model.ESDMModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
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
	private JTable table;
	private JTable tblGuardian;
	private DefaultTableModel tblGuardianModel;
	private JLabel lblPicture;

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
		txtName.setBounds(66, 63, 205, 30);
		add(txtName);
		txtName.setColumns(10);

		dobChooser = new JDateChooser();
		dobChooser.setBounds(87, 107, 184, 30);
		add(dobChooser);

		dateJoinedChooser = new JDateChooser();
		dateJoinedChooser.setBounds(87, 148, 184, 30);
		add(dateJoinedChooser);

		btnSave = new JButton("Save");

		btnSave.setBounds(10, 363, 89, 23);
		add(btnSave);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshView();
			}
		});
		btnReset.setBounds(109, 363, 105, 21);
		add(btnReset);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(224, 363, 94, 21);
		add(btnCancel);

		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 33, 46, 30);
		add(lblId);

		txtId = new JLabel("");
		txtId.setBounds(40, 33, 216, 30);
		add(txtId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 189, 319, 150);
		add(scrollPane);
		
		tblGuardian = new JTable();
		tblGuardianModel = new DefaultTableModel();
		tblGuardian.setModel(tblGuardianModel);
		scrollPane.setViewportView(tblGuardian);
		
		lblPicture = new JLabel("");
		lblPicture.setBounds(369, 66, 189, 129);
		add(lblPicture);
		String[] colIdentifiers = {"Name", "Phone Number", "Email Address"};
		tblGuardianModel.setColumnIdentifiers(colIdentifiers);
	}

	// Takes in an ActionListener and adds it to the Save Child button
	public void saveChildListener(ActionListener al) {
		btnSave.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the Cancel button
	public void cancelListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	public void setId(String childId) {
		try {
			setChild(this.getModel().viewChild(childId));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
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
		txtId.setText(child.getId());
		txtName.setText(child.getName());

		
		lblPicture.setIcon(child.getPicture());
		
		populateTable();
		System.out.println(child.getGuardians().size());
	}

	private void populateTable() {
		ArrayList<Guardian> guardians = new ArrayList<Guardian>(child.getGuardians());
		
		while(tblGuardianModel.getRowCount() > 0)
		{
			tblGuardianModel.removeRow(0);
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
	}
}
