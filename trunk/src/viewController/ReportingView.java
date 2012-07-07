package viewController;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import system.model.ESDMModel;

public class ReportingView extends PanelView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -970035637832481957L;
	/**
	 * Create the panel.
	 */
	JButton btnViewReport;
	
	public ReportingView() {
		super();
		initialise();
	
	}
	
	public ReportingView(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Reporting");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 504, 34);
		add(lblTitle);
		
		btnViewReport = new JButton("View Report");
		btnViewReport.setBounds(20, 70, 200, 40);
		add(btnViewReport);
	
	}
	
	public void viewReportListener(ActionListener al)
	{
		btnViewReport.addActionListener(al);
	}

	@Override
	public void refreshView() {
	
	}

}
