package viewController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingConstants;
import system.individuals.Child;
import system.marking.Mark;
import system.marking.Objective;
import system.marking.Step;
import system.sessions.Day;
import system.sessions.Setting;
import java.awt.event.ActionEvent;

public class ChangeMark extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int step;
	private Calendar time;
	private ArrayList<Setting> settings;
	private ArrayList<Objective> objectives;
	private JComboBox<Mark> cmbMark;
	private JComboBox<Child> cmbChild;
	private JButton btnSave;
	private JButton btnCancel;
	private Mark mark;
	private Day day;
	private DefaultComboBoxModel<Setting> cmbSettingModel;
	private DefaultComboBoxModel<Child> cmbChildModel;
	private DefaultComboBoxModel<Objective> cmbObjectiveModel;
	private JTextField txtStep;
	private DefaultComboBoxModel<Mark> cmbMarkModel;
	private JTextArea txtComments;
	private DateEditor timeEditor;
	private SpinnerDateModel spinnerModel;
	private JSpinner timeSpinner;

	

	/**
	 * Create the dialog.
	 */
	public ChangeMark() {
		setBounds(100, 100, 556, 435);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSession = new JLabel("Session");
			lblSession.setBounds(10, 74, 53, 28);
			contentPanel.add(lblSession);
		}
		{
			JLabel lblChangeMark = new JLabel("Change Mark");
			lblChangeMark.setHorizontalAlignment(SwingConstants.CENTER);
			lblChangeMark.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblChangeMark.setBounds(10, 11, 520, 44);
			contentPanel.add(lblChangeMark);
		}
		

		
		JComboBox<Setting> cmbSetting = new JComboBox<Setting>();
		cmbSettingModel = new DefaultComboBoxModel<Setting>();
		cmbSetting.setModel(cmbSettingModel);
		
		cmbSetting.setBounds(73, 74, 223, 28);
		contentPanel.add(cmbSetting);
		
		
		
		cmbChild = new JComboBox<Child>();
		cmbChild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setObjectives(mark);
			}
		});
		cmbChild.setBounds(73, 113, 223, 28);
		contentPanel.add(cmbChild);
		cmbChildModel = new DefaultComboBoxModel<Child>();
		cmbChild.setModel(cmbChildModel);
		

		
		
		JLabel lblObjective = new JLabel("Objective");
		lblObjective.setBounds(10, 150, 53, 25);
		contentPanel.add(lblObjective);
		
		JComboBox<Objective> cmbObjective = new JComboBox<Objective>();
		cmbObjectiveModel = new DefaultComboBoxModel<Objective>();
		cmbObjective.setModel(cmbObjectiveModel);
		
		cmbObjective.setBounds(73, 147, 457, 28);
		contentPanel.add(cmbObjective);
		
		
		
		
		JLabel lblStep = new JLabel("Step");
		lblStep.setBounds(10, 200, 46, 14);
		contentPanel.add(lblStep);
		
		txtStep = new JTextField();
		txtStep.setBounds(73, 197, 28, 28);
		contentPanel.add(txtStep);
        	
		
		
		timeSpinner = new JSpinner( new SpinnerDateModel() );
		timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		
		timeSpinner.setBounds(363, 74, 167, 30);
		contentPanel.add(timeSpinner);
		
		
		JLabel lblMark = new JLabel("Mark");
		lblMark.setBounds(179, 200, 46, 14);
		contentPanel.add(lblMark);
		
		cmbMark = new JComboBox<Mark>();
		cmbMark.setBounds(222, 197, 53, 28);
		cmbMarkModel = new DefaultComboBoxModel<Mark>();
		contentPanel.add(cmbMark);
		cmbMark.setModel(cmbMarkModel);
		

		
		txtComments = new JTextArea();
		txtComments.setBounds(10, 240, 520, 113);
		contentPanel.add(txtComments);
		txtComments.setLineWrap(true);
		txtComments.setWrapStyleWord(true);
		
		JLabel lblChild = new JLabel("Child");
		lblChild.setBounds(10, 116, 53, 25);
		contentPanel.add(lblChild);
		
		JLabel lblHhmmss = new JLabel("hh:mm:ss");
		lblHhmmss.setBounds(366, 58, 82, 14);
		contentPanel.add(lblHhmmss);
	
		

		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnSave = new JButton("Save");
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		
		
	}
	
	protected void setObjectives(Mark mark) {
		// Get the child that has been selected
		Child child = (Child) cmbChildModel.getSelectedItem();
		// Removed everything from the objectives model
		cmbObjectiveModel.removeAllElements();
		
		// Gets all the objectives from the selected child.
		objectives = new ArrayList<Objective>(child.getObjectives());
	
		
		// Adds the an empty objective to the combo box
		cmbObjectiveModel.addElement(null);

		// Iterates through list of objectives in a child adding them all to the combo box
		int size = objectives.size();		
		for(int i = 0; i < size; i++)
		{
			cmbObjectiveModel.addElement(objectives.get(i));
		}

		if(child == mark.getChild())
		{
			cmbObjectiveModel.setSelectedItem(mark.getObjective());
		}
		else
		{
			cmbObjectiveModel.setSelectedItem(null);
		}
		
		setSteps(mark);


	}

	private void setSteps(Mark mark) {
		txtStep.setText(mark.getStep().getNo());
	}

	public void refreshView(Mark mark, Day day)
	{
		
		this.mark = mark;
		this.day = day;
		Child child = mark.getChild();
		
		
		Date d = mark.getTime().getTime();
		timeSpinner.setValue(d);
		

		// Iterate through all the sessions in the day and add them to combo box
		settings = new ArrayList<Setting>(day.getSettings());
		int size = settings.size();
		for(int i = 0; i < size; i++)
		{
			cmbSettingModel.addElement(settings.get(i));
		}
		// Set the selected item for the session box to be the one selected.
		cmbSettingModel.setSelectedItem(mark.getSetting());
		
		// Get all the children from the day. iterate through teh list adding all children
		ArrayList<Child> children = new ArrayList<Child>(day.getChildren());
		size = children.size();
		for(int i = 0; i < size; i++)
		{
			cmbChildModel.addElement(children.get(i));
		}
		// Set the child to the one that was selected
		cmbChildModel.setSelectedItem(child);
		
		setObjectives(mark);
		
		for(int i = -1; i < 2; i++)
		{
			Mark newMark = new Mark(i);
			cmbMarkModel.addElement(newMark);
			if(newMark.toString().equals(mark.toString()))
			{
				cmbMarkModel.setSelectedItem(newMark);
			}
		}
	
		txtComments.setText(mark.getComment());
		
	}
	
	public void saveButtonListener(ActionListener al){
		btnSave.addActionListener(al);
	}
	
	public void cancelButtonListener(ActionListener al){
		btnCancel.addActionListener(al);
	}

	public Setting getSetting() {
		return (Setting) cmbSettingModel.getSelectedItem();
	}

	public Child getChild() {
		return (Child) cmbChildModel.getSelectedItem();
	}

	public Calendar getTime() {
		SpinnerDateModel s = timeEditor.getModel();
		Calendar c = Calendar.getInstance();
		c.setTime(s.getDate());
		return c;
	}

	public Objective getObjective() {
		return (Objective) cmbObjectiveModel.getSelectedItem();
	}

	public Step getStep() throws Exception {
		int step = -1;
		if(txtStep.getText().length() != 0)
		{
			try{
				step = Integer.parseInt(txtStep.getText());
			}
			catch(Exception e)
			{
				throw new IllegalArgumentException("Step is not a number."); 
			}
		}
		else
		{
			return null;
		}
		Objective o = getObjective();
		Step s = null;
		if(step <= o.getStepsNo())
		{
			s = o.getStep(step);
		}
		
		return s;

	}

	public String getComment() {
		return txtComments.getText();
	}
	
	public Mark getMark()
	{
		return mark;
	}

	public int getIntMark() {
		Mark m = (Mark) cmbMarkModel.getSelectedItem(); 
		return m.getMark();
	}
}
