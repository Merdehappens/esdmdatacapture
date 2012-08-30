package viewController;

import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

import system.helper.Helper;
import system.individuals.Child;
import system.individuals.Guardian;
import system.marking.Mark;
import system.marking.Objective;
import system.marking.Step;
import system.model.ESDMModel;
import system.model.Room;
import system.sessions.Day;
import system.sessions.Session;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.awt.Font;
import java.awt.event.WindowAdapter;

import org.hibernate.exception.ConstraintViolationException;
import javax.swing.JButton;

//testing
/**
 * @author Damian
 * 
 */
public class Controller extends JFrame {

	/**
	 * 
	 */
	private static final String adminAccess = "a";
	private static final String therapistAccess = "n";
	private static final String guardianAccess = "g";
	
	private static final long serialVersionUID = -6281745567153858417L;
	private static final int xRes = 1024;
	private static final int yRes = 600;

	private ESDMModel model;
	
	private Component parent;

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
	private UpdateDetails changeEmail;
	private AddObjectiveChild addObjectiveChild;
	private ReviewSession reviewSession;
	private ViewReport viewReport;
	private NewUserAccount newUserAccount;
	private ChildView childViewGrid;
	private ViewObjective viewObjective;
	private JLabel lblMessage;
	private String access;
	private ChangeMark changeMark;
	private AddGuardian addGuardian;
	
	private HashMap<String, JPanel> panelMap = new HashMap<String, JPanel>();
	private JButton btnBack;

	
	public Controller() throws MalformedURLException {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				int answer = JOptionPane
						.showConfirmDialog(null,
								"Are you sure you want to quit?", "Quit",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);
				if (answer == JOptionPane.YES_OPTION) {
					exitProgram();
				}

			}
		});
		setLookAndFeel();
		model = new ESDMModel();
		// Initialises all the components of the screens
		initComponents();
		// Initialises all the button listeners for the components
		initButtonListeners();
	}
	
	// called before program exits to make sure the database connection is closed correctly 
	private void exitProgram()
	{
		model.modelExit();
		System.exit(0);
	}

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		loadingFrame = new JFrame("Loading");

		// Creates a thread to create the new Controller class
		final Thread t1 = new Thread(new Runnable() {
			public void run() {

				try {
					Controller frame = new Controller();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		// Creates a thread to create the loading screen
		final Thread t2 = new Thread(new Runnable() {
			public void run() {

				try {
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

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		t2.run();
		t1.run();

	}

	// Initialises all of the Graphical information in the class

	public void initComponents() {
		// Sets the main frame Title, and bounds.

		setTitle("ESDM Data Capture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Set the main layout of the project (tabbed pane)

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tabbedPane.setBorder(null);

		tabbedPane.setBounds(0, 0, 1008, 541);
		contentPane.add(tabbedPane);

		// Add the 6 main panels to the program and then add them to the tabbed
		// pane.

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

		// Add all the panels (Cards) to the Session Tab

		homeView = new HomeView(model);
		homePanel.add(homeView, "Home");
		panelMap.put("Home", homePanel);

		// Add all the panels (Cards) to the Session Tab
		
		sessionView = new SessionView(model);
		sessionPanel.add(sessionView, "Session");
		panelMap.put("Session", sessionPanel);

		addDay = new AddDay(model);
		sessionPanel.add(addDay, "addDay");
		panelMap.put("addDay", sessionPanel);

		reviewSession = new ReviewSession();
		sessionPanel.add(reviewSession, "reviewSession");
		panelMap.put("reviewSession", sessionPanel);

		logSessionData = new LogSessionData(model);
		sessionPanel.add(logSessionData, "logSessionData");
		panelMap.put("logSessionData", sessionPanel);

		// Add all the panels (Cards) to the Child Tab

		childViewGrid = new ChildView(model);
		childPanel.add(childViewGrid, "Child");
		panelMap.put("Child", childPanel);

		addChild = new AddChild(model);
		childPanel.add(addChild, "addChild");
		panelMap.put("addChild", childPanel);

		editChild = new EditChild();
		childPanel.add(editChild, "editChild");
		panelMap.put("editChild", childPanel);


		// Add all the panels (Cards) to the Objectives Tab

		objectiveView = new ObjectiveView(model);
		objectivePanel.add(objectiveView, "Objective");
		panelMap.put("Objective", objectivePanel);

		addObjectiveChild = new AddObjectiveChild(model);
		objectivePanel.add(addObjectiveChild, "addObjectiveChild");
		panelMap.put("addObjectiveChild", objectivePanel);

		viewObjective = new ViewObjective(model);
		objectivePanel.add(viewObjective, "viewObjective");
		panelMap.put("viewObjective", objectivePanel);

		addObjective = new AddObjective();
		objectivePanel.add(addObjective, "addObjective");
		panelMap.put("addObjective", objectivePanel);

		// Add all the panels (Cards) to the Reporting Tab

		reportingView = new ReportingView();
		reportingPanel.add(reportingView, "Reporting");
		panelMap.put("Reporting", reportingPanel);

		findChildReport = new FindChild(model);
		reportingPanel.add(findChildReport, "findChildReport");
		panelMap.put("findChildReport", reportingPanel);

		viewReport = new ViewReport();
		reportingPanel.add(viewReport, "viewReport");
		panelMap.put("viewReport", reportingPanel);
		

		// Add all the panels (Cards) to the Accounts Tab

		accountView = new AccountView();
		accountPanel.add(accountView, "Account");
		panelMap.put("Account", accountPanel);

		changeEmail = new UpdateDetails();
		accountPanel.add(changeEmail, "changeEmail");
		panelMap.put("changeEmail", accountPanel);

		changePassword = new ChangePassword();
		accountPanel.add(changePassword, "changePassword");
		panelMap.put("changePassword", accountPanel);

		newUserAccount = new NewUserAccount();
		accountPanel.add(newUserAccount, "newUserAccount");
		panelMap.put("newUserAccount", accountPanel);
		
		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMessage.setBounds(87, 539, 829, 46);
		contentPane.add(lblMessage);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goBack();
			}
		});
		btnBack.setBounds(7, 539, 70, 25);
		contentPane.add(btnBack);
		btnBack.setEnabled(false);
		
		parent = this;

		// Show the login screen

		showLogin();
		homeView.refreshView();
		backList.add("Home");
	}

	// This function calls the functions from within each view class that
	// retrieve an ActionListener
	// And determine what should be done in the case of a button press.

	public void initSessionButtonListeners() {

		addDay.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				ArrayList<Child> children = addDay.getChildren();
				Room room = addDay.getRoom();
				ArrayList<Session> sessions = addDay.getSessions();
				Calendar date = addDay.getDate();

				try {
					logSessionData.setDay(model.addDay(date, children, room,
							sessions));
					show(sessionPanel, "logSessionData");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}

			}
		});
		
		
		sessionView.newDay(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(sessionPanel, "addDay");
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
				showMessage("Mark added.");
			}
		});

		logSessionData
				.addTimestampListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						logSessionData.addTimestamp();
						showMessage("Timestamp added.");
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
		
		reviewSession.editMarksListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				
				// TODO Change Mark
				Mark mark = reviewSession.getSelectedMark();
				Day day = reviewSession.getDay();
				changeMark = new ChangeMark();
				
				changeMark.saveButtonListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						changeMark.setVisible(false);
						if(reviewSession.getSelectedMark() != null)
						{
							saveMark();
						}
						else
						{
							showMessage("No mark selected.");
						}
						reviewSession.refreshView();
					}
				});
				
				changeMark.refreshView(mark, day);
				changeMark.setModalityType(ModalityType.APPLICATION_MODAL);
				changeMark.setVisible(true);
				
				

				
				
			}
		});
		
		reviewSession.exportListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					reviewSession.saveCSV();
				} catch (Exception e) {
					showMessage(e.getMessage());
				}
			}
		});

		sessionView.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {

					reviewSession.setDay(sessionView.getDay());
					show(sessionPanel, "reviewSession");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
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

	public void initChildButtonListeners() {

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


		addObjectiveChild.cancelListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					show(childPanel, "Child");
				}
			});
				

		childViewGrid.addChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(childPanel, "addChild");
			}
		});
		
		
		childViewGrid.editChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				try {
					editChild.setChild(childViewGrid.getSelectedChild());
					show(childPanel, "editChild");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}
			}
		});

		childViewGrid.removeChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				Child child;
				try {
					child = childViewGrid.getSelectedChild();

					String temp = "Are you sure you wish to delete "
							+ child.getName() + " ?";

					int res = JOptionPane.showConfirmDialog(parent, temp,
							"Delete Child", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE);

					if (res == 0) {
						model.removeChild(child);
						showMessage(child.getName()
								+ " was removed from the system.");
					}

				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}

				childViewGrid.refreshView();

			}
		});

	}

	public void initAccountButtonListeners() {

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

		changeEmail.saveListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					model.setEmail(changeEmail.getEmail());
					show(accountPanel, "Account");
					showMessage("Details successfully saved.");
				} catch (Exception e) {
					showMessage(e.getMessage());
				}
			}
		});

		changeEmail.cancel(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(accountPanel, "Account");
			}
		});
		
		newUserAccount.submit(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String name = newUserAccount.getUsersName();
				String username = newUserAccount.getUsername();
				String emailAddress = newUserAccount.getEmailAddress();
				String phoneNo = newUserAccount.getPhoneNo();
				String pass = Helper.generateRandomString(8);
				
				String access = newUserAccount.getSelectedAccess();
				

				try {
					if(access.equals(guardianAccess))
					{
						model.newGuardian(name, username, emailAddress, phoneNo, pass, access);
					}
					else
					{
						model.newTherapist(name, username, emailAddress, phoneNo, pass, access);
						
					}
					show(accountPanel, "Account");
					showErrorMessage("The password has been set to: " + pass + "."
							+ "\nPlease note this down and inform the user.");
				} catch(ConstraintViolationException e) 
				{
					showErrorMessage("That username already exists");
				}
				catch (Exception e) {
					showErrorMessage(e.getMessage());
				}


			}
		});

		newUserAccount.cancel(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(accountPanel, "Account");
			}
		});
			

	}

	public void initObjectiveButtonListeners() {
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

	public void initReportingButtonListeners() {
		reportingView.viewReportListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(reportingPanel, "findChildReport");
				findChildReport.setDestination(viewReport);
				if(access.equals(guardianAccess))
				{
					Guardian g = (Guardian) model.getCurrentUser();
					findChildReport.setChildren(g.getChildren());
				}
				else	
				{
					findChildReport.setChildren(model.getChildList(true));
				}
			}
		});
		
		viewReport.exportListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					viewReport.saveCSV();
				} catch (Exception e) {
					showErrorMessage("There was an issue with saving the file");
					e.printStackTrace();
				}
			}
		});

		findChildReport.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					findChildReportSubmit(evt);
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
					e.printStackTrace();
				}
			}
		});

		findChildReport.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(reportingPanel, "Reporting");
			}
		});

	}

	public void initButtonListeners() {
		initSessionButtonListeners();
		initChildButtonListeners();
		initObjectiveButtonListeners();
		initAccountButtonListeners();
		initReportingButtonListeners();


		

		viewObjective.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(objectivePanel, "Objective");
			}
		});
		
		viewObjective.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
					try {
						Objective objective = viewObjective.getObjective();
						String name = viewObjective.getObjectiveName();
						String description = viewObjective.getObjectiveDescription();
						int level = viewObjective.getLevel();
						String[][] steps = viewObjective.getSteps();
						model.saveObjective(objective, name, description, level, steps);
						show(objectivePanel, "Objective");
						showMessage("Objective successfully added");
					} catch (Exception e) {
						showErrorMessage(e.getMessage());
						e.printStackTrace();
					}
			}
		});


		objectiveView.viewObjectives(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				try {					
					viewObjective.setObjective(objectiveView
							.getSelectedObjective());
					show(objectivePanel, "viewObjective");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}
			}
		});

		objectiveView.addObjectiveToChild(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					addObjectiveChild.setObjective(objectiveView
							.getSelectedObjective());
					show(objectivePanel, "addObjectiveChild");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
					e.printStackTrace();
				}

			}
		});

		addObjectiveChild.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				Child child = addObjectiveChild.getSelectedChild();
				Objective objective = addObjectiveChild.getObjective();

				try {
					model.addObjectiveChild(child, objective);
					show(objectivePanel, "Objective");
					showMessage("Objective successfully added to "
							+ child.getName() + " .");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
					e.printStackTrace();
				}

			}
		});

		addObjectiveChild.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(objectivePanel, "Objective");
			}
		});

		objectiveView.addNewObjective(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(objectivePanel, "addObjective");
			}
		});

		editChild.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(childPanel, "Child");
			}
		});
	
		
		editChild.addGuardianListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// TODO Add guardian
				addGuardian = new AddGuardian(model.getGuardianList());
				
				addGuardian.saveButtonListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						addGuardian.setVisible(false);
						if(addGuardian.getGuardian() != null)
						{
							try {
								model.addChildGuardian(editChild.getChild(), addGuardian.getGuardian());
							} catch (Exception e) {
								showErrorMessage(e.getMessage());
								e.printStackTrace();
							}
						}
						else
						{
							showMessage("No Guardian Selected.");
						}
						editChild.refreshView();
					}
				});
			
				addGuardian.setModalityType(ModalityType.APPLICATION_MODAL);
				addGuardian.setVisible(true);
				
			}
		});
		
		

		editChild.saveChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Object[] childDetails = editChild.getInformation();
				Child child = editChild.getChild();
				String name = (String) childDetails[0];
				Calendar dob = (Calendar) childDetails[1];
				Calendar dateJoined = (Calendar) childDetails[2];

				String temp = "Are you sure you wish to save details for "
						+ child.getName() + "\nWith ID: " + child + "?";

				int res = JOptionPane.showConfirmDialog(parent, temp,
						"Save Details", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				if (res == 0) {
					try {
						model.updateChild(child, name, dob, dateJoined);
						showMessage("Details successfully saved.");
					} catch (Exception e) {
						showErrorMessage(e.getMessage());
					}
				} else {

				}
				editChild.refreshView();
			}
		});
		
		editChild.removeObjectiveListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.removeObjective(editChild.getChild(), editChild.getSelectedObjective());
				editChild.refreshView();
				showMessage("Successfully removed objective from child");
			}
		});
		
		editChild.setMasteredListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Child c = editChild.getChild();
				Objective o = editChild.getSelectedObjective();
				try {
					model.setMastered(c, o);
				} catch (Exception e) {
					showMessage(e.getMessage());
					e.printStackTrace();
				}
				editChild.refreshView();
			}
		});
		
		editChild.addObjectiveListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//TODO Add Objective to Child
			}
		});
		
		editChild.incrementStepListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Child c = editChild.getChild();
				Objective o = editChild.getSelectedObjective();
				try {
					model.incrementStep(c, o);
					lblMessage.setText("");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					showMessage(e.getMessage());
					e.printStackTrace();
				}
				editChild.refreshView();
				
			}
		});
		
		editChild.decrementStepListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Child c = editChild.getChild();
				Objective o = editChild.getSelectedObjective();
				try {
					model.decrementStep(c, o);
					lblMessage.setText("");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					showMessage(e.getMessage());
					e.printStackTrace();
				}
				editChild.refreshView();
			}
		});
		accountView.changeEmailAddress(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(accountPanel, "changeEmail");
			}
		});

		accountView.changePassword(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(accountPanel, "changePassword");
			}
		});
		
		accountView.newUserAccount(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(accountPanel, "newUserAccount");
			}
		});
		
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
		
				CardLayout c;
				switch(tabbedPane.getSelectedIndex()) {
				case 0:
					c = (CardLayout) homePanel.getLayout();
					show(homePanel, "Home");
					homeView.refreshView();
					break;
				case 1:
					c = (CardLayout) sessionPanel.getLayout();
					show(sessionPanel, "Session");
					sessionView.refreshView();
					break;
				case 2:
					c = (CardLayout) childPanel.getLayout();
					show(childPanel, "Child");
					childViewGrid.refreshView();
					break;
				case 3:
					c = (CardLayout) objectivePanel.getLayout();
					show(objectivePanel, "Objective");
					objectiveView.refreshView();
					break;
				case 4:
					c = (CardLayout) reportingPanel.getLayout();
					show(reportingPanel, "Reporting");
					reportingView.refreshView();
					break;
				case 5:
					c = (CardLayout) accountPanel.getLayout();
					show(accountPanel, "Account");
					break;
				}
			}
		});

	}

	private void addObjective() {

		try {
			String name = addObjective.getObjectiveName();
			String description = addObjective.getObjectiveDescription();
			int level = addObjective.getLevel();
			String[][] steps = addObjective.getSteps();
			
			model.addObjective(name, description, steps, level);
			show(objectivePanel, "Objective");
			showMessage("Objective successfully added to system.");
		} catch (Exception e) {
			showErrorMessage(e.getMessage());
		}
	}

	protected void changePassword() {
		try {
			String[] arr = changePassword.getNewPassword();
			model.changePassword(changePassword.getOldPassword(), arr[0],
					arr[1]);
			show(accountPanel, "Account");
			showMessage("Your password has been successfully changed.");
		} catch (Exception e) {
			showErrorMessage(e.getMessage());
		}

	}

	// Takes in one of the tabbed panes panels and a string of card name and
	// shows that panel
	// Only panels that are compatible with this method are panels that are
	// CardLayout.
	// Such as: homePanel, sessionPanel, childPanel, objectivePanel,
	// reportingPanel, accountPanel.

	// Sets the child in the edit child panel and then shows the edit child
	// panel

	private void findChildReportSubmit(ActionEvent evt) throws Exception {
		ViewReport p = (ViewReport) findChildReport.getDestination();
		if(findChildReport.getSelectedChild() == null)
		{
			throw new Exception("No child is selected.");
		}
		p.setChild(findChildReport.getSelectedChild());
		p.refreshTable();
		show(reportingPanel, "viewReport");
	}

	// Adds a child to the model then shows the EditChild panel with all those
	// details listed

	private void addChildSubmit(ActionEvent evt) {
		String name = addChild.getChildName();
		Calendar dob = addChild.getDob();
		Calendar dateJoined = addChild.getDateJoined();
		try {
			model.addChild(name, dob, dateJoined);
			show(childPanel, "Child");
			showMessage(name + " has been successfully added to the system.");
		} catch (Exception e) {
			showErrorMessage(e.getMessage());
		}

	}

	// Sets the look and feel of the user interface.

	private void setLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
			
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}

	}

	/***********************************************************************************
	 * 
	 * Sets up the login JOptionpane, then calls the model object to log in.
	 * 
	 ***********************************************************************************/

	@SuppressWarnings("deprecation")
	public void showLogin() {

		loadingFrame.setVisible(false);

		JLabel label_loginname = new JLabel("Please enter your login name:");
		JTextField loginname;

		JLabel label_password = new JLabel("Please enter your password:");
		JPasswordField password;

		JLabel label_tries;

		int tries = 0;

		do {
			if (tries > 0)
				label_tries = new JLabel(
						"You have entered an incorrect username or password.");
			else
				label_tries = new JLabel("");

			loginname = new JTextField(25);
			password = new JPasswordField();

			Object[] array = { label_tries, label_loginname, loginname,
					label_password, password };

			int res = JOptionPane.showConfirmDialog(null, array, "Login",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

			if (res != 0) {
				System.exit(1);
			}
			tries++;

		} while (!model.login(loginname.getText(), password.getText())
				&& tries != 3);

		if (model.loggedIn()) {

		} else {
			showErrorMessage("Three incorrect attempts. Exiting program.");

			System.exit(1);
		}
		
		access = model.getCurrentAccess();
		
		setPermissions();

	}
	
	private void setPermissions() {
		if(access.equals(adminAccess))
		{
			
		}
		else if(access.equals(therapistAccess))
		{
		
		}
		else if(access.equals(guardianAccess))
		{
			tabbedPane.setEnabledAt(1, false);
			tabbedPane.setEnabledAt(2, false);
			tabbedPane.setEnabledAt(3, false);
			accountView.setAccess(guardianAccess);
		}
	}

	private void showMessage(String message) {
		lblMessage.setText(message);
	}

	private void showErrorMessage(String message) {
		showMessage(message);
		JOptionPane.showMessageDialog(parent, message);
	}
	
	public void saveMark() {
		try {
		Session session = changeMark.getSession();
		Child child = changeMark.getChild();
		Calendar time = changeMark.getTime();
		Objective objective = changeMark.getObjective();
		Step step = changeMark.getStep();
		int markVal = changeMark.getIntMark();
		String comment = changeMark.getComment();
		
		model.updateMark(changeMark.getMark(), session, child, time, objective, step, markVal, comment);
		
		} catch (Exception e) {
			showMessage(e.getMessage());
		}
	}
	
	public void goBack() {
		try{
		int size = backList.size() - 1;
		backList.remove(size);	
		System.out.println(size);
		String card = backList.remove(size - 1);
		JPanel panel = panelMap.get(card);
		show(panel, card);
		if(tabbedPane.getSelectedComponent() != panel)
		{
			tabbedPane.setSelectedComponent(panel);
			backList.remove(backList.size() - 1);
		}
			
		}
		catch(Exception e)
		{
			showErrorMessage("There is not any history any further");
		}
		
		if(backList.size() <= 1)
		{
			btnBack.setEnabled(false);
		}
		
	}
	
	private void show(JPanel panel, String card) {
		CardLayout temp = (CardLayout) panel.getLayout();
		temp.show(panel, card);
		lblMessage.setText("");
		backList.add(card);
		btnBack.setEnabled(true);		
	}
	
	ArrayList<String> backList = new ArrayList<String>();

}