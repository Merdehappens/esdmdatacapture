package viewController;

import javax.swing.JButton;

import system.model.ESDMModel;
import system.sessions.Day;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 4174778855073882231L;
	private JButton btnViewDay;
	private JButton btnReset;
	private JDateChooser dateChooserFrom;
	private JDateChooser dateChooserTo;
	private JScrollPane scrollPane;
	private JTable tblSession;
	private DefaultTableModel tableModel;
	private JButton btnNewDay;

	/**
	 * Create the panel.
	 */
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

		super.setTitle("Session");

		btnViewDay = new JButton("View Day");
		btnViewDay.setBounds(180, 109, 108, 30);
		add(btnViewDay);

		btnReset = new JButton("Reset Search Criteria");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshView();
			}
		});
		btnReset.setBounds(799, 109, 151, 30);
		add(btnReset);

		JButton btnRefineSearch = new JButton("Refine Search");
		btnRefineSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showRefineSearch();
			}
		});
		btnRefineSearch.setBounds(656, 109, 133, 30);
		add(btnRefineSearch);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 150, 900, 300);
		add(scrollPane);

		tblSession = new JTable() {

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		scrollPane.setViewportView(tblSession);
		String[] columnNames = new String[] { "SessionID", "Room Name", "Date" };

		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);

		tblSession.setModel(tableModel);

		btnNewDay = new JButton("New Day");
		btnNewDay.setBounds(50, 109, 120, 30);
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

	private void refreshTable(ArrayList<Day> dayList) {

		while (tblSession.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YY");
		int size = dayList.size();
		for (int i = 0; i < size; i++) {
			Object[] row = new Object[3];
			Day temp = dayList.get(i);

			row[0] = temp;
			row[1] = temp.getRoom().getRoomName();
			row[2] = dateFormatter.format(temp.getDate().getTime());

			tableModel.addRow(row);
		}

		tblSession.setModel(tableModel);

	}

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
		JLabel lblDateFrom = new JLabel("Date From:");
		dateChooserFrom = new JDateChooser();
		JLabel lblDateTo = new JLabel("Date To:");
		dateChooserTo = new JDateChooser();
		JButton btnTodaysDate = new JButton("Todays Date");
		btnTodaysDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Calendar cal = Calendar.getInstance();
				dateChooserFrom.setCalendar(cal);
				dateChooserTo.setCalendar(cal);

			}
		});

		Object[] arr = { lblDateFrom, dateChooserFrom, lblDateTo,
				dateChooserTo, btnTodaysDate };

		int res = JOptionPane.showConfirmDialog(null, arr, "Refine Search",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

		if (res == 0) {
			ArrayList<Day> dayList = new ArrayList<Day>(this.getModel()
					.getDays(dateChooserFrom.getCalendar(),
							dateChooserTo.getCalendar()));
			refreshTable(dayList);
		}

	}

}
