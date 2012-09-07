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
import system.model.Room;
import system.sessions.Setting;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class SettingView extends PanelView {

	private static final long serialVersionUID = -4321169254400022059L;
	private JButton btnAddSetting;
	private MyJTable tblSetting;
	private DefaultTableModel tblmdlSetting;
	private ArrayList<Setting> settingList;
	private JScrollPane scrollPane;
	private JButton btnRemoveSetting;

	public SettingView() {
		super();
		initialise();

	}

	public SettingView(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {

		setLayout(null);

		super.setTitle("Rooms");

		btnAddSetting = new JButton("Add New Setting");
		btnAddSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addSetting();
			}


		});
		btnAddSetting.setBounds(50, 109, 126, 30);
		add(btnAddSetting);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 150, 900, 300);
		add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		tblmdlSetting = new DefaultTableModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tblmdlSetting);
		tblSetting = new MyJTable();
		
		scrollPane.setViewportView(tblSetting);

		String[] columnNames = new String[] { "ID", "Name" };

		tblmdlSetting.setColumnIdentifiers(columnNames);

		tblSetting.setModel(tblmdlSetting);

		tblSetting.setRowSorter(sorter);
		
		btnRemoveSetting = new JButton("Remove Setting");
		btnRemoveSetting.setBounds(322, 109, 137, 30);
		add(btnRemoveSetting);
		
		TableColumnModel tblColModel = tblSetting.getColumnModel();

		tblColModel.getColumn(0).setPreferredWidth(100);
		tblColModel.getColumn(1).setPreferredWidth(500);

	}
	
	private void addSetting() {
		String name = JOptionPane.showInputDialog(null, "Please enter the name of the new setting.");
		if(name != null)
		{
			this.getModel().addSetting(name);
		}
		refreshView();
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		populateTable();
	}

	private void populateTable() {
		settingList = new ArrayList<Setting>(this.getModel().getSettingList());
		while (tblmdlSetting.getRowCount() > 0) {
			tblmdlSetting.removeRow(0);
		}

		int size = settingList.size();
		for (int i = 0; i < size; i++) {
			Setting setting = settingList.get(i);
			

			Object[] rowData = new Object[2];
			rowData[0] = setting.getId();
			rowData[1] = setting.getDescription();

			tblmdlSetting.addRow(rowData);

		}
	}


	// Takes in an ActionListener and adds it to the Remove Child button
	public void removeSettingListener(ActionListener al) {
		btnRemoveSetting.addActionListener(al);
	}
	
	public Room getSelectedSetting()
	{
		return (Room) tblmdlSetting.getValueAt(tblSetting.getSelectedRow(), 1);
	}

}