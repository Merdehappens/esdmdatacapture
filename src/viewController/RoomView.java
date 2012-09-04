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

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class RoomView extends PanelView {

	private static final long serialVersionUID = -4321169254400022059L;
	private JButton btnAddRoom;
	private JButton btnRemoveRoom;
	private JTable tblRoom;
	private DefaultTableModel tblmdlRoom;
	private ArrayList<Room> roomList;
	private JScrollPane scrollPane;

	public RoomView() {
		super();
		initialise();

	}

	public RoomView(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {

		setLayout(null);

		super.setTitle("Rooms");

		btnAddRoom = new JButton("Add New Room");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addRoom();
			}


		});
		btnAddRoom.setBounds(50, 109, 126, 30);
		add(btnAddRoom);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 150, 900, 300);
		add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		tblmdlRoom = new DefaultTableModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tblmdlRoom);
		tblRoom = new JTable() {

			private static final long serialVersionUID = 6404756832838176556L;

			public boolean isCellEditable(int row, int column) {
				if(column == 0)
					return false;
				else
					return true;
			}

		};
		scrollPane.setViewportView(tblRoom);

		String[] columnNames = new String[] { "ID", "Name" };

		tblmdlRoom.setColumnIdentifiers(columnNames);

		tblRoom.setModel(tblmdlRoom);

		tblRoom.setRowSorter(sorter);
		
		btnRemoveRoom = new JButton("Remove Room");
		btnRemoveRoom.setBounds(322, 109, 137, 30);
		add(btnRemoveRoom);
		
		TableColumnModel tblColModel = tblRoom.getColumnModel();

		tblColModel.getColumn(0).setPreferredWidth(100);
		tblColModel.getColumn(1).setPreferredWidth(500);

	}
	
	private void addRoom() {
		String name = JOptionPane.showInputDialog(null, "Please enter the name of the new room");
		if(name != null)
		{
			this.getModel().addRoom(name);
		}
		refreshView();
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		populateTable();
	}

	private void populateTable() {
		roomList = new ArrayList<Room>(this.getModel().getRoomList());
		while (tblmdlRoom.getRowCount() > 0) {
			tblmdlRoom.removeRow(0);
		}

		int size = roomList.size();
		for (int i = 0; i < size; i++) {
			Room tempRoom = roomList.get(i);
			

			Object[] rowData = new Object[2];
			rowData[0] = tempRoom.getId();
			rowData[1] = tempRoom;

			tblmdlRoom.addRow(rowData);

		}
	}


	// Takes in an ActionListener and adds it to the Remove Child button
	public void removeRoomListener(ActionListener al) {
		btnRemoveRoom.addActionListener(al);
	}
	
	public Room getSelectedRoom()
	{
		return (Room) tblmdlRoom.getValueAt(tblRoom.getSelectedRow(), 1);
	}

}