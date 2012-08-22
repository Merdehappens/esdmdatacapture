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
		
		JLabel lblObjective = new JLabel("Objective");
		lblObjective.setBounds(10, 110, 53, 14);
		contentPanel.add(lblObjective);
		
		JComboBox<Objective> cmbObjective = new JComboBox<Objective>();
		
		objectives = new ArrayList<Objective>(child.getObjectives());
		
		size = objectives.size();
		DefaultComboBoxModel<Objective> cmbObjectiveModel = new DefaultComboBoxModel<Objective>();
		for(int i = 0; i < size; i++)
		{
			cmbObjectiveModel.addElement(objectives.get(i));
		}
		cmbObjectiveModel.setSelectedItem(mark.getObjective());
		
		cmbObjective.setModel(cmbObjectiveModel);
		
		
		cmbObjective.setBounds(73, 107, 457, 20);
		contentPanel.add(cmbObjective);
		
		JLabel lblStep = new JLabel("Step");
		lblStep.setBounds(10, 189, 46, 14);
		contentPanel.add(lblStep);
		
		JTextField txtStep = new JTextField();
		txtStep.setBounds(73, 186, 46, 17);
		contentPanel.add(txtStep);
        
        
		JTextField txtTime = new JTextField();
		txtTime.setBounds(363, 74, 167, 17);
		contentPanel.add(txtTime);
		
		JLabel lblMark = new JLabel("Mark");
		lblMark.setBounds(179, 189, 46, 14);
		contentPanel.add(lblMark);
		
		JComboBox cmboxMark = new JComboBox();
		cmboxMark.setBounds(222, 186, 53, 20);
		contentPanel.add(cmboxMark);
		
		JTextArea txtComments = new JTextArea();
		txtComments.setBounds(10, 240, 520, 113);
		contentPanel.add(txtComments);
		txtComments.setLineWrap(true);
		txtComments.setWrapStyleWord(true);
		
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
