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
import javax.swing.ToolTipManager;

import javax.swing.JToggleButton;
import java.awt.BorderLayout;

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
	private JButton btnRemove;

	public AddDay() {
		super();
		initialise();

	}

	public AddDay(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the panel.
	
	private void initialise() {
		setLayout(null);

		// Sets the title of the panel
		super.setTitle("Add Day");

		// Initialises and adds a new date chooser to the panel
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBounds(447, 65, 223, 29);
		add(dateChooser);
		

		JLabel lblDate = new JLabel("Date:");
		dateChooser.add(lblDate, BorderLayout.WEST);

		// Initialises and adds a new button to the panel
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(438, 488, 89, 23);
		add(btnSubmit);

		// Initialises and adds a reset button to the page and an action listener
		// that when clicked refresh the view (Reset all fields)
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshView();
			}
		});
		btnReset.setBounds(537, 489, 94, 21);
		add(btnReset);

		// Adds a cancel button to the panel
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(641, 489, 94, 21);
		add(btnCancel);

		// adds a new Panel to the screen inside the scroll pane that was required
		// to choose the child
		childPanel = new JPanel();
		childPanel.setLayout(null);
		childPanel.setBounds(0, 0, 10000, 10000);

		childScrollPane = new JScrollPane();
		childScrollPane.setBounds(35, 104, 358, 407);
		childScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		childScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(childScrollPane);

		childScrollPane.setViewportView(childPanel);

		// Creates 3 labels
		JLabel lblSessions = new JLabel("Sessions");
		lblSessions.setBounds(403, 133, 133, 14);
		lblSessions.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSessions);

		JLabel lblCurrentSessionsinOrder = new JLabel("Current Sessions(In Order)");
		lblCurrentSessionsinOrder.setBounds(581, 133, 154, 14);
		lblCurrentSessionsinOrder.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCurrentSessionsinOrder);
		
		JLabel lblRoom = new JLabel("Room:");
		lblRoom.setBounds(403, 430, 46, 14);
		add(lblRoom);

		// Creates a new list and populates it with all the sessions in the model 
		lstSession = new JList<Session>(new Vector<Session>(this.getModel()
				.getSessionList()));
		lstSession.setBounds(403, 158, 133, 240);
		add(lstSession);

		

		// Creates a new empty list for the chosen sessions to go in
		listModel = new DefaultListModel<Session>();
		lstCurrentSession = new JList<Session>(listModel);
		lstCurrentSession.setBounds(591, 158, 143, 240);
		add(lstCurrentSession);

		// Creates the add button which when pressed adds the chosen element in to the current session list
		JButton btnAdd = new JButton(">");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.addElement((Session) lstSession.getSelectedValue());
			}
		});
		btnAdd.setBounds(546, 210, 43, 29);
		add(btnAdd);
		
		// Creates the remove button which when pressed removes the chosen element in the current session list
		btnRemove = new JButton("<");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(lstCurrentSession.getSelectedIndex() >= 0)
				{
					listModel.remove(lstCurrentSession.getSelectedIndex());
				}
			}
		});
		btnRemove.setBounds(546, 246, 43, 29);
		add(btnRemove);


		// Adds a new combo box to the page which contains the rooms

		cmbRoom = new JComboBox<Room>();
		Vector<Room> v = new Vector<Room>(this.getModel().getRoomList());
		cmbModel = new DefaultComboBoxModel<Room>(v);
		cmbRoom.setModel(cmbModel);
		cmbRoom.setBounds(451, 427, 138, 21);
		add(cmbRoom);

		// Moves the selected object in the list up one
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				swapButton(-1);
			}
		});
		btnUp.setBounds(744, 210, 60, 29);
		add(btnUp);

		// Moves the selected object in the list down one
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				swapButton(1);
			}
		});
		btnDown.setBounds(744, 249, 60, 26);
		add(btnDown);
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		// Removes all children from panel
		childPanel.removeAll();
		
		ArrayList<Child> temp = (ArrayList<Child>) this.getModel()
				.getChildList(true);

		tglbtn = new Object[temp.size()][2];

		JToggleButton tempButton;

		int x = -90;
		int s = -300;
		// Iterates through the list of children and adds buttons for each child
		int size = temp.size();
		for (int i = 0; i < size; i++) {
			if (i % 3 == 0) {
				x = x + 100;
				s = s + 300;
			}

			Child child = temp.get(i);

			tglbtn[i][1] = child;
			
			
			ToolTipManager.sharedInstance().setInitialDelay(0);
			
			String name = child.getName();			
			tempButton = new JToggleButton(name);
			tempButton.setToolTipText(name);
			tempButton.setBounds(10 + (100 * i) - s, x, 100, 100);
			tglbtn[i][0] = tempButton;
			childPanel.add(tempButton);
			tempButton.requestFocusInWindow();

		}

		childPanel.setPreferredSize(new Dimension(350, (tglbtn.length * 40)));
		revalidate();

		listModel.clear();
		// Sets the room list
		Vector<Room> v = new Vector<Room>(this.getModel().getRoomList());
		ComboBoxModel<Room> cmbModel = new DefaultComboBoxModel<Room>(v);
		cmbRoom.setModel(cmbModel);
		dateChooser.setCalendar(null);

		this.requestFocusInWindow();

	}

	// Swaps the items in the list up or down depending on the number parsed through
	// Positive is down the list (higher index) negative is up the list (lower index)
	private void swapButton(int i) {
		int index = lstCurrentSession.getSelectedIndex();
		if (index >= 0) {
			Session temp = (Session) listModel.remove(index);

			index = index + i;

			if (index >= 0 && index <= listModel.size()) {
				listModel.add(index, temp);
			} else {
				index = index - i;
				listModel.add(index, temp);
			}
		}
	}

	// Takes in an ActionListener and adds it to the submit button

	public void submitListener(ActionListener al) {
		btnSubmit.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the cancel button

	public void cancelListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	public ArrayList<Child> getChildren() {
		ArrayList<Child> childList = new ArrayList<Child>();
		// Iterates through the buttons and any that are selected add the child to the list
		for (int i = 0; i < tglbtn.length; i++) {
			JToggleButton toggleButton = (JToggleButton) tglbtn[i][0];
			if (toggleButton.isSelected()) {
				childList.add((Child) tglbtn[i][1]);
			}
		}
		return childList;
	}

	// returns the room that is selected
	public Room getRoom() {
		Room room = cmbModel.getElementAt(cmbRoom.getSelectedIndex());
		return room;
	}

	// Returns the sessions that are selected in the list in order
	public ArrayList<Session> getSessions() {
		ArrayList<Session> session = new ArrayList<Session>();

		int size = listModel.getSize();
		for (int i = 0; i < size; i++) {
			session.add(listModel.get(i));
		}

		return session;
	}

	// Returns the selected date
	public Calendar getDate() {
		return dateChooser.getCalendar();
	}

}
