package viewController;

import system.model.ESDMModel;
import system.sessions.Day;
import system.sessions.Setting;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class HomeView extends PanelView {

	private static final long serialVersionUID = -4031028574687279686L;
	private JLabel lblName;
	private JScrollPane scrollPane;
	private JTable tblSession;
	private DefaultTableModel tableModel;
	private JButton btnReviewPastSessions;
	private JButton btnObjectives;
	private JButton btnAccounts;
	private JButton btnReporting;
	private JButton btnChildren;
	private JButton btnAdministration;
	private JButton btnLogData;
	private JButton btnCreateSession;

	public HomeView() {
		initialise();
	}

	public HomeView(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises the graphical objects on the page

	public void initialise() {
		setLayout(null);
		
		JLabel lblWelcomeLabel = new JLabel("Welcome,");
		lblWelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWelcomeLabel.setBounds(10, 115, 1003, 14);
		add(lblWelcomeLabel);
		lblName = new JLabel();
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(10, 140, 1003, 21);
		add(lblName);
		
		JLabel lblSessionsOrganised = new JLabel("Sessions organised for today:");
		lblSessionsOrganised.setHorizontalAlignment(SwingConstants.CENTER);
		lblSessionsOrganised.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSessionsOrganised.setBounds(186, 200, 641, 21);
		add(lblSessionsOrganised);
		
		
		
		btnObjectives = new JButton("Objectives");
		btnObjectives.setBounds(813, 250, 170, 40);
		add(btnObjectives);
		
		btnReporting = new JButton("Reporting");
		btnReporting.setBounds(813, 300, 170, 40);
		add(btnReporting);
		
		btnAccounts = new JButton("Accounts");
		btnAccounts.setBounds(813, 350, 170, 40);
		add(btnAccounts);
		
		btnChildren = new JButton("Children");
		btnChildren.setBounds(813, 400, 170, 40);
		add(btnChildren);
		
		btnCreateSession = new JButton("Create Session");
		btnCreateSession.setBounds(35, 258, 164, 103);
		add(btnCreateSession);
		
		btnReviewPastSessions = new JButton("Review Past Sessions");
		btnReviewPastSessions.setBounds(35, 372, 164, 48);
		add(btnReviewPastSessions);
		
		btnAdministration = new JButton("Administration");
		btnAdministration.setBounds(813, 450, 170, 40);
		add(btnAdministration);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(257, 232, 496, 262);
		add(scrollPane);
		
		// Creates a new JTable
		tblSession = new MyJTable();		
		scrollPane.setViewportView(tblSession);
		
		// Sets the name of the columns
		String[] columnNames = new String[] { "Day ID", "Room Name", "No of children", "Setting" };
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		
		// Sets the model of the table to the new DefaultTableModel
		tblSession.setModel(tableModel);
		
		btnLogData = new JButton("Log data for selected session");
		btnLogData.setBounds(257, 503, 496, 32);
		add(btnLogData);

		

		super.setTitle("Homepage");
	}

	public void createSessionListener(ActionListener al) {
		btnCreateSession.addActionListener(al);
	}
	
	public void logDataListener(ActionListener al) {
		btnLogData.addActionListener(al);
	}
	
	public void reviewPastSessionsListener(ActionListener al) {
		btnReviewPastSessions.addActionListener(al);
	}
	
	public void objectivesListener(ActionListener al) {
		btnObjectives.addActionListener(al);
	}
	
	public void administrationListener(ActionListener al) {
		btnAdministration.addActionListener(al);
	}
	public void accountsListener(ActionListener al) {
		btnAccounts.addActionListener(al);
	}
	
	public void reportingListener(ActionListener al) {
		btnReporting.addActionListener(al);
	}
	
	public void childListener(ActionListener al) {
		btnChildren.addActionListener(al);
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		while(tableModel.getRowCount() > 0)
		{
			tableModel.removeRow(0);
		}
		
		lblName.setText(this.getModel().getCurrentUser().getName());
		
		Calendar currDay = Calendar.getInstance();
		ArrayList<Day> dayList = new ArrayList<Day>(this.getModel().getDays(currDay, currDay));

		int size = dayList.size();
			for(int i = 0; i < size; i++)
			{
				Object[] rowData = new Object[4];
				Day day = dayList.get(i);
				
				rowData[0] = day;
				rowData[1] = day.getRoom();
				rowData[2] = day.getChildren().size();
				
				ArrayList<Setting> settingList = new ArrayList<Setting>(day.getSettings());
				
				for(int x = 0; x < settingList.size(); x++)
				{
					rowData[3] = settingList.get(x);
					tableModel.addRow(rowData);
				}
				
			}
		}
		
	

	public Setting getSelectedSetting() {
		return (Setting) tableModel.getValueAt(tblSession.getSelectedRow(), 3);
	}

	public Day getSelectedDay() throws ArrayIndexOutOfBoundsException {
	
		return (Day) tableModel.getValueAt(tblSession.getSelectedRow(), 0);
	}

	public void setAccess(String adminAccess) {
		
		if(adminAccess.equalsIgnoreCase("a"))
		{
			System.out.println("YES");
			btnAdministration.setVisible(true);
		} else
		{
			System.out.println("NOOOOO");
			btnAdministration.setVisible(false);
		}
		
	}
}
