package viewController;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import system.model.ESDMModel;

import java.awt.Font;
import java.awt.event.ActionListener;

public class SessionView extends PanelView {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6468072713908533918L;
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
		
		JLabel lblTitle = new JLabel("Sessions");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 504, 34);
		add(lblTitle);
		
		btnStartNewDay = new JButton("Start New Day");

		btnStartNewDay.setBounds(20, 70, 200, 40);
		add(btnStartNewDay);
		
		btnViewDay = new JButton("View Day");
		btnViewDay.setBounds(20, 120, 200, 40);
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

	@Override
	public void refreshView() {
		// TODO Auto-generated method stub
		
	}

}
