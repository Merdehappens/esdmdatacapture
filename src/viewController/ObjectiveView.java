package viewController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import system.marking.Objective;
import system.model.ESDMModel;

import java.awt.Font;
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
	private JTable table;
	private DefaultTableModel tableModel;
	
	
	public ObjectiveView() {
		super();
		initialise();
	}
	
	public ObjectiveView(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Objective");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 679, 34);
		add(lblTitle);
		
		btnAddNewObjective = new JButton("Add New Objective");
		btnAddNewObjective.setBounds(20, 70, 200, 40);
		add(btnAddNewObjective);
		
		btnViewObjectives = new JButton("View Objectives");
		btnViewObjectives.setBounds(228, 70, 200, 40);
		add(btnViewObjectives);
		
		btnAddObjectiveTo = new JButton("Add Objective to Child");
		btnAddObjectiveTo.setBounds(438, 70, 200, 40);
		add(btnAddObjectiveTo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 140, 677, 248);
		add(scrollPane);
		
		tableModel = new DefaultTableModel();
		table = new JTable();
		scrollPane.setViewportView(table);
		
		String[] columnNames = new String[] {"ID", "Name", "Description", "Level", "Number of Steps"};

		tableModel.setColumnIdentifiers(columnNames);
		
		table.setModel(tableModel);
		refreshView();
	
	}
	
	public void addNewObjective(ActionListener al)
	{
		btnAddNewObjective.addActionListener(al);
	}
	
	
	public void viewObjectives(ActionListener al)
	{
		btnViewObjectives.addActionListener(al);
	}
	
	
	public void addObjectiveToChild(ActionListener al)
	{
		btnAddObjectiveTo.addActionListener(al);
	}

	
	public void refreshView() {
		populateTable();
	}
	
	public Objective getSelectedObjective() throws Exception {

			Objective objective;
			try{
			objective = (Objective)objectives.get(table.getSelectedRow());
			}
			catch(Exception e)
			{
				throw new Exception("You must select an objective first.");
			}
			
			return objective;
		
	}

	private void populateTable() {
		
		while(tableModel.getRowCount() > 0)
		{
			tableModel.removeRow(0);
		}
		
		objectives = new ArrayList<Objective>(this.getModel().getObjectiveList());

		

		for(int i = 0; i < objectives.size(); i++)
		{
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
