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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.MissingResourceException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import system.individuals.UserAccount;
import system.marking.Mark;
import system.marking.Objective;
import system.marking.ObjectiveType;
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
	private EditAccount editAccount;

	private HashMap<String, JPanel> panelMap = new HashMap<String, JPanel>();
	private RoomView roomView;
	private SettingView settingView;
	private ObjectiveTypeView objectiveTypeView;
	private JButton btnExit;
	private JPanel mainPanel;
	private JButton btnHome;

	public Controller() throws Exception {
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

	// called before program exits to make sure the database connection is
	// closed correctly
	private void exitProgram() {
		int answer = JOptionPane.showConfirmDialog(this,
				"Are you sure you want to quit?", "Quit",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "There was an error loading from database. this program will now close.");
					System.exit(0);
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

	/**
	 * Iniitialises all of the graphical components for the program.
	 */
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

		addObjective = new AddObjective(model);
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
		
		editAccount = new EditAccount(model);
		esdmPanel.add(editAccount, "editAccount");
		panelMap.put("editAccount", esdmPanel);

		JPanel footer = new JPanel(null);

		mainPanel.add(footer, BorderLayout.PAGE_END);

		footer.setBounds(20, 100, 200, 100);
		footer.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));


		btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("YES");
				if(access.equalsIgnoreCase("g")) {
					show(esdmPanel, "findChildReport");
				} else {
					show(esdmPanel, "Home");
				}
			}
		});
		footer.add(btnHome);
		
		int width = this.getWidth();
		width = width / 2 + (width / 4);
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
		backList.add("Home");

		homeView.refreshView();
		

		setPermissions();
		
	}

	// This function calls the functions from within each view class that
	// retrieve an ActionListener
	// And determine what should be done in the case of a button press.

	private void initSessionButtonListeners() {

		homeView.logDataListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					logSessionData.setDay(homeView.getSelectedDay());
					show(esdmPanel, "logSessionData");
				} catch (ArrayIndexOutOfBoundsException e) {
					showErrorMessage("You must select a session");
				}
			}
		});

		homeView.administrationListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Account");
			}
		});

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
		
		homeView.createSessionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "addDay");
			}
		});

		homeView.accountsListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				String access = model.getCurrentAccess();
				
				if(access.equals("g"))
				{
					editAccount.setUser(model.getCurrentUser());
					show(esdmPanel, "editAccount");
					
				} else
				{
					show(esdmPanel, "userAccountView");
				}
			}
		});

		homeView.reportingListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				show(esdmPanel, "findChildReport");
				findChildReport.setDestination(viewReport);
				if (access.equals(guardianAccess)) {
					Guardian g = (Guardian) model.getCurrentUser();
					findChildReport.setChildren(g.getChildren());
				} else {
					findChildReport.setChildren(model.getChildList(true));
				}
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
		
		userAccountView.resetPasswordListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				UserAccount u = null;
				try {
					
					u = userAccountView.getSelectedAccount();			
					String name = JOptionPane.showInputDialog(null, "Please enter the password you wish to set.");
					if(name != null)
					{
						model.setPassword(u, name);
					}
					showMessage("Successfully changed password.");
				} catch (Exception e) {
					showMessage(e.getMessage());
				}
				
			}
		});


		
		objectiveTypeView.addObjectiveTypeListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					objectiveTypeView.addObjectiveType();
					objectiveTypeView.refreshView();
					showMessage("This objective was successfully added");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}
			}
		});

		
		roomView.addRoomListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					roomView.addRoom();
				} catch (Exception e) {
					showMessage(e.getMessage());
				}
			}
		});

		reviewSession.homeListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Home");
			}
		});

		// Listeners to link back to admin screen

		objectiveTypeView
				.backToAdminListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						show(esdmPanel, "Account");
					}
				});

		userAccountView.addNewUser(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println("YES");
				show(esdmPanel, "newUserAccount");
			}
		});

		settingView.backToAdminListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Account");
			}
		});

		roomView.backToAdminListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "Account");
			}
		});

		viewReport.backToSelectListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "findChildReport");
			}
		});
		
		viewReport.dateFromListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				Date dateFrom = viewReport.getDateFrom();
				Date dateTo = viewReport.getDateTo();
				if(dateFrom != null && dateTo != null) {
					System.out.println("TEST");
					if(dateTo.before(dateFrom)) {
						showErrorMessage("Date to cannot be before Date from.");
						viewReport.setDateFrom(dateTo);
					}
				}
				viewReport.refreshView();
			}
		});
		
		viewReport.dateToListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				Date dateFrom = viewReport.getDateFrom();
				Date dateTo = viewReport.getDateTo();
				if(dateFrom != null && dateTo != null) {
					System.out.println("TEST");
					if(dateTo.before(dateFrom)) {
						showErrorMessage("Date to cannot be before Date from.");
						viewReport.setDateTo(dateFrom);
					}
				}
				viewReport.refreshView();
			}
		});

		reviewSession
				.reviewSessionsListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						show(esdmPanel, "Session");
					}
				});
		
		userAccountView.viewUser(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					editAccount.setUser(userAccountView.getSelectedAccount());
					show(esdmPanel, "editAccount");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}
			}
		});
		
		editAccount.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(access.equals("g"))
				{
					show(esdmPanel, "findChildReport");
					
				} else
				{
					show(esdmPanel, "userAccountView");
				}
			}
		});
		
		editAccount.saveAccountListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				UserAccount u = editAccount.getAccount();
				
				String name = editAccount.getUserName();
				String ph = editAccount.getPhoneNumber();
				String email = editAccount.getEmail();
				
				try {
					model.setUserDetails(u, name, ph, email);
					if(access.equalsIgnoreCase("g")) {
						show(esdmPanel, "findChildReport");
					} else {
						show(esdmPanel, "userAccountView");
					}
					showMessage("Successfully changed details.");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}
				
			}
		});

		editAccount.changePasswordListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				
				JLabel lblPassword = new JLabel("Old Password:");
				
				JTextField txtPassword = new JPasswordField();

				JLabel lblNewPassword = new JLabel("New Password:");
				JTextField txtNewPassword = new JPasswordField();

				JLabel lblNewPassword2 = new JLabel("Confirm New Password:");
				JTextField txtNewPassword2 = new JPasswordField();
				
				Object[] array = { lblPassword, txtPassword, lblNewPassword, 
						txtNewPassword, lblNewPassword2, txtNewPassword2 };

				int res = JOptionPane.showConfirmDialog(null, array,
						"Change Password", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);

				if (res == 0) {
					try {
						model.changePassword(txtPassword.getText(),
								txtNewPassword.getText(), txtNewPassword2.getText());
					} catch (Exception e) {
						showErrorMessage(e.getMessage());
						e.printStackTrace();
					}
				}
				
			}
		});
		
		addDay.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				try {

					ArrayList<Child> children = addDay.getChildren();
					Room room = addDay.getRoom();
					ArrayList<Setting> settings = addDay.getSettings();
					Calendar date = addDay.getDate();
					if(date != null)
					{
					boolean exists = model.dayExists(room, date);

					int res = 0;
					if (exists) {
						res = JOptionPane
								.showConfirmDialog(
										null,
										"Another day already exists for this date in this room. are you sure you wish to proceed?",
										"Add New Day",
										JOptionPane.YES_NO_OPTION);
					}
					System.out.println("RES " + res);
					if (res == 0) {
						logSessionData.setDay(model.addDay(date, children,
								room, settings));
						// show(sessionPanel, "logSessionData");
						show(esdmPanel, "logSessionData");
					} else {
						// show(sessionPanel, "Session");
						show(esdmPanel, "Session");
					}
					}
					else
					{
						showErrorMessage("30001: Date must be selected");
					}

				} catch (Exception e) {
					showErrorMessage(e.getMessage());
					e.printStackTrace();
				}

			}
		});

		sessionView.newDay(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// show(sessionPanel, "addDay");
				show(esdmPanel, "addDay");
			}
		});

		addDay.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// show(sessionPanel, "Session");
				show(esdmPanel, "Session");
			}
		});
		
		logSessionData.listenBehaviourMarkListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int mark = logSessionData.getSelectedBehaviourMark();
				model.playBehaviouralMarkSound(mark);
			}
		});
		
		logSessionData.listenMarkListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Mark m = logSessionData.getSelectedMark();
				model.playMarkSound(m);
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

		logSessionData
				.listenObjectiveListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							model.playSound(logSessionData
									.getSelectedObjective());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

		logSessionData
				.listenSettingListener(new java.awt.event.ActionListener() {
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
				try {
					logSessionData.addMark();
					showMessage("Mark added.");
				} catch (Exception e) {
					showMessage(e.getMessage());
				}
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
				show(esdmPanel, "reviewSession");
			}
		});

		logSessionData
				.saveBehaviourMarkListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {

						try {
							Day day = logSessionData.getDay();
							Child child = logSessionData.getSelectedChild();
							int markInt = logSessionData
									.getSelectedBehaviourMark();
							model.addBehaviouralMark(day, child, markInt);
							showMessage("Behavioural Mark added.");
						} catch (Exception e) {
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

				changeMark
						.saveButtonListener(new java.awt.event.ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								changeMark.setVisible(false);
								try{
									
								
								if (reviewSession.getSelectedMark() != null) {
									saveMark();
								} else {
									showMessage("No mark selected.");
								}
								reviewSession.refreshView();
								showMessage("Mark successfully saved");
								}
								catch(Exception e) {
									e.printStackTrace();
									showMessage(e.getMessage());
								}
							}
						});

				changeMark
						.cancelButtonListener(new java.awt.event.ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								changeMark.setVisible(false);
							}
						});

				if(mark != null)
				{
					changeMark.refreshView(mark, day);
					changeMark.setModalityType(ModalityType.APPLICATION_MODAL);
					changeMark.setVisible(true);
				}
				else {
					showErrorMessage("20001: No mark is selected.");
				}

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
					// show(sessionPanel, "reviewSession");
					show(esdmPanel, "reviewSession");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
				}
			}
		});

		reviewSession.logMarksListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				logSessionData.setDay(reviewSession.getDay());
				// show(sessionPanel, "logSessionData");
				show(esdmPanel, "logSessionData");
			}
		});

	}

	private void initChildButtonListeners() {

		addChild.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				addChildSubmit(evt);
			}
		});

		addChild.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// show(childPanel, "Child");
				show(esdmPanel, "Child");
			}
		});

		addObjectiveChild.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// show(childPanel, "Child");
				show(esdmPanel, "Child");
			}
		});

		childViewGrid.addChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// show(childPanel, "addChild");
				show(esdmPanel, "addChild");
			}
		});

		childViewGrid.editChildListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				try {
					editChild.setChild(childViewGrid.getSelectedChild());
					// show(childPanel, "editChild");
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

	private void initAccountButtonListeners() {

		changePassword.changePassword(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				changePassword();
			}
		});

		changePassword.cancel(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// show(accountPanel, "Account");
				show(esdmPanel, "Account");
			}
		});

		changeEmail.saveListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					model.setEmail(changeEmail.getEmail());
					// show(accountPanel, "Account");
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
				// show(accountPanel, "Account");
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
				if (access.equals("false")) {
					showErrorMessage("You must select access level.");
				} else {
					try {
						if (access.equals(guardianAccess)) {
							model.newGuardian(name, username, emailAddress,
									phoneNo, pass, access);
						} else {
							model.newTherapist(name, username, emailAddress,
									phoneNo, pass, access);
						}
						show(esdmPanel, "userAccountView");
						showErrorMessage("The password has been set to: "
								+ pass
								+ "."
								+ "\nPlease note this down and inform the user.");
					} catch (ConstraintViolationException e) {
						showErrorMessage("That username already exists");
					} catch (MissingResourceException e) {
						showErrorMessage(e.getMessage());
					} catch (Exception e) {
						showErrorMessage("Undefined Error: 90001: "
								+ e.getMessage());
					}
				}

			}
		});

		newUserAccount.cancel(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "userAccountView");
			}
		});

	}

	private void initObjectiveButtonListeners() {

		addObjective.templateListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				chooseObjective = new ChooseObjective(model.getObjectiveList());
				chooseObjective.setSaveText("Save");

				chooseObjective
						.saveButtonListener(new java.awt.event.ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								chooseObjective.setVisible(false);
								Objective obj = chooseObjective.getObjective();
								// show(objectivePanel, "addObjective");
								show(esdmPanel, "addObjective");
								try {
									addObjective.setName(obj.getName());
									addObjective.setDescription(obj
											.getDescription());
									addObjective.setLevel(obj.getLevel());
									addObjective.setSteps(obj.getSteps());
								} catch (NullPointerException e) {
									showErrorMessage("10003: No Objective is selected");
								}
							}
						});
				
				chooseObjective.cancelButtonListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						chooseObjective.setVisible(false);
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
				// show(objectivePanel, "Objective");
				show(esdmPanel, "Objective");
			}
		});

	}

	private void initReportingButtonListeners() {


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
		
		findChildReport.changeDetailsListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				editAccount.setUser(model.getCurrentUser());
				show(esdmPanel, "editAccount");
			}
		});

		findChildReport.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// show(reportingPanel, "Reporting");
				show(esdmPanel, "Home");
			}
		});

	}

	/**
	 * Is run after initialising all graphical components, adds all the button listeners to
	 * the different buttons in the program.
	 */
	public void initButtonListeners() {
		initSessionButtonListeners();
		initChildButtonListeners();
		initObjectiveButtonListeners();
		initAccountButtonListeners();
		initReportingButtonListeners();

		viewObjective.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// show(objectivePanel, "Objective");
				show(esdmPanel, "Objective");
			}
		});

		viewObjective.submitListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					Objective objective = viewObjective.getObjective();
					String name = viewObjective.getObjectiveName();
					String description = viewObjective
							.getObjectiveDescription();
					int level = viewObjective.getLevel();
					String[][] steps = viewObjective.getSteps();
					ObjectiveType objType = viewObjective.getObjectiveType();
					boolean hidden = viewObjective.getHidden();
					model.saveObjective(objective, name, description, level,
							steps, objType, hidden);
					objective.setHidden(viewObjective.getHidden());
					show(esdmPanel, "Objective");
					showMessage("Objective successfully saved.");
				} catch (Exception e) {
					showErrorMessage(e.getMessage());
					e.printStackTrace();
				}
			}
		});

		objectiveView.refineSearch(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				JLabel lblText = new JLabel("Text:");
				JTextField txtText = new JTextField(25);

				JLabel lblLevel = new JLabel("Level:");
				JTextField txtLevel = new JTextField(2);

				JLabel lblType = new JLabel("Type:");
				JComboBox<ObjectiveType> type = new JComboBox<ObjectiveType>();

				ArrayList<ObjectiveType> typeList = new ArrayList<ObjectiveType>(
						model.getObjectiveTypeList());
				type.addItem(null);
				for (int i = 0; i < typeList.size(); i++) {
					type.addItem(typeList.get(i));
				}

				Object[] array = { lblText, txtText, lblLevel, txtLevel,
						lblType, type };

				int res = JOptionPane.showConfirmDialog(null, array,
						"Refine Search", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);

				if (res == 0) {
					int lvl;
					if (txtLevel.getText().length() == 0) {
						lvl = 0;
					} else {
						lvl = Integer.parseInt(txtLevel.getText());
					}

					objectiveView.setSearchVariables(txtText.getText(), lvl,
							(ObjectiveType) type.getSelectedItem());
				} else {
					objectiveView.refreshView();
				}

			}
		});

		objectiveView.viewObjectives(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				try {
					viewObjective.setObjective(objectiveView
							.getSelectedObjective());
					// show(objectivePanel, "viewObjective");
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
					// show(objectivePanel, "addObjectiveChild");
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
					// show(objectivePanel, "Objective");
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
				// show(objectivePanel, "Objective");
				show(esdmPanel, "Objective");
			}
		});

		objectiveView.addNewObjective(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// show(objectivePanel, "addObjective");
				show(esdmPanel, "addObjective");
			}
		});

		editChild.cancelListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// show(childPanel, "Child");
				show(esdmPanel, "Child");
			}
		});

		editChild.addNewGuardianListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addNewGuardian = new AddNewGuardian();

				addNewGuardian
						.saveButtonListener(new java.awt.event.ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								addNewGuardian.setVisible(false);
								try {
									String name = addNewGuardian.getName();
									String username = addNewGuardian
											.getUsername();
									String email = addNewGuardian.getEmail();
									String phone = addNewGuardian.getPhone();
									String password = Helper
											.generateRandomString(8);
									Guardian g = model.newGuardian(name,
											username, email, phone, password,
											"g");
									Child c = editChild.getChild();
									model.addChildGuardian(c, g);
									showErrorMessage("The initial password has been set to: "
											+ password
											+ ". Please note this down and inform the user");
									editChild.refreshView();
								} catch (NullPointerException e) {
									e.printStackTrace();
									showErrorMessage(e.getMessage());
								} catch (Exception e) {
									e.printStackTrace();
									showErrorMessage(e.getMessage());
								}

							}
						});

				addNewGuardian
						.cancelButtonListener(new java.awt.event.ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								addNewGuardian.setVisible(false);
							}
						});

				addNewGuardian.setModalityType(ModalityType.APPLICATION_MODAL);
				addNewGuardian.setVisible(true);

			}
		});
		
	
		
		editChild.removeGuardianListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Guardian g = editChild.getSelectedGuardian();
					model.removeGuardian(g, editChild.getChild());
					editChild.refreshView();
					showMessage("Guardian successfully removed");
				} catch(Exception e)
				{
					showErrorMessage("30010: There was no row selected");
				}
			}
		});

		editChild
				.addExistingGuardianListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						addGuardian = new AddGuardian(model.getGuardianList());

						addGuardian
								.saveButtonListener(new java.awt.event.ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										addGuardian.setVisible(false);
										if (addGuardian.getGuardian() != null) {
											try {
												model.addChildGuardian(
														editChild.getChild(),
														addGuardian
																.getGuardian());
											} catch (Exception e) {
												showErrorMessage(e.getMessage());
												e.printStackTrace();
											}
										} else {
											showMessage("No Guardian Selected.");
										}
										editChild.refreshView();
									}
								});

						addGuardian
								.cancelButtonListener(new java.awt.event.ActionListener() {
									public void actionPerformed(ActionEvent evt) {
										addGuardian.setVisible(false);
									}
								});

						addGuardian
								.setModalityType(ModalityType.APPLICATION_MODAL);
						addGuardian.setVisible(true);

					}
				});

		editChild.addObjectiveListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				chooseObjective = new ChooseObjective(model.getObjectiveList());
				chooseObjective.setSaveText("Add");

				chooseObjective
						.saveButtonListener(new java.awt.event.ActionListener() {
							public void actionPerformed(ActionEvent evt) {
								chooseObjective.setVisible(false);
								try {
									model.addObjectiveChild(
											editChild.getChild(),
											chooseObjective.getObjective());
								} catch (Exception e) {
									showErrorMessage(e.getMessage());
								} finally {
									editChild.refreshView();
								}
							}
						});
				chooseObjective.cancelButtonListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						chooseObjective.setVisible(false);
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
					show(esdmPanel, "Child");
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
					model.removeObjective(editChild.getChild(),
							editChild.getSelectedObjective());
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
				// TODO Add Objective to Child
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

		accountView.roomsListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "roomView");
			}
		});

		accountView.objectiveTypeListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				show(esdmPanel, "objectiveTypeView");
			}
		});

		objectiveTypeView
				.removeObjectiveTypeListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						try {
							model.removeObjectiveType(objectiveTypeView
									.getSelectedObjectiveType());
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
				try {
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
				try {
					model.removeRoom(roomView.getSelectedRoom());
					roomView.refreshView();
					showMessage("Room successfully removed");
				} catch (Exception e) {
					e.printStackTrace();
					showErrorMessage(e.getMessage());
				}
				
			}
		});

	}

	private void addObjective() {

		try {
			String name = addObjective.getObjectiveName();
			String description = addObjective.getObjectiveDescription();
			String level = addObjective.getLevel();
			String[][] steps = addObjective.getSteps();
			ObjectiveType objType = addObjective.getObjectiveType();

			model.addObjective(name, description, steps, level, objType);
			// show(objectivePanel, "Objective");
			show(esdmPanel, "Objective");
			showMessage("Objective successfully added to system.");
		} catch (Exception e) {
			e.printStackTrace();
			showErrorMessage(e.getMessage());
		}
	}

	protected void changePassword() {
		try {
			String[] arr = changePassword.getNewPassword();
			model.changePassword(changePassword.getOldPassword(), arr[0],
					arr[1]);
			// show(accountPanel, "Account");
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
		
		if (findChildReport.getSelectedChild() == null) {
			throw new Exception("30001: No child is selected.");
		}
		viewReport.setChild(findChildReport.getSelectedChild());
		viewReport.refreshTable();
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
			// show(childPanel, "editChild");
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
					UIManager.put("nimbusBlueGrey", new Color(195, 198, 205)); // Buttons
																				// and
																				// table
																				// headings
					UIManager.put("nimbusBase", new Color(176, 196, 222)); // Other
					// UIManager.put("control", new Color(176, 196, 222)); //
					// Background
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
	private void showLogin() {

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


	}

	private void setPermissions() {
		UserAccount u = model.getCurrentUser();
		access = u.getAccess();
		homeView.setAccess(access);
		
		if(access.equalsIgnoreCase(guardianAccess))
		{
			Guardian g = (Guardian)u;
			show(esdmPanel, "findChildReport");
			findChildReport.setAccess(access);
			findChildReport.setChildren(g.getChildren());
			findChildReport.refreshView();
			
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

	private void saveMark() throws Exception {
		try{
			Setting setting = changeMark.getSetting();
			Child child = changeMark.getChild();
			Calendar time = changeMark.getTime();
			Objective objective = changeMark.getObjective();
			Step step = changeMark.getStep();
			int markVal = changeMark.getIntMark();
			String comment = changeMark.getComment();

			model.updateMark(changeMark.getMark(), setting, child, time,
					objective, step, markVal, comment);
		} catch(Exception e) {
			e.printStackTrace();
			showErrorMessage("Mark was not saved as one of the fields wasn't correctly filled");
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