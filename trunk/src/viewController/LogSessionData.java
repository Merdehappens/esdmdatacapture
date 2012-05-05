package viewController;


import javax.swing.JButton;

import systemModel.Day;
import systemModel.ESDMModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import javax.swing.JList;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class LogSessionData extends PanelView {
	private JButton btnSubmit;
	private JLabel lblSetting;
	private JLabel lblChild;
	private JLabel lblObjectives;
	private JLabel lblStep;
	private JLabel lblMark;
	private JButton btnCommitMark;
	private JButton btnAddTimestamp;
	private JList lstSetting;
	private JList lstChild;
	private JList lstObjective;
	private JList lstStep;
	private JList lstMark;
	private Day day;
	private JButton btnChildPrevious;
	private JButton btnChildNext;
	private JButton btnObjectivePrevious;
	private JButton btnObjectiveNext;
	private JButton btnStepPrevious;
	private JButton btnStepNext;
	private JButton btnMarkPrevious;
	private JButton btnMarkNext;
	
	/**
	 * Create the panel.
	 */
	public LogSessionData() {
		setLayout(null);
		

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
	}
	
	private void initialise()
	{
		
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

		
		
		lstObjective = new JList();
		lstObjective.setBounds(310, 94, 146, 174);
		add(lstObjective);
		
		lstStep = new JList();
		lstStep.setBounds(466, 94, 206, 174);
		add(lstStep);
		
		lstMark = new JList();
		lstMark.setBounds(682, 94, 85, 174);
		add(lstMark);
		
		btnCommitMark = new JButton("Commit Mark");
		btnCommitMark.setBounds(10, 346, 154, 47);
		add(btnCommitMark);
		
		btnAddTimestamp = new JButton("Add Timestamp");
		btnAddTimestamp.setBounds(398, 346, 115, 47);
		add(btnAddTimestamp);
		
		JButton btnSettingNext = new JButton(">");
		btnSettingNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lstSetting.setSelectedIndex(lstSetting.getSelectedIndex() + 1);
			}
		});
		btnSettingNext.setBounds(90, 270, 40, 40);
		add(btnSettingNext);
		
		JButton btnSettingPrevious = new JButton("<");
		btnSettingPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lstSetting.setSelectedIndex(lstSetting.getSelectedIndex() - 1);
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
	
	public void refresh()
	{
		lstSetting = new JList(new Vector(day.getSessions()));
		lstSetting.setBounds(10, 94, 154, 174);
		add(lstSetting);

		
		lstChild = new JList(new Vector(day.getChildren()));
		lstChild.setBounds(174, 94, 126, 174);
		add(lstChild);
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
}
