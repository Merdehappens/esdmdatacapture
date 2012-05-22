package viewController;


import javax.swing.JLabel;
import javax.swing.SwingConstants;

import system.model.ESDMModel;

public class HomeView extends PanelView {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4031028574687279686L;


	public HomeView() {
		initialise();

	}
	
	public HomeView(ESDMModel model)
	{
		super(model);
		initialise();
	}
	
	
	public void initialise()
	{
		setLayout(null);
		
		JLabel lblTitle = new JLabel("Home Page/Dashboard");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 430, 23);
		add(lblTitle);
	
	}

}
