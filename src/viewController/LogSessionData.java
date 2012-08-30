package viewController;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import system.individuals.Child;
import system.marking.Mark;
import system.marking.Objective;
import system.marking.Step;
import system.model.ESDMModel;
import system.sessions.Day;
import system.sessions.Session;

public class LogSessionData extends PanelView {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5029048398607740463L;
	private JButton btnSubmit;
	private JLabel lblSetting;
	private JLabel lblChild;
	private JLabel lblObjectives;
	private JLabel lblMark;
	private JButton btnCommitMark;
	private JButton btnAddTimestamp;
	private JList<Session> lstSession;
	private JList<Child> lstChild;
	private JList<Objective> lstObjective;
	private JList<Mark> lstMark;
	private Day day;
	private JButton btnChildPrevious;
	private JButton btnChildNext;
	private JButton btnObjectivePrevious;
	private JButton btnObjectiveNext;
	private JButton btnMarkPrevious;
	private JButton btnMarkNext;

	private DefaultListModel<Session> sessionModel;
	private DefaultListModel<Child> childModel;
	private DefaultListModel<Objective> objectiveModel;
	private DefaultListModel<Step> stepModel;
	private DefaultListModel<Mark> markModel;


	public LogSessionData() {

		initialise();

	}

	public LogSessionData(ESDMModel model) {
		super(model);
		initialise();
	}

	public void setDay(Day day) {
		this.day = day;
		setDayView();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {

		setLayout(null);

		super.setTitle("Log Data");

		btnSubmit = new JButton("Complete Data Logging");
		btnSubmit.setBounds(322, 346, 154, 47);
		add(btnSubmit);

		lblSetting = new JLabel("Setting");
		lblSetting.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetting.setBounds(10, 62, 154, 21);
		add(lblSetting);

		lblChild = new JLabel("Child");
		lblChild.setHorizontalAlignment(SwingConstants.CENTER);
		lblChild.setBounds(174, 65, 126, 14);
		add(lblChild);

		lblObjectives = new JLabel("Objectives");
		lblObjectives.setHorizontalAlignment(SwingConstants.CENTER);
		lblObjectives.setBounds(310, 65, 522, 14);
		add(lblObjectives);

		lblMark = new JLabel("Mark");
		lblMark.setHorizontalAlignment(SwingConstants.CENTER);
		lblMark.setBounds(842, 65, 83, 14);
		add(lblMark);

		sessionModel = new DefaultListModel<Session>();
		lstSession = new JList<Session>(sessionModel);
		lstSession.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lstSession.setBounds(10, 94, 154, 174);
		add(lstSession);

		childModel = new DefaultListModel<Child>();
		lstChild = new JList<Child>(childModel);
		lstChild.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lstChild.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {
				populateObjectiveList();
			}

		});
		lstChild.setBounds(174, 94, 126, 174);
		add(lstChild);

		objectiveModel = new DefaultListModel<Objective>();
		lstObjective = new JList<Objective>(objectiveModel);
		lstObjective.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lstObjective.setBounds(310, 94, 522, 174);
		add(lstObjective);

		stepModel = new DefaultListModel<Step>();

		markModel = new DefaultListModel<Mark>();
		lstMark = new JList<Mark>(markModel);
		lstMark.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lstMark.setBounds(842, 94, 83, 174);
		add(lstMark);

		markModel.addElement(new Mark(-1));
		markModel.addElement(new Mark(0));
		markModel.addElement(new Mark(1));

		lstMark.setSelectedIndex(1);

		btnCommitMark = new JButton("Commit Mark");
		btnCommitMark.setMnemonic(KeyEvent.VK_J);
		btnCommitMark.setBounds(10, 346, 154, 47);
		add(btnCommitMark);

		btnAddTimestamp = new JButton("Add Timestamp");
		btnAddTimestamp.setBounds(10, 404, 154, 47);
		add(btnAddTimestamp);

		JButton btnSettingNext = new JButton(">");
		btnSettingNext.setMnemonic(KeyEvent.VK_B);

		btnSettingNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setIndex(lstSession, sessionModel, 1);
			}
		});
		btnSettingNext.setBounds(90, 270, 40, 40);
		add(btnSettingNext);

		JButton btnSettingPrevious = new JButton("<");
		btnSettingPrevious.setMnemonic(KeyEvent.VK_Z);
		btnSettingPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setIndex(lstSession, sessionModel, -1);
			}

		});
		btnSettingPrevious.setBounds(40, 270, 40, 40);
		add(btnSettingPrevious);

		btnChildPrevious = new JButton("<");

		btnChildPrevious.setMnemonic(KeyEvent.VK_A);

		btnChildPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setIndex(lstChild, childModel, -1);
			}
		});

		btnChildPrevious.setBounds(198, 270, 40, 40);
		add(btnChildPrevious);

		btnChildNext = new JButton(">");

		btnChildNext.setMnemonic(KeyEvent.VK_S);

		btnChildNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setIndex(lstChild, childModel, 1);
			}
		});

		btnChildNext.setBounds(241, 270, 40, 40);
		add(btnChildNext);

		btnObjectivePrevious = new JButton("<");
		btnObjectivePrevious.setMnemonic(KeyEvent.VK_Q);
		btnObjectivePrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setIndex(lstObjective, objectiveModel, -1);
			}
		});

		btnObjectivePrevious.setBounds(527, 270, 40, 40);
		add(btnObjectivePrevious);

		btnObjectiveNext = new JButton(">");
		btnObjectiveNext.setMnemonic(KeyEvent.VK_W);
		btnObjectiveNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setIndex(lstObjective, objectiveModel, 1);

			}
		});

		btnObjectiveNext.setBounds(577, 270, 40, 40);
		add(btnObjectiveNext);

		btnMarkPrevious = new JButton("<");
		btnMarkPrevious.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMarkPrevious.setMnemonic(KeyEvent.VK_D);
		btnMarkPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setIndex(lstMark, markModel, -1);
			}
		});
		btnMarkPrevious.setBounds(842, 270, 40, 40);
		add(btnMarkPrevious);

		btnMarkNext = new JButton(">");
		btnMarkNext.setMnemonic(KeyEvent.VK_F);
		btnMarkNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setIndex(lstMark, markModel, 1);
			}

		});
		btnMarkNext.setBounds(885, 270, 40, 40);
		add(btnMarkNext);

	}

	protected void setIndex(JList lst, DefaultListModel model, int i) {
		int temp = lst.getSelectedIndex() + i;
		if (temp >= model.getSize()) {
			lst.setSelectedIndex(0);
		} else if (temp < 0) {
			lst.setSelectedIndex(model.getSize() - 1);
		} else {
			lst.setSelectedIndex(temp);
		}
	}

	protected void populateObjectiveList() {

		objectiveModel.clear();
		try {
			Child child = childModel.get(lstChild.getSelectedIndex());

			ArrayList<Objective> temp = (ArrayList<Objective>) child
					.getObjectives();

			int size = temp.size();
			for (int i = 0; i < size; i++) {
				objectiveModel.addElement(temp.get(i));
			}
		} catch (Exception e) {
		}
		lstObjective.setSelectedIndex(0);

	}

	public void clearLists() {
		sessionModel.clear();
		childModel.clear();
		objectiveModel.clear();
		stepModel.clear();
	}

	// Takes in an ActionListener and adds it to the submit button
	public void submitListener(ActionListener al) {
		btnSubmit.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the commit mark button
	public void commitMarkListener(ActionListener al) {
		btnCommitMark.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the add timestamp button
	public void addTimestampListener(ActionListener al) {
		btnAddTimestamp.addActionListener(al);
	}

	public void setDayView() {
		sessionModel.clear();
		childModel.clear();

		ArrayList<Session> settings = new ArrayList<Session>(day.getSessions());
		ArrayList<Child> children = new ArrayList<Child>(day.getChildren());

		int size = settings.size();
		for (int i = 0; i < size; i++) {
			sessionModel.addElement(settings.get(i));
		}

		size = children.size();
		for (int i = 0; i < size; i++) {
			childModel.addElement(children.get(i));
		}

	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		clearLists();
		setDayView();
	}

	public void addMark() {
		try {
			Session session = sessionModel.get(lstSession.getSelectedIndex());
			Child child = childModel.get(lstChild.getSelectedIndex());
			Objective objective = objectiveModel.get(lstObjective
					.getSelectedIndex());
			Step step = child.getCurrentStep(objective);
			int mark = markModel.get(lstMark.getSelectedIndex()).getMark();
			this.getModel().addMark(session, child, objective, step, mark, day);
		} catch (Exception e) {
			JOptionPane
					.showMessageDialog(this,
							"Error: One or more items are not selected. This mark was not saved.");
		}
	}

	public void addTimestamp() {
		Session session = null;
		Child child = null;
		Objective objective = null;
		Step step = null;
		int mark = 0;

		if (lstSession.getSelectedIndex() != -1) {
			session = sessionModel.get(lstSession.getSelectedIndex());
		}

		try {
			child = childModel.get(lstChild.getSelectedIndex());
			this.getModel().addTimestamp(session, child, objective, step, mark,
					day);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Day getDay() {
		return day;
	}
}
