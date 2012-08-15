package viewController;

import system.model.ESDMModel;

public class HomeView extends PanelView {

	private static final long serialVersionUID = -4031028574687279686L;

	public HomeView() {
		initialise();
	}

	public HomeView(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises the graphical objects on the page

	public void initialise() {
		setLayout(null);
		super.setTitle("Homepage/Dashboard");
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {

	}

}
