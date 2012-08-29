package viewController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;

import system.individuals.Guardian;
import system.individuals.UserAccount;

public class FindUserAccount extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSave;
	private JButton btnCancel;
	private JTextField txtGuardianName;
	private ArrayList<UserAccount> userAccounts;
	private JList<UserAccount> lstAccount;
	
	
	/**
	 * Create the dialog.
	 */
	public FindUserAccount() {
		this.userAccounts = new ArrayList<UserAccount>();
		initialise();
	}	
	
	public FindUserAccount(ArrayList<UserAccount> userAccounts) {
		this.userAccounts = new ArrayList<UserAccount>(userAccounts);
		initialise();
	}

	public void initialise()
	{
		setBounds(100, 100, 396, 423);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitle = new JLabel("View User Account");
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblTitle.setBounds(10, 11, 360, 44);
			contentPanel.add(lblTitle);
		}
		
		txtGuardianName = new JTextField();
		txtGuardianName.setBounds(164, 66, 169, 20);
		contentPanel.add(txtGuardianName);
		txtGuardianName.setColumns(10);
		
		JLabel lblSearchGuardianName = new JLabel("Search username:");
		lblSearchGuardianName.setBounds(40, 69, 128, 14);
		contentPanel.add(lblSearchGuardianName);
		
		lstAccount = new JList<UserAccount>();
		lstAccount.setBounds(40, 110, 293, 195);
		contentPanel.add(lstAccount);
		lstAccount.setModel(new DefaultListModel<UserAccount>());
		

		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				btnSave = new JButton("Save");
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			
			
				btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			
		
		
		populateList("");
		
	}
	
	public void populateList(String search)
	{
		DefaultListModel<UserAccount> listModel = (DefaultListModel<UserAccount>) lstAccount.getModel();
		listModel.removeAllElements();
		
		for(int i = 0; i < userAccounts.size(); i++)
		{
			UserAccount u = userAccounts.get(i);
			if(u.getUsername().toLowerCase().contains(search.toLowerCase()))
			{
				listModel.addElement(u);
			}
		}
	}
	
	public void setAccounts(List<UserAccount> userAccounts)
	{
		this.userAccounts = new ArrayList<UserAccount>(userAccounts);
	}
	
	public void saveButtonListener(ActionListener al){
		btnSave.addActionListener(al);
	}
	
	public void cancelButtonListener(ActionListener al){
		btnCancel.addActionListener(al);
	}

	public UserAccount getUserAccount() {
		return lstAccount.getSelectedValue();
	}
}
