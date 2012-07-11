package viewController;

import javax.swing.JButton;

import system.model.ESDMModel;
import system.sessions.Day;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ViewDay extends PanelView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174778855073882231L;
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
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
	
	@SuppressWarnings("serial")
	private void initialise()
	{
		setLayout(null);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				refreshTable();
			}
		});
		
		
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
				resetForm();
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
		scrollPane.setBounds(88, 160, 592, 244);
		add(scrollPane);
		
		
		tblSession = new JTable() {
			
			public boolean isCellEditable(int row, int column){
				return false;
			}
			
		
		};
		
		
		
		scrollPane.setViewportView(tblSession);
		String[] columnNames = new String[] {"ID", "Room Name", "Date"};
		
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		
		tblSession.setModel(tableModel);
		
	}
	
	public void submitListener(ActionListener al)
	{
		btnSubmit.addActionListener(al);
	}

	
	public void cancelListener(ActionListener al)
	{
		btnCancel.addActionListener(al);	
	}

	private void resetForm() {
		dateChooserFrom.setDate(null);
		dateChooserTo.setDate(null);
		chkbxActiveSessions.setSelected(false);
		chkbxCompletedSessions.setSelected(false);
	}
	
	private void refreshTable()
	{
		ArrayList<Day> dayList = new ArrayList<Day>(this.getModel().getDays(dateChooserFrom.getCalendar(), dateChooserTo.getCalendar()));

		while(tblSession.getRowCount() > 0)
		{
			tableModel.removeRow(0);
		}

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YY");
		
		for(int i = 0; i < dayList.size(); i++)
		{
			Object[] row = new Object[3];
			Day temp = dayList.get(i);
			
			row[0] = temp;
			row[1] = temp.getRoom().getRoomName();
			row[2] = dateFormatter.format(temp.getDate().getTime());
			
			tableModel.addRow(row);
		}
		
		tblSession.setModel(tableModel);
		
		
	}

	public Day getDay() {
		return (Day)tableModel.getValueAt(tblSession.getSelectedRow(), 0);
	}

	@Override
	public void refreshView() {
		resetForm();
		refreshTable();
	}
}
