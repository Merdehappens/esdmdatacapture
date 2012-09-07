package viewController;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class MyJTable extends JTable {

	public MyJTable() {
		super();
		initialise();
	}

	public MyJTable(TableModel arg0) {
		super(arg0);
		initialise();
	}

	public MyJTable(TableModel arg0, TableColumnModel arg1) {
		super(arg0, arg1);
		initialise();
	}

	public MyJTable(int arg0, int arg1) {
		super(arg0, arg1);
		initialise();
	}

	public MyJTable(Vector arg0, Vector arg1) {
		super(arg0, arg1);
		initialise();
	}

	public MyJTable(Object[][] arg0, Object[] arg1) {
		super(arg0, arg1);
		initialise();
	}

	public MyJTable(TableModel arg0, TableColumnModel arg1,
			ListSelectionModel arg2) {
		super(arg0, arg1, arg2);
		initialise();
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	private void initialise() {
		this.setShowGrid(true);
		this.setRowHeight(19);
	}

}
