package viewController;

import javax.swing.JButton;

import system.model.ESDMModel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.JScrollPane;

public class AddObjective extends PanelView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7613043122443669408L;
	private JTextField txtObjectiveName;
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JTable tblStep;
	private DefaultTableModel tableModel;
	private JTextArea txtObjectiveDescription;

	/**
	 * Create the panel.
	 */
	public AddObjective() {
		super();
		initialise();

	}

	public AddObjective(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {
		setLayout(null);

		super.setTitle("Add New Objective");

		JLabel lblName = new JLabel("Objective Name:");
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
		String[] columnNames = new String[] { "Code", "Step" };

		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);

		tblStep.setModel(tableModel);

		String[] tempRow = new String[] { "", "" };
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
				String[] tempRow = new String[] { "", "" };
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

		tblStep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {

				TableCellEditor editor = tblStep.getCellEditor();
				if (editor != null) {
					editor.stopCellEditing();
				}
			}
		});

	}

	// Takes in an ActionListener and adds it to the submit button

	public void submitListener(ActionListener al) {
		btnSubmit.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the cancel button

	public void cancelListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	private void resetForm() {
		txtObjectiveName.setText("");
		txtObjectiveDescription.setText("");
	}

	private void swapRows(int i) {
		int start = tblStep.getSelectedRow();
		if (start + i >= 0 && start + i < tblStep.getRowCount()) {
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

		for (int i = 0; i < tblStep.getRowCount(); i++) {
			temp[i][0] = (String) tblStep.getValueAt(i, 0);
			temp[i][1] = (String) tblStep.getValueAt(i, 1);
		}

		return temp;
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		resetForm();
		resetTable();
	}

	private void resetTable() {
		while (tblStep.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		String[] row = new String[] { "", "" };
		tableModel.addRow(row);
	}

}
