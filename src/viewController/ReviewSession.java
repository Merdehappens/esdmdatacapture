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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReviewSession extends PanelView {

	private Day day;
	private JTable tblReview;
	private DefaultTableModel tableModel;
	private JLabel lblDate;
	private JLabel lblId;
	private JLabel lblRoomName;
	
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
		
		String[] columnNames = new String[] {"ID", "Therapist", "Session", "Child", "Objective", "Step", "Mark", "Time", "Comments"};
		tableModel.setColumnIdentifiers(columnNames);
		
		tblReview = new JTable(tableModel);
		scrollPane.setViewportView(tblReview);
		
		JLabel lblSessionId = new JLabel("Session ID:");
		lblSessionId.setBounds(10, 43, 92, 14);
		add(lblSessionId);
		
		JLabel lblSessionDate = new JLabel("Date");
		lblSessionDate.setBounds(10, 68, 92, 14);
		add(lblSessionDate);
		
		JLabel lblRoom = new JLabel("Room:");
		lblRoom.setBounds(10, 93, 94, 14);
		add(lblRoom);
		
		lblId = new JLabel("");
		lblId.setBounds(83, 43, 78, 14);
		add(lblId);
		
		lblDate = new JLabel("");
		lblDate.setBounds(83, 68, 78, 14);
		add(lblDate);
		
		lblRoomName = new JLabel("");
		lblRoomName.setBounds(83, 93, 78, 14);
		add(lblRoomName);
		
		
	}
	
	private void refreshTable()
	{
		lblId.setText(day.getId());
		lblDate.setText(day.getDate().getDate() + "/" + day.getDate().getMonth() + "/" + (day.getDate().getYear() + 1900));
		lblRoomName.setText(day.getRoom().getRoomName());
		
		while(tableModel.getRowCount() > 0)
		{
			tableModel.removeRow(0);
		}
		
		ArrayList<Mark> marks = (ArrayList<Mark>) day.getMarks();
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm:ss a");
		
		for(int i = 0; i < marks.size(); i++)
		{
			Mark tempMark = marks.get(i);
			
			Object[] rowData = new Object[9];
			
			rowData[0] = tempMark;
			rowData[1] = tempMark.getTherapist();
			rowData[2] = tempMark.getSession();
			rowData[3] = tempMark.getChild();
			rowData[4] = tempMark.getObjective();
			rowData[5] = tempMark.getStep();
			rowData[6] = tempMark.getMark();
			rowData[7] = dateFormatter.format(tempMark.getTime().getTime());
			rowData[8] = tempMark.getComment();
			
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
