package viewController;

import javax.swing.JButton;
import system.marking.Objective;
import system.model.ESDMModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;

public class ObjectiveView extends PanelView {

	private static final long serialVersionUID = 3263162072078805101L;
	private JButton btnAddNewObjective;
	private JButton btnViewObjectives;
	private JButton btnAddObjectiveTo;
	private ArrayList<Objective> objectives;
	private MyJTable objectiveTable;
	private DefaultTableModel tableModel;
	private JButton btnResetSearch;
	private JButton btnRefineSearch;
	private TableRowSorter<DefaultTableModel> sorter;
	private JButton btnHome;

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
		// Sets the title of the page
		super.setTitle("Objective");

		// Creates 3 new buttons and adds them to the page
		btnAddNewObjective = new JButton("Add New Objective");
		btnAddNewObjective.setBounds(50, 90, 200, 35);
		add(btnAddNewObjective);

		btnViewObjectives = new JButton("View Objectives");
		btnViewObjectives.setBounds(260, 90, 200, 35);
		add(btnViewObjectives);

		btnAddObjectiveTo = new JButton("Add Objective to Child");
		btnAddObjectiveTo.setBounds(470, 90, 186, 35);
		add(btnAddObjectiveTo);

		// Creates a new scrollpane and adds it to the screen
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 135, 900, 390);
		add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		// creates a new table model, and table and adds the table to the scroll pane
		tableModel = new DefaultTableModel();
		objectiveTable = new MyJTable();
		scrollPane.setViewportView(objectiveTable);
		objectiveTable.setModel(tableModel);
		

		sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		objectiveTable.setRowSorter(sorter);
		
		
		
		btnRefineSearch = new JButton("Refine Search");
		btnRefineSearch.setBounds(666, 90, 142, 35);
		add(btnRefineSearch);
		
		btnResetSearch = new JButton("Reset Search");
		btnResetSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetSearch();
			}
		});
		btnResetSearch.setBounds(818, 90, 142, 35);
		add(btnResetSearch);
		
		btnHome = new JButton("Home");
		btnHome.setBounds(20, 536, 89, 23);
		add(btnHome);

		// sets the column names to the string array
		String[] columnNames = new String[] { "ID", "Name",
				/*"Description",*/ "Level", "# Steps" };

		tableModel.setColumnIdentifiers(columnNames);
		
		TableColumnModel tblColModel = objectiveTable.getColumnModel();
		
		tblColModel.getColumn(0).setPreferredWidth(50);
		tblColModel.getColumn(1).setPreferredWidth(700);
		tblColModel.getColumn(2).setPreferredWidth(50);
		tblColModel.getColumn(3).setPreferredWidth(75);
		//tblColModel.getColumn(4).setPreferredWidth(50);
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
	
	// Takes in an action listener and adds it to the refine search button
	public void refineSearch(ActionListener al) {
		btnRefineSearch.addActionListener(al);
	}
	
	public void homeListener(ActionListener al) {
		btnHome.addActionListener(al);
	}
	
	public void resetSearch() {
		sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		objectiveTable.setRowSorter(sorter);
	}
	
	public void setSearchVariables(String name, int level) {

		ArrayList<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();

		filters.add(RowFilter.regexFilter("(?i).*" + name + ".*"));
		
		if(level != 0)
		{
			filters.add(RowFilter.numberFilter(ComparisonType.EQUAL, level, 2));
		}
		
		sorter.setRowFilter(RowFilter.andFilter(filters));
		
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		populateTable();
		resetSearch();
	}

	// Returns the objective for the row that is selected
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

		// Removes all rows from table
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		// Gets the objective list from the model
		objectives = new ArrayList<Objective>(this.getModel()
				.getObjectiveList());

		// Iterates through the objectives list adding the details to the table
		int size = objectives.size();
		for (int i = 0; i < size; i++) {
			Objective objective = objectives.get(i);

			Object[] rowData = new Object[4];//new Object[5];

			rowData[0] = objective.getId();
			rowData[1] = objective.getName();
			//rowData[2] = objective.getDescription();
			rowData[2] = objective.getLevel();
			rowData[3] = objective.getSteps().size();

			tableModel.addRow(rowData);

		}

	}
}
