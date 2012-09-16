package viewController;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import system.model.ESDMModel;

public class ReportingView extends PanelView {

	private static final long serialVersionUID = -970035637832481957L;

	JButton btnViewReport;
	JButton btnHome;

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
		// Sets the title of the page
		super.setTitle("Reporting");
		
		// Adds the view Report button to the page
		btnViewReport = new JButton("View Report");
		btnViewReport.setBounds(20, 70, 200, 40);
		add(btnViewReport);
		
		btnHome = new JButton("Home");
		btnHome.setBounds(20, 500, 89, 23);
		add(btnHome);

	}

	// Takes in an ActionListener and adds it to the View Report button
	public void viewReportListener(ActionListener al) {
		btnViewReport.addActionListener(al);
	}
	
	public void homeListener(ActionListener al) {
		btnHome.addActionListener(al);
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {

	}
}
