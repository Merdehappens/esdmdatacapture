package viewController;

import java.awt.Dimension;
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
import java.awt.event.WindowEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import system.individuals.Child;
import system.individuals.Guardian;
import system.marking.Objective;
import system.model.ESDMModel;
import system.model.Room;
import system.sessions.Session;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Font;
import java.awt.event.WindowAdapter;

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
	private static final int xRes = 1024;
	private static final int yRes = 600;
	
	private ESDMModel model;
	
	private static JFrame loadingFrame;
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
	private ChildView childViewGrid;
	private ViewObjective viewObjective;
	
	
	
	public Controller() throws MalformedURLException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
		        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		        if (answer == JOptionPane.YES_OPTION) {
		            System.exit(0);
		        }
	
			}
		});
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
		

		loadingFrame = new JFrame("Loading");
		
		final Thread t1 = new Thread(new Runnable() {
			public void run() {

				try{
				Controller frame = new Controller();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


				
				}
		catch (Exception e) {
			e.printStackTrace();
		}
				
		}
	});
		

		
		final Thread t2 = new Thread(new Runnable() {
			public void run() {

				try{
					JPanel contentPane = new JPanel();
					JLabel loading = new JLabel("Loading...Please Wait.");
					loading.setFont(new Font("Monotype Corsiva", 1, 25));
					loading.setPreferredSize(new Dimension(300, 150));
					contentPane.add(loading);
					loadingFrame.setContentPane(contentPane);
					loadingFrame.pack();
					loadingFrame.setVisible(true);
					
					loadingFrame.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent arg0) {
								System.exit(0);
					        }
					});
					
				}
		catch (Exception e) {
			e.printStackTrace();
		}
				
		}
	});
		t2.run();
		t1.run();
		
		
		
		
	//	EventQueue.invokeLater
		
		
	}



	// Initialises all of the Graphical information in the class
	
	public void initComponents()
	{
		//Sets the main frame Title, and bounds.

		setTitle("ESDM Data Capture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, xRes, yRes);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Set the main layout of the project (tabbed pane)

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);


		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setBorder(null);
		
		tabbedPane.setBounds(0, 0, 1008, 564);
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
		tabbedPane.addTab("Account Management", null, accountPanel, null);
		accountPanel.setLayout(new CardLayout(0, 0));
		

		
		
		
		//Add all the panels (Cards) to the Session Tab

		homeView = new HomeView();
		homePanel.add(homeView, "Home");

		
		//Add all the panels (Cards) to the Session Tab
		
		addDay = new AddDay(model);
		sessionPanel.add(addDay, "addDay");

		sessionView = new SessionView(model);
		sessionPanel.add(sessionView, "Session");
		
		reviewSession = new ReviewSession();
		sessionPanel.add(reviewSession, "reviewSession");

		logSessionData = new LogSessionData(model);
		sessionPanel.add(logSessionData, "logSessionData");


		//Add all the panels (Cards) to the Child Tab
		
		childViewGrid = new ChildView(model);
		childPanel.add(childViewGrid, "Child");

		addChild = new AddChild(model);
		childPanel.add(addChild, "addChild");

		editChild = new EditChild();
		childPanel.add(editChild, "editChild");

		findChild = new FindChild(model);
		childPanel.add(findChild, "findChild");
		
		
		
		//Add all the panels (Cards) to the Objectives Tab

		objectiveView = new ObjectiveView(model);
		objectivePanel.add(objectiveView, "Objective");

		addObjectiveChild = new AddObjectiveChild(model);
		objectivePanel.add(addObjectiveChild, "addObjectiveChild");
		
		viewObjective = new ViewObjective(model);
		objectivePanel.add(viewObjective, "viewObjective");
		
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
					showMessage(e.getMessage());
				}
				
			}
		});
		
		
		addDay.cancelListener(ActionListenerShow(sessionPanel, "Session"));
		
		logSessionData.commitMarkListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				logSessionData.addMark();
			}
		});
		
		logSessionData.addTimestampListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				logSessionData.addTimestamp();
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
				show(sessionPanel, "Session");
			}
		});
		
		
		sessionView.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try{

					reviewSession.setDay(sessionView.getDay());
					show(sessionPanel, "reviewSession");
				}
				catch(Exception e)
				{
					showMessage(e.getMessage());
				}
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
		
		addChild.cancelListener(ActionListenerShow(childPanel, "Child"));
		
		findChild.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				findChildSubmit(evt);
			}
		});

		findChild.cancelListener(ActionListenerShow(childPanel, "Child"));
		addObjectiveChild.cancelListener(ActionListenerShow(childPanel, "Child"));
		
		childViewGrid.addChildListener(ActionListenerShow(childPanel, "addChild"));

		
		childViewGrid.editChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				try{
					editChild.setChild(childViewGrid.getSelectedChild());
					show(childPanel, "editChild");
				}
				catch(Exception e)
				{
					showMessage(e.getMessage());
				}
			}
		});
		
		
		childViewGrid.removeChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				Child child;
				try {
					child = childViewGrid.getSelectedChild();
					
					String temp = "Are you sure you wish to delete " + child.getName() + " ?";
					
					int res = JOptionPane.showConfirmDialog(null, temp, "Delete Child", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);				
					
					if(res == 0 )
					{
						model.removeChild(child);
						showMessage(child.getName() + " was removed from the system.");
					}
					
				} catch (Exception e) {
					showMessage(e.getMessage());
				}
				

				childViewGrid.refreshView();
				
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
		
		changePassword.cancel(ActionListenerShow(accountPanel, "Account"));
		
		changeEmail.changeEmail(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.setEmail(changeEmail.getEmail());
			}
		});
		
		changeEmail.cancel(ActionListenerShow(accountPanel, "Account"));
		
		newUserAccount.submit(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String name = newUserAccount.getUsersName();
				String username = newUserAccount.getUsername();
				String emailAddress = newUserAccount.getEmailAddress();
				String phoneNo = newUserAccount.getPhoneNo();
				String pass = model.addUser(name, username, emailAddress, phoneNo);
				
				showMessage("The password has been set to: " + pass	+ "." + "\nPlease note this down and inform the user.");
			}
		});
		
		newUserAccount.cancel(ActionListenerShow(accountPanel, "Account"));
		
	}
	
	public void initObjectiveButtonListeners()
	{
		addObjective.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addObjective();
			}
		});
		
		addObjective.cancelListener(ActionListenerShow(objectivePanel, "Objective"));
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

		findChildReport.cancelListener(ActionListenerShow(reportingPanel, "Reporting"));
		
	}
	

	public void initButtonListeners()
	{
		initSessionButtonListeners();
		initChildButtonListeners();
		initObjectiveButtonListeners();
		initAccountButtonListeners();
		initReportingButtonListeners();
		
		sessionView.newDay(ActionListenerShow(sessionPanel, "addDay"));

		viewObjective.cancelListener(ActionListenerShow(objectivePanel, "Objective"));
		

		objectiveView.viewObjectives(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				try {
					viewObjective.setObjective(objectiveView.getSelectedObjective());
					show(objectivePanel, "viewObjective");
				} catch (Exception e) {
					showMessage(e.getMessage());
				}
			}
		});

		objectiveView.addObjectiveToChild(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					addObjectiveChild.setObjective(objectiveView.getSelectedObjective());
					show(objectivePanel, "addObjectiveChild");
				} catch (Exception e) {
					showMessage(e.getMessage());
				}
				
				
			}
		});

		addObjectiveChild.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				Child child = addObjectiveChild.getSelectedChild();
				Objective objective = addObjectiveChild.getObjective();
				
				try {
					model.addObjectiveChild(child, objective);
					showMessage("Objective successfully added to " + child.getName() + " .");
					show(objectivePanel, "Objective");
				} catch (Exception e) {
					showMessage(e.getMessage());
				}
				
			}
		});
		
		addObjectiveChild.cancelListener(ActionListenerShow(objectivePanel, "Objective"));
		
		
		
		objectiveView.addNewObjective(ActionListenerShow(objectivePanel, "addObjective"));
		
		editChild.cancelListener(ActionListenerShow(childPanel, "Child"));
		
		editChild.saveChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Object[] childDetails = editChild.getInformation();
				Child child = editChild.getChild();
				String name = (String) childDetails[0];
				Calendar dob = (Calendar) childDetails[1];
				Calendar dateJoined = (Calendar) childDetails[2];
				
				
				String temp = "Are you sure you wish to save details for " + child.getName() + "\nWith ID: " + child + "?";
				
				int res = JOptionPane.showConfirmDialog(null, temp, "Save Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(res == 0)
				{
					try {
						model.updateChild(child, name, dob, dateJoined);
						showMessage("Details successfully saved.");
					} catch (Exception e) {
						showMessage(e.getMessage());
					}
				}
				else
				{
					
				}
				editChild.refreshView();
			}
		});
		
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
			}
		});
		


	}
	
	
	private void addObjective() {
		String name = addObjective.getObjectiveName();
		String description = addObjective.getObjectiveDescription();
		String[][] steps = addObjective.getSteps();
		try{
		model.addObjective(name, description, steps);
		showMessage("Objective successfully added to system.");
		show(objectivePanel, "Objective");
		}
		catch(Exception e)
		{
			showMessage(e.getMessage());
		}
	}

	protected void changePassword() {
		try{
			String[] arr = changePassword.getNewPassword();
			model.changePassword(changePassword.getOldPassword(), arr[0], arr[1]);
			showMessage("Your password has been successfully changed.");
		}
		catch(Exception e){
			showMessage(e.getMessage());
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
		Calendar dob = addChild.getDob();
		Calendar dateJoined = addChild.getDateJoined();
		ArrayList<Guardian> guardians = addChild.getGuardians();
		Child c;
		try {
			c = model.addChild(name, dob, dateJoined, guardians);
			showMessage(name + " has been successfully added to the system.");
			editChild.setChild(c);
			show(childPanel, "Child");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
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

		loadingFrame.setVisible(false);
    	
        JLabel label_loginname = new JLabel("Please enter your login name:");
        JTextField loginname;
        
        JLabel label_password = new JLabel("Please enter your password:");
        JPasswordField password;
    	
        JLabel label_tries;
        
        int tries = 0;
        
        do
        {
            label_tries = new JLabel("Attempt " + (tries + 1) + " out of 3.");
        	
            loginname = new JTextField(15);
            password = new JPasswordField();
        

        
         Object[] array = { label_tries,
        		 		label_loginname, 
                       loginname,
                       label_password,
                       password };
         
         int res = JOptionPane.showConfirmDialog(null, array, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
         
         if(res != 0)
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
        	 showMessage("Unable to log on to server. Exiting program.");
        	 
        	 System.exit(1);
         }
         
    }

    private void showMessage(String message)
    {
    	JOptionPane.showMessageDialog(null, message);
    }
    

}