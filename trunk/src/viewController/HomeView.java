package viewController;

import system.model.ESDMModel;
import system.sessions.Day;
import system.sessions.Session;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.SwingConstants;

public class HomeView extends PanelView {

	private static final long serialVersionUID = -4031028574687279686L;
	private JLabel lblName;
	private JLabel lblSessionList;

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
		lblSessionsOrganised.setBounds(10, 200, 1003, 21);
		add(lblSessionsOrganised);
		
		lblSessionList = new JLabel("");
		lblSessionList.setVerticalAlignment(SwingConstants.TOP);
		lblSessionList.setHorizontalAlignment(SwingConstants.CENTER);
		lblSessionList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSessionList.setBounds(10, 232, 1003, 281);
		add(lblSessionList);
		super.setTitle("Homepage");
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		lblName.setText(this.getModel().getCurrentUser().getName());
		Calendar currDay = Calendar.getInstance();
		ArrayList<Day> dayList = new ArrayList<Day>(this.getModel().getDays(currDay, currDay));
		String sessions = "";
		ArrayList<Session> sessionList;
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
				sessionList = new ArrayList<Session>(dayList.get(i).getSessions());
				for(int x = 0; x < sessionList.size(); x++)
				{
					sessions = sessions + sessionList.get(x) + "<br>";
				}
				sessions = sessions + "<br>";
			}
			sessions = sessions + "</p></html>";
		}
		lblSessionList.setText(sessions);
	}
}
