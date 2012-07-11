package viewController;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import system.helper.Helper;
import system.individuals.Child;
import system.model.ESDMModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ChildViewGrid extends PanelView {

	//testing
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4321169254400022059L;
	private JButton btnAddChild;
	private JButton btnEditChild;
	private JButton btnAddObjectiveChild;
	private JButton btnRemoveChild;
	private JTable table;
	private DefaultTableModel childTableModel;
	
	
	public ChildViewGrid() {
		super();
		initialise();
	
	}
	
	public ChildViewGrid(ESDMModel model)
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
		btnAddChild.setBounds(20, 70, 126, 34);
		add(btnAddChild);
		
		btnEditChild = new JButton("Edit Child");
		btnEditChild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEditChild.setBounds(156, 70, 126, 34);
		add(btnEditChild);
		
		btnAddObjectiveChild = new JButton("Add Objective To Child");
		btnAddObjectiveChild.setBounds(289, 70, 146, 34);
		add(btnAddObjectiveChild);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 127, 518, 273);
		add(scrollPane);
		
		childTableModel = new DefaultTableModel();
		table = new JTable();
		scrollPane.setViewportView(table);
		
		String[] columnNames = new String[] {"ID", "Name", "DOB", "Date Joined"};

		childTableModel.setColumnIdentifiers(columnNames);
		
		table.setModel(childTableModel);
		
		btnRemoveChild = new JButton("Remove Child");
		btnRemoveChild.setBounds(445, 70, 97, 34);
		add(btnRemoveChild);
		
	}
	
	public void refreshView()
	{
		populateTable();
		
	}
	
	private void populateTable()
	{
		
			while(childTableModel.getRowCount() > 0)
			{
				childTableModel.removeRow(0);
			}
			
			ArrayList<Child> childs = (ArrayList<Child>) this.getModel().getChildList(true);
	

			for(int i = 0; i < childs.size(); i++)
			{
				Child tempChild = childs.get(i);
				
				Object[] rowData = new Object[4];
				
				rowData[0] = tempChild.getId();
				rowData[1] = tempChild;
				rowData[2] = Helper.simpleDateFormat(tempChild.getDob());
				rowData[3] = Helper.simpleDateFormat(tempChild.getDateJoined());
				
				childTableModel.addRow(rowData);
				
			}
	}
	
	public void addChildListener(ActionListener al)
	{
		btnAddChild.addActionListener(al);	
	}
	
	public void removeChildListener(ActionListener al)
	{
		btnRemoveChild.addActionListener(al);
	}

	
	public void addObjectiveChildListener(ActionListener al)
	{
		btnAddObjectiveChild.addActionListener(al);
	}
	
	public Child getSelectedChild() throws Exception
	{
		Child child;
		try{
		child = (Child)childTableModel.getValueAt(table.getSelectedRow(), 1);
		}
		catch(Exception e)
		{
			throw new Exception("You must select a child first.");
		}
		
		return child;
	}

	public void editChildListener(ActionListener al) 
	{
		btnEditChild.addActionListener(al);
	}
}
