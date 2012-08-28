package viewController;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import system.helper.Helper;
import system.individuals.Child;
import system.model.ESDMModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ChildView extends PanelView {

	private static final long serialVersionUID = -4321169254400022059L;
	private JButton btnAddChild;
	private JButton btnEditChild;
	private JButton btnRemoveChild;
	private JTable childTable;
	private DefaultTableModel childTableModel;
	private ArrayList<Child> childList;
	private JScrollPane scrollPane;

	public ChildView() {
		super();
		initialise();

	}

	public ChildView(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {

		setLayout(null);

		super.setTitle("Child");

		btnAddChild = new JButton("Add New Child");
		btnAddChild.setBounds(50, 109, 126, 30);
		add(btnAddChild);

		btnEditChild = new JButton("Edit Child");
		btnEditChild.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEditChild.setBounds(186, 109, 126, 30);
		add(btnEditChild);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 150, 900, 300);
		add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		childTableModel = new DefaultTableModel();
		childTable = new JTable() {

			private static final long serialVersionUID = 6404756832838176556L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		scrollPane.setViewportView(childTable);

		String[] columnNames = new String[] { "ChildID", "Name",
				"Date of Birth", "Date Joined", "Active?" };

		childTableModel.setColumnIdentifiers(columnNames);

		childTable.setModel(childTableModel);

		btnRemoveChild = new JButton("Remove Child");
		btnRemoveChild.setBounds(322, 109, 137, 30);
		add(btnRemoveChild);

		JButton btnRefineSearch = new JButton("Refine Search");
		btnRefineSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showRefineSearch();
			}
		});
		btnRefineSearch.setBounds(634, 109, 160, 30);
		add(btnRefineSearch);

		JButton btnResetSearch = new JButton("Reset Search");
		btnResetSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				populateTable(childList);
			}
		});
		btnResetSearch.setBounds(804, 109, 146, 30);
		add(btnResetSearch);

		refreshView();

	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		childList = (ArrayList<Child>) this.getModel().getChildList(true);
		populateTable(childList);
	}

	private void populateTable(ArrayList<Child> childs) {

		while (childTableModel.getRowCount() > 0) {
			childTableModel.removeRow(0);
		}

		int size = childs.size();
		for (int i = 0; i < size; i++) {
			Child tempChild = childs.get(i);

			Object[] rowData = new Object[5];

			rowData[0] = tempChild.getId();
			rowData[1] = tempChild;
			rowData[2] = Helper.simpleDateFormat(tempChild.getDob());
			rowData[3] = Helper.simpleDateFormat(tempChild.getDateJoined());

			if (tempChild.getActive() == true) {
				rowData[4] = "Active";
			} else {
				rowData[4] = "Inactive";
			}

			childTableModel.addRow(rowData);

		}
	}

	// Takes in an ActionListener and adds it to the Add Child button
	public void addChildListener(ActionListener al) {
		btnAddChild.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the Remove Child button
	public void removeChildListener(ActionListener al) {
		btnRemoveChild.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the Edit Child button
	public void editChildListener(ActionListener al) {
		btnEditChild.addActionListener(al);
	}

	public Child getSelectedChild() throws Exception {
		Child child;
		try {
			child = (Child) childTableModel.getValueAt(
					childTable.getSelectedRow(), 1);
		} catch (Exception e) {
			throw new Exception("You must select a child first.");
		}

		return child;
	}

	public void showRefineSearch() {
		JLabel lblName = new JLabel("Name:");
		JTextField txtName = new JTextField();
		JLabel lblActive = new JLabel("Search Level:");
		String[] active = { "Active", "Inactive", "Both" };
		JComboBox<String> cmbActive = new JComboBox<String>(active);

		Object[] arr = { lblName, txtName, lblActive, cmbActive };

		int res = JOptionPane.showConfirmDialog(null, arr, "Refine Search",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(res == 0)
		{
			ArrayList<Child> fullChildList = new ArrayList<Child>(this.getModel()
					.getChildList());
			ArrayList<Child> showChildList = new ArrayList<Child>();

			int size = fullChildList.size();
			for (int i = 0; i < size; i++) {
				Child tempChild = fullChildList.get(i);
				String name = tempChild.getName();

				if (name.toLowerCase().contains(txtName.getText().toLowerCase())) {
					int index = cmbActive.getSelectedIndex();
					if (index == 2) {
						showChildList.add(tempChild);
					} else if (index == 1) {
						if (tempChild.getActive() == false) {
							showChildList.add(tempChild);
						}
					} else {
						if (tempChild.getActive() == true) {
							showChildList.add(tempChild);
						}
					}
				}
			}
			populateTable(showChildList);
		}
		else
		{
			populateTable(childList);
		}
	}

}