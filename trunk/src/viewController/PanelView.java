package viewController;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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

	public void setTitle(String string) {

		JLabel lblTitle = new JLabel(string);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 10, 1008, 40);
		add(lblTitle);
	}

}
