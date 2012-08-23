package viewController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;

import system.helper.Helper;
import system.individuals.Child;
import system.marking.Mark;
import system.marking.Objective;
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

	

	/**
	 * Create the dialog.
	 * @param m 
	 * @param day 
	 */
	public ChangeMark(Mark mark, Day day) {
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
		
		child = mark.getChild();
		
		JComboBox<Session> cmbSession = new JComboBox<Session>();
		sessions = new ArrayList<Session>(day.getSessions());
		int size = sessions.size();
		DefaultComboBoxModel<Session> cmbSessionModel = new DefaultComboBoxModel<Session>();
		for(int i = 0; i < size; i++)
		{
			cmbSessionModel.addElement(sessions.get(i));
		}
		cmbSession.setModel(cmbSessionModel);
		
		cmbSessionModel.setSelectedItem(mark.getSession());
		
		cmbSession.setBounds(73, 74, 223, 20);
		contentPanel.add(cmbSession);
		
		
		
		cmbChild = new JComboBox<Child>();
		cmbChild.setBounds(73, 102, 223, 20);
		contentPanel.add(cmbChild);
		DefaultComboBoxModel<Child> cmbChildModel = new DefaultComboBoxModel<Child>();
		
		ArrayList<Child> children = new ArrayList<Child>(day.getChildren());
		size = children.size();
		for(int i = 0; i < size; i++)
		{
			cmbChildModel.addElement(children.get(i));
		}
		cmbChildModel.setSelectedItem(child);
		cmbChild.setModel(cmbChildModel);
		
		
		JLabel lblObjective = new JLabel("Objective");
		lblObjective.setBounds(10, 131, 53, 14);
		contentPanel.add(lblObjective);
		
		JComboBox<Objective> cmbObjective = new JComboBox<Objective>();
		
		objectives = new ArrayList<Objective>(child.getObjectives());
		
		size = objectives.size();
		DefaultComboBoxModel<Objective> cmbObjectiveModel = new DefaultComboBoxModel<Objective>();
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
		
		cmbObjective.setModel(cmbObjectiveModel);
		
		cmbObjective.setBounds(73, 128, 457, 20);
		contentPanel.add(cmbObjective);
		
		
		
		
		JLabel lblStep = new JLabel("Step");
		lblStep.setBounds(10, 189, 46, 14);
		contentPanel.add(lblStep);
		
		JTextField txtStep = new JTextField();
		txtStep.setBounds(73, 186, 46, 20);
		contentPanel.add(txtStep);
        if(mark.getStep() != null)
        {
        	txtStep.setText(mark.getStep().getNo());
        }
        	
		JTextField txtTime = new JTextField();
		txtTime.setBounds(363, 74, 167, 20);
		contentPanel.add(txtTime);
		
		txtTime.setText(Helper.simpleDateFormat(mark.getTime()));
		
		JLabel lblMark = new JLabel("Mark");
		lblMark.setBounds(179, 189, 46, 14);
		contentPanel.add(lblMark);
		
		cmbMark = new JComboBox<Mark>();
		cmbMark.setBounds(222, 186, 53, 20);
		DefaultComboBoxModel<Mark> cmbMarkModel = new DefaultComboBoxModel<Mark>();
		contentPanel.add(cmbMark);
		for(int i = -1; i < 2; i++)
		{
			Mark newMark = new Mark(i);
			cmbMarkModel.addElement(newMark);
			if(newMark.toString().equals(mark.toString()))
			{
				cmbMarkModel.setSelectedItem(newMark);
			}
		}
		cmbMark.setModel(cmbMarkModel);
		
		JTextArea txtComments = new JTextArea();
		txtComments.setBounds(10, 240, 520, 113);
		contentPanel.add(txtComments);
		txtComments.setLineWrap(true);
		txtComments.setWrapStyleWord(true);
		txtComments.setText(mark.getComment());
		
		JLabel lblChild = new JLabel("Child");
		lblChild.setBounds(10, 105, 53, 14);
		contentPanel.add(lblChild);
		

		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton saveButton = new JButton("Save");
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
