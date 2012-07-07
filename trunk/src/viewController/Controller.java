package viewController;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import system.individuals.Child;
import system.individuals.Guardian;
import system.model.ESDMModel;
import system.model.Room;
import system.sessions.Session;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//testing
/**
 * @author Damian
 *
 */
public class Controller extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6281745567153858417L;

	private ESDMModel model;
	
	private JPanel contentPane;
	private JPanel homePanel;
	private JPanel sessionPanel;
	private JPanel childPanel;
	private JPanel objectivePanel;
	private JPanel reportingPanel;
	private JPanel accountPanel;
	private JTabbedPane tabbedPane;
	private EditChild editChild;
	private FindChild findChild;
	private FindChild findChildReport;
	private SessionView sessionView;
	private ChildView childView;
	private ViewDay viewDay;
	private ObjectiveView objectiveView;
	private AddObjective addObjective;
	private LogSessionData logSessionData;
	private HomeView homeView;
	private AddDay addDay;
	private AddChild addChild;
	private ReportingView reportingView;
	private AccountView accountView;
	private ChangePassword changePassword;
	private ChangeEmail changeEmail;
	private AddObjectiveChild addObjectiveChild;
	private ReviewSession reviewSession;
	private ViewReport viewReport;
	private NewUserAccount newUserAccount;
	private ChildViewGrid childViewGrid;
	
	
	
	public Controller() throws MalformedURLException {
		setLookAndFeel();
		model = new ESDMModel();
		initComponents();
		initButtonListeners();
	}
	
	public Controller(ESDMModel model) throws MalformedURLException {
		setLookAndFeel();
		this.model = model;
		initComponents();
		initButtonListeners();
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller frame = new Controller();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	// Initialises all of the Graphical information in the class
	
	public void initComponents()
	{
		
		//Sets the main frame Title, and bounds.

		setTitle("ESDM Data Capture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 979, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Set the main layout of the project (tabbed pane)

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setBorder(null);
		
		tabbedPane.setBounds(0, 0, 963, 613);
		contentPane.add(tabbedPane);
		
		
		
		//Add the 6 main panels to the program and then add them to the tabbed pane.
			
		homePanel = new JPanel();
		tabbedPane.addTab("Home", null, homePanel, null);
		homePanel.setLayout(new CardLayout(0, 0));
		
		sessionPanel = new JPanel();
		tabbedPane.addTab("Session", null, sessionPanel, null);
		sessionPanel.setLayout(new CardLayout(0, 0));

		childPanel = new JPanel();
		tabbedPane.addTab("Child", null, childPanel, null);
		childPanel.setLayout(new CardLayout(0, 0));

		objectivePanel = new JPanel();
		tabbedPane.addTab("Objectives", null, objectivePanel, null);
		objectivePanel.setLayout(new CardLayout(0, 0));
		
		reportingPanel = new JPanel();
		tabbedPane.addTab("Reporting", null, reportingPanel, null);
		reportingPanel.setLayout(new CardLayout(0, 0));
		
		accountPanel = new JPanel();
		tabbedPane.addTab("Account Managment", null, accountPanel, null);
		accountPanel.setLayout(new CardLayout(0, 0));
		

		
		
		
		//Add all the panels (Cards) to the Session Tab

		homeView = new HomeView();
		homePanel.add(homeView, "Home");

		
		//Add all the panels (Cards) to the Session Tab
		
		sessionView = new SessionView();
		sessionPanel.add(sessionView, "Session");

		addDay = new AddDay(model);
		sessionPanel.add(addDay, "addDay");

		viewDay = new ViewDay(model);
		sessionPanel.add(viewDay, "viewDay");
		
		reviewSession = new ReviewSession();
		sessionPanel.add(reviewSession, "reviewSession");

		logSessionData = new LogSessionData(model);
		sessionPanel.add(logSessionData, "logSessionData");


		//Add all the panels (Cards) to the Child Tab

		childView = new ChildView();
		childPanel.add(childView, "Child");
		
		childViewGrid = new ChildViewGrid(model);
		childPanel.add(childViewGrid, "ChildViewGrid");

		addChild = new AddChild(model);
		childPanel.add(addChild, "addChild");

		editChild = new EditChild();
		childPanel.add(editChild, "editChild");

		findChild = new FindChild(model);
		childPanel.add(findChild, "findChild");
		
		addObjectiveChild = new AddObjectiveChild();
		childPanel.add(addObjectiveChild, "addObjectiveChild");
		
		
		//Add all the panels (Cards) to the Objectives Tab

		objectiveView = new ObjectiveView();
		objectivePanel.add(objectiveView, "Objective");

		addObjective = new AddObjective();
		objectivePanel.add(addObjective, "addObjective");
		
		
		//Add all the panels (Cards) to the Reporting Tab

		reportingView = new ReportingView();
		reportingPanel.add(reportingView, "Reporting");
		
		findChildReport = new FindChild(model);
		reportingPanel.add(findChildReport, "findChildReport");
		
		viewReport = new ViewReport();
		reportingPanel.add(viewReport, "viewReport");

		//Add all the panels (Cards) to the Accounts Tab
		
		accountView = new AccountView();
		accountPanel.add(accountView, "Account");
		
		changeEmail = new ChangeEmail();
		accountPanel.add(changeEmail, "changeEmail");
		
		changePassword = new ChangePassword();
		accountPanel.add(changePassword, "changePassword");
		
		newUserAccount = new NewUserAccount();
		accountPanel.add(newUserAccount, "newUserAccount");

		
		// Show the login screen
		
		showLogin();


	}
	
	
	// This function calls the functions from within each view class that retrieve an ActionListener
	// And determine what should be done in the case of a button press.
	
	public void initSessionButtonListeners()
	{
		
		addDay.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				ArrayList<Child> children = addDay.getChildren();
				Room room = addDay.getRoom();
				ArrayList<Session> sessions = addDay.getSessions();
				Calendar date = addDay.getDate();
				
				try {
					logSessionData.setDay(model.addDay(date, children, room, sessions));
					show(sessionPanel, "logSessionData");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}
		});
		
		addDay.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(sessionPanel, "Session");
			}
		});
		
		logSessionData.commitMarkListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				logSessionData.addMark();
			}
		});
		
		logSessionData.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				reviewSession.setDay(logSessionData.getDay());
				show(sessionPanel, "reviewSession");
			}
		});
		
		reviewSession.saveListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				JOptionPane.showMessageDialog(null, "Marks successfully saved to the system");
				show(sessionPanel, "Session");
			}
		});
		
		
		viewDay.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				reviewSession.setDay(viewDay.getDay());
				show(sessionPanel, "reviewSession");
			}
		});
		
		viewDay.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(sessionPanel, "Session");
			}
		});
		
		reviewSession.logMarksListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				logSessionData.setDay(reviewSession.getDay());
				show(sessionPanel, "logSessionData");
			}
		});
		
		
		
		
	}
	
	public void initChildButtonListeners()
	{
		
		addChild.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addChildSubmit(evt);
			}
		});
		
		addChild.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(childPanel, "Child");
			}
		});
		
		findChild.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				findChildSubmit(evt);
			}
		});

		findChild.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				findChildCancel(evt);
			}
		});
		
		addObjectiveChild.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(childPanel, "Child");
			}
		});
		
		
		childViewGrid.removeChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				Child child = childViewGrid.getSelectedChild();
				
				String temp = "Are you sure you wish to delete Child: " + child.getName() + "\nWith ID: " + child;
				
				int res = JOptionPane.showConfirmDialog(null, temp, "Delete Child", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				System.out.println(res);
				
				
				
				if(res == 0 )
				{
					model.removeChild(child);
					JOptionPane.showMessageDialog(null, child.getName() + " Was removed from the system");
				}
			}
		});
		
	}

	public void initAccountButtonListeners()
	{
		
		changePassword.changePassword(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				changePassword();
			}
		});
		
		changePassword.cancel(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(accountPanel, "Account");
			}
		});
		
		changeEmail.changeEmail(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.setEmail(changeEmail.getEmail());
			}
		});
		
		newUserAccount.submit(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String name = newUserAccount.getUsersName();
				String username = newUserAccount.getUsername();
				String emailAddress = newUserAccount.getEmailAddress();
				String phoneNo = newUserAccount.getPhoneNo();
				String pass = model.addUser(name, username, emailAddress, phoneNo);
				
				
				JOptionPane.showMessageDialog(null, "The password has been set to: " + pass
											+ "\nPlease note this down and inform the user");
			}
		});
		
	}
	
	public void initObjectiveButtonListeners()
	{
		addObjective.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addObjective();
			}
		});
		
		addObjective.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(objectivePanel, "Objective");
			}
		});
	}
	
	public void initReportingButtonListeners()
	{
		reportingView.viewReportListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(reportingPanel, "findChildReport");
				findChildReport.setDestination(viewReport);
				findChildReport.setChildren(model.getChildList());
			}
		});
		
		findChildReport.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				findChildReportSubmit(evt);
			}
		});

		findChildReport.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				findChildReportCancel(evt);
			}
		});
		
	}
	

	public void initButtonListeners()
	{
		initSessionButtonListeners();
		initChildButtonListeners();
		initObjectiveButtonListeners();
		initAccountButtonListeners();
		initReportingButtonListeners();
		
		sessionView.newDay(ActionListenerShow(sessionPanel, "addDay"));

		sessionView.viewDay(ActionListenerShow(sessionPanel, "viewDay"));
		
		childView.childGridListener(ActionListenerShow(childPanel, "ChildViewGrid"));
		
		childView.addChildListener(ActionListenerShow(childPanel, "addChild"));

		childView.retrieveChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(childPanel, "findChild");
				findChild.setDestination(editChild);
				findChild.setChildren(model.getChildList());
			}
		});

		childView.addObjectiveChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(childPanel, "addObjectiveChild");
				addObjectiveChild.setLists(model.getChildList(), model.getObjectiveList());
			}
		});
		

		objectiveView.viewObjectives(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				viewObjectives(evt);
			}
		});

		objectiveView.addObjectiveToChild(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addObjectiveChild(evt);
			}
		});

		
		
		
		objectiveView.addNewObjective(ActionListenerShow(objectivePanel, "addObjective"));
		
		editChild.cancelListener(ActionListenerShow(childPanel, "Child"));
		
		accountView.changeEmailAddress(ActionListenerShow(accountPanel, "changeEmailAddress"));
		
		accountView.changePassword(ActionListenerShow(accountPanel, "changePassword"));
		
		accountView.newUserAccount(ActionListenerShow(accountPanel, "newUserAccount"));
		
		
		
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				show(childPanel, "Child");
				show(homePanel, "Home");
				show(sessionPanel, "Session");
				show(objectivePanel, "Objective");
				show(reportingPanel, "Reporting");
				show(accountPanel, "Account");
				System.out.println("Test");
			}
		});
		

	}
	
	
	private void addObjective() {

		String name = addObjective.getObjectiveName();
		String description = addObjective.getObjectiveDescription();
		String[][] steps = addObjective.getSteps();
		try{
		model.addObjective(name, description, steps);
		show(objectivePanel, "Objective");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	protected void changePassword() {
		try{
			String[] arr = changePassword.getNewPassword();
			model.changePassword(changePassword.getOldPassword(), arr[0], arr[1]);
			JOptionPane.showMessageDialog(null, "Password Successfully Changed");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}

	// Takes in one of the tabbed panes panels and a string of card name and shows that panel
	// Only panels that are compatible with this method are panels that are CardLayout.
	// Such as: 	homePanel, sessionPanel, childPanel, objectivePanel, reportingPanel, accountPanel.

	private void show(JPanel panel, String card)
	{
		CardLayout temp = (CardLayout)panel.getLayout();
		temp.show(panel, card);
	}
	
	private ActionListener ActionListenerShow(JPanel panel, String card)
	{
		final JPanel j = panel;
		final String c = card;
		ActionListener al = new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(j, c);
			}
		};
		
		return al;
		
	}


	//Shows main child panel 
	
	private void findChildCancel(ActionEvent evt)
	{
		show(childPanel, "Child");
	}
	
	private void findChildReportCancel(ActionEvent evt)
	{
		show(reportingPanel, "Reporting");
	}

	
	//Sets the child in the edit child panel and then shows the edit child panel
	
	private void findChildSubmit(ActionEvent evt)
	{
		EditChild p = (EditChild)findChild.getDestination();
		p.setChild(findChild.getSelectedChild());
		show(childPanel, "editChild");
	}
	
	private void findChildReportSubmit(ActionEvent evt)
	{
		ViewReport p = (ViewReport)findChildReport.getDestination();
		p.setChild(findChildReport.getSelectedChild());
		p.refreshTable();
		show(reportingPanel, "viewReport");
	}
	
	//Adds a child to the model then shows the EditChild panel with all those details listed 

	private void addChildSubmit(ActionEvent evt)
	{
		String name = addChild.getChildName();
		Date dob = addChild.getDob();
		Date dateJoined = addChild.getDateJoined();
		ArrayList<Guardian> guardians = addChild.getGuardians();
		Child c;
		try {
			c = model.addChild(name, dob, dateJoined, guardians);
			JOptionPane.showMessageDialog(null, "Child has been successfully added to the system");
			editChild.setChild(c);
			show(childPanel, "editChild");
			addChild.resetTextField();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
	}

	private void addObjectiveChild(ActionEvent evt)
	{

	}

	private void viewObjectives(ActionEvent evt)
	{

	}


	//Sets the look and feel of the user interface.
	
	private void setLookAndFeel()
	{
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}

	}
	
	
	/***********************************************************************************
	 * 
	 * Sets up the login JOptionpane, then calls the model object to log in.
	 * 
	 ***********************************************************************************/
	
    @SuppressWarnings("deprecation")
	public void showLogin()
    {
        JLabel label_loginname = new JLabel("Enter your login name:");
        JTextField loginname;
        
        JLabel label_password = new JLabel("Enter your password:");
        JPasswordField password;
        
        int tries = 0;
        
        do
        {
            loginname = new JTextField(15);
            password = new JPasswordField();
        

        
         Object[] array = { label_loginname, 
                       loginname,
                       label_password,
                       password };
         
         int res = JOptionPane.showConfirmDialog(null, array, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
         
         if(res == 2)
         {
        	 System.exit(1);
         }
         tries++;
         
        } while( !model.login(loginname.getText(), password.getText()) && tries != 3);
         
         if(model.loggedIn())
         {
           	
         }
         else
         {
        	 JOptionPane.showMessageDialog(null, "Unable to log on to server. Closing program");
        	 
        	 System.exit(1);
         }
         
    }

    

}