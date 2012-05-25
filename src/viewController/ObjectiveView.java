package viewController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import system.model.ESDMModel;

import java.awt.Font;
import java.awt.event.ActionListener;

public class ObjectiveView extends PanelView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3263162072078805101L;
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
		
		JLabel lblTitle = new JLabel("Objective");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 504, 34);
		add(lblTitle);
		
		btnAddNewObjective = new JButton("Add New Objective");
		btnAddNewObjective.setBounds(20, 70, 200, 40);
		add(btnAddNewObjective);
		
		btnViewObjectives = new JButton("View Objectives");
		btnViewObjectives.setBounds(20, 120, 200, 40);
		add(btnViewObjectives);
		
		btnAddObjectiveTo = new JButton("Add Objective to Child");
		btnAddObjectiveTo.setBounds(20, 170, 200, 40);
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
