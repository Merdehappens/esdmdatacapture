package viewController;

import javax.swing.JButton;

import system.model.ESDMModel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class EditObjective extends PanelView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7613043122443669408L;
	private JTextField txtObjectiveName;
	private JButton btnSave;
	private JButton btnReset;
	private JButton btnCancel;
	private JTable tblStep;
	private DefaultTableModel tableModel;
	private JTextArea txtObjectiveDescription;
	
	/**
	 * Create the panel.
	 */
	public EditObjective() {
		super();
		initialise();
	
	}
	
	public EditObjective(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Add New Objective");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 430, 21);
		add(lblTitle);
		
		JLabel lblName = new JLabel("Objective Name");
		lblName.setBounds(10, 43, 130, 35);
		add(lblName);
		
		
		JLabel lblDateOfBirth = new JLabel("Objective Description");
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateOfBirth.setBounds(10, 89, 345, 14);
		add(lblDateOfBirth);
		
		txtObjectiveName = new JTextField();
		txtObjectiveName.setBounds(114, 43, 241, 30);
		add(txtObjectiveName);
		txtObjectiveName.setColumns(10);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(21, 324, 89, 23);
		add(btnSave);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetForm();
			}
		});
		btnReset.setBounds(120, 324, 105, 21);
		add(btnReset);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(235, 324, 94, 21);
		add(btnCancel);
		
		txtObjectiveDescription = new JTextArea();
		txtObjectiveDescription.setBounds(10, 114, 345, 178);
		add(txtObjectiveDescription);
		txtObjectiveDescription.setLineWrap(true);
		
		JLabel lblCurrentSteps = new JLabel("Current Steps (In Order)");
		lblCurrentSteps.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSteps.setBounds(394, 43, 467, 14);
		add(lblCurrentSteps);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(394, 68, 478, 244);
		add(scrollPane);
		
		
		tblStep = new JTable();
		scrollPane.setViewportView(tblStep);
		String[] columnNames = new String[] {"Code", "Step"};
		
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		
		tblStep.setModel(tableModel);
		
		String[] tempRow = new String[] {"", ""};
		tableModel.addRow(tempRow);
		
		
		
		JButton btnDeleteSelectedStep = new JButton("Delete Selected Step");
		btnDeleteSelectedStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.removeRow(tblStep.getSelectedRow());
			}
		});
		btnDeleteSelectedStep.setBounds(503, 324, 150, 23);
		add(btnDeleteSelectedStep);
		
		JButton btnAddStep = new JButton("Add Step");
		btnAddStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] tempRow = new String[] {"", ""};
				tableModel.addRow(tempRow);
			}
		});
		btnAddStep.setBounds(404, 324, 89, 23);
		add(btnAddStep);
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapRows(-1);
			}
		});
		btnUp.setBounds(877, 115, 59, 35);
		add(btnUp);
		
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapRows(1);
				
			}
		});
		btnDown.setBounds(876, 161, 60, 40);
		add(btnDown);
		
		tblStep.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblStep.getColumnModel().getColumn(1).setPreferredWidth(320);
		
	}

	
	public void saveListener(ActionListener al)
	{
		btnSave.addActionListener(al);	
	}

	
	public void cancelListener(ActionListener al)
	{
		btnCancel.addActionListener(al);	
	}
	
	private void resetForm()
	{
		txtObjectiveName.setText("");
		txtObjectiveDescription.setText("");
	}
	
	private void swapRows(int i)
	{
		int start = tblStep.getSelectedRow();
		if(start + i >= 0 && start + i < tblStep.getRowCount())
		{
			tableModel.moveRow(start, start, start + i);
		}
	}

	public String getObjectiveName() {
		return txtObjectiveName.getText();
	}

	public String getObjectiveDescription() {
		return txtObjectiveDescription.getText();
	}

	public String[][] getSteps() {
		
		String[][] temp = new String[tblStep.getRowCount()][2];
		
		for(int i = 0; i < tblStep.getRowCount(); i++) 
		{
			temp[i][0] = (String) tblStep.getValueAt(i, 0);
			temp[i][1] = (String) tblStep.getValueAt(i, 1);
		}
		
		return temp;
	}
	
	public void refreshView()
	{
		resetForm();
	}
	
}
