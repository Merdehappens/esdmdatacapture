package viewController;

import javax.swing.JButton;

import system.individuals.Guardian;
import system.model.ESDMModel;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddChild extends PanelView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5863857435197571726L;
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
		super();
		initialise();
		
	}
	
	public AddChild(ESDMModel model)
	{
		super(model);
		initialise();
		
	}
	
	/*
	 * Initialises all the graphical components on the page.
	 */
	
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
		
		// Creates a reset button and adds an action listener to it.
		
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
		

		
		// Creates a new JTable where the first column is not editable 		
		
		tblGuardian = new JTable()
		{ 
			private static final long serialVersionUID = -6097806136687511132L;

		public boolean isCellEditable(int rowIndex, int colIndex) 
			{ if(colIndex == 0)
				{
					return false;
				}
				return true; } };

				
		/*
		 * Adds a listener so that if the table loses focus it stops editing on that cell.
		 * 
		 * This is so that when add is clicked when a cell is still being edited the information in the cell
		 * is saved and not lost.		
		 */
				
		tblGuardian.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

					TableCellEditor editor = tblGuardian.getCellEditor();
					if (editor != null) {
						editor.stopCellEditing();
					}
					
					
			}
		});
		
		// Sets up the tables columns and model
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 237, 342, 95);
		add(scrollPane);
		
		scrollPane.setViewportView(tblGuardian);
		String[] columnNames = new String[] {"Guardian", "Name", "Phone Number"};
		
		tblGuardianModel = new DefaultTableModel();

		tblGuardianModel.setColumnIdentifiers(columnNames);
		
		tblGuardian.setModel(tblGuardianModel);
		
		JLabel lblChildsGuardians = new JLabel("Childs Guardians");
		lblChildsGuardians.setHorizontalAlignment(SwingConstants.CENTER);
		lblChildsGuardians.setBounds(10, 212, 342, 14);
		add(lblChildsGuardians);
		
		
		// Creates a new Button and adds a new action listener to the button so that when button is pressed
		// it adds a new row to the table.
		
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
		
		
		//Creates a new button and adds a new action listener to the button so when it is pressed 
		// it removes the selected row from the table.
		
		JButton btnDeleteGuardian = new JButton("Remove Guardian");
		btnDeleteGuardian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tblGuardian.getSelectedRow() >= 0)
				{
					tblGuardianModel.removeRow(tblGuardian.getSelectedRow());
				}
			}
		});
		btnDeleteGuardian.setBounds(364, 281, 120, 23);
		add(btnDeleteGuardian);
		
		//Adds a new button that when pressed makes a new File Chooser dialog open So that the user can choose
		// a picture to upload for this child.
	

		JButton btnSelectPictureTo = new JButton("Select Picture to Add");
		btnSelectPictureTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				
				picture = fileChooser.getSelectedFile();
				
				ImageIcon temp = new ImageIcon(picture.getAbsolutePath());
				lblPicture.setIcon(temp);
			}
		});
		btnSelectPictureTo.setBounds(364, 43, 144, 27);
		add(btnSelectPictureTo);
		
		//Adds a new Label that will hold the picture chosen for the child.
		
		lblPicture = new JLabel("");
		lblPicture.setBounds(355, 78, 153, 114);
		add(lblPicture);
		
		
		
	}
	

	public String getChildName()
	{
		return txtName.getText();
	}
	
	/*
	 *  Returns the guardian objects that are in the table. if there is no object for the specified row it
	 *  will create an object and add it to the list to be returned.
	 */
	
	
	public ArrayList<Guardian> getGuardians()
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
		
		return guardians;
	}
	
	public Calendar getDob()
	{
		return dobChooser.getCalendar();
	}
	
	public Calendar getDateJoined()
	{
		return dateJoinedChooser.getCalendar();
	}	
	
	/*
	 * Takes in an ActionListener and adds it to the submit button
	 */
	
	public void submitListener(ActionListener al)
	{
		btnSubmit.addActionListener(al);	
	}

	
	/*
	 * Takes in an ActionListener and adds it to the cancel button
	 */
	
	public void cancelListener(ActionListener al)
	{
		btnCancel.addActionListener(al);
	}
	
	/*
	 * Resets all the text fields to default
	 */
	
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
	
	public void refreshView()
	{
		resetTextField();
	}
}
