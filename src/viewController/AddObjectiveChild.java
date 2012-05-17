package viewController;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import system.individuals.Child;
import system.marking.Objective;
import system.model.ESDMModel;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;


public class AddObjectiveChild extends PanelView {


	private ESDMModel model;
	private JTextField txtChildName;
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JList<Child> childList;
	private DefaultListModel<Child> childListModel;
	private DefaultListModel<Objective> objectiveListModel;
	private ArrayList<Child> children;
	private ArrayList<Objective> objective;
	private JTextField txtObjective;

	public AddObjectiveChild() {

		initialise();
	
	}
	
	public AddObjectiveChild(ESDMModel model)
	{
		this.model = model;
		
		initialise();
	}
	
	private void initialise()
	{
		setLayout(null);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(157, 353, 89, 23);
		add(btnSubmit);
		
		btnReset = new JButton("Reset");

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtChildName.setText("");
			}
		});


		
		btnReset.setBounds(256, 353, 89, 23);
		add(btnReset);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(355, 353, 89, 23);
		add(btnCancel);
		
		childListModel = new DefaultListModel<Child>();
		
		childList = new JList<Child>(childListModel);
		childList.setBounds(27, 118, 238, 172);
		add(childList);
		

		
		JLabel lblSelectChild = new JLabel("Select Child that you wish to add Objective to");
		lblSelectChild.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectChild.setBounds(24, 93, 241, 14);
		add(lblSelectChild);
		
		JLabel lblSearchChild = new JLabel("Search Child");
		lblSearchChild.setBounds(27, 31, 77, 30);
		add(lblSearchChild);
		
		txtChildName = new JTextField();
		txtChildName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				searchList(txtChildName.getText());
				
			}
		});


		txtChildName.setBounds(100, 31, 162, 30);
		add(txtChildName);
		txtChildName.setColumns(10);
		
		objectiveListModel = new DefaultListModel<Objective>();
		
		JList<Objective> objectiveList = new JList<Objective>(objectiveListModel);
		objectiveList.setBounds(334, 118, 259, 172);
		add(objectiveList);
		
		txtObjective = new JTextField();
		txtObjective.setColumns(10);
		txtObjective.setBounds(431, 31, 162, 30);
		add(txtObjective);
		
		JLabel lblSearchObjective = new JLabel("Search Objective");
		lblSearchObjective.setBounds(337, 31, 107, 30);
		add(lblSearchObjective);

	}
	

	public void setLists(List<Child> childList, List<Objective> objectiveList)
	{
		children = (ArrayList<Child>)childList;
		childListModel.clear();
		txtChildName.setText("");
		
		for(int i = 0; i < children.size(); i++)
		{
			childListModel.addElement(children.get(i));
		}
		
		objective = (ArrayList<Objective>)objectiveList;
		objectiveListModel.clear();
		txtObjective.setText("");
		
		for(int i = 0; i < objective.size(); i++)
		{
			objectiveListModel.addElement(objective.get(i));
		}
		
	}
	
	
	public Child getSelectedChild()
	{
		return childList.getSelectedValue();
	}
	
	public void submitListener(ActionListener al)
	{
		btnSubmit.addActionListener(al);	
	}
	
	public void cancelListener(ActionListener al)
	{
		btnCancel.addActionListener(al);
	}
	
	private void searchList(String text) {
		
		childListModel.clear();
		
		for(int i = 0; i < children.size(); i++)
		{
			Child temp = children.get(i);
			
			if(temp.toString().contains(text))
			{
				childListModel.addElement(temp);
			}
		}
		
	}
}
