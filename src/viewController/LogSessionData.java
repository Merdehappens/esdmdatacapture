package viewController;

//JUst testering

import javax.swing.JButton;

import systemModel.ESDMModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import javax.swing.JList;
import java.awt.Font;

public class LogSessionData extends PanelView {
	private JButton btnSubmit;
	private JLabel lblSetting;
	private JLabel lblChild;
	private JLabel lblObjectives;
	private JLabel lblStep;
	private JLabel lblMark;
	private JButton btnCommitMark;
	private JButton btnAddTimestamp;

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
	
	private void initialise()
	{
		
		JLabel lblTitle = new JLabel("Log Session Data");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 757, 40);
		add(lblTitle);
		
		btnSubmit = new JButton("Complete Data Logging");
		btnSubmit.setBounds(10, 379, 154, 23);
		add(btnSubmit);
		
		lblSetting = new JLabel("Setting");
		lblSetting.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetting.setBounds(10, 62, 154, 21);
		add(lblSetting);
		
		lblChild = new JLabel("Child");
		lblChild.setHorizontalAlignment(SwingConstants.CENTER);
		lblChild.setBounds(174, 65, 115, 14);
		add(lblChild);
		
		lblObjectives = new JLabel("Objectives");
		lblObjectives.setHorizontalAlignment(SwingConstants.CENTER);
		lblObjectives.setBounds(299, 65, 121, 14);
		add(lblObjectives);
		
		lblStep = new JLabel("Step");
		lblStep.setHorizontalAlignment(SwingConstants.CENTER);
		lblStep.setBounds(430, 65, 206, 14);
		add(lblStep);
		
		lblMark = new JLabel("Mark");
		lblMark.setHorizontalAlignment(SwingConstants.CENTER);
		lblMark.setBounds(646, 65, 63, 14);
		add(lblMark);
		
		JList lstSetting = new JList();
		lstSetting.setBounds(10, 94, 154, 174);
		add(lstSetting);
		
		JList lstChild = new JList();
		lstChild.setBounds(174, 94, 115, 174);
		add(lstChild);
		
		JList lstObjective = new JList();
		lstObjective.setBounds(299, 94, 121, 174);
		add(lstObjective);
		
		JList lstStep = new JList();
		lstStep.setBounds(430, 94, 206, 174);
		add(lstStep);
		
		JList lstMark = new JList();
		lstMark.setBounds(646, 94, 63, 174);
		add(lstMark);
		
		btnCommitMark = new JButton("Commit Mark");
		btnCommitMark.setBounds(10, 279, 154, 47);
		add(btnCommitMark);
		
		btnAddTimestamp = new JButton("Add Timestamp");
		btnAddTimestamp.setBounds(174, 279, 115, 47);
		add(btnAddTimestamp);
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
