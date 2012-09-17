package viewController;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.MissingResourceException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.hibernate.exception.ConstraintViolationException;

import system.helper.Helper;
import system.helper.MessageFade;
import system.individuals.Child;
import system.individuals.Guardian;
import system.marking.Mark;
import system.marking.Objective;
import system.marking.Step;
import system.model.ESDMModel;
import system.model.Room;
import system.sessions.Day;
import system.sessions.Setting;


public class Controller extends JFrame {


	private static final String adminAccess = "a";
	private static final String therapistAccess = "n";
	private static final String guardianAccess = "g";
	
	private static final long serialVersionUID = -6281745567153858417L;
	private static final int xRes = 1024;
	private static final int yRes = 600;

	private ESDMModel model;
	
	private Component parent;

	private static JFrame loadingFrame;
	private JPanel esdmPanel;
	/*private JPanel contentPane;
	private JPanel homePanel;
	private JPanel sessionPanel;
	private JPanel childPanel;
	private JPanel objectivePanel;
	private JPanel reportingPanel;
	private JPanel accountPanel;*/
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
	private ChooseObjective chooseObjective;
	private AddNewGuardian addNewGuardian;
	private UserAccountView userAccountView;
	
	private HashMap<String, JPanel> panelMap = new HashMap<String, JPanel>();
	private RoomView roomView;
	private SettingView settingView;
	private ObjectiveTypeView objectiveTypeView;
	private JButton btnExit;
	private JPanel mainPanel;
	

	
	public Controller() throws MalformedURLException {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				exitProgram();
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
		int answer = JOptionPane
				.showConfirmDialog(this,
						"Are you sure you want to quit?", "Quit",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
		if (answer == JOptionPane.YES_OPTION) {
			model.modelExit();
			System.exit(0);
		}
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
		setBounds(100, 100, 1025, 663);

		mainPanel = new JPanel(new BorderLayout());
		// Set the main layout of the project (tabbed pane)
		setContentPane(mainPanel);
		
		esdmPanel = new JPanel();
		esdmPanel.setLayout(new CardLayout(0, 0));
		mainPanel.add(esdmPanel, BorderLayout.CENTER);

		// Add all the panels (Cards) to the Session Tab

		homeView = new HomeView(model);
		esdmPanel.add(homeView, "Home");
		panelMap.put("Home", esdmPanel);

		
		// Add all the panels (Cards) to the Session Tab
		
		sessionView = new SessionView(model);
		esdmPanel.add(sessionView, "Session");
		panelMap.put("Session", esdmPanel);

		addDay = new AddDay(model);
		esdmPanel.add(addDay, "addDay");
		panelMap.put("addDay", esdmPanel);

		reviewSession = new ReviewSession();
		esdmPanel.add(reviewSession, "reviewSession");
		panelMap.put("reviewSession", esdmPanel);

		logSessionData = new LogSessionData(model);
		esdmPanel.add(logSessionData, "logSessionData");
		panelMap.put("logSessionData", esdmPanel);

		// Add all the panels (Cards) to the Child Tab

		childViewGrid = new ChildView(model);
		esdmPanel.add(childViewGrid, "Child");
		panelMap.put("Child", esdmPanel);

		addChild = new AddChild(model);
		esdmPanel.add(addChild, "addChild");
		panelMap.put("addChild", esdmPanel);

		editChild = new EditChild();
		esdmPanel.add(editChild, "editChild");
		panelMap.put("editChild", esdmPanel);


		// Add all the panels (Cards) to the Objectives Tab

		objectiveView = new ObjectiveView(model);
		esdmPanel.add(objectiveView, "Objective");
		panelMap.put("Objective", esdmPanel);

		addObjectiveChild = new AddObjectiveChild(model);
		esdmPanel.add(addObjectiveChild, "addObjectiveChild");
		panelMap.put("addObjectiveChild", esdmPanel);

		viewObjective = new ViewObjective(model);
		esdmPanel.add(viewObjective, "viewObjective");
		panelMap.put("viewObjective", esdmPanel);

		addObjective = new AddObjective();
		esdmPanel.add(addObjective, "addObjective");
		panelMap.put("addObjective", esdmPanel);

		// Add all the panels (Cards) to the Reporting Tab

		reportingView = new ReportingView();
		esdmPanel.add(reportingView, "Reporting");
		panelMap.put("Reporting", esdmPanel);

		findChildReport = new FindChild(model);
		esdmPanel.add(findChildReport, "findChildReport");
		panelMap.put("findChildReport", esdmPanel);

		viewReport = new ViewReport();
		esdmPanel.add(viewReport, "viewReport");
		panelMap.put("viewReport", esdmPanel);
		

		// Add all the panels (Cards) to the Accounts Tab

		accountView = new AccountView();
		esdmPanel.add(accountView, "Account");
		panelMap.put("Account", esdmPanel);

		changeEmail = new UpdateDetails();
		esdmPanel.add(changeEmail, "changeEmail");
		panelMap.put("changeEmail", esdmPanel);

		changePassword = new ChangePassword();
		esdmPanel.add(changePassword, "changePassword");
		panelMap.put("changePassword", esdmPanel);

		newUserAccount = new NewUserAccount();
		esdmPanel.add(newUserAccount, "newUserAccount");
		panelMap.put("newUserAccount", esdmPanel);
		
		roomView = new RoomView(model);
		esdmPanel.add(roomView, "roomView");
		panelMap.put("roomView", esdmPanel);
		
		settingView = new SettingView(model);
		esdmPanel.add(settingView, "settingView");
		panelMap.put("settingView", esdmPanel);

		objectiveTypeView = new ObjectiveTypeView(model);
		esdmPanel.add(objectiveTypeView, "objectiveTypeView");
		panelMap.put("objectiveTypeView", esdmPanel);
		
		userAccountView = new UserAccountView(model);
		esdmPanel.add(userAccountView, "userAccountView");
		panelMap.put("userAccountView", esdmPanel);
		
		
		JPanel footer = new JPanel(null);
		
		mainPanel.add(footer, BorderLayout.PAGE_END);
		
		footer.setBounds(20, 100, 200, 100);
		footer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		int width = this.getWidth();
		width = width/2 + (width / 4);
		lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMessage.setPreferredSize(new Dimension(width, 30));
		footer.add(lblMessage);
		
		btnExit = new JButton("Log Out");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exitProgram();
			}
		});
		btnExit.setPreferredSize(new Dimension(90, 30));
		footer.add(btnExit);

		
		
		

		
		parent = this;

		// Show the login screen

		showLogin();
		homeView.refreshView();
		backList.add("Home");
		
		show(esdmPanel, "Home");
	}

	// This function calls the functions from within each view class that
	// retrieve an ActionListener
	// And determine what should be done in the case of a button press.

	public void initSessionButtonListeners() {
		
		homeView.reviewPastSessionsListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Session");
			}
		});
		
		homeView.objectivesListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Objective");
			}
		});
		
		homeView.accountsListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "userAccountView");
			}
		});
		
		homeView.reportingListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Reporting");
			}
		});
		
		homeView.childListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Child");
			}
		});
		
		reportingView.homeListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Home");
			}
		});
		
		userAccountView.homeListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Home");
			}
		});
		
		childViewGrid.homeListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Home");
			}
		});
		
		sessionView.homeListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Home");
			}
		});
		
		objectiveView.homeListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Home");
			}
		});
		
		viewReport.homeListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Home");
			}
		});
		
		viewReport.backToSelectListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "findChildReport");
			}
		});

		addDay.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				try {
				
				ArrayList<Child> children = addDay.getChildren();
				Room room = addDay.getRoom();
				ArrayList<Setting> settings = addDay.getSettings();
				Calendar date = addDay.getDate();
				boolean exists = model.dayExists(room, date);
			
				int res = 0;
				if(exists)
				{
					res = JOptionPane.showConfirmDialog(null, "Another day already exists for this date in this room. are you sure you wish to proceed?", "Add New Day", JOptionPane.YES_NO_OPTION);
				}
				System.out.println("RES " + res);
				if(res == 0)
				{
					logSessionData.setDay(model.addDay(date, children, room, settings));
					//show(sessionPanel, "logSessionData");
					show(esdmPanel, "logSessionData");
				}
				else
				{
					//show(sessionPanel, "Session");
					show(esdmPanel, "Session");
				}
				
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
					e.printStackTrace();
				}

			}
		});
		
		
		sessionView.newDay(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(sessionPanel, "addDay");
				show(esdmPanel, "addDay");
			}
		});

		addDay.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(sessionPanel, "Session");
				show(esdmPanel, "Session");
			}
		});
		
		logSessionData.listenChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					model.playSound(logSessionData.getSelectedChild());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		logSessionData.listenObjectiveListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					model.playSound(logSessionData.getSelectedObjective());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		logSessionData.listenSettingListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					model.playSound(logSessionData.getSelectedSetting());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		logSessionData.commitMarkListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// TODO FIX
				logSessionData.addMark();
				showMessage("Mark added.");
			}
		});

		logSessionData
				.addTimestampListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						// TODO FIX
						logSessionData.addTimestamp();
						showMessage("Timestamp added.");
					}
				});

		logSessionData.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				reviewSession.setDay(logSessionData.getDay());
				//show(sessionPanel, "reviewSession");
				show(esdmPanel, "reviewSession");
			}
		});
		
		logSessionData.saveBehaviourMarkListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				try {				
					Day day = logSessionData.getDay();
					Child child = logSessionData.getSelectedChild();
					int markInt = logSessionData.getSelectedBehaviourMark();
					model.addBehaviouralMark(day, child, markInt);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					showMessage("Unable to save the behavioural mark");
					e.printStackTrace();
				}
			}
		});

		reviewSession.editMarksListener(new java.awt.event.ActionListener() {

			public void actionPerformed(ActionEvent evt) {
				
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
					//show(sessionPanel, "reviewSession");
					show(esdmPanel, "reviewSession");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}
			}
		});

		reviewSession.logMarksListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				logSessionData.setDay(reviewSession.getDay());
				//show(sessionPanel, "logSessionData");
				show(esdmPanel, "logSessionData");
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
				//show(childPanel, "Child");
				show(esdmPanel, "Child");
			}
		});


		addObjectiveChild.cancelListener(new java.awt.event.ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					//show(childPanel, "Child");
					show(esdmPanel, "Child");
				}
			});
				

		childViewGrid.addChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(childPanel, "addChild");
				show(esdmPanel, "addChild");
			}
		});
		
		
		childViewGrid.editChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				try {
					editChild.setChild(childViewGrid.getSelectedChild());
					//show(childPanel, "editChild");
					show(esdmPanel, "editChild");
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
				//show(accountPanel, "Account");
				show(esdmPanel, "Account");
			}
		});

		changeEmail.saveListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					model.setEmail(changeEmail.getEmail());
					//show(accountPanel, "Account");
					show(esdmPanel, "Account");
					showMessage("Details successfully saved.");
				} catch (Exception e) {
					showMessage(e.getMessage());
					e.printStackTrace();
				}
			}
		});

		changeEmail.cancel(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(accountPanel, "Account");
				show(esdmPanel, "Account");
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
				if(access.equals("false"))
				{
					showErrorMessage("You must select access level.");
				}
				else
				{
					try {
						if(access.equals(guardianAccess))
						{
							model.newGuardian(name, username, emailAddress, phoneNo, pass, access);
						}
						else
						{
							model.newTherapist(name, username, emailAddress, phoneNo, pass, access);
						}
						//show(accountPanel, "Account");
						show(esdmPanel, "Account");
						showErrorMessage("The password has been set to: " + pass + "."
								+ "\nPlease note this down and inform the user.");
					} catch(ConstraintViolationException e) {
						showErrorMessage("That username already exists");
					} catch(MissingResourceException e) {	
						showErrorMessage(e.getMessage());
					} catch (Exception e) {
						showErrorMessage("Undefined Error: 90001: " + e.getMessage());
					}
				}


			}
		});

		newUserAccount.cancel(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(accountPanel, "Account");
				show(esdmPanel, "Account");
			}
		});
			

	}

	public void initObjectiveButtonListeners() {
		
		addObjective.templateListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				chooseObjective = new ChooseObjective(model.getObjectiveList());
				chooseObjective.setSaveText("Save");
				
				chooseObjective.saveButtonListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						chooseObjective.setVisible(false);
						Objective obj = chooseObjective.getObjective();
						//show(objectivePanel, "addObjective");
						show(esdmPanel, "addObjective");
						try{
						addObjective.setName(obj.getName());
						addObjective.setDescription(obj.getDescription());
						addObjective.setLevel(obj.getLevel());
						addObjective.setSteps(obj.getSteps());
						} catch(NullPointerException e) {
							showErrorMessage("10003: No Objective is selected");
						}
					}
				});

				chooseObjective.setModalityType(ModalityType.APPLICATION_MODAL);
				chooseObjective.setVisible(true);
			
				
				
			}
		});
		
		addObjective.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addObjective();
			}
		});

		addObjective.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(objectivePanel, "Objective");
				show(esdmPanel, "Objective");
			}
		});
		
	}

	public void initReportingButtonListeners() {
		reportingView.viewReportListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(reportingPanel, "findChildReport");
				show(esdmPanel, "findChildReport");
				findChildReport.setDestination(viewReport);
				if(access.equals(guardianAccess))
				{
					Guardian g = (Guardian) model.getCurrentUser();
					findChildReport.setChildren(g.getChildren());
					System.out.println(g.getChildren().size());
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
				//show(reportingPanel, "Reporting");
				show(esdmPanel, "Reporting");
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
				//show(objectivePanel, "Objective");
				show(esdmPanel, "Objective");
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
						objective.setHidden(viewObjective.getHidden());
						//show(objectivePanel, "Objective");
						show(esdmPanel, "Objective");
						showMessage("Objective successfully added");
					} catch (Exception e) {
						showErrorMessage(e.getMessage());
						e.printStackTrace();
					}
			}
		});


		objectiveView.refineSearch(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				JLabel lblText = new JLabel("Text:");
				JTextField txtText = new JTextField(25);;

				JLabel lblLevel = new JLabel("Level:");
				JTextField txtLevel = new JTextField(3);;
				

				Object[] array = { lblText, txtText, lblLevel, txtLevel };

				int res = JOptionPane.showConfirmDialog(null, array, "Refine Search",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				
				if(res == 0)
				{
					int lvl;
					if(txtLevel.getText().length() == 0) {
						lvl = 0;
					}
					else {
						lvl = Integer.parseInt(txtLevel.getText()); 
					}
			
					objectiveView.setSearchVariables(txtText.getText(), lvl);
				} else
				{
					objectiveView.refreshView();
				}
				
			}
		});
		
		objectiveView.viewObjectives(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				try {					
					viewObjective.setObjective(objectiveView
							.getSelectedObjective());
					//show(objectivePanel, "viewObjective");
					show(esdmPanel, "viewObjective");
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
					//show(objectivePanel, "addObjectiveChild");
					show(esdmPanel, "addObjectiveChild");
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
					//show(objectivePanel, "Objective");
					show(esdmPanel, "Objective");
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
				//show(objectivePanel, "Objective");
				show(esdmPanel, "Objective");
			}
		});

		objectiveView.addNewObjective(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(objectivePanel, "addObjective");
				show(esdmPanel, "addObjective");
			}
		});

		editChild.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(childPanel, "Child");
				show(esdmPanel, "Child");
			}
		});
	
		editChild.addNewGuardianListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewGuardian = new AddNewGuardian();
				
				addNewGuardian.saveButtonListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						addNewGuardian.setVisible(false);
						try {
							String name = addNewGuardian.getName();
							String username = addNewGuardian.getUsername();
							String email = addNewGuardian.getEmail();
							String phone = addNewGuardian.getPhone();
							String password = Helper.generateRandomString(8);
							Guardian g = model.newGuardian(name, username, email, phone, password, "g");
							Child c = editChild.getChild();
							model.addChildGuardian(c, g);
							showErrorMessage("The initial password has been set to: " + password +
									". Please note this down and inform the user");
							editChild.refreshView();
						} catch (NullPointerException e) {
							e.printStackTrace();
							showErrorMessage(e.getMessage());
						}
						catch (Exception e) {
							e.printStackTrace();
							showErrorMessage(e.getMessage());
						}
						
					}
				});
				
				addNewGuardian.cancelButtonListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						addNewGuardian.setVisible(false);
					}
				});
				
				addNewGuardian.setModalityType(ModalityType.APPLICATION_MODAL);
				addNewGuardian.setVisible(true);

			}
		});
		
		editChild.addExistingGuardianListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
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
				
				addGuardian.cancelButtonListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						addGuardian.setVisible(false);
					}
				});
			
				addGuardian.setModalityType(ModalityType.APPLICATION_MODAL);
				addGuardian.setVisible(true);
				
			}
		});
		
		editChild.addObjectiveListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {			
				
				chooseObjective = new ChooseObjective(model.getObjectiveList());
				chooseObjective.setSaveText("Add");
			
				chooseObjective.saveButtonListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						chooseObjective.setVisible(false);
						try{
							model.addObjectiveChild(editChild.getChild(), chooseObjective.getObjective());
						} catch(Exception e) {
							showErrorMessage(e.getMessage());
						} 
						finally{
							editChild.refreshView();
						}
					}
				});

				chooseObjective.setModalityType(ModalityType.APPLICATION_MODAL);
				chooseObjective.setVisible(true);
			
			}
		});
		
		

		editChild.saveChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Object[] childDetails = editChild.getInformation();
				Child child = editChild.getChild();
				String name = (String) childDetails[0];
				Calendar dob = (Calendar) childDetails[1];
				Calendar dateJoined = (Calendar) childDetails[2];

				try {
						model.updateChild(child, name, dob, dateJoined);
						showMessage("Details successfully saved.");
					} catch (Exception e) {
						showErrorMessage(e.getMessage());
					}
				editChild.refreshView();
			}
		});
		
		editChild.removeObjectiveListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					model.removeObjective(editChild.getChild(), editChild.getSelectedObjective());
				editChild.refreshView();
				showMessage("Successfully removed objective from child");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}
			}
		});
		
		editChild.setMasteredListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Child c = editChild.getChild();
					Objective o = editChild.getSelectedObjective();
					model.setMastered(c, o, true);
					editChild.refreshView();
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}
			}
		});
		
		editChild.addObjectiveListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//TODO Add Objective to Child
			}
		});
		
		editChild.incrementStepListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Child c = editChild.getChild();
					Objective o = editChild.getSelectedObjective();
					model.incrementStep(c, o);
					lblMessage.setText("");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}
				editChild.refreshView();
				
			}
		});
		
		editChild.decrementStepListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Child c = editChild.getChild();
					Objective o = editChild.getSelectedObjective();
					model.decrementStep(c, o);
					lblMessage.setText("");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}
				editChild.refreshView();
			}
		});
		accountView.changeEmailAddress(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(accountPanel, "changeEmail");
				show(esdmPanel, "changeEmail");
			}
		});

		accountView.changePassword(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(accountPanel, "changePassword");
				show(esdmPanel, "changePassword");
			}
		});
		
		accountView.newUserAccount(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(accountPanel, "newUserAccount");
				show(esdmPanel, "newUserAccount");
			}
		});
		
		accountView.roomsListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(accountPanel, "roomView");
				show(esdmPanel, "roomView");
			}
		});
		
		accountView.objectiveTypeListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(accountPanel, "objectiveTypeView");
				show(esdmPanel, "objectiveTypeView");
			}
		});
		
		accountView.userAccountsListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(accountPanel, "userAccountView");
				show(esdmPanel, "userAccountView");
			}
		});
		
		objectiveTypeView.removeObjectiveTypeListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					model.removeObjectiveType(objectiveTypeView.getSelectedObjectiveType());
					objectiveTypeView.refreshView();
					System.out.println("REMOVE");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		
		accountView.settingListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//show(accountPanel, "settingView");
				show(esdmPanel, "settingView");
			}
		});
		
		settingView.editSettingListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					settingView.editSetting();
					settingView.refreshView();
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		
		roomView.editRoomListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					roomView.editRoom();
					roomView.refreshView();
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		
		settingView.removeSettingListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try{
					model.removeSetting(settingView.getSelectedSetting());
					settingView.refreshView();
				} catch (ArrayIndexOutOfBoundsException e) {
					showErrorMessage("No setting was selected.");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}
				roomView.refreshView();
			}
		});
		
		roomView.removeRoomListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try{
				model.removeRoom(roomView.getSelectedRoom());
				}
				catch (Exception e)
				{
					e.printStackTrace();
					showErrorMessage(e.getMessage());
				}
				roomView.refreshView();
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
			//show(objectivePanel, "Objective");
			show(esdmPanel, "Objective");
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
			//show(accountPanel, "Account");
			show(esdmPanel, "Account");
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
		//show(reportingPanel, "viewReport");
		show(esdmPanel, "viewReport");
	}

	// Adds a child to the model then shows the EditChild panel with all those
	// details listed

	private void addChildSubmit(ActionEvent evt) {
		String name = addChild.getChildName();
		Calendar dob = addChild.getDob();
		Calendar dateJoined = addChild.getDateJoined();
		try {
			Child c = model.addChild(name, dob, dateJoined);
			editChild.setChild(c);
			//show(childPanel, "editChild");
			show(esdmPanel, "editChild");
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
					System.out.println("TEST");
					UIManager.put("nimbusBlueGrey", new Color(195, 198, 205)); // Buttons and table headings
					UIManager.put("nimbusBase", new Color(176,196, 222)); // Other
				//	UIManager.put("control", new Color(176, 196, 222)); // Background
					UIManager.put("control", new Color(220, 229, 255));

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
		MessageFade mf = new MessageFade(lblMessage);
		new Thread(mf).start();
	}

	private void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(parent, message);
		showMessage(message);
	}
	
	public void saveMark() {
		try {
		Setting setting = changeMark.getSetting();
		Child child = changeMark.getChild();
		Calendar time = changeMark.getTime();
		Objective objective = changeMark.getObjective();
		Step step = changeMark.getStep();
		int markVal = changeMark.getIntMark();
		String comment = changeMark.getComment();
		
		model.updateMark(changeMark.getMark(), setting, child, time, objective, step, markVal, comment);
		
		} catch (Exception e) {
			showMessage(e.getMessage());
		}
	}
	
	private void show(JPanel panel, String card) {
		CardLayout temp = (CardLayout) panel.getLayout();
		temp.show(panel, card);
		lblMessage.setText("");
		backList.add(card);
	}
	
	ArrayList<String> backList = new ArrayList<String>();
}