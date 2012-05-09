package viewController;

import javax.swing.JButton;

import systemModel.ESDMModel;
import java.awt.event.ActionListener;

public class SessionView extends PanelView {

	
	JButton btnStartNewDay;
	JButton btnViewDay;
	
	public SessionView() {
		initialise();
	}
	
	public SessionView(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	private void initialise()
	{
		setLayout(null);
		
		btnStartNewDay = new JButton("Start New Day");

		btnStartNewDay.setBounds(10, 25, 142, 23);
		add(btnStartNewDay);
		
		btnViewDay = new JButton("View Day");
		btnViewDay.setBounds(10, 59, 142, 23);
		add(btnViewDay);
	
	}
	
	public void newDay(ActionListener al)
	{
		btnStartNewDay.addActionListener(al);	
	}
	
	public void viewDay(ActionListener al)
	{
		btnViewDay.addActionListener(al);
	}

}