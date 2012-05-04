package viewController;

import javax.swing.JButton;

import systemModel.ESDMModel;
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
	private JTextField txtCode;
	private JTextField txtStep;
	private JTable tblStep;

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
		lblName.setBounds(10, 43, 130, 14);
		add(lblName);
		
		
		JLabel lblDateOfBirth = new JLabel("Objective Description");
		lblDateOfBirth.setBounds(10, 68, 111, 14);
		add(lblDateOfBirth);
		
		txtObjectiveName = new JTextField();
		txtObjectiveName.setBounds(128, 43, 227, 20);
		add(txtObjectiveName);
		txtObjectiveName.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(21, 252, 89, 23);
		add(btnSubmit);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetForm();
			}
		});
		btnReset.setBounds(120, 252, 105, 21);
		add(btnReset);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(235, 252, 94, 21);
		add(btnCancel);
		
		JTextArea txtObjectiveDescription = new JTextArea();
		txtObjectiveDescription.setBounds(128, 68, 227, 61);
		add(txtObjectiveDescription);
		
		JLabel lblCode = new JLabel("Code");
		lblCode.setBounds(22, 155, 46, 14);
		add(lblCode);
		
		JLabel lblSteps = new JLabel("Steps");
		lblSteps.setBounds(94, 155, 261, 14);
		add(lblSteps);
		
		txtCode = new JTextField();
		txtCode.setBounds(10, 177, 74, 21);
		add(txtCode);
		txtCode.setColumns(10);
		
		txtStep = new JTextField();
		txtStep.setBounds(94, 177, 323, 21);
		add(txtStep);
		txtStep.setColumns(10);
		
		JButton btnAddStep = new JButton("Add Step");

		btnAddStep.setBounds(328, 203, 89, 23);
		add(btnAddStep);
		
		JLabel lblCurrentSteps = new JLabel("Current Steps");
		lblCurrentSteps.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSteps.setBounds(479, 43, 309, 14);
		add(lblCurrentSteps);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(443, 68, 357, 207);
		add(scrollPane);
		
		
		tblStep = new JTable();
		scrollPane.setViewportView(tblStep);
		tblStep.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "C2F", "Orients and grasp/tap w/ partial physical prompt 50% opp "},
			},
			new String[] {
				"No", "Code", "Step"
			}
		));
		
		JButton btnDeleteSelectedStep = new JButton("Delete Selected Step");
		btnDeleteSelectedStep.setBounds(443, 280, 139, 23);
		add(btnDeleteSelectedStep);
		tblStep.getColumnModel().getColumn(0).setPreferredWidth(15);
		tblStep.getColumnModel().getColumn(1).setPreferredWidth(35);
		tblStep.getColumnModel().getColumn(2).setPreferredWidth(320);
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
}
