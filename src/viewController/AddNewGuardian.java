package viewController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddNewGuardian extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSave;
	private JButton btnCancel;
	private JTextField txtGuardianName;
	private ArrayList<Guardian> guardians;
	private JLabel lblUsername;
	private JTextField txtGuardianUsername;
	private JTextField txtGuardianEmail;
	private JTextField txtGuardianPhone;
	
	
	/**
	 * Create the dialog.
	 */
	public AddNewGuardian() {
		this.guardians = new ArrayList<Guardian>();
		initialise();
	}	
	
	public AddNewGuardian(List<Guardian> guardians) {
		this.guardians = new ArrayList<Guardian>(guardians);
		initialise();
	}

	public void initialise()
	{
		setBounds(100, 100, 489, 395);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitle = new JLabel("Add New Guardian");
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblTitle.setBounds(10, 11, 460, 44);
			contentPanel.add(lblTitle);
		}
		
		txtGuardianName = new JTextField();
		txtGuardianName.setBounds(115, 69, 277, 30);
		contentPanel.add(txtGuardianName);
		txtGuardianName.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(23, 69, 122, 30);
		contentPanel.add(lblName);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(23, 110, 122, 30);
		contentPanel.add(lblUsername);
		
		txtGuardianUsername = new JTextField();
		txtGuardianUsername.setColumns(10);
		txtGuardianUsername.setBounds(115, 110, 277, 30);
		contentPanel.add(txtGuardianUsername);
		
		JLabel lblEmail = new JLabel("Email Address:");
		lblEmail.setBounds(23, 151, 122, 30);
		contentPanel.add(lblEmail);
		
		txtGuardianEmail = new JTextField();
		txtGuardianEmail.setColumns(10);
		txtGuardianEmail.setBounds(115, 151, 277, 30);
		contentPanel.add(txtGuardianEmail);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(22, 204, 96, 14);
		contentPanel.add(lblPhoneNumber);
		
		txtGuardianPhone = new JTextField();
		txtGuardianPhone.setColumns(10);
		txtGuardianPhone.setBounds(115, 192, 277, 30);
		contentPanel.add(txtGuardianPhone);
		

		
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
			
		
	}
	
	
	public void setGuardians(List<Guardian> guardians)
	{
		this.guardians = new ArrayList<Guardian>(guardians);
	}
	
	public void saveButtonListener(ActionListener al){
		btnSave.addActionListener(al);
	}
	
	public void cancelButtonListener(ActionListener al){
		btnCancel.addActionListener(al);
	}
	
	public String getName(){
		return txtGuardianName.getText();
	}
	
	public String getUsername() {
		return txtGuardianUsername.getText();
	}
	
	public String getEmail() {
		return txtGuardianEmail.getText();
	}
	
	public String getPhone() { 
		return txtGuardianPhone.getText();
	}
}
