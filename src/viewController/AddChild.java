package viewController;

import javax.swing.JButton;

import systemModel.Child;
import systemModel.ESDMModel;
import systemModel.Guardian;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

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
	private File picture;
	private JLabel lblPicture;
	
	/**
	 * Create the panel.
	 */
	public AddChild() {
		initialise();
		
	}
	
	public AddChild(ESDMModel model)
	{
		super(model);
		initialise();
		
	}
	
	private void initialise()
	{
		picture = null;
		guardians = new ArrayList<Guardian>();
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 237, 342, 95);
		add(scrollPane);
		
		
		
		
		
		tblGuardian = new JTable(){ public boolean isCellEditable(int rowIndex, int colIndex) 
			{ if(colIndex == 0)
				{
					return false;
				}
				return true; } };
		
		scrollPane.setViewportView(tblGuardian);
		String[] columnNames = new String[] {"Guardian", "Name", "Phone Number"};
		
		tblGuardianModel = new DefaultTableModel();

		tblGuardianModel.setColumnIdentifiers(columnNames);
		
		tblGuardian.setModel(tblGuardianModel);
		
		JLabel lblChildsGuardians = new JLabel("Childs Guardians");
		lblChildsGuardians.setHorizontalAlignment(SwingConstants.CENTER);
		lblChildsGuardians.setBounds(10, 212, 342, 14);
		add(lblChildsGuardians);
		
		JButton btnAddGuardian = new JButton("Add Guardian");
		btnAddGuardian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Object[] tempRow = new Object[3];
				tempRow[0] = null;
				tempRow[1] = "";
				tempRow[2] = "";
				
				tblGuardianModel.addRow(tempRow);
			}
		});
		btnAddGuardian.setBounds(364, 249, 120, 23);
		add(btnAddGuardian);
		
		JButton btnDeleteGuardian = new JButton("Remove Guardian");
		btnDeleteGuardian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblGuardianModel.removeRow(tblGuardian.getSelectedRow());
			}
		});
		btnDeleteGuardian.setBounds(364, 281, 120, 23);
		add(btnDeleteGuardian);

		JButton btnSelectPictureTo = new JButton("Select Picture to Add");
		btnSelectPictureTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JLabel typeLabel = new JLabel("Choose a picture to upload (.JPG, .JPEG, .PNG or .GIF");
				JFileChooser fileChooser = new JFileChooser();
				
				Object[] array = { typeLabel,
						fileChooser };

				JOptionPane.showConfirmDialog(null, array, "Select File to Add", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				picture = fileChooser.getSelectedFile();

				ImageIcon temp = new ImageIcon(picture.getAbsolutePath());
				lblPicture.setIcon(temp);
			}
		});
		btnSelectPictureTo.setBounds(364, 43, 144, 27);
		add(btnSelectPictureTo);
		
		lblPicture = new JLabel("");
		lblPicture.setBounds(355, 78, 153, 114);
		add(lblPicture);
		
		
		
	}
	

	public Child addChild()
	{
		guardians = new ArrayList<Guardian>();
		
		for(int i = 0; i < tblGuardian.getRowCount(); i++)
		{
			Guardian guardian = (Guardian)tblGuardianModel.getValueAt(i, 0);
			if(guardian == null)
			{
				guardian = new Guardian();
				guardian.setName((String)tblGuardianModel.getValueAt(i, 1));
				guardian.setPhoneNo((String)tblGuardianModel.getValueAt(i, 2));
			}
			guardians.add(guardian);
		}
		
		return super.getModel().addChild(txtName.getText(), dobChooser.getDate(), dateJoinedChooser.getDate(), guardians);
	}
	
	
	public void submitListener(ActionListener al)
	{
		btnSubmit.addActionListener(al);	
	}

	
	public void cancelListener(ActionListener al)
	{
		btnCancel.addActionListener(al);	
	}
	
	public void resetTextField()
	{
		txtName.setText("");
		Date tempDate = null;
		guardians = new ArrayList<Guardian>();
		dateJoinedChooser.setDate(tempDate);
		dobChooser.setDate(tempDate);
		tblGuardianModel.setRowCount(0);
		lblPicture.setIcon(null);
	}
}
