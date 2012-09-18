package viewController;

import javax.swing.JButton;

import system.marking.ObjectiveType;
import system.marking.Step;
import system.model.ESDMModel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

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
	private JTextField txtLevel;
	private JButton btnCreateFromTemplate;
	private JComboBox<ObjectiveType> cmbbxType;

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
		// Sets the title to the string parsedd through
		super.setTitle("Add New Objective");

		// create and add labels to page
		JLabel lblName = new JLabel("Objective Name:");
		lblName.setBounds(10, 66, 130, 35);
		add(lblName);

		JLabel lblDateOfBirth = new JLabel("Objective Description");
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateOfBirth.setBounds(10, 157, 345, 14);
		add(lblDateOfBirth);

		JLabel lblCurrentSteps = new JLabel("Current Steps (In Order)");
		lblCurrentSteps.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSteps.setBounds(389, 112, 467, 14);
		add(lblCurrentSteps);
		
		// Creates and adds text fields to page
		txtObjectiveName = new JTextField();
		txtObjectiveName.setBounds(114, 66, 461, 30);
		add(txtObjectiveName);
		txtObjectiveName.setColumns(10);

		txtObjectiveDescription = new JTextArea();
		txtObjectiveDescription.setBounds(10, 182, 345, 178);
		add(txtObjectiveDescription);
		txtObjectiveDescription.setLineWrap(true);
		
		// Adds submit button to the page
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(21, 392, 89, 23);
		add(btnSubmit);

		// Adds reset button to page and adds action listener to it that
		// calls the refreshView method
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshView();
			}
		});
		btnReset.setBounds(120, 392, 105, 21);
		add(btnReset);

		// Adds a cancel button to the page.
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(235, 392, 94, 21);
		add(btnCancel);
		
		// Adds the scroll pane for the table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(389, 137, 478, 244);
		add(scrollPane);

		// Creates and adds the table to the scroll pane and 
		// initialises the column names and table model
		tblStep = new JTable();
		scrollPane.setViewportView(tblStep);
		String[] columnNames = new String[] { "Code", "Step" };
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		tblStep.setModel(tableModel);

		// Adds an empty row to the table model
		String[] tempRow = new String[] { "", "" };
		tableModel.addRow(tempRow);

		// Adds the delete step button to the screen and adds
		// the action listener that removes the selected row
		JButton btnDeleteSelectedStep = new JButton("Delete Selected Step");
		btnDeleteSelectedStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.removeRow(tblStep.getSelectedRow());
			}
		});
		btnDeleteSelectedStep.setBounds(488, 392, 150, 23);
		add(btnDeleteSelectedStep);

		// Adds the add step button to the screen and adds
		// the action listener that adds a blank row
		JButton btnAddStep = new JButton("Add Step");
		btnAddStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] tempRow = new String[] { "", "" };
				tableModel.addRow(tempRow);
			}
		});
		btnAddStep.setBounds(389, 392, 89, 23);
		add(btnAddStep);

		// Adds the swap up button to the screen and adds
		// the action listener that swaps the rows around
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapRows(-1);
			}
		});
		btnUp.setBounds(878, 229, 59, 35);
		add(btnUp);

		// Adds the swap down button to the screen and adds
		// the action listener that swaps the rows around
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapRows(1);

			}
		});
		btnDown.setBounds(877, 275, 60, 40);
		add(btnDown);
		
		JLabel lblLevel = new JLabel("Level #");
		lblLevel.setBounds(585, 66, 65, 30);
		add(lblLevel);
		
		txtLevel = new JTextField();
		txtLevel.setBounds(628, 66, 59, 30);
		add(txtLevel);
		txtLevel.setColumns(10);
		
		btnCreateFromTemplate = new JButton("Create from Template");
		btnCreateFromTemplate.setBounds(697, 68, 169, 30);
		add(btnCreateFromTemplate);
		
		JLabel lblObjectiveType = new JLabel("Objective Type");
		lblObjectiveType.setBounds(10, 112, 90, 23);
		add(lblObjectiveType);
		
		cmbbxType = new JComboBox<ObjectiveType>();
		cmbbxType.setBounds(124, 112, 230, 23);
		add(cmbbxType);

		// Sets the column widths
		tblStep.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblStep.getColumnModel().getColumn(1).setPreferredWidth(320);

		
		// Adds a listener that listens for when the table loses focus.
		// This is required so that if save is clicked while still editing
		// it will stop all editing and be able to save the text
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

	// Takes in an ActionListener and adds it to the create from template button
	public void templateListener(ActionListener al) {
		btnCreateFromTemplate.addActionListener(al);
	}
	
	// Takes in an ActionListener and adds it to the submit button

	public void submitListener(ActionListener al) {
		btnSubmit.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the cancel button

	public void cancelListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	// Resets the text fields
	private void resetForm() {
		txtObjectiveName.setText("");
		txtObjectiveDescription.setText("");
		txtLevel.setText("");
	}

	private void populateTypeBox() {

		cmbbxType.removeAllItems();
		
		ArrayList<ObjectiveType> objTypes = new ArrayList<ObjectiveType>(this.getModel().getObjectiveTypeList());
		for(int i = 0; i < objTypes.size(); i++)
		{
			cmbbxType.addItem(objTypes.get(i));
		}
	}

	// Swaps the selected row either up or down by the value specified in i
	private void swapRows(int i) {
		int start = tblStep.getSelectedRow();
		if (start + i >= 0 && start + i < tblStep.getRowCount()) {
			tableModel.moveRow(start, start, start + i);
		}
	}

	// Returns the string value for name text box
	public String getObjectiveName() {
		return txtObjectiveName.getText();
	}

	// Returns the string value for description text box
	public String getObjectiveDescription() {
		return txtObjectiveDescription.getText();
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

	// Returns a multi dimensional string array for the steps
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
		populateTypeBox();
	}
	
	// Resets the table to be blank with just 1 row in them
	private void resetTable() {
		while (tblStep.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		String[] row = new String[] { "", "" };
		tableModel.addRow(row);
	}
	
	public void setName(String name) {
		txtObjectiveName.setText(name);
	}
	
	public void setLevel(int level) {
		txtLevel.setText("" + level);
	}
	
	public void setDescription(String description) {
		txtObjectiveDescription.setText(description);
	}
	
	public void setSteps(List<Step> stepsList) {

		while (tblStep.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		
		ArrayList<Step> steps = new ArrayList<Step>(stepsList);
		
		for(int i = 0; i < steps.size(); i++)
		{
			Object[] row = new Object[2];
			Step s = steps.get(i);
			
			row[0] = s.getCode();
			row[1] = s.getDescription();
			
			tableModel.addRow(row);
		}
	}

	public ObjectiveType getObjectiveType() {
		return (ObjectiveType)cmbbxType.getSelectedItem();
	}
}
