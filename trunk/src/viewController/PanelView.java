package viewController;

import javax.swing.JPanel;

import system.individuals.Child;
import system.model.ESDMModel;

public class PanelView extends JPanel {

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
