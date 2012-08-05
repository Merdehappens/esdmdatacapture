package viewController;

import javax.swing.JButton;

import system.individuals.Child;
import system.model.ESDMModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditChild extends PanelView {
	
	//test
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3129950176718443795L;
	private JTextField txtName;
	private Child child;
	private JDateChooser dateJoinedChooser;
	private JDateChooser dobChooser;
	private JLabel txtId;
	private JButton btnCancel;
	private JButton btnSave;
	
	/**
	 * Create the panel.
	 */
	public EditChild() {
		super();
		initialise();
	
	}
	
	public EditChild(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		setLayout(null);
		
		super.setTitle("Edit Child");
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 66, 46, 30);
		add(lblName);
		
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth:");
		lblDateOfBirth.setBounds(10, 107, 73, 30);
		add(lblDateOfBirth);
		
		JLabel lblDateJoined = new JLabel("Date Joined:");
		lblDateJoined.setBounds(10, 148, 73, 30);
		add(lblDateJoined);
		
		txtName = new JTextField();
		txtName.setBounds(66, 63, 205, 30);
		add(txtName);
		txtName.setColumns(10);
		
		dobChooser = new JDateChooser();
		dobChooser.setBounds(87, 107, 184, 30);
		add(dobChooser);
		
		dateJoinedChooser = new JDateChooser();
		dateJoinedChooser.setBounds(87, 148, 184, 30);
		add(dateJoinedChooser);
		
		btnSave = new JButton("Save");

		btnSave.setBounds(10, 189, 89, 23);
		add(btnSave);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshView();
			}
		});
		btnReset.setBounds(109, 189, 105, 21);
		add(btnReset);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(224, 189, 94, 21);
		add(btnCancel);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(10, 33, 46, 30);
		add(lblId);
		
		txtId = new JLabel("");
		txtId.setBounds(40, 33, 216, 30);
		add(txtId);
		
	}
	
	public void saveChildListener(ActionListener al)
	{
		btnSave.addActionListener(al);
	}
	
	public void cancelListener(ActionListener al)
	{
		btnCancel.addActionListener(al);
	}

	public void setId(String childId)
	{
		try {
			setChild(this.getModel().viewChild(childId));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public Child getChild()
	{
		return child;
	}
	
	
	public void setChild(Child child)
	{
		this.child = child;
		refreshView();
	}
	
	public Object[] getInformation()
	{
		Object[] temp = new Object[3];
		temp[0] = txtName.getText();
		temp[1] = dobChooser.getCalendar();
		temp[2] = dateJoinedChooser.getCalendar();
		
		return temp;
	}
	
	public void refreshView()
	{
		dobChooser.setCalendar(child.getDob());
		dateJoinedChooser.setCalendar(child.getDateJoined());
		txtId.setText(child.getId());
		txtName.setText(child.getName());
	}
	
}
