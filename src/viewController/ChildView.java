package viewController;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import system.model.ESDMModel;

public class ChildView extends PanelView {

	//testing
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4321169254400022059L;
	JButton btnAddChild;
	JButton btnRetrieveChild;
	JButton btnAddObjectiveChild;
	
	public ChildView() {
		initialise();
	
	}
	
	public ChildView(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		setLayout(null);
		
		btnAddChild = new JButton("Add New Child");
		btnAddChild.setBounds(10, 11, 200, 30);
		add(btnAddChild);
		
		btnRetrieveChild = new JButton("Retrieve Childs Details");
		btnRetrieveChild.setBounds(10, 45, 200, 30);
		add(btnRetrieveChild);
		
		btnAddObjectiveChild = new JButton("Add Objective To Child");
		btnAddObjectiveChild.setBounds(10, 79, 200, 30);
		add(btnAddObjectiveChild);
	
	}
	
	public void addChildListener(ActionListener al)
	{
		btnAddChild.addActionListener(al);	
	}
	
	public void retrieveChildListener(ActionListener al)
	{
		btnRetrieveChild.addActionListener(al);
	}
	
	public void addObjectiveChildListener(ActionListener al)
	{
		btnAddObjectiveChild.addActionListener(al);
	}

	

}
