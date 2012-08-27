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
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;

import system.helper.Helper;
import system.individuals.Child;
import system.marking.Mark;
import system.marking.Objective;
import system.marking.Step;
import system.sessions.Day;
import system.sessions.Session;
import javax.swing.JTextPane;

public class ChangeMark extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Child child;
	private int step;
	private Calendar time;
	private ArrayList<Session> sessions;
	private ArrayList<Objective> objectives;
	private JComboBox<Mark> cmbMark;
	private JComboBox<Child> cmbChild;
	private JButton btnSave;
	private JButton btnCancel;
	private Mark mark;
	private Day day;
	private DefaultComboBoxModel<Session> cmbSessionModel;
	private DefaultComboBoxModel<Child> cmbChildModel;
	private DefaultComboBoxModel<Objective> cmbObjectiveModel;
	private JTextField txtStep;
	private JTextField txtTime;
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
			lblSession.setBounds(10, 77, 53, 14);
			contentPanel.add(lblSession);
		}
		{
			JLabel lblChangeMark = new JLabel("Change Mark");
			lblChangeMark.setHorizontalAlignment(SwingConstants.CENTER);
			lblChangeMark.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblChangeMark.setBounds(10, 11, 520, 44);
			contentPanel.add(lblChangeMark);
		}
		

		
		JComboBox<Session> cmbSession = new JComboBox<Session>();
		cmbSessionModel = new DefaultComboBoxModel<Session>();
		cmbSession.setModel(cmbSessionModel);
		
		cmbSession.setBounds(73, 74, 223, 20);
		contentPanel.add(cmbSession);
		
		
		
		cmbChild = new JComboBox<Child>();
		cmbChild.setBounds(73, 102, 223, 20);
		contentPanel.add(cmbChild);
		cmbChildModel = new DefaultComboBoxModel<Child>();
		cmbChild.setModel(cmbChildModel);
		

		
		
		JLabel lblObjective = new JLabel("Objective");
		lblObjective.setBounds(10, 131, 53, 14);
		contentPanel.add(lblObjective);
		
		JComboBox<Objective> cmbObjective = new JComboBox<Objective>();
		cmbObjectiveModel = new DefaultComboBoxModel<Objective>();
		cmbObjective.setModel(cmbObjectiveModel);
		
		cmbObjective.setBounds(73, 128, 457, 20);
		contentPanel.add(cmbObjective);
		
		
		
		
		JLabel lblStep = new JLabel("Step");
		lblStep.setBounds(10, 189, 46, 14);
		contentPanel.add(lblStep);
		
		txtStep = new JTextField();
		txtStep.setBounds(73, 186, 46, 20);
		contentPanel.add(txtStep);
        	
		
		
		timeSpinner = new JSpinner( new SpinnerDateModel() );
		timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		 // will only show the current time
		
		timeSpinner.setBounds(363, 74, 167, 30);
		contentPanel.add(timeSpinner);
		
		
		JLabel lblMark = new JLabel("Mark");
		lblMark.setBounds(179, 189, 46, 14);
		contentPanel.add(lblMark);
		
		cmbMark = new JComboBox<Mark>();
		cmbMark.setBounds(222, 186, 53, 20);
		cmbMarkModel = new DefaultComboBoxModel<Mark>();
		contentPanel.add(cmbMark);
		cmbMark.setModel(cmbMarkModel);
		

		
		txtComments = new JTextArea();
		txtComments.setBounds(10, 240, 520, 113);
		contentPanel.add(txtComments);
		txtComments.setLineWrap(true);
		txtComments.setWrapStyleWord(true);
		
		JLabel lblChild = new JLabel("Child");
		lblChild.setBounds(10, 105, 53, 14);
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
	
	public void refreshView(Mark mark, Day day)
	{
		this.mark = mark;
		this.day = day;
		child = mark.getChild();
		
		Date d = mark.getTime().getTime();
		timeSpinner.setValue(d);

		
		sessions = new ArrayList<Session>(day.getSessions());
		int size = sessions.size();
		for(int i = 0; i < size; i++)
		{
			cmbSessionModel.addElement(sessions.get(i));
		}
		
		cmbSessionModel.setSelectedItem(mark.getSession());
		
		ArrayList<Child> children = new ArrayList<Child>(day.getChildren());
		size = children.size();
		for(int i = 0; i < size; i++)
		{
			cmbChildModel.addElement(children.get(i));
		}
		cmbChildModel.setSelectedItem(child);
		
		
		objectives = new ArrayList<Objective>(child.getObjectives());
		
		size = objectives.size();
		boolean obj = (mark.getObjective() == null);
		
		if(obj)
		{
			cmbObjectiveModel.addElement(null);
		}
		
		for(int i = 0; i < size; i++)
		{
			cmbObjectiveModel.addElement(objectives.get(i));
		}

		if(!obj)
		{
			cmbObjectiveModel.setSelectedItem(mark.getObjective());
		}
		
		
        if(mark.getStep() != null)
        {
        	txtStep.setText(mark.getStep().getNo());
        }
        
		
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

	public Session getSession() {
		return (Session) cmbSessionModel.getSelectedItem();
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
				throw new Exception("Step is in incorrect format."); 
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
