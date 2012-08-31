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

public class AddGuardian extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSave;
	private JButton btnCancel;
	private JTextField txtGuardianName;
	private ArrayList<Guardian> guardians;
	private JList<Guardian> lstGuardian;
	
	
	/**
	 * Create the dialog.
	 */
	public AddGuardian() {
		this.guardians = new ArrayList<Guardian>();
		initialise();
	}	
	
	public AddGuardian(List<Guardian> guardians) {
		this.guardians = new ArrayList<Guardian>(guardians);
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
			JLabel lblAddGuardian = new JLabel("Add Guardian");
			lblAddGuardian.setHorizontalAlignment(SwingConstants.CENTER);
			lblAddGuardian.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblAddGuardian.setBounds(10, 11, 360, 44);
			contentPanel.add(lblAddGuardian);
		}
		
		txtGuardianName = new JTextField();
		txtGuardianName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				populateList(txtGuardianName.getText());
			}
		});
		txtGuardianName.setBounds(164, 66, 169, 33);
		contentPanel.add(txtGuardianName);
		txtGuardianName.setColumns(10);
		
		JLabel lblSearchGuardianName = new JLabel("Search Guardian");
		lblSearchGuardianName.setBounds(40, 69, 128, 30);
		contentPanel.add(lblSearchGuardianName);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 110, 293, 195);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPanel.add(scrollPane);
		lstGuardian = new JList<Guardian>();
		scrollPane.setViewportView(lstGuardian);
		lstGuardian.setModel(new DefaultListModel<Guardian>());
		

		
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
		DefaultListModel<Guardian> listModel = (DefaultListModel<Guardian>) lstGuardian.getModel();
		listModel.removeAllElements();
		
		for(int i = 0; i < guardians.size(); i++)
		{
			Guardian g = guardians.get(i);
			if(g.getUsername().toLowerCase().contains(search.toLowerCase()))
			{
				listModel.addElement(g);
			}
			else
			{
				
			}
		}
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

	public Guardian getGuardian() {
		return lstGuardian.getSelectedValue();
	}
}
