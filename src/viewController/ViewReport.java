package viewController;

import javax.swing.JButton;

import system.individuals.Child;
import system.marking.Mark;
import system.model.ESDMModel;
import system.sessions.Day;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class ViewReport extends PanelView {
	private DefaultTableModel tblDayModel; 
	private JScrollPane scrollPane;
	private JTable tblSession;
	private DefaultTableModel tableModel;
	private Child child;
	private JButton btnSave;
	
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
		String[] columnNames = new String[] {"Date", "Objective", "Step", "Setting", "Mark"};
		
		
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
		
	}
	
	public void saveListener(ActionListener al)
	{
		btnSave.addActionListener(al);
	}
	
	public void refreshTable()
	{
		ArrayList<Mark> marks = (ArrayList<Mark>)child.getMarks();

		while(tableModel.getRowCount() > 0)
		{
			tableModel.removeRow(0);
		}
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/YY h:mm:ss a");
		
		for(int i = 0; i < marks.size(); i++)
		{
			Mark mark = marks.get(i);
			
			Object[] rowData = new Object[5];
			
			rowData[0] = dateFormatter.format(mark.getTime().getTime());
			rowData[1] = mark.getObjective().getName();
			rowData[2] = mark.getStep().getNo();
			rowData[3] = mark.getSession();
			rowData[4] = mark;
			
			tableModel.addRow(rowData);
			
			
		}
	}


	
	public void setChild(Child c)
	{
		child = c;
	}
}