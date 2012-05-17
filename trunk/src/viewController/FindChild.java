package viewController;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import system.individuals.Child;
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

public class FindChild extends PanelView {


	private ESDMModel model;
	private JTextField txtChildName;
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JList<Child> childList;
	private DefaultListModel<Child> childListModel;
	private ArrayList<Child> children;

	public FindChild() {

		initialise();
	
	}
	
	public FindChild(ESDMModel model)
	{
		this.model = model;
		
		initialise();
	}
	
	private void initialise()
	{
		setLayout(null);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 316, 89, 23);
		add(btnSubmit);
		
		btnReset = new JButton("Reset");

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtChildName.setText("");
			}
		});


		
		btnReset.setBounds(111, 316, 89, 23);
		add(btnReset);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(210, 316, 89, 23);
		add(btnCancel);
		
		childListModel = new DefaultListModel<Child>();
		
		childList = new JList<Child>(childListModel);
		childList.setBounds(27, 118, 238, 172);
		add(childList);
		

		
		JLabel lblSelectChild = new JLabel("Select Child that you wish to view details of");
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

	}
	

	public void setChildren(List<Child> list)
	{
		children = (ArrayList<Child>)list;
		childListModel.clear();
		txtChildName.setText("");
		
		for(int i = 0; i < children.size(); i++)
		{
			childListModel.addElement(children.get(i));
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
