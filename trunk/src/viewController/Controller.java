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
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import systemModel.Child;
import systemModel.ESDMModel;
import systemModel.Room;
import systemModel.Session;
import systemModel.UserAccount;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;

//testing
/**
 * @author Damian
 *
 */
public class Controller extends JFrame {

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

		//Add all the panels (Cards) to the Accounts Tab
		
		accountView = new AccountView();
		accountPanel.add(accountView, "Account");
		
		changeEmail = new ChangeEmail();
		accountPanel.add(changeEmail, "changeEmail");
		
		changePassword = new ChangePassword();
		accountPanel.add(changePassword, "changePassword");

		
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
				Date date = addDay.getDate();
				
				logSessionData.setDay(model.addDay(date, children, room, sessions));
				show(sessionPanel, "logSessionData");
				
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
		
		
		
	}
	
	public void initChildButtonListeners()
	{
		
		addChild.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addChildSubmit(evt);
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
	
	
	public void initButtonListeners()
	{
		initSessionButtonListeners();
		initChildButtonListeners();
		initObjectiveButtonListeners();
		initAccountButtonListeners();
		
		sessionView.newDay(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(sessionPanel, "addDay");
			}
		});

		sessionView.viewDay(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(sessionPanel, "viewDay");
			}
		});

		
		childView.addChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(childPanel, "addChild");
			}
		});

		childView.retrieveChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(childPanel, "findChild");
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

		objectiveView.addNewObjective(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(objectivePanel, "addObjective");
			}
		});
		

	
		
		accountView.changeEmailAddress(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
			}
		});
		
		accountView.changePassword(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(accountPanel, "changePassword");
			}
		});
		
	
		
		
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				show(childPanel, "Child");
				show(homePanel, "Home");
				show(sessionPanel, "Session");
				show(objectivePanel, "Objective");
				show(reportingPanel, "Reporting");
				show(accountPanel, "Account");
			}
		});

	}
	
	
	private void addObjective() {

		String name = addObjective.getObjectiveName();
		String description = addObjective.getObjectiveDescription();
		String[][] steps = addObjective.getSteps();
		
		model.addObjective(name, description, steps);
		
		show(objectivePanel, "Objective");
	}

	protected void changePassword() {
		try{
			String[] arr = changePassword.getNewPassword();
			
			model.changePassword(changePassword.getOldPassword(), arr[0], arr[1]);
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


	//Shows main child panel 
	
	private void findChildCancel(ActionEvent evt)
	{
		show(childPanel, "Child");
	}

	
	//Sets the child in the edit child panel and then shows the edit child panel
	
	private void findChildSubmit(ActionEvent evt)
	{
		editChild.setChild(findChild.getSelectedChild());
		show(childPanel, "editChild");
	}
	
	//Adds a child to the model then shows the EditChild panel with all those details listed 

	private void addChildSubmit(ActionEvent evt)
	{
		editChild.setChild(addChild.addChild());
		show(childPanel, "editChild");
		addChild.resetTextField();
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