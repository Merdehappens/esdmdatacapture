package viewController;

import javax.swing.JButton;
import system.marking.Objective;
import system.model.ESDMModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ObjectiveView extends PanelView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3263162072078805101L;
	private JButton btnAddNewObjective;
	private JButton btnViewObjectives;
	private JButton btnAddObjectiveTo;
	private ArrayList<Objective> objectives;
	private JTable objectiveTable;
	private DefaultTableModel tableModel;

	public ObjectiveView() {
		super();
		initialise();
	}

	public ObjectiveView(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {
		setLayout(null);

		super.setTitle("Objective");

		btnAddNewObjective = new JButton("Add New Objective");
		btnAddNewObjective.setBounds(50, 109, 200, 30);
		add(btnAddNewObjective);

		btnViewObjectives = new JButton("View Objectives");
		btnViewObjectives.setBounds(260, 109, 200, 30);
		add(btnViewObjectives);

		btnAddObjectiveTo = new JButton("Add Objective to Child");
		btnAddObjectiveTo.setBounds(470, 109, 200, 30);
		add(btnAddObjectiveTo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 150, 900, 300);
		add(scrollPane);

		tableModel = new DefaultTableModel();
		objectiveTable = new JTable() {

			private static final long serialVersionUID = 8081316035031635743L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(objectiveTable);

		String[] columnNames = new String[] { "ObjectiveID", "Name",
				"Description", "Level", "Number of Steps" };

		tableModel.setColumnIdentifiers(columnNames);

		objectiveTable.setModel(tableModel);
		refreshView();

	}

	// Takes in an ActionListener and adds it to the Add New Objective button
	public void addNewObjective(ActionListener al) {
		btnAddNewObjective.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the View Objectives button
	public void viewObjectives(ActionListener al) {
		btnViewObjectives.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the Add Objective To Child
	// button
	public void addObjectiveToChild(ActionListener al) {
		btnAddObjectiveTo.addActionListener(al);
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		populateTable();
	}

	public Objective getSelectedObjective() throws Exception {

		Objective objective;
		try {
			objective = (Objective) objectives.get(objectiveTable
					.getSelectedRow());
		} catch (Exception e) {
			throw new Exception("You must select an objective first.");
		}

		return objective;

	}

	private void populateTable() {

		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		objectives = new ArrayList<Objective>(this.getModel()
				.getObjectiveList());

		int size = objectives.size();
		for (int i = 0; i < size; i++) {
			Objective objective = objectives.get(i);

			Object[] rowData = new Object[5];

			rowData[0] = objective.getId();
			rowData[1] = objective.getName();
			rowData[2] = objective.getDescription();
			rowData[3] = objective.getLevel();
			rowData[4] = objective.getSteps().size();

			tableModel.addRow(rowData);

		}

	}
}
