package viewController;

import system.model.ESDMModel;
import system.sessions.Day;
import system.sessions.Setting;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class HomeView extends PanelView {

	private static final long serialVersionUID = -4031028574687279686L;
	private JLabel lblName;
	private JLabel lblSessionList;
	private JButton btnReviewPastSessions;

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
		
		lblSessionList = new JLabel("");
		lblSessionList.setVerticalAlignment(SwingConstants.TOP);
		lblSessionList.setHorizontalAlignment(SwingConstants.CENTER);
		lblSessionList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSessionList.setBounds(196, 232, 631, 281);
		add(lblSessionList);
		
		JButton btnObjectives = new JButton("Objectives");
		btnObjectives.setBounds(849, 250, 164, 40);
		add(btnObjectives);
		
		JButton btnReporting = new JButton("Reporting");
		btnReporting.setBounds(849, 301, 164, 40);
		add(btnReporting);
		
		JButton btnAccounts = new JButton("Accounts");
		btnAccounts.setBounds(849, 352, 164, 40);
		add(btnAccounts);
		
		JButton btnChildren = new JButton("Children");
		btnChildren.setBounds(846, 403, 167, 40);
		add(btnChildren);
		
		JButton btnCreateSession = new JButton("Create Session");
		btnCreateSession.setBounds(10, 259, 164, 103);
		add(btnCreateSession);
		
		btnReviewPastSessions = new JButton("Review Past Sessions");
		btnReviewPastSessions.setBounds(10, 373, 164, 48);
		add(btnReviewPastSessions);
		
		super.setTitle("Homepage");
	}
	
	public void reviewPastSessionsListener(ActionListener al) {
		btnReviewPastSessions.addActionListener(al);
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		lblName.setText(this.getModel().getCurrentUser().getName());
		Calendar currDay = Calendar.getInstance();
		ArrayList<Day> dayList = new ArrayList<Day>(this.getModel().getDays(currDay, currDay));
		String sessions = "";
		ArrayList<Setting> settingList;
		int size = dayList.size();
		if(size == 0)
		{
			sessions = "There are no sessions organised for today.";
		}
		else
		{
			sessions = "<html> <p align='center'>";
			for(int i = 0; i < dayList.size(); i++)
			{
				sessions = sessions + dayList.get(i).getRoom() + ":<br>";
				settingList = new ArrayList<Setting>(dayList.get(i).getSettings());
				for(int x = 0; x < settingList.size(); x++)
				{
					sessions = sessions + settingList.get(x) + "<br>";
				}
				sessions = sessions + "<br>";
			}
			sessions = sessions + "</p></html>";
		}
		lblSessionList.setText(sessions);
	}
}
