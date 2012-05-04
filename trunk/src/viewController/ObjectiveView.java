package viewController;

import javax.swing.JButton;

import systemModel.ESDMModel;
import java.awt.event.ActionListener;

public class ObjectiveView extends PanelView {

	private JButton btnAddNewObjective;
	private JButton btnViewObjectives;
	private JButton btnAddObjectiveTo;

	
	
	public ObjectiveView() {
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
		
		btnAddNewObjective = new JButton("Add New Objective");
		btnAddNewObjective.setBounds(10, 11, 152, 23);
		add(btnAddNewObjective);
		
		btnViewObjectives = new JButton("View Objectives");
		btnViewObjectives.setBounds(10, 42, 152, 23);
		add(btnViewObjectives);
		
		btnAddObjectiveTo = new JButton("Add Objective to Child");
		btnAddObjectiveTo.setBounds(10, 76, 152, 23);
		add(btnAddObjectiveTo);
	
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
	

}
