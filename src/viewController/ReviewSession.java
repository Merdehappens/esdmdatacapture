package viewController;

import javax.swing.JButton;

import systemModel.Child;
import systemModel.Day;
import systemModel.ESDMModel;
import systemModel.Mark;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReviewSession extends PanelView {
	private String childId;
	private Child child;
	private Day day;
	private JTable tblReview;
	private DefaultTableModel tableModel;
	
	/**
	 * Create the panel.
	 */
	public ReviewSession() {
		setLayout(null);
		

		initialise();
	
	}
	
	public ReviewSession(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		
		JLabel lblTitle = new JLabel("Review Marks taken");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 739, 21);
		add(lblTitle);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveChild();
			}
		});
		btnSave.setBounds(40, 454, 89, 23);
		add(btnSave);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetTextFields();
			}
		});
		btnReset.setBounds(139, 454, 105, 21);
		add(btnReset);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(254, 454, 94, 21);
		add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 123, 724, 320);
		add(scrollPane);
		
		
		
		tableModel = new DefaultTableModel();
		
		String[] columnNames = new String[] {"ID", "Therapist", "Session", "Child", "Objective", "Step", "Mark", "Comments"};
		tableModel.setColumnIdentifiers(columnNames);
		
		tblReview = new JTable(tableModel);
		scrollPane.setViewportView(tblReview);
		
		
	}
	
	private void refreshTable()
	{
		ArrayList<Mark> marks = (ArrayList<Mark>) day.getMarks();
		
		for(int i = 0; i < marks.size(); i++)
		{
			Mark tempMark = marks.get(i);
			
			Object[] rowData = new Object[8];
			
			rowData[0] = tempMark;
			rowData[1] = tempMark.getTherapist();
			rowData[2] = tempMark.getSession();
			rowData[3] = tempMark.getChild();
			rowData[4] = tempMark.getObjective();
			rowData[5] = tempMark.getStep();
			rowData[6] = tempMark.getMark();
			rowData[7] = tempMark.getComment();
			
			tableModel.addRow(rowData);
			
		}
		
	}
	
	private void resetTextFields() {

		
	}


	private void saveChild() {

	}
	
	
	public void refreshView()
	{

	}

	public void setDay(Day day) {
		this.day = day;
		refreshTable();
	}
}
