package viewController;

import javax.swing.JLabel;
import javax.swing.JButton;

import system.helper.Helper;
import system.individuals.Child;
import system.individuals.Guardian;
import system.individuals.UserAccount;
import system.model.ESDMModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;


public class LinkGuardian extends PanelView {


	private static final long serialVersionUID = -2762847607634692026L;
	private JButton btnSubmit;
	private JButton btnCancel;
	private JTable table;
	private JTable guardianTable;
	private DefaultTableModel guardianTableModel;
	private JButton btnLinkAccount;

	public LinkGuardian() {
		initialise();
	}

	public LinkGuardian(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	public void initialise() {
		setLayout(null);
		
		super.setTitle("Link Guardian to Account");

		// Creates and adds 2 new buttons to panel
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 359, 139, 30);
		add(btnSubmit);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(160, 359, 139, 30);
		add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(188, 90, 556, 229);
		add(scrollPane);

		guardianTableModel = new DefaultTableModel();
		guardianTable = new JTable(guardianTableModel);
		scrollPane.setViewportView(guardianTable);
		
		btnLinkAccount = new JButton("Link Account");

		btnLinkAccount.setBounds(309, 359, 157, 30);
		add(btnLinkAccount);
		
		String[] col = {"Username", "Name", "Email Address"};
		guardianTableModel.setColumnIdentifiers(col);
	}
	
	public void addLinkAccount(ActionListener al) {
		btnLinkAccount.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the submit button
	public void submit(ActionListener al) {
		btnSubmit.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the cancel button
	public void cancel(ActionListener al) {
		btnCancel.addActionListener(al);
	}
	
	// Refreshes the tables view
	public void refreshTable()
	{
		while (guardianTableModel.getRowCount() > 0) {
			guardianTableModel.removeRow(0);
		}
		
		ArrayList<Guardian> guardians = new ArrayList<Guardian>(this.getModel().getGuardianList());

		int size = guardians.size();
		System.out.println(size);
		for (int i = 0; i < size; i++) {
			Guardian tempGuard = guardians.get(i);

			Object[] rowData = new Object[3];
			
			rowData[0] = tempGuard.getUsername();
			rowData[1] = tempGuard.getName();
			rowData[2] = tempGuard.getEmailAddress();
			guardianTableModel.addRow(rowData);

		}
		
		guardianTable.setModel(guardianTableModel);
	
	}
	
	public void linkAccount() throws Exception {

		JLabel lblUsername = new JLabel("Username");
		JTextField txtUsername = new JTextField();

		Object[] arr = { lblUsername, txtUsername };

		int res = JOptionPane.showConfirmDialog(null, arr, "Link Account",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		String username = txtUsername.getText();
		
		if(res == 0)
		{
			UserAccount user = this.getModel().getUserAccount(username);
			if(user != null)
			{
				guardianTableModel.setValueAt(txtUsername.getText(), guardianTable.getSelectedRow(), 0);
			}
			else
			{
				throw new Exception("That username does not exist");
			}
		}
		
	}

	// Resets all text fields to empty
	public void resetTextFields() {
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		resetTextFields();
		refreshTable();
	}
}
