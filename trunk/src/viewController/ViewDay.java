package viewController;

import javax.swing.JButton;

import systemModel.Day;
import systemModel.ESDMModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ViewDay extends PanelView {

	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private DefaultTableModel tblDayModel; 
	private JDateChooser dateChooserFrom;
	private JDateChooser dateChooserTo;
	private JCheckBox chkbxActiveSessions;
	private JCheckBox chkbxCompletedSessions;
	private JScrollPane scrollPane;
	private JTable tblSession;
	private DefaultTableModel tableModel;
	
	/**
	 * Create the panel.
	 */
	public ViewDay() {

		initialise();
	
	}
	
	public ViewDay(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		setLayout(null);
		
		
		JLabel lblTitle = new JLabel("View Session");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 661, 21);
		add(lblTitle);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(71, 465, 89, 23);
		add(btnSubmit);
		
		btnReset = new JButton("Reset Search Criteria");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshForm();
			}
		});
		btnReset.setBounds(170, 465, 166, 21);
		add(btnReset);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(346, 466, 94, 21);
		add(btnCancel);
		
		JLabel lblRefineSearch = new JLabel("Refine Search:");
		lblRefineSearch.setBounds(10, 58, 99, 14);
		add(lblRefineSearch);
		
		chkbxActiveSessions = new JCheckBox("Only Active Sessions");
		chkbxActiveSessions.setBounds(10, 89, 157, 21);
		add(chkbxActiveSessions);
		
		chkbxCompletedSessions = new JCheckBox("Only Completed Sessions");
		chkbxCompletedSessions.setBounds(10, 118, 157, 21);
		add(chkbxCompletedSessions);
		
		JLabel lblFromDate = new JLabel("From Date:");
		lblFromDate.setBounds(215, 43, 74, 35);
		add(lblFromDate);
		
		JButton btnTodaysDate = new JButton("Todays Date");
		btnTodaysDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Calendar cal = Calendar.getInstance();
				dateChooserFrom.setCalendar(cal);
				dateChooserTo.setCalendar(cal);
				
			}
		});
		btnTodaysDate.setBounds(504, 43, 112, 51);
		add(btnTodaysDate);
		
		JLabel lblToDate = new JLabel("To Date:");
		lblToDate.setBounds(215, 89, 74, 36);
		add(lblToDate);
		
		dateChooserFrom = new JDateChooser();
		dateChooserFrom.setBounds(283, 43, 157, 35);
		add(dateChooserFrom);
		
		dateChooserTo = new JDateChooser();
		dateChooserTo.setBounds(283, 90, 157, 35);
		add(dateChooserTo);
		
		
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshTable();
			}
		});
		btnSearch.setBounds(498, 105, 118, 21);
		add(btnSearch);
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(202, 160, 478, 244);
		add(scrollPane);
		
		
		tblSession = new JTable();
		scrollPane.setViewportView(tblSession);
		String[] columnNames = new String[] {"ID", "Room Name", "Date"};
		
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		
		tblSession.setModel(tableModel);
		
		String[] tempRow = new String[] {"", "", ""};
		tableModel.addRow(tempRow);
		
	}

	private void refreshForm() {
		dateChooserFrom.setDate(null);
		dateChooserTo.setDate(null);
		chkbxActiveSessions.setSelected(false);
		chkbxCompletedSessions.setSelected(false);
	}
	
	private void refreshTable()
	{
		ArrayList<Day> dayList = (ArrayList<Day>)this.getModel().getDayList();

		while(tblSession.getRowCount() > 0)
		{
			tableModel.removeRow(0);
		}
		
		for(int i = 0; i < dayList.size(); i++)
		{
			String[] row = new String[3];
			Day temp = dayList.get(i);
			
			row[0] = temp.getId();
			row[1] = temp.getRoom().getRoomName();
			row[2] = temp.getDate().toString();
			
			tableModel.addRow(row);
		}
		
		tblSession.setModel(tableModel);
		
	}
}
