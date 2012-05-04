package viewController;

import javax.swing.JButton;

import systemModel.ESDMModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ViewSession extends PanelView {

	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JTable dayTable;
	private JDateChooser dateChooserFrom;
	private JDateChooser dateChooserTo;
	
	/**
	 * Create the panel.
	 */
	public ViewSession() {
		setLayout(null);
		

		initialise();
	
	}
	
	public ViewSession(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		
		JLabel lblTitle = new JLabel("View Session");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 661, 21);
		add(lblTitle);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(71, 465, 89, 23);
		add(btnSubmit);
		
		btnReset = new JButton("Reset Search Criteria");
		btnReset.setBounds(170, 465, 166, 21);
		add(btnReset);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(346, 466, 94, 21);
		add(btnCancel);
		
		JLabel lblRefineSearch = new JLabel("Refine Search:");
		lblRefineSearch.setBounds(10, 58, 99, 14);
		add(lblRefineSearch);
		
		JCheckBox chckbxOnlyActiveSessions = new JCheckBox("Only Active Sessions");
		chckbxOnlyActiveSessions.setBounds(10, 89, 157, 21);
		add(chckbxOnlyActiveSessions);
		
		JCheckBox chckbxOnlyCompletedSessions = new JCheckBox("Only Completed Sessions");
		chckbxOnlyCompletedSessions.setBounds(10, 118, 157, 21);
		add(chckbxOnlyCompletedSessions);
		
		JLabel lblFromDate = new JLabel("From Date:");
		lblFromDate.setBounds(215, 58, 74, 14);
		add(lblFromDate);
		
		JButton btnTodaysDate = new JButton("Todays Date");
		btnTodaysDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Calendar cal = Calendar.getInstance();
				dateChooserFrom.setCalendar(cal);
				dateChooserTo.setCalendar(cal);
				
			}
		});
		btnTodaysDate.setBounds(505, 54, 112, 51);
		add(btnTodaysDate);
		
		JLabel lblToDate = new JLabel("To Date:");
		lblToDate.setBounds(215, 90, 74, 18);
		add(lblToDate);
		
		dateChooserFrom = new JDateChooser();
		dateChooserFrom.setBounds(283, 43, 157, 35);
		add(dateChooserFrom);
		
		dateChooserTo = new JDateChooser();
		dateChooserTo.setBounds(283, 90, 157, 35);
		add(dateChooserTo);
		
		dayTable = new JTable();
		dayTable.setBounds(10, 148, 646, 273);
		add(dayTable);
	}
}
