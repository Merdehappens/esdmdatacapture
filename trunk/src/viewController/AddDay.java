package viewController;

import javax.swing.JButton;

import system.individuals.Child;
import system.model.ESDMModel;
import system.model.Room;
import system.sessions.Session;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


import com.toedter.calendar.JDateChooser;


import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
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

public class AddDay extends PanelView {


	private static final long serialVersionUID = 3352767716977743660L;
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JPanel childPanel;
	private JScrollPane childScrollPane;
	private JComboBox<Room> cmbRoom;
	private JList<Session> lstCurrentSession;
	private JList<Session> lstSession;
	private Object[][] tglbtn;
	private JDateChooser dateChooser;
	private ComboBoxModel<Room> cmbModel;
	
	private DefaultListModel<Session> listModel;
	private JButton button_1;
	
	
	public AddDay() {
		super();
		initialise();
	
	}
	
	public AddDay(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{	
		setLayout(null);
		
		super.setTitle("Add Day");
		
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(10, 43, 73, 14);
		add(lblDate);
		
		dateChooser = new JDateChooser();
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
		
		lstSession = new JList<Session>(new Vector<Session>(this.getModel().getSessionList()));
		lstSession.setBounds(403, 158, 133, 240);
		add(lstSession);
		
		listModel = new DefaultListModel<Session>();
		
		lstCurrentSession = new JList<Session>(listModel);
		lstCurrentSession.setBounds(591, 158, 143, 240);
		add(lstCurrentSession);
		
		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.addElement((Session)lstSession.getSelectedValue());
			}
		});
		button.setBounds(546, 210, 43, 29);
		add(button);
		
		JLabel lblRoom = new JLabel("Room:");
		lblRoom.setBounds(403, 430, 46, 14);
		add(lblRoom);
		
		cmbRoom = new JComboBox<Room>();
		
		Vector<Room> v = new Vector<Room>(this.getModel().getRoomList());
		cmbModel = new DefaultComboBoxModel<Room>(v);
		
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
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				swapButton(-1);
			}
		});
		btnUp.setBounds(744, 210, 60, 29);
		add(btnUp);
		
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapButton(1);
			}
		});
		btnDown.setBounds(744, 249, 60, 26);
		add(btnDown);
		
		refreshView();

	}
	
	
	public void refreshView()
	{
		childPanel.removeAll();
		
		ArrayList<Child> temp = (ArrayList<Child>)this.getModel().getChildList();
		
		
		tglbtn = new Object[temp.size()][2];
		
		JToggleButton tempButton;
		
		int x = -90;
		int s = -300;
		
		int size = temp.size();
		for(int i = 0; i < size; i++)
		{
			if(i % 3 == 0)
			{
				x = x + 100;
				s = s + 300;
			}
			
			
			Child child = temp.get(i);
			
			tglbtn[i][1] = child;
			
			tempButton = new JToggleButton(child.getName());
			tempButton.setBounds(10 + (100 * i) - s, x, 100, 100);
			tglbtn[i][0] = tempButton;
			childPanel.add(tempButton);
			tempButton.requestFocusInWindow();
			
			
		}

		childPanel.setPreferredSize(new Dimension(350, (tglbtn.length * 40)));
		revalidate();
		
		listModel.clear();
		
		Vector<Room> v = new Vector<Room>(this.getModel().getRoomList());
		ComboBoxModel<Room> cmbModel = new DefaultComboBoxModel<Room>(v);
		cmbRoom.setModel(cmbModel);
		
		this.requestFocusInWindow();
		
	}
	
	private void swapButton(int i) 
	{
		int index = lstCurrentSession.getSelectedIndex();
		System.out.println(index);
		if(index >= 0)
		{
			Session temp = (Session)listModel.remove(index);
		
			index = index + i;
		
			if(index >= 0 && index <= listModel.size())
			{
				listModel.add(index, temp);
			}
			else
			{
				index = index - i;
				listModel.add(index, temp);
			}
		}
	}
	
	public void submitListener(ActionListener al)
	{
		btnSubmit.addActionListener(al);	
	}
	
	public void cancelListener(ActionListener al)
	{
		btnCancel.addActionListener(al);
	}
	
	public ArrayList<Child> getChildren()
	{
		ArrayList<Child> childList = new ArrayList<Child>();

		for(int i = 0; i < tglbtn.length; i++)
		{
			JToggleButton toggleButton = (JToggleButton)tglbtn[i][0];
			System.out.println(i);
			if(toggleButton.isSelected())
			{
				childList.add((Child)tglbtn[i][1]);
			}
		}
		return childList;
	}

	public Room getRoom() {
		Room room = cmbModel.getElementAt(cmbRoom.getSelectedIndex());
		return room;
	}

	public ArrayList<Session> getSessions() {
		ArrayList<Session> session = new ArrayList<Session>();
		
		int size = listModel.getSize();;
		for(int i = 0; i < size; i++)
		{
			session.add(listModel.get(i));
		}
		
		return session;
	}

	public Calendar getDate() {
		return dateChooser.getCalendar();
	}
	
}
