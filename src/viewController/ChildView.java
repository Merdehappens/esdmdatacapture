package viewController;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
		
		JLabel lblTitle = new JLabel("Child");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 504, 34);
		add(lblTitle);
		
		btnAddChild = new JButton("Add New Child");
		btnAddChild.setBounds(20, 70, 200, 40);
		add(btnAddChild);
		
		btnRetrieveChild = new JButton("Retrieve Child's Details");
		btnRetrieveChild.setBounds(20, 120, 200, 40);
		add(btnRetrieveChild);
		
		btnAddObjectiveChild = new JButton("Add Objective To Child");
		btnAddObjectiveChild.setBounds(20, 170, 200, 40);
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
