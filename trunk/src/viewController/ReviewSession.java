package viewController;

import javax.swing.JButton;

import system.helper.Helper;
import system.marking.Mark;
import system.model.ESDMModel;
import system.sessions.Day;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ReviewSession extends PanelView {


	private static final long serialVersionUID = -271506336391846049L;
	private Day day;
	private MyJTable tblReview;
	private DefaultTableModel tableModel;
	private JLabel lblDate;
	private JLabel lblId;
	private JLabel lblRoomName;
	private JButton btnLogMarks;
	private JButton btnExport;
	private JButton btnEditMark;
	private JButton btnReviewSessions;
	

	public ReviewSession() {
		initialise();
	}

	public ReviewSession(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {
		setLayout(null);

		super.setTitle("Review Marks");

		// Adds a scroll pane to the screen for the table to go in
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 123, 866, 320);
		add(scrollPane);

		// Creates a table model and sets the column names
		tableModel = new DefaultTableModel();

		String[] columnNames = new String[] { "Therapist", "Session", "Child",
				"Objective", "Step", "Mark", "Time", "Comments" };
		tableModel.setColumnIdentifiers(columnNames);
		
		// Creates the table and sets the table within the scrollpane
		tblReview = new MyJTable(tableModel);
		scrollPane.setViewportView(tblReview);

		// Creates and adds labels to the screen
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

		// Creates and adds the Log Marks button to the screen
		btnLogMarks = new JButton("Log more marks \nfor this session");
		btnLogMarks.setBounds(631, 454, 260, 23);
		add(btnLogMarks);
		
		btnExport = new JButton("Save to File");
		btnExport.setBounds(438, 454, 170, 23);
		add(btnExport);
		
		btnEditMark = new JButton("Edit Mark");
		btnEditMark.setBounds(254, 454, 139, 23);
		add(btnEditMark);
		
		btnReviewSessions = new JButton("Review Sessions");
		btnReviewSessions.setBounds(25, 500, 125, 23);
		add(btnReviewSessions);

		// Sets the column widths 
		tblReview.getColumnModel().getColumn(0).setPreferredWidth(40);
		tblReview.getColumnModel().getColumn(1).setPreferredWidth(40);
		tblReview.getColumnModel().getColumn(2).setPreferredWidth(40);
		tblReview.getColumnModel().getColumn(3).setPreferredWidth(40);
		tblReview.getColumnModel().getColumn(4).setPreferredWidth(20);
		tblReview.getColumnModel().getColumn(5).setPreferredWidth(5);
		tblReview.getColumnModel().getColumn(6).setPreferredWidth(40);
		tblReview.getColumnModel().getColumn(7).setPreferredWidth(280);

	}

	// Refreshes the table to the day set in this object
	private void refreshTable() {
		// Sets the id, date and room name
		lblId.setText(day.getId()+"");
		lblDate.setText(Helper.simpleDateFormat(day.getDate()));
		lblRoomName.setText(day.getRoom().getRoomName());

		// Removes all rows from table
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		ArrayList<Mark> marks = new ArrayList<Mark>(day.getMarks());
		SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm:ss a");

		// Iterates through the marks list and adds them to the table
		int size = marks.size();
		for (int i = 0; i < size; i++) {
			Mark tempMark = marks.get(i);

			Object[] rowData = new Object[8];

			rowData[0] = tempMark.getTherapist();
			rowData[1] = tempMark.getSetting();
			rowData[2] = tempMark.getChild();
			rowData[3] = tempMark.getObjective();
			rowData[4] = tempMark.getStep();
			rowData[5] = tempMark;
			rowData[6] = dateFormatter.format(tempMark.getTime().getTime());
			rowData[7] = tempMark.getComment();

			tableModel.addRow(rowData);
		}
	}
	
	public void exportListener(ActionListener al) {
		btnExport.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the Log Mark button
	public void logMarksListener(ActionListener al) {
		btnLogMarks.addActionListener(al);
	}
	
	// Takes in an ActionListener and adds it to the edit mark button
	public void editMarksListener(ActionListener al) {
		btnEditMark.addActionListener(al);
	}
	
	public void reviewSessionsListener(ActionListener al) {
		btnReviewSessions.addActionListener(al);
	}

	// Sets the day to the day parsed through
	public void setDay(Day day) {
		this.day = day;
	}
	
	// Returns the day in the object
	public Day getDay() {
		return day;
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		refreshTable();
	}
	
	// Saves the table to CSV file
	public void saveCSV() throws Exception {
		File f = Helper.chooseFile();
		Helper.exportCSV(f, tblReview);
	}

	public Mark getSelectedMark() {
		int row = tblReview.getSelectedRow();
		if(row == -1)
		{
			return null;
		}
		else
		{
			return (Mark)tableModel.getValueAt(row, 5);
		}
	}
}
