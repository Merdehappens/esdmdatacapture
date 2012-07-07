package viewController;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import system.individuals.Child;
import system.model.ESDMModel;

public abstract class PanelView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2035849306687852560L;
	private ESDMModel model;

	public PanelView() {
		addShownListener();
	}
	
	public PanelView(ESDMModel model)
	{
		addShownListener();
		this.model = model;
	}
	
	private void addShownListener()
	{
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				refreshView();
			}
		});
	}
	
	public ESDMModel getModel()
	{
		return model;
	}
	
	public void setChild(Child c)
	{
	}
	
	public abstract void refreshView();

}
