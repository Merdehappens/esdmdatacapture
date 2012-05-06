package viewController;

import javax.swing.JButton;

import systemModel.ESDMModel;
import systemModel.Step;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class AddObjective extends PanelView {
	private JTextField txtObjectiveName;
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JButton btnAddStep;
	private JTable tblStep;
	private DefaultTableModel tableModel;
	
	/**
	 * Create the panel.
	 */
	public AddObjective() {
		setLayout(null);
		

		initialise();
	
	}
	
	public AddObjective(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		
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
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(21, 324, 89, 23);
		add(btnSubmit);
		
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
		
		JTextArea txtObjectiveDescription = new JTextArea();
		txtObjectiveDescription.setBounds(10, 114, 345, 178);
		add(txtObjectiveDescription);
		
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
		
		JButton btnAddStep_1 = new JButton("Add Step");
		btnAddStep_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] tempRow = new String[] {"", ""};
				tableModel.addRow(tempRow);
			}
		});
		btnAddStep_1.setBounds(404, 324, 89, 23);
		add(btnAddStep_1);
		
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
	
	public void submitListener(ActionListener al)
	{
		btnSubmit.addActionListener(al);	
	}

	
	public void cancelListener(ActionListener al)
	{
		btnCancel.addActionListener(al);	
	}
	
	private void resetForm()
	{
		
	}
	
	private void swapRows(int i)
	{
		int start = tblStep.getSelectedRow();
		if(start + i >= 0 && start + i < tblStep.getRowCount())
		{
			tableModel.moveRow(start, start, start + i);
		}
	}
	
}
