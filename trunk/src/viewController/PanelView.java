package viewController;

import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import system.model.ESDMModel;
import java.awt.Color;

public abstract class PanelView extends JPanel {

	private static final long serialVersionUID = 2035849306687852560L;
	private ESDMModel model;

	public PanelView() {
		initialise();
		addShownListener();
	}

	private void initialise() {

		setBackground(new Color(255, 255, 153));
		setOpaque(true);
	}

	public PanelView(ESDMModel model) {
		initialise();
		addShownListener();
		this.model = model;
	}
	
	// Adds a listener to this panel that calls refresh view whenever this panel (or any child panel) is shown

	private void addShownListener() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				refreshView();
			}
		});
	}
	
	// Returns the ESDMModel that is in this panel.

	public ESDMModel getModel() {
		return model;
	}

	
	// Method for all child classes to override that refreshes the view whenever it is shown.
	public abstract void refreshView();

	// Sets the title of the panel to be what is parsed through.
	public void setTitle(String string) {
		JLabel lblTitle = new JLabel(string);
		lblTitle.setFont(new Font("Tahoma", Font.CENTER_BASELINE, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(0, 10, 1008, 40);
		add(lblTitle);
	}

}
