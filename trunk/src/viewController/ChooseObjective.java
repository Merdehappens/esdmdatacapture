package viewController;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;

import system.individuals.Guardian;
import system.marking.Objective;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChooseObjective extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnSave;
	private JButton btnCancel;
	private JTextField txtGuardianName;
	private ArrayList<Objective> objectives;
	private JList<Objective> lstObjective;
	
	
	/**
	 * Create the dialog.
	 */
	public ChooseObjective() {
		this.objectives = new ArrayList<Objective>();
		initialise();
	}	
	
	public ChooseObjective(List<Objective> objectives) {
		this.objectives = new ArrayList<Objective>(objectives);
		initialise();
	}

	public void initialise()
	{
		setBounds(100, 100, 554, 423);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblTitle = new JLabel("Select Objective");
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblTitle.setBounds(10, 11, 518, 44);
			contentPanel.add(lblTitle);
		}
		
		txtGuardianName = new JTextField();
		txtGuardianName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				populateList(txtGuardianName.getText());
			}
		});
		txtGuardianName.setBounds(148, 68, 353, 33);
		contentPanel.add(txtGuardianName);
		txtGuardianName.setColumns(10);
		
		JLabel lblSearchGuardianName = new JLabel("Search Objective");
		lblSearchGuardianName.setBounds(43, 69, 128, 30);
		contentPanel.add(lblSearchGuardianName);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 110, 461, 199);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPanel.add(scrollPane);
		lstObjective = new JList<Objective>();
		scrollPane.setViewportView(lstObjective);
		lstObjective.setModel(new DefaultListModel<Objective>());
		

		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				btnSave = new JButton("Save");
				btnSave.setActionCommand("OK");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			
			
				btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			
		
		
		populateList("");
		
	}
	

	public void populateList(String search)
	{
		DefaultListModel<Objective> listModel = (DefaultListModel<Objective>) lstObjective.getModel();
		listModel.removeAllElements();
		
		for(int i = 0; i < objectives.size(); i++)
		{
			Objective o = objectives.get(i);
			if(o.getName().toLowerCase().contains(search.toLowerCase()))
			{
				listModel.addElement(o);
			}
		}
	}
	
	public void setObjectives(List<Objective> objectives)
	{
		this.objectives = new ArrayList<Objective>(objectives);
	}
	
	public void saveButtonListener(ActionListener al){
		btnSave.addActionListener(al);
	}
	
	public void cancelButtonListener(ActionListener al){
		btnCancel.addActionListener(al);
	}


	public Objective getObjective() {
		return lstObjective.getSelectedValue();
	}
}
