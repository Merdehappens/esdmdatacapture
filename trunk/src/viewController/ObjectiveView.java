package viewController;

import javax.swing.JButton;
import system.marking.Objective;
import system.marking.ObjectiveType;
import system.model.ESDMModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
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

		btnViewObjectives = new JButton("View Objective");
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
		String[] columnNames = new String[] { "ID", "Name", "Type",
				"Level", "# Steps"};

		tableModel.setColumnIdentifiers(columnNames);
		
		TableColumnModel tblColModel = objectiveTable.getColumnModel();
		
		tblColModel.getColumn(0).setPreferredWidth(50);
		tblColModel.getColumn(1).setPreferredWidth(600);
		tblColModel.getColumn(2).setPreferredWidth(150);
		tblColModel.getColumn(3).setPreferredWidth(50);
		tblColModel.getColumn(4).setPreferredWidth(75);
		refreshView();

		
	}

	/**
	 * Adds the parsed through ActionListener to the Add New Objective button.
	 * 
	 *  @param al
	 *    - The action listener we wish to add to the button
	 */
	public void addNewObjective(ActionListener al) {
		btnAddNewObjective.addActionListener(al);
	}

	/**
	 * Adds the parsed through ActionListener to the View Objectives button.
	 * 
	 *  @param al
	 *    - The action listener we wish to add to the button
	 */
	public void viewObjectives(ActionListener al) {
		btnViewObjectives.addActionListener(al);
	}

	/**
	 * Adds the parsed through ActionListener to the Add Objective to Child button.
	 * 
	 *  @param al
	 *    - The action listener we wish to add to the button
	 */
	public void addObjectiveToChild(ActionListener al) {
		btnAddObjectiveTo.addActionListener(al);
	}
	
	/**
	 * Adds the parsed through ActionListener to the Refine Search button.
	 * 
	 *  @param al
	 *    - The action listener we wish to add to the button
	 */
	public void refineSearch(ActionListener al) {
		btnRefineSearch.addActionListener(al);
	}

	/**
	 * Adds the parsed through ActionListener to the Home button.
	 * 
	 *  @param al
	 *    - The action listener we wish to add to the button
	 */
	public void homeListener(ActionListener al) {
		btnHome.addActionListener(al);
	}

	/**
	 * Resets the search parameters to be null
	 */
	public void resetSearch() {
		sorter = new TableRowSorter<DefaultTableModel>(tableModel);
		
		objectiveTable.setRowSorter(sorter);
	}

	/**
	 * Sets the search parameters to be the ones parsed through
	 * 
	 * @param name
	 *  String that is the name of the objective, the String can occur at the start, middle or end of the name
	 * 
	 * @param level
	 *  Integer that is the level of the objective.
	 *
	 * @param type
	 *  Type that specifies which type of objective you are searching for 
	 */
	public void setSearchVariables(String name, int level, ObjectiveType type) {

		ArrayList<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();

		filters.add(RowFilter.regexFilter("(?i).*" + name + ".*"));

		if(level != 0)
		{
			filters.add(RowFilter.numberFilter(ComparisonType.EQUAL, level, 3));
		}
		if(type != null)
		{
			filters.add(RowFilter.regexFilter("(?i).*" + type.getName() + ".*", 2));
		}
		
		
		sorter.setRowFilter(RowFilter.andFilter(filters));
		
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		populateTable();
		resetSearch();
	}

	/**
	 *  Returns the objective for the row that is selected
	 *  
	 *  @return 
	 *   Returns the objective that is selected
	 */
	public Objective getSelectedObjective() throws Exception {

		Objective objective;
		try {
			objective = (Objective) objectives.get(objectiveTable.convertRowIndexToModel(objectiveTable.getSelectedRow()));
			
		
		} catch (Exception e) {
			throw new Exception("30010: You must select an objective first.");
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

			Object[] rowData = new Object[5];

			rowData[0] = objective.getId();
			rowData[1] = objective.getName();
			rowData[2] = objective.getType();
			rowData[3] = objective.getLevel();
			rowData[4] = objective.getSteps().size();

			tableModel.addRow(rowData);

		}

	}
}
