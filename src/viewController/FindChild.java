package viewController;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import systemModel.Child;
import systemModel.ESDMModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;

public class FindChild extends PanelView {


	private ESDMModel model;
	private JTextField txtChildName;
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JList<Child> childList;

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
		
		childList = new JList();
		childList.setBounds(27, 118, 238, 172);
		add(childList);
		
		JLabel lblSelectChild = new JLabel("Select Child that you wish to view details of");
		lblSelectChild.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectChild.setBounds(24, 93, 241, 14);
		add(lblSelectChild);
		
		JLabel lblSearchChild = new JLabel("Search Child");
		lblSearchChild.setBounds(27, 35, 77, 14);
		add(lblSearchChild);
		
		txtChildName = new JTextField();
		txtChildName.setBounds(100, 31, 162, 23);
		add(txtChildName);
		txtChildName.setColumns(10);

	}
	
	public void setList(ListModel<Child> list)
	{
		childList.setModel(list);
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
	
	public String getId()
	{
		return "ds";
	}
	
	
}
