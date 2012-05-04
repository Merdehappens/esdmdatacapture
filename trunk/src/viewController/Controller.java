package viewController;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListModel;
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


import java.net.MalformedURLException;
import java.util.ArrayList;

//testing
/**
 * @author Damian
 *
 */
public class Controller extends JFrame {

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
	private ESDMModel model;


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

	/**
	 * Create the frame.
	 * @throws MalformedURLException 
	 */

	public void initComponents()
	{
		
		//Sets the main frame Title, and bounds.

		setTitle("ESDM Data Capture");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Set the main layout of the project (tabbed pane)

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		
		tabbedPane.setBounds(0, 0, 848, 613);
		contentPane.add(tabbedPane);
		
		

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
		
		accountPanel = new JPanel();
		tabbedPane.addTab("Account Managment", null, accountPanel, null);
		accountPanel.setLayout(new CardLayout(0, 0));
		
		

		homeView = new HomeView();
		homePanel.add(homeView, "Home");

		
		
		sessionView = new SessionView();
		sessionPanel.add(sessionView, "Session");

		addDay = new AddDay(model);
		sessionPanel.add(addDay, "addDay");

		viewSession = new ViewSession();
		sessionPanel.add(viewSession, "viewSession");

		logSessionData = new LogSessionData();
		sessionPanel.add(logSessionData, "logSessionData");



		childView = new ChildView();
		childPanel.add(childView, "Child");

		addChild = new AddChild(model);
		childPanel.add(addChild, "addChild");

		editChild = new EditChild();
		childPanel.add(editChild, "editChild");

		findChild = new FindChild(model);
		childPanel.add(findChild, "findChild");

		objectiveView = new ObjectiveView();
		objectivePanel.add(objectiveView, "Objective");

		addObjective = new AddObjective();
		objectivePanel.add(addObjective, "addObjective");

		reportingPanel = new JPanel();
		tabbedPane.addTab("Reporting", null, reportingPanel, null);
		reportingPanel.setLayout(new CardLayout(0, 0));

		reportingView = new ReportingView();
		reportingPanel.add(reportingView, "Reporting");

		accountView = new AccountView();
		accountPanel.add(accountView, "Account");



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
				DefaultListModel<Child> list = new DefaultListModel();
				
				ArrayList<Child> childList = new ArrayList<Child>(model.getChildList());
				
				for(int i = 0; i < childList.size(); i++)
				{
					list.addElement(childList.get(i));
				}					
				findChild.setList(list);
				
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
				editChild.setChild(findChild.getSelectedChild());
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
	
	//Takes in one of the tabbed panes panels and a string of card name and shows that panel

	private void show(JPanel panel, String card)
	{
		CardLayout temp = (CardLayout)panel.getLayout();
		temp.show(panel, card);
	}



	private void findChildCancel(ActionEvent evt)
	{
		show(childPanel, "Child");
	}

	private void findChildSubmit(ActionEvent evt)
	{
		editChild.setChild(findChild.getSelectedChild());
		show(childPanel, "editChild");
	}

	private void addChildSubmit(ActionEvent evt)
	{
		addChild.addChild();
		show(childPanel, "addChild");
	}

	private void addObjectiveChild(ActionEvent evt)
	{

	}

	private void viewObjectives(ActionEvent evt)
	{


	}



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


}