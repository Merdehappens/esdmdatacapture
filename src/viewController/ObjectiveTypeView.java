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
import system.marking.ObjectiveType;
import system.model.ESDMModel;
import system.model.Room;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class ObjectiveTypeView extends PanelView {

	private static final long serialVersionUID = -4321169254400022059L;
	private JButton btnAddObjectiveType;
	private JButton btnRemoveObjectiveType;
	private MyJTable tblType;
	private DefaultTableModel tblmdlType;
	private JScrollPane scrollPane;
	private ArrayList<ObjectiveType> otList;
	private JButton btnHome;
	private JButton btnBackToAdministration;

	public ObjectiveTypeView() {
		super();
		initialise();

	}

	public ObjectiveTypeView(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {

		setLayout(null);

		super.setTitle("Objective Types");

		btnAddObjectiveType = new JButton("Add New Objective Type");
		btnAddObjectiveType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addObjectiveType();
			}


		});
		btnAddObjectiveType.setBounds(50, 109, 126, 30);
		add(btnAddObjectiveType);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 150, 900, 300);
		add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		tblmdlType = new DefaultTableModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tblmdlType);
		tblType = new MyJTable();
		
		scrollPane.setViewportView(tblType);

		//String[] columnNames = new String[] { "ID", "Name" };
		String[] columnNames = new String[] { "Name" };

		tblmdlType.setColumnIdentifiers(columnNames);

		tblType.setModel(tblmdlType);

		tblType.setRowSorter(sorter);
		
		btnRemoveObjectiveType = new JButton("Remove Objective Type");
		btnRemoveObjectiveType.setBounds(322, 109, 137, 30);
		add(btnRemoveObjectiveType);
		
		btnHome = new JButton("Home");
		btnHome.setBounds(20, 536, 89, 23);
		add(btnHome);
		
		btnBackToAdministration = new JButton("Back to Administration");
		btnBackToAdministration.setBounds(119, 536, 165, 23);
		add(btnBackToAdministration);
		
		TableColumnModel tblColModel = tblType.getColumnModel();

		/*tblColModel.getColumn(0).setPreferredWidth(100);
		tblColModel.getColumn(1).setPreferredWidth(500);*/
		tblColModel.getColumn(0).setPreferredWidth(600);

	}
	
	private void addObjectiveType() {
		String name = JOptionPane.showInputDialog(null, "Please enter the name of the new objective type");
		if(name != null)
		{
			this.getModel().addObjectiveType(name);
		}
		refreshView();
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		populateTable();
	}

	private void populateTable() {
		otList = new ArrayList<ObjectiveType>(this.getModel().getObjectiveTypeList());
		while (tblmdlType.getRowCount() > 0) {
			tblmdlType.removeRow(0);
		}

		int size = otList.size();
		for (int i = 0; i < size; i++) {
			ObjectiveType objectiveType = otList.get(i);
			

			Object[] rowData = new Object[1];
			//rowData[0] = objectiveType.getId();
			rowData[0] = objectiveType;

			tblmdlType.addRow(rowData);

		}
	}


	// Takes in an ActionListener and adds it to the Remove Child button
	public void removeObjectiveTypeListener(ActionListener al) {
		btnRemoveObjectiveType.addActionListener(al);
	}
	
	public ObjectiveType getSelectedObjectiveType()
	{
		return (ObjectiveType) tblmdlType.getValueAt(tblType.getSelectedRow(), 0);
	}
	
	public void homeListener(ActionListener al) {
		btnHome.addActionListener(al);
	}
	
	public void backToAdminListener(ActionListener al) {
		btnBackToAdministration.addActionListener(al);
	}
}