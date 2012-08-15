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
		super();
		initialise();

	}

	public ReportingView(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {
		setLayout(null);

		super.setTitle("Reporting");

		btnViewReport = new JButton("View Report");
		btnViewReport.setBounds(20, 70, 200, 40);
		add(btnViewReport);

	}

	// Takes in an ActionListener and adds it to the View Report button
	public void viewReportListener(ActionListener al) {
		btnViewReport.addActionListener(al);
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {

	}

}
