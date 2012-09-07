package viewController;

import javax.swing.JButton;

import system.model.ESDMModel;
import system.sessions.Day;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class SessionView extends PanelView {

	private static final long serialVersionUID = 4174778855073882231L;
	private JButton btnViewDay;
	private JButton btnReset;
	private JDateChooser dateChooserFrom;
	private JDateChooser dateChooserTo;
	private JScrollPane scrollPane;
	private JTable tblSession;
	private DefaultTableModel tableModel;
	private JButton btnNewDay;


	public SessionView() {
		initialise();
	}

	public SessionView(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {
		setLayout(null);
		// Sets title of the panel
		super.setTitle("Session");
		
		// Adds view Day button to panel
		btnViewDay = new JButton("View Day");
		btnViewDay.setBounds(180, 90, 108, 35);
		add(btnViewDay);

		// Adds the reset search criteria button to the page and add action listener
		// that makes the view refresh
		btnReset = new JButton("Reset Search Criteria");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshView();
			}
		});
		btnReset.setBounds(799, 90, 151, 35);
		add(btnReset);

		// Adds refine search button to the screen that when clicked calls showRefineSearch method
		JButton btnRefineSearch = new JButton("Refine Search");
		btnRefineSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showRefineSearch();
			}
		});
		btnRefineSearch.setBounds(656, 90, 133, 35);
		add(btnRefineSearch);

		// Adds a scroll pane to the screen for the table to go in
		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 135, 900, 390);
		add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		// Creates a new JTable
		tblSession = new MyJTable();
		
		scrollPane.setViewportView(tblSession);
		
		// Sets the name of the columns
		String[] columnNames = new String[] { "SessionID", "Room Name", "No of children", "Date" };
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		
		// Sets the model of the table to the new DefaultTableModel
		tblSession.setModel(tableModel);
		
		// Adds the new day button to the screen
		btnNewDay = new JButton("New Day");
		btnNewDay.setBounds(50, 90, 120, 35);
		add(btnNewDay);

	}

	// Takes in an ActionListener and adds it to the submit button
	public void submitListener(ActionListener al) {
		btnViewDay.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the New Day button
	public void newDay(ActionListener al) {
		btnNewDay.addActionListener(al);
	}

	// Refreshes the table to the list that was parsed through
	
	private void refreshTable(ArrayList<Day> dayList) {

		// Removes all rows in the table
		while (tblSession.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YY");
		int size = dayList.size();
		
		// Iterates through the list parsed through and adds them to the table
		for (int i = 0; i < size; i++) {
			Object[] row = new Object[4];
			
			Day temp = dayList.get(i);
			
			row[0] = temp;
			row[1] = temp.getRoom().getRoomName();
			row[2] = temp.getChildren().size();
			row[3] = "" + dateFormatter.format(temp.getDate().getTime());
			

			tableModel.addRow(row);
		}
		
		tblSession.setModel(tableModel);
	}

	// Returns the day that is selected. if no day is selected it throws an exception that
	// will be caught/handled by the caller
	public Day getDay() throws Exception {
		if (tblSession.getSelectedRow() == -1) {
			throw new Exception("No Day was selected.");
		}
		return (Day) tableModel.getValueAt(tblSession.getSelectedRow(), 0);
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		refreshTable((ArrayList<Day>) this.getModel().getDayList());
	}


	public void showRefineSearch() {
		// Initialises all the buttons/labels for the refine search screen
		JLabel lblDateFrom = new JLabel("Date From:");
		dateChooserFrom = new JDateChooser();
		JLabel lblDateTo = new JLabel("Date To:");
		dateChooserTo = new JDateChooser();
		
		// Adds the todays date button and when it is pressed sets both date from and to to todays date
		JButton btnTodaysDate = new JButton("Todays Date");
		btnTodaysDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Calendar cal = Calendar.getInstance();
				dateChooserFrom.setCalendar(cal);
				dateChooserTo.setCalendar(cal);

			}
		});
		// Adds all the buttons/labels to an array
		Object[] arr = { lblDateFrom, dateChooserFrom, lblDateTo,
				dateChooserTo, btnTodaysDate };

		// Creates the option pane for the refine search screen
		int res = JOptionPane.showConfirmDialog(this, arr, "Refine Search",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		// Refines the search based on what options were ticked in the refine search panel
		if (res == 0) {
			ArrayList<Day> dayList = new ArrayList<Day>(this.getModel()
					.getDays(dateChooserFrom.getCalendar(),
							dateChooserTo.getCalendar()));
			refreshTable(dayList);
		}

	}

}
