package viewController;

import javax.swing.JButton;

import system.model.ESDMModel;

import java.awt.event.ActionListener;

public class AccountView extends PanelView {


	private static final long serialVersionUID = -7002494511986322397L;
	private JButton btnRooms;
	private JButton btnSetting;
	private JButton btnObjectiveType;

	public AccountView() {
		super();
		initialise();
	}

	public AccountView(ESDMModel model) {
		super(model);
		initialise();
	}

	
	// Initialises all the graphical items on the screen.
	public void initialise() {
		setLayout(null);

		// Adds the title to the top of the screen.

		super.setTitle("Administration");

		
		btnRooms = new JButton("Rooms");
		btnRooms.setBounds(20, 120, 165, 40);
		add(btnRooms);
		
		btnSetting = new JButton("Settings");
		btnSetting.setBounds(20, 171, 165, 40);
		add(btnSetting);
		
		
		btnObjectiveType = new JButton("Objective Types");
		btnObjectiveType.setBounds(20, 222, 165, 40);
		add(btnObjectiveType);
		
	}

	
	public void roomsListener(ActionListener al) {
		btnRooms.addActionListener(al);
	}
	
	public void settingListener(ActionListener al) {
		btnSetting.addActionListener(al);
	}
	
	public void objectiveTypeListener(ActionListener al) {
		btnObjectiveType.addActionListener(al);
	}
	
	

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
	}

}
