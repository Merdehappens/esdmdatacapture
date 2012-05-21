package viewController;


import javax.swing.JButton;

import system.individuals.Child;
import system.marking.Mark;
import system.marking.Objective;
import system.marking.Step;
import system.model.ESDMModel;
import system.sessions.Day;
import system.sessions.Session;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import javax.swing.JList;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class LogSessionData extends PanelView {
	private JButton btnSubmit;
	private JLabel lblSetting;
	private JLabel lblChild;
	private JLabel lblObjectives;
	private JLabel lblStep;
	private JLabel lblMark;
	private JButton btnCommitMark;
	private JButton btnAddTimestamp;
	private JList<Session> lstSession;
	private JList<Child> lstChild;
	private JList<Objective> lstObjective;
	private JList<Step> lstStep;
	private JList<Mark> lstMark;
	private Day day;
	private JButton btnChildPrevious;
	private JButton btnChildNext;
	private JButton btnObjectivePrevious;
	private JButton btnObjectiveNext;
	private JButton btnStepPrevious;
	private JButton btnStepNext;
	private JButton btnMarkPrevious;
	private JButton btnMarkNext;
	
	private DefaultListModel<Session> sessionModel;
	private DefaultListModel<Child> childModel;
	private DefaultListModel<Objective> objectiveModel;
	private DefaultListModel<Step> stepModel;
	private DefaultListModel<Mark> markModel;
	
	/**
	 * Create the panel.
	 */
	public LogSessionData() {
		initialise();
	
	}
	
	public LogSessionData(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	public void setDay(Day day)
	{
		this.day = day;
		setDayView();
	}
	
	private void initialise()
	{
		setLayout(null);
		JLabel lblTitle = new JLabel("Log Session Data");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 757, 40);
		add(lblTitle);
		
		btnSubmit = new JButton("Complete Data Logging");
		btnSubmit.setBounds(10, 404, 154, 23);
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
		lblObjectives.setBounds(310, 65, 146, 14);
		add(lblObjectives);
		
		lblStep = new JLabel("Step");
		lblStep.setHorizontalAlignment(SwingConstants.CENTER);
		lblStep.setBounds(466, 65, 206, 14);
		add(lblStep);
		
		lblMark = new JLabel("Mark");
		lblMark.setHorizontalAlignment(SwingConstants.CENTER);
		lblMark.setBounds(682, 65, 85, 14);
		add(lblMark);

		
		sessionModel = new DefaultListModel<Session>();
		lstSession = new JList<Session>(sessionModel);
		lstSession.setBounds(10, 94, 154, 174);
		add(lstSession);
		
		childModel = new DefaultListModel<Child>();
		lstChild = new JList<Child>(childModel);
		lstChild.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				populateObjectiveList();
			}
		});
		lstChild.setBounds(174, 94, 126, 174);
		add(lstChild);
		
		objectiveModel = new DefaultListModel<Objective>();
		lstObjective = new JList<Objective>(objectiveModel);
		lstObjective.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
					populateStepList();
			}
		});
		
		lstObjective.setBounds(310, 94, 146, 174);
		add(lstObjective);
		
		stepModel = new DefaultListModel<Step>();
		lstStep = new JList<Step>(stepModel);
		lstStep.setBounds(466, 94, 206, 174);
		add(lstStep);
		
		markModel = new DefaultListModel<Mark>();
		lstMark = new JList<Mark>(markModel);
		lstMark.setBounds(682, 94, 85, 174);
		add(lstMark);
		
		markModel.addElement(new Mark(-1));
		markModel.addElement(new Mark(0));
		markModel.addElement(new Mark(1));
		
		lstMark.setSelectedIndex(1);
		
		btnCommitMark = new JButton("Commit Mark");
		btnCommitMark.setBounds(10, 346, 154, 47);
		add(btnCommitMark);
		
		btnAddTimestamp = new JButton("Add Timestamp");
		btnAddTimestamp.setBounds(398, 346, 115, 47);
		add(btnAddTimestamp);
		
		JButton btnSettingNext = new JButton(">");
		btnSettingNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lstSession.setSelectedIndex(lstSession.getSelectedIndex() + 1);
			}
		});
		btnSettingNext.setBounds(90, 270, 40, 40);
		add(btnSettingNext);
		
		JButton btnSettingPrevious = new JButton("<");
		btnSettingPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstSession.setSelectedIndex(lstSession.getSelectedIndex() - 1);
			}
			
			

		});
		btnSettingPrevious.setBounds(40, 270, 40, 40);
		add(btnSettingPrevious);
		
		btnChildPrevious = new JButton("<");
		btnChildPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstChild.setSelectedIndex(lstChild.getSelectedIndex() - 1);
			}
		});
		
		btnChildPrevious.setBounds(198, 270, 40, 40);
		add(btnChildPrevious);
		
		btnChildNext = new JButton(">");
		btnChildNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstChild.setSelectedIndex(lstChild.getSelectedIndex() + 1);
			}
		});
		
		btnChildNext.setBounds(241, 270, 40, 40);
		add(btnChildNext);
		
		btnObjectivePrevious = new JButton("<");
		btnObjectivePrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstObjective.setSelectedIndex(lstObjective.getSelectedIndex() - 1);
			}
		});
		
		btnObjectivePrevious.setBounds(336, 270, 40, 40);
		add(btnObjectivePrevious);
		
		btnObjectiveNext = new JButton(">");
		btnObjectiveNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstObjective.setSelectedIndex(lstObjective.getSelectedIndex() + 1);
			}
		});
		
		btnObjectiveNext.setBounds(386, 270, 40, 40);
		add(btnObjectiveNext);
		
		btnStepPrevious = new JButton("<");
		btnStepPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstStep.setSelectedIndex(lstStep.getSelectedIndex() - 1);
			}
		});
		btnStepPrevious.setBounds(524, 270, 40, 40);
		add(btnStepPrevious);
		
		btnStepNext = new JButton(">");
		btnStepNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstStep.setSelectedIndex(lstStep.getSelectedIndex() + 1);
			}
		});
		btnStepNext.setBounds(574, 270, 40, 40);
		add(btnStepNext);
		
		btnMarkPrevious = new JButton("<");
		btnMarkPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstMark.setSelectedIndex(lstMark.getSelectedIndex() - 1);
			}
		});
		btnMarkPrevious.setBounds(682, 270, 40, 40);
		add(btnMarkPrevious);
		
		btnMarkNext = new JButton(">");
		btnMarkNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstMark.setSelectedIndex(lstMark.getSelectedIndex() + 1);
			}
		});
		btnMarkNext.setBounds(729, 270, 40, 40);
		add(btnMarkNext);
	}
	
	protected void populateObjectiveList() {
		
		objectiveModel.clear();
		try{
		Child child = childModel.get(lstChild.getSelectedIndex());
		
		
		ArrayList<Objective> temp = (ArrayList<Objective>)child.getObjectives();
		
		for(int i = 0; i < temp.size(); i++)
		{
			objectiveModel.addElement(temp.get(i));
		}
		}
		catch (Exception e)
		{}
		lstObjective.setSelectedIndex(0);
		

		
	}
	
	private void populateStepList() {
		
		stepModel.clear();

		
		Objective objective = lstObjective.getSelectedValue();
		if(objective != null)
		{
		ArrayList<Step> steps = (ArrayList<Step>) objective.getSteps();
		if(steps != null)
		{
			
		
		for(int i = 0; i < steps.size(); i++)
		{
			stepModel.addElement(steps.get(i));
		}
		
		}
		lstStep.setSelectedIndex(0);
		}
	}

	public void refresh()
	{
		sessionModel.clear();
		childModel.clear();
		objectiveModel.clear();
		stepModel.clear();
		markModel.clear();
		
		day.getChildren();
		day.getSessions();
		
		
	}
	
	public void submitListener(ActionListener al)
	{
		btnSubmit.addActionListener(al);	
	}

	
	public void commitMarkListener(ActionListener al)
	{
		btnCommitMark.addActionListener(al);	
	}
	
	public void addTimestampListener(ActionListener al)
	{
		btnAddTimestamp.addActionListener(al);
	}
	
	public void setDayView()
	{
		sessionModel.clear();
		childModel.clear();
		
		ArrayList<Session> settings = (ArrayList<Session>)day.getSessions();
		ArrayList<Child> children = (ArrayList<Child>)day.getChildren();
		
		for(int i = 0; i < settings.size(); i++)
		{
			sessionModel.addElement(settings.get(i));
		}
		
		for(int i = 0; i < children.size(); i++)
		{
			childModel.addElement(children.get(i));
		}
		
	}
	
	public void refreshView()
	{
		setDayView();
		
	}

	public void addMark() {
		try {
		Session session = sessionModel.get(lstSession.getSelectedIndex());
		Child child = childModel.get(lstChild.getSelectedIndex());
		Objective objective = objectiveModel.get(lstObjective.getSelectedIndex());
		Step step = stepModel.get(lstStep.getSelectedIndex());
		int mark = markModel.get(lstMark.getSelectedIndex()).getMark();
		

			this.getModel().addMark(session, child, objective, step, mark, day);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: One or more items aren't selected, Mark was not saved");
		}
	}

	public Day getDay() {
		return day;
	}
}
