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

import systemModel.ESDMModel;
import systemModel.UserAccount;


import java.net.MalformedURLException;

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
	private ViewSession viewSession;
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

		viewSession = new ViewSession();
		sessionPanel.add(viewSession, "viewSession");

		logSessionData = new LogSessionData();
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

	public void initButtonListeners()
	{
		
		sessionView.newDay(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(sessionPanel, "addDay");
			}
		});

		sessionView.viewDay(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(sessionPanel, "viewSession");
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
				addObjectiveChild(evt);
			}
		});
		
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
		
		addObjective.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				String name = addObjective.getObjectiveName();
				String description = addObjective.getObjectiveDescription();
				String[][] steps = addObjective.getSteps();
				
				model.addObjective(name, description, steps);
				
				show(objectivePanel, "Objective");
			}
		});
		
		addObjective.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(objectivePanel, "Objective");
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
	
	
	protected void changePassword() {
		if(changePassword.newPasswordMatch())
		{
			UserAccount temp = model.getCurrentUser();
			
			if(BCrypt.BCrypt.checkpw(changePassword.getOldPassword(), temp.getPassword()))
			{
				model.setPassword(changePassword.getNewPassword());
				System.out.println("Password Was Changed");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Entered Incorrect password");
			}
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "The two passwords did not match");
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