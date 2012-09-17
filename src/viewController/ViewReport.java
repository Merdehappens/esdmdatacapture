package viewController;

import javax.swing.JButton;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.RowFilter.Entry;

import system.helper.Helper;
import system.individuals.Child;
import system.marking.Mark;
import system.model.ESDMModel;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ViewReport extends PanelView {

	private static final long serialVersionUID = 7874110638597279843L;
	private JScrollPane scrollPane;
	private MyJTable tblSession;
	private DefaultTableModel tableModel;
	private Child child;
	private JLabel lblChildName;
	private JButton btnExport;
	private TableRowSorter<DefaultTableModel> sorter;
	private JDateChooser dateChooserFrom;
	private JDateChooser dateChooserTo;
	private JButton btnHome;
	private JButton btnBackToSelect;

	/**
	 * Create the panel.
	 */
	public ViewReport() {
		initialise();
	}

	public ViewReport(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {
		setLayout(null);
		super.setTitle("View Report");

		// Creates table, sets column name and adds the model to the table

		String[] columnNames = new String[] { "Date", "Objective", "Step",
				"Setting", "Mark", "Comments" };
		scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 154, 835, 303);
		add(scrollPane);

		tableModel = new DefaultTableModel() {

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableModel.setColumnIdentifiers(columnNames);

		sorter = new TableRowSorter<DefaultTableModel>(tableModel);

		tblSession = new MyJTable(tableModel);
		scrollPane.setViewportView(tblSession);

		tblSession.setRowSorter(sorter);

		// Adds the date chooser to the page

		dateChooserTo = new JDateChooser();
		dateChooserTo.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				refreshView();
			}
		});
		dateChooserTo.setBounds(529, 87, 200, 35);
		add(dateChooserTo);

		JLabel lblTo = new JLabel("To Date:");
		dateChooserTo.add(lblTo, BorderLayout.WEST);

		// Adds the second date chooser to the page

		dateChooserFrom = new JDateChooser();
		dateChooserFrom.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				refreshView();
			}
		});
		dateChooserFrom.setBounds(319, 87, 200, 35);
		add(dateChooserFrom);

		JLabel lblFrom = new JLabel("From Date:");
		dateChooserFrom.add(lblFrom, BorderLayout.WEST);

		JLabel lblChild = new JLabel("Child: ");
		lblChild.setBounds(10, 69, 44, 14);
		add(lblChild);

		lblChildName = new JLabel("");
		lblChildName.setBounds(64, 69, 233, 14);
		add(lblChildName);

		// Creates a new button to export report to file

		btnExport = new JButton("Save to File");
		btnExport.setBounds(64, 468, 139, 26);
		add(btnExport);
		
		btnHome = new JButton("Home");
		btnHome.setBounds(20, 536, 89, 23);
		add(btnHome);
		
		btnBackToSelect = new JButton("Back to Select");
		btnBackToSelect.setBounds(119, 536, 117, 23);
		add(btnBackToSelect);

	}

	// For the Export to file button. Sets up a file chooser and then creates
	// the file in the folder specified.

	public void saveCSV() throws Exception {
		File f = Helper.chooseFile();
		Helper.exportCSV(f, tblSession);
	}

	// Adds the action listener that is parsed in on the export button
	public void exportListener(ActionListener al) {
		btnExport.addActionListener(al);
	}
	
	public void homeListener(ActionListener al) {
		btnHome.addActionListener(al);
	}
	
	public void backToSelectListener(ActionListener al) {
		btnBackToSelect.addActionListener(al);
	}

	// Refreshes the table to be the same as the object

	public void refreshTable() {
		if (child != null) {
			lblChildName.setText(child.getName());
			ArrayList<Mark> marks = new ArrayList<Mark>(child.getMarks());

			// Iterates through everything in the table and removes them all
			while (tableModel.getRowCount() > 0) {
				tableModel.removeRow(0);
			}

			// Iterates through all the marks in the list (which is retrieved
			// from
			// the child) then adds them to the object array and adds the object
			// array to
			// the table as a row
			int size = marks.size();

			for (int i = 0; i < size; i++) {
				Mark mark = marks.get(i);

				Object[] rowData = new Object[6];

				rowData[0] = mark.getTime().getTime();
				if (mark.getObjective() != null) {
					rowData[1] = mark.getObjective().getName();
				}
				if (mark.getStep() != null) {
					rowData[2] = mark.getStep().getNo();
				}
				if (mark.getSetting() != null) {
					rowData[3] = mark.getSetting();
				}
				rowData[4] = mark.toString();
				rowData[5] = mark.getComment();

				tableModel.addRow(rowData);

			}

			
			ArrayList<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>();

			Calendar dateFrom = dateChooserFrom.getCalendar();
			Calendar dateTo = dateChooserTo.getCalendar();

			if (dateTo != null) {
				dateTo.set(Calendar.HOUR_OF_DAY, 0);
				dateTo.set(Calendar.MINUTE, 0);
				dateTo.set(Calendar.SECOND, 0);
				dateTo.set(Calendar.MILLISECOND, 0);
				int day = dateTo.get(Calendar.DAY_OF_YEAR);
				dateTo.set(Calendar.DAY_OF_YEAR, day + 1);

				filters.add(RowFilter.dateFilter(ComparisonType.BEFORE,
						dateTo.getTime()));
			}
			if (dateFrom != null) {
				dateFrom.set(Calendar.HOUR_OF_DAY, 0);
				dateFrom.set(Calendar.MINUTE, 0);
				dateFrom.set(Calendar.SECOND, 0);
				dateFrom.set(Calendar.MILLISECOND, 0);

				filters.add(RowFilter.dateFilter(ComparisonType.AFTER,
						dateFrom.getTime()));
			}
			
			sorter.setRowFilter(RowFilter.andFilter(filters));
		}

	}

	// Sets the child on this screen to the one parsed through

	public void setChild(Child c) {
		child = c;
	}

	// Overrides the refreshView method in PanelView and refreshes the view of
	// this panel
	public void refreshView() {
		refreshTable();
	}
}
