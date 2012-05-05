package viewController;

import javax.swing.JButton;

import systemModel.Child;
import systemModel.ESDMModel;
import systemModel.Room;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


import com.toedter.calendar.JDateChooser;


import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

import javax.swing.JToggleButton;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class AddDay extends PanelView {

	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JPanel childPanel;
	private JScrollPane childScrollPane;
	private JComboBox<Room> cmbRoom;
	private JList lstCurrentSession;
	private JList lstSession;
	
	private DefaultListModel listModel;
	private JButton button_1;
	
	
	public AddDay() {

		
		

		initialise();
	
	}
	
	public AddDay(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				refreshView();
			}
		});
		
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Start New Day");
		lblTitle.setBounds(10, 11, 430, 21);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle);
		
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(10, 43, 73, 14);
		add(lblDate);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(48, 43, 184, 29);
		add(dateChooser);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(438, 488, 89, 23);
		add(btnSubmit);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshView();
			}
		});
		btnReset.setBounds(537, 489, 94, 21);
		add(btnReset);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(641, 489, 94, 21);
		add(btnCancel);
		
		
		
		
		childPanel = new JPanel();
		childPanel.setLayout(null);
		childPanel.setBounds(0, 0, 10000, 10000);
		
		
		childScrollPane = new JScrollPane();
		childScrollPane.setBounds(35, 104, 358, 456);
		childScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		childScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(childScrollPane);
		
		childScrollPane.setViewportView(childPanel);
		
		
		JLabel lblSessions = new JLabel("Sessions");
		lblSessions.setBounds(403, 133, 133, 14);
		lblSessions.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSessions);
		
		JLabel lblCurrentSessionsinOrder = new JLabel("Current Sessions(In Order)");
		lblCurrentSessionsinOrder.setBounds(581, 133, 154, 14);
		lblCurrentSessionsinOrder.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCurrentSessionsinOrder);
		
		lstSession = new JList(new Vector(this.getModel().getSessionList()));
		lstSession.setBounds(403, 158, 133, 240);
		add(lstSession);
		
		listModel = new DefaultListModel();
		
		lstCurrentSession = new JList(listModel);
		lstCurrentSession.setBounds(591, 158, 143, 240);
		add(lstCurrentSession);
		
		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.addElement(lstSession.getSelectedValue());
			}
		});
		button.setBounds(546, 210, 43, 29);
		add(button);
		
		JLabel lblRoom = new JLabel("Room:");
		lblRoom.setBounds(403, 430, 46, 14);
		add(lblRoom);
		
		cmbRoom = new JComboBox();
		
		Vector<Room> v = new Vector<Room>(this.getModel().getRoomList());
		ComboBoxModel<Room> cmbModel = new DefaultComboBoxModel<Room>(v);
		
		cmbRoom.setModel(cmbModel);
		cmbRoom.setBounds(451, 427, 138, 21);
		add(cmbRoom);
		
		button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.remove(lstCurrentSession.getSelectedIndex());
			}
		});
		button_1.setBounds(546, 246, 43, 29);
		add(button_1);
		
		refreshView();

	}
	
	
	public void refreshView()
	{
		childPanel.removeAll();
		
		ArrayList<Child> temp = new ArrayList<Child>(this.getModel().getChildList());
		
		
		
		Object[] tglbtn = new JToggleButton[temp.size()];
		
		JToggleButton tempButton;
		
		int x = -90;
		int s = -300;
		
		for(int i = 0; i < temp.size(); i++)
		{
			
			if(i % 3 == 0)
			{
				x = x + 100;
				s = s + 300;
			}
			
			tempButton = (JToggleButton)tglbtn[i];
			
			Child child = temp.get(i);
			
			tempButton = new JToggleButton(child.getName());
			tempButton.setBounds(10 + (100 * i) - s, x, 100, 100);
			
			childPanel.add(tempButton);
			
		}

		childPanel.setPreferredSize(new Dimension(350, (tglbtn.length * 40)));
		revalidate();
		
		
		Vector<Room> v = new Vector<Room>(this.getModel().getRoomList());
		ComboBoxModel<Room> cmbModel = new DefaultComboBoxModel<Room>(v);
		cmbRoom.setModel(cmbModel);
	
	}
}
