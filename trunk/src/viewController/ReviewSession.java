package viewController;

import javax.swing.JButton;

import system.helper.Helper;
import system.marking.Mark;
import system.model.ESDMModel;
import system.sessions.Day;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ReviewSession extends PanelView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -271506336391846049L;
	private Day day;
	private JTable tblReview;
	private DefaultTableModel tableModel;
	private JLabel lblDate;
	private JLabel lblId;
	private JLabel lblRoomName;
	private JButton btnLogMarks;
	private JButton btnSave;
	
	/**
	 * Create the panel.
	 */
	public ReviewSession() {
		initialise();
	
	}
	
	public ReviewSession(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		setLayout(null);

		super.setTitle("Review Marks");
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveMarks();
			}
		});
		btnSave.setBounds(40, 454, 89, 23);
		add(btnSave);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetTextFields();
				refreshTable();
			}
		});
		btnReset.setBounds(139, 454, 105, 21);
		add(btnReset);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 123, 866, 320);
		add(scrollPane);
		
		
		
		tableModel = new DefaultTableModel();
		
		String[] columnNames = new String[] {"Therapist", "Session", "Child", "Objective", "Step", "Mark", "Time", "Comments"};
		tableModel.setColumnIdentifiers(columnNames);
		
		tblReview = new JTable(tableModel);
		scrollPane.setViewportView(tblReview);
		
		JLabel lblSessionId = new JLabel("Session ID:");
		lblSessionId.setBounds(10, 43, 92, 14);
		add(lblSessionId);
		
		JLabel lblSessionDate = new JLabel("Date:");
		lblSessionDate.setBounds(10, 68, 92, 14);
		add(lblSessionDate);
		
		JLabel lblRoom = new JLabel("Room:");
		lblRoom.setBounds(10, 93, 94, 14);
		add(lblRoom);
		
		lblId = new JLabel("");
		lblId.setBounds(83, 43, 78, 14);
		add(lblId);
		
		lblDate = new JLabel("");
		lblDate.setBounds(83, 68, 139, 14);
		add(lblDate);
		
		lblRoomName = new JLabel("");
		lblRoomName.setBounds(83, 93, 105, 14);
		add(lblRoomName);
		
		btnLogMarks = new JButton("Log more marks \nfor this session");
		btnLogMarks.setBounds(489, 454, 260, 21);
		add(btnLogMarks);
		
		tblReview.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblReview.getColumnModel().getColumn(1).setPreferredWidth(40);
		tblReview.getColumnModel().getColumn(2).setPreferredWidth(40);
		tblReview.getColumnModel().getColumn(3).setPreferredWidth(40);
		tblReview.getColumnModel().getColumn(4).setPreferredWidth(20);
		tblReview.getColumnModel().getColumn(5).setPreferredWidth(5);
		tblReview.getColumnModel().getColumn(6).setPreferredWidth(30);
		tblReview.getColumnModel().getColumn(7).setPreferredWidth(300);

	}
	
	private void refreshTable()
	{
		lblId.setText(day.getId());
		
		
		lblDate.setText(Helper.simpleDateFormat(day.getDate()));
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
			
			Object[] rowData = new Object[8];
			
			rowData[0] = tempMark.getTherapist();
			rowData[1] = tempMark.getSession();
			rowData[2] = tempMark.getChild();
			rowData[3] = tempMark.getObjective();
			rowData[4] = tempMark.getStep();
			rowData[5] = tempMark;
			rowData[6] = dateFormatter.format(tempMark.getTime().getTime());
			rowData[7] = tempMark.getComment();
			
			tableModel.addRow(rowData);
			
		}
		
	}
	
	private void resetTextFields() {

		
	}


	private void saveMarks() {
		
		for(int i = 0; i < tableModel.getRowCount(); i++)
		{
			Mark m = (Mark)tableModel.getValueAt(i, 5);
			m.setComments((String)tableModel.getValueAt(i, 7));
			
		}
		
	}
	
	public void saveListener(ActionListener al)
	{
		btnSave.addActionListener(al);
	}
	
	public void logMarksListener(ActionListener al)
	{
		btnLogMarks.addActionListener(al);
	}
	
	
	public void setDay(Day day) {
		this.day = day;
	}

	public Day getDay() {
		return day;
	}

	@Override
	public void refreshView() {
		resetTextFields();
		refreshTable();
	}
}
