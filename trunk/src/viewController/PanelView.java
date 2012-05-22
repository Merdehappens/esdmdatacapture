package viewController;

import javax.swing.JPanel;

import system.individuals.Child;
import system.model.ESDMModel;

public class PanelView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2035849306687852560L;
	private ESDMModel model;

	public PanelView() {
		
	}
	
	public PanelView(ESDMModel model)
	{
		this.model = model;
	}
	
	public ESDMModel getModel()
	{
		return model;
	}
	
	public void setChild(Child c)
	{
	}

}
