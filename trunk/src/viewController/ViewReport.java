package viewController;

import javax.swing.JButton;

import system.helper.Helper;
import system.individuals.Child;
import system.marking.Mark;
import system.model.ESDMModel;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import java.io.File;
import java.awt.BorderLayout;

public class ViewReport extends PanelView {

	private static final long serialVersionUID = 7874110638597279843L;
	private JScrollPane scrollPane;
	private JTable tblSession;
	private DefaultTableModel tableModel;
	private Child child;
	private JButton btnSave;
	private JLabel lblChildName;
	private JButton btnExport;
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

		tblSession = new JTable();
		String[] columnNames = new String[] { "Date", "Objective", "Step",
				"Setting", "Mark", "Comments" };

		scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 154, 835, 303);
		add(scrollPane);
		scrollPane.setViewportView(tblSession);

		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);

		tblSession.setModel(tableModel);

		// Adds the Save button to the page.

		btnSave = new JButton("Save");
		btnSave.setBounds(44, 471, 89, 23);
		add(btnSave);

		// Adds the date chooser to the page

		JDateChooser dateChooserTo = new JDateChooser();
		dateChooserTo.setBounds(254, 108, 200, 35);
		add(dateChooserTo);

		JLabel lblTo = new JLabel("To Date:");
		dateChooserTo.add(lblTo, BorderLayout.WEST);

		// Adds the second date chooser to the page

		JDateChooser dateChooserFrom = new JDateChooser();
		dateChooserFrom.setBounds(254, 61, 200, 35);
		add(dateChooserFrom);
		
				JLabel lblFrom = new JLabel("From Date:");
				dateChooserFrom.add(lblFrom, BorderLayout.WEST);

		JLabel lblChild = new JLabel("Child: ");
		lblChild.setBounds(10, 43, 44, 14);
		add(lblChild);

		lblChildName = new JLabel("");
		lblChildName.setBounds(64, 43, 115, 14);
		add(lblChildName);
		
		// Creates a new button to export report to file

		btnExport = new JButton("Export to File");
		btnExport.setBounds(396, 468, 139, 26);
		add(btnExport);

	}
	
	// For the Export to file button. Sets up a file chooser and then creates the file in the folder specified.


	public void saveCSV() throws Exception {
		File f = Helper.chooseFile();
		Helper.exportCSV(f, tblSession);
	}

	// Adds the action listener that is parsed in on the save button

	public void saveListener(ActionListener al) {
		btnSave.addActionListener(al);
	}
	
	public void exportListener(ActionListener al) {
		btnExport.addActionListener(al);
	}

	// Refreshes the table to be the same as the object

	public void refreshTable() {
		
		lblChildName.setText(child.getName());
		ArrayList<Mark> marks = (ArrayList<Mark>) child.getMarks();
		
		// Iterates through everything in the table and removes them all
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		SimpleDateFormat dateFormatter = new SimpleDateFormat(
				"dd/MM/YY hh:mm:ss a");



		// Iterates through all the marks in the list (which is retrieved from
		// the child) then adds them to the object array and adds the object array to
		// the table as a row
		int size = marks.size();
		
		for (int i = 0; i < size; i++) {
			Mark mark = marks.get(i);

			Object[] rowData = new Object[6];

			rowData[0] = dateFormatter.format(mark.getTime().getTime());
			if (mark.getObjective() != null) {
				rowData[1] = mark.getObjective().getName();
			}
			if (mark.getStep() != null) {
				rowData[2] = mark.getStep().getNo();
			}
			if (mark.getSession() != null) {
				rowData[3] = mark.getSession();
			}
			rowData[4] = mark.toString();
			rowData[5] = mark.getComment();

			tableModel.addRow(rowData);

		}
	}

	// Sets the child on this screen to the one parsed through

	public void setChild(Child c) {
		child = c;
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		refreshTable();
	}
}
