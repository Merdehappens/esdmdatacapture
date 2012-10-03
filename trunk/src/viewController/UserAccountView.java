package viewController;

import javax.swing.JButton;
import system.individuals.UserAccount;
import system.model.ESDMModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class UserAccountView extends PanelView {

	private static final long serialVersionUID = 3263162072078805101L;
	private JButton btnAddNewUserAccount;
	private JButton btnViewUserAccount;
	//private JButton btnAddObjectiveTo;
	private ArrayList<UserAccount> accounts;
	private MyJTable userAccountTable;
	private DefaultTableModel tableModel;
	private JButton btnResetPassword;

	public UserAccountView() {
		super();
		initialise();
	}

	public UserAccountView(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {
		setLayout(null);
		// Sets the title of the page
		super.setTitle("User Account");

		// Creates 3 new buttons and adds them to the page
		btnAddNewUserAccount = new JButton("Add New User Account");
		btnAddNewUserAccount.setBounds(50, 90, 200, 35);
		add(btnAddNewUserAccount);

		btnViewUserAccount = new JButton("View User");
		btnViewUserAccount.setBounds(260, 90, 200, 35);
		add(btnViewUserAccount);

		/*btnAddObjectiveTo = new JButton("Add Objective to Child");
		btnAddObjectiveTo.setBounds(470, 90, 200, 35);
		add(btnAddObjectiveTo);*/

		// Creates a new scrollpane and adds it to the screen
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 135, 900, 390);
		add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		// creates a new table model, and table and adds the table to the scroll pane
		tableModel = new DefaultTableModel();
		userAccountTable = new MyJTable();
		scrollPane.setViewportView(userAccountTable);
		userAccountTable.setModel(tableModel);
		
		btnResetPassword = new JButton("Reset Password");
		btnResetPassword.setBounds(470, 90, 200, 35);
		add(btnResetPassword);

		// sets the column names to the string array
		String[] columnNames = new String[] { "Account", "Name",
				"Email Address", "Phone Number" };

		tableModel.setColumnIdentifiers(columnNames);
		
		TableColumnModel tblColModel = userAccountTable.getColumnModel();
		
		tblColModel.getColumn(0).setPreferredWidth(75);
		tblColModel.getColumn(1).setPreferredWidth(400);
		tblColModel.getColumn(2).setPreferredWidth(200);
		tblColModel.getColumn(3).setPreferredWidth(200);
		
		refreshView();

	}

	// Takes in an ActionListener and adds it to the Add New Objective button
	public void addNewUser(ActionListener al) {
		btnAddNewUserAccount.addActionListener(al);
	}
	
	public void resetPasswordListener(ActionListener al) {
		btnResetPassword.addActionListener(al);
	}
	
	public void viewUser(ActionListener al) {
		btnViewUserAccount.addActionListener(al);
	}


	// Takes in an ActionListener and adds it to the Add Objective To Child
	// button
	/*public void addObjectiveToChild(ActionListener al) {
		btnAddObjectiveTo.addActionListener(al);
	}*/

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		populateTable();
		if(this.getModel().getCurrentUser() != null)
		{
			if(this.getModel().getCurrentAccess().equals("a"))
			{
				btnResetPassword.setVisible(true);
			} else
			{
				btnResetPassword.setVisible(false);	
			}
		}
	}

	// Returns the objective for the row that is selected
	public UserAccount getSelectedAccount() throws Exception {

		UserAccount account;
		try {
			account = (UserAccount) accounts.get(userAccountTable
					.getSelectedRow());
		} catch (Exception e) {
			throw new Exception("30010: You must select an account first.");
		}

		return account;

	}

	private void populateTable() {

		// Removes all rows from table
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		// Gets the objective list from the model
		accounts = new ArrayList<UserAccount>(this.getModel().getUserList());

		// Iterates through the objectives list adding the details to the table
		int size = accounts.size();
		for (int i = 0; i < size; i++) {
			UserAccount account = accounts.get(i);

			Object[] rowData = new Object[4];
			
			if(account.getAccess().equals("g"))
			{
				rowData[0] = "Guardian";
			}
			else
			{
				rowData[0] = "Therapist";
			}
			rowData[1] = account.getName();
			rowData[2] = account.getEmailAddress();
			rowData[3] = account.getPhoneNo();

			tableModel.addRow(rowData);

		}

	}
}
