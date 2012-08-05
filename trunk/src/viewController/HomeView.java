package viewController;


import java.awt.Font;

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
		
		super.setTitle("Homepage/Dashboard");
	
	}

	@Override
	public void refreshView() {
		
	}

}
