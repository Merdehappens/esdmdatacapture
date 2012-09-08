package viewController;

import javax.swing.JButton;

import system.marking.Objective;
import system.marking.Step;
import system.model.ESDMModel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ViewObjective extends PanelView {

	private static final long serialVersionUID = 7613043122443669408L;
	private JTextField txtObjectiveName;
	private JButton btnSave;
	private JButton btnReset;
	private JButton btnCancel;
	private JTable tblStep;
	private DefaultTableModel tableModel;
	private JTextArea txtObjectiveDescription;
	private Objective objective;
	private JTextField txtLevel;


	public ViewObjective() {
		super();
		initialise();

	}

	public ViewObjective(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {
		setLayout(null);
		// Sets the title of the page.
		super.setTitle("View Objective");
		JLabel lblName = new JLabel("Objective Name");
		lblName.setBounds(10, 88, 130, 35);
		add(lblName);

		JLabel lblDescription = new JLabel("Objective Description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBounds(10, 147, 345, 14);
		add(lblDescription);

		// Adds the text field for the name

		txtObjectiveName = new JTextField();
		txtObjectiveName.setBounds(114, 88, 563, 30);
		add(txtObjectiveName);
		txtObjectiveName.setColumns(10);

		
		// Adds the save button to the page

		btnSave = new JButton("Save");
		btnSave.setBounds(21, 428, 89, 23);
		add(btnSave);

		// adds the reset button to the page

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshView();
			}
		});
		btnReset.setBounds(120, 428, 105, 21);
		add(btnReset);

		// Adds the cancel button to the page

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(235, 428, 94, 21);
		add(btnCancel);

		// Adds the objective description text field to the page

		txtObjectiveDescription = new JTextArea();
		txtObjectiveDescription.setBounds(10, 172, 345, 244);
		add(txtObjectiveDescription);
		txtObjectiveDescription.setLineWrap(true);
		txtObjectiveDescription.setWrapStyleWord(true);

		JLabel lblCurrentSteps = new JLabel("Steps (In Order)");
		lblCurrentSteps.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSteps.setBounds(394, 147, 467, 14);
		add(lblCurrentSteps);

		// Adds a scroll pane for the JTable to go in

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(394, 172, 478, 279);
		add(scrollPane);

		// Adds a table for the steps and sets the column names

		tblStep = new JTable();
		scrollPane.setViewportView(tblStep);
		String[] columnNames = new String[] { "Code", "Step" };

		// Sets the model for the table (The data resides in the table model)

		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		tblStep.setModel(tableModel);

		// adds a blank row 
		
		String[] tempRow = new String[] { "", "" };
		tableModel.addRow(tempRow);
		
		txtLevel = new JTextField();
		txtLevel.setColumns(10);
		txtLevel.setBounds(730, 88, 119, 30);
		add(txtLevel);
		
		JLabel lblLevel = new JLabel("Level:");
		lblLevel.setBounds(687, 88, 65, 30);
		add(lblLevel);

		// Sets the preferred width of the columns in the table

		tblStep.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblStep.getColumnModel().getColumn(1).setPreferredWidth(320);

	}

	
	public Objective saveObjective() throws Exception {
		if(txtObjectiveDescription.getText().length() == 0){
			throw new IllegalArgumentException("Description field is not filled in.");			
		}
		if(txtObjectiveName.getText().length() == 0){
			throw new IllegalArgumentException("Name field is not filled in.");
		}
		
		objective.setDescription(txtObjectiveDescription.getText());
		objective.setName(txtObjectiveName.getText());
		objective.setLevel(Integer.parseInt(txtLevel.getText()));

		ArrayList<Step> steps = new ArrayList<Step>(objective.getSteps());
		
		int size = tblStep.getRowCount();
		// Iterates through the table and creates step objects and adds them to the arraylist 
		for(int i = 0; i < size; i++)
		{
			String code = (String)tblStep.getValueAt(i, 0);
			String description = (String)tblStep.getValueAt(i, 1);
			steps.get(i).setCode(code);
			steps.get(i).setDescription(description);
		}
		// Sets the step list in the objective to the ArrayList 
		objective.setSteps(steps);
		
		return objective;
	}


	// Takes in an ActionListener and adds it to the cancel button

	public void cancelListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the save button
	public void submitListener(ActionListener al) {
		btnSave.addActionListener(al);
	}

	// Sets the objective of the page ot the one parsed through and
	// refreshes the view for this objective
	public void setObjective(Objective o) {
		objective = o;
		refreshView();
	}

	// returns the text in objective name
	public String getObjectiveName() {
		return txtObjectiveName.getText();
	}

	// returns the text in objective description
	public String getObjectiveDescription() {
		return txtObjectiveDescription.getText();
	}

	// Returns the steps in the table
	public String[][] getSteps() {

		String[][] temp = new String[tblStep.getRowCount()][2];

		for (int i = 0; i < tblStep.getRowCount(); i++) {
			temp[i][0] = (String) tblStep.getValueAt(i, 0);
			temp[i][1] = (String) tblStep.getValueAt(i, 1);
		}

		return temp;
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		populateFields(objective);
	}
	
	public int getLevel() throws Exception {
		if(txtLevel.getText().length() != 0)
		{
			try{
			return Integer.parseInt(txtLevel.getText());
			}
			catch(Exception e) {
				throw new IllegalArgumentException("Level must be a number.");
			}
		}
		
		return 0;
	}

	
	private void populateFields(Objective o) {
		// Sets the txt box for objective and name
		txtObjectiveName.setText(o.getName());
		txtObjectiveDescription.setText(o.getDescription());
		txtLevel.setText(Integer.toString(o.getLevel()));
		// removes all current rows from the table
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		ArrayList<Step> steps = new ArrayList<Step>(o.getSteps());
		// adds new rows to the table from the steps in the objective
		int size = steps.size();
		for (int i = 0; i < size; i++) {
			Step s = steps.get(i);
			String[] row = new String[] { s.getCode(), s.getDescription() };
			tableModel.addRow(row);
		}

	}

	public Objective getObjective() {
		return objective;
	}


}
