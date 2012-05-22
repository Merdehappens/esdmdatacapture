package viewController;

import java.awt.event.ActionListener;

import javax.swing.JButton;

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
		
		btnViewReport = new JButton("View Report");
		btnViewReport.setBounds(10, 11, 160, 29);
		add(btnViewReport);
	
	}
	
	public void viewReportListener(ActionListener al)
	{
		btnViewReport.addActionListener(al);
	}

}
