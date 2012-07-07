package viewController;

import javax.swing.JButton;

import system.individuals.Child;
import system.marking.Mark;
import system.model.ESDMModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;

public class ViewReport extends PanelView {

	
	private static final long serialVersionUID = 7874110638597279843L;
	private JScrollPane scrollPane;
	private JTable tblSession;
	private DefaultTableModel tableModel;
	private Child child;
	private JButton btnSave;
	private JLabel lblChildName;
	
	/**
	 * Create the panel.
	 */
	public ViewReport() {
		initialise();
	
	}
	
	public ViewReport(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(66, 154, 592, 244);
		add(scrollPane);
		
		
		tblSession = new JTable();
		scrollPane.setViewportView(tblSession);
		String[] columnNames = new String[] {"Date", "Objective", "Step", "Setting", "Mark", "Comments"};
		
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		
		tblSession.setModel(tableModel);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				refreshTable();
			}
		});
		btnSave.setBounds(44, 436, 89, 23);
		add(btnSave);
		
		JDateChooser dateChooserTo = new JDateChooser();
		dateChooserTo.setBounds(257, 80, 157, 35);
		add(dateChooserTo);
		
		JLabel lblTo = new JLabel("To Date:");
		lblTo.setBounds(189, 79, 74, 36);
		add(lblTo);
		
		JLabel lblFrom = new JLabel("From Date:");
		lblFrom.setBounds(189, 33, 74, 35);
		add(lblFrom);
		
		JDateChooser dateChooserFrom = new JDateChooser();
		dateChooserFrom.setBounds(257, 33, 157, 35);
		add(dateChooserFrom);
		
		JLabel lblChild = new JLabel("Child: ");
		lblChild.setBounds(10, 43, 30, 14);
		add(lblChild);
		
		lblChildName = new JLabel("");
		lblChildName.setBounds(44, 43, 115, 14);
		add(lblChildName);
		
	}
	
	public void saveListener(ActionListener al)
	{
		btnSave.addActionListener(al);
	}
	
	public void refreshTable()
	{
		lblChildName.setText(child.getName());
		ArrayList<Mark> marks = (ArrayList<Mark>)child.getMarks();

		while(tableModel.getRowCount() > 0)
		{
			tableModel.removeRow(0);
		}
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YY h:mm:ss a");
		
		for(int i = 0; i < marks.size(); i++)
		{
			Mark mark = marks.get(i);
			
			Object[] rowData = new Object[6];
			
			rowData[0] = dateFormatter.format(mark.getTime().getTime());
			rowData[1] = mark.getObjective().getName();
			rowData[2] = mark.getStep().getNo();
			rowData[3] = mark.getSession();
			rowData[4] = mark;
			rowData[5] = mark.getComment();
			
			tableModel.addRow(rowData);
			
		}
	}


	
	public void setChild(Child c)
	{
		child = c;
	}

	@Override
	public void refreshView() {
		refreshTable();
	}
}
