package viewController;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import system.individuals.Child;
import system.marking.Objective;
import system.model.ESDMModel;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class AddObjectiveChild extends PanelView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7230820180339202570L;
	private JTextField txtChildName;
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JList<Child> childList;
	private DefaultListModel<Child> childListModel;
	private ArrayList<Child> children;
	private Objective objective;

	public AddObjectiveChild() {
		super();
		initialise();

	}

	public AddObjectiveChild(ESDMModel model) {
		super(model);

		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {
		setLayout(null);

		super.setTitle("Add Objective to Child");

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(27, 333, 89, 23);
		add(btnSubmit);

		btnReset = new JButton("Reset");

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtChildName.setText("");
				searchList("");
			}
		});

		btnReset.setBounds(126, 333, 89, 23);
		add(btnReset);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(225, 333, 89, 23);
		add(btnCancel);

		childListModel = new DefaultListModel<Child>();

		childList = new JList<Child>(childListModel);
		childList.setBounds(27, 118, 238, 172);
		add(childList);

		JLabel lblSelectChild = new JLabel(
				"Select the child that you wish to add this objective to:");
		lblSelectChild.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectChild.setBounds(24, 93, 241, 14);
		add(lblSelectChild);

		JLabel lblSearchChild = new JLabel("Search Child:");
		lblSearchChild.setBounds(27, 31, 77, 30);
		add(lblSearchChild);

		txtChildName = new JTextField();
		txtChildName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				searchList(txtChildName.getText());

			}
		});

		txtChildName.setBounds(100, 31, 162, 30);
		add(txtChildName);
		txtChildName.setColumns(10);

	}

	public void setList(List<Child> childList) {
		children = (ArrayList<Child>) childList;
		childListModel.clear();
		txtChildName.setText("");

		int size = children.size();
		for (int i = 0; i < size; i++) {
			childListModel.addElement(children.get(i));
		}

	}

	public Child getSelectedChild() {
		return childList.getSelectedValue();
	}

	// Takes in an ActionListener and adds it to the submit button

	public void submitListener(ActionListener al) {
		btnSubmit.addActionListener(al);
	}

	// Takes in an ActionListener and adds it to the cancel button

	public void cancelListener(ActionListener al) {
		btnCancel.addActionListener(al);
	}

	private void searchList(String text) {

		childListModel.clear();
		int size = children.size();
		for (int i = 0; i < size; i++) {
			Child temp = children.get(i);

			if (temp.toString().contains(text)) {
				childListModel.addElement(temp);
			}
		}

	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		setList(this.getModel().getChildList(true));
	}

	public Objective getObjective() {
		return objective;
	}

	public void setObjective(Objective objective) {
		this.objective = objective;
	}
}
