package viewController;


import javax.swing.JLabel;
import javax.swing.SwingConstants;

import systemModel.ESDMModel;

public class HomeView extends PanelView {


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
