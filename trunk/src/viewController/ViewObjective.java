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
	private Objective objective;

	/**
	 * Create the panel.
	 */
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
		lblName.setBounds(10, 43, 130, 35);
		add(lblName);

		JLabel lblDescription = new JLabel("Objective Description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBounds(10, 89, 345, 14);
		add(lblDescription);

		// Adds the text field for the name

		txtObjectiveName = new JTextField();
		txtObjectiveName.setBounds(114, 43, 689, 30);
		add(txtObjectiveName);
		txtObjectiveName.setColumns(10);

		// Adds the save button to the page

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				objective.setDescription(txtObjectiveDescription.getText());
				objective.setName(txtObjectiveName.getText());
			}
		});
		btnSave.setBounds(21, 324, 89, 23);
		add(btnSave);

		// adds the reset button to the page

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshView();
			}
		});
		btnReset.setBounds(120, 324, 105, 21);
		add(btnReset);

		// Adds the cancel button to the page

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(235, 324, 94, 21);
		add(btnCancel);

		// Adds the objective description text field to the page

		txtObjectiveDescription = new JTextArea();
		txtObjectiveDescription.setBounds(10, 114, 345, 178);
		add(txtObjectiveDescription);
		txtObjectiveDescription.setLineWrap(true);
		txtObjectiveDescription.setWrapStyleWord(true);

		JLabel lblCurrentSteps = new JLabel("Current Steps (In Order)");
		lblCurrentSteps.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSteps.setBounds(394, 102, 467, 14);
		add(lblCurrentSteps);

		// Adds a scroll pane for the JTable to go in

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(394, 127, 478, 244);
		add(scrollPane);

		// Adds a table for the steps and sets the column names

		tblStep = new JTable();
		scrollPane.setViewportView(tblStep);
		String[] columnNames = new String[] { "Code", "Step" };

		// Sets the model for the table (The data resides in the table model)

		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		tblStep.setModel(tableModel);

		String[] tempRow = new String[] { "", "" };
		tableModel.addRow(tempRow);

		// Adds the button to delete step and a listener to determine what that
		// button does

		JButton btnDeleteSelectedStep = new JButton("Delete Selected Step");
		btnDeleteSelectedStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.removeRow(tblStep.getSelectedRow());
			}
		});
		btnDeleteSelectedStep.setBounds(503, 383, 150, 23);
		add(btnDeleteSelectedStep);

		// Adds the button to add step and a listener to determine what the
		// button does

		JButton btnAddStep = new JButton("Add Step");
		btnAddStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] tempRow = new String[] { "", "" };
				tableModel.addRow(tempRow);
			}
		});
		btnAddStep.setBounds(404, 383, 89, 23);
		add(btnAddStep);

		// Adds the button to move a step up the list and a listener to
		// determine what the button does

		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapRows(-1);
			}
		});
		btnUp.setBounds(877, 115, 59, 35);
		add(btnUp);

		// Adds the button to move a step down the list and a listener to
		// determine what the button does

		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapRows(1);

			}
		});
		btnDown.setBounds(876, 161, 60, 40);
		add(btnDown);

		// Sets the preferred width of the columns in the table

		tblStep.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblStep.getColumnModel().getColumn(1).setPreferredWidth(320);

	}

	// Takes in an ActionListener and adds it to the save button

	public void submitListener(ActionListener al) {
		btnSave.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the cancel button

	public void cancelListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	private void resetForm() {
		txtObjectiveName.setText("");
		txtObjectiveDescription.setText("");
	}

	public void setObjective(Objective o) {
		objective = o;
		refreshView();
	}

	private void swapRows(int i) {
		int start = tblStep.getSelectedRow();
		if (start + i >= 0 && start + i < tblStep.getRowCount()) {
			tableModel.moveRow(start, start, start + i);
			tblStep.changeSelection(start + i, 0, false, false);
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

		for (int i = 0; i < tblStep.getRowCount(); i++) {
			temp[i][0] = (String) tblStep.getValueAt(i, 0);
			temp[i][1] = (String) tblStep.getValueAt(i, 1);
		}

		return temp;
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		resetForm();
		populateFields(objective);
	}

	private void populateFields(Objective o) {
		txtObjectiveName.setText(o.getName());
		txtObjectiveDescription.setText(o.getDescription());

		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		ArrayList<Step> steps = new ArrayList<Step>(o.getSteps());

		int size = steps.size();
		for (int i = 0; i < size; i++) {
			Step s = steps.get(i);
			String[] row = new String[] { s.getCode(), s.getDescription() };
			tableModel.addRow(row);
		}

	}

}
