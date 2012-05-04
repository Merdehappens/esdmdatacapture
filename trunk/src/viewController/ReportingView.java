package viewController;

import javax.swing.JButton;

import systemModel.ESDMModel;

public class ReportingView extends PanelView {

	/**
	 * Create the panel.
	 */
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
		
		JButton btnViewReport = new JButton("View Report");
		btnViewReport.setBounds(10, 11, 160, 29);
		add(btnViewReport);
	
	}

}
