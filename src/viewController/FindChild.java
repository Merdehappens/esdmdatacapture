package viewController;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import system.individuals.Child;
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

public class FindChild extends PanelView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8214827932958587060L;
	private JTextField txtChildName;
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnCancel;
	private JList<Child> childList;
	private DefaultListModel<Child> childListModel;
	private ArrayList<Child> children;
	private PanelView destination;

	public FindChild() {
		super();
		initialise();

	}

	public FindChild(ESDMModel model) {
		super(model);
		initialise();
	}

	// Initialises all the graphical components on the page.
	private void initialise() {
		setLayout(null);

		super.setTitle("Select Child");

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 316, 89, 23);
		add(btnSubmit);

		btnReset = new JButton("Reset");

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtChildName.setText("");
				searchList(txtChildName.getText());
			}
		});

		btnReset.setBounds(111, 316, 89, 23);
		add(btnReset);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(210, 316, 89, 23);
		add(btnCancel);

		childListModel = new DefaultListModel<Child>();

		childList = new JList<Child>(childListModel);
		childList.setBounds(27, 118, 238, 172);
		add(childList);

		JLabel lblSelectChild = new JLabel(
				"Select the Child that you wish to view details of:");
		lblSelectChild.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectChild.setBounds(24, 93, 241, 14);
		add(lblSelectChild);

		JLabel lblSearchChild = new JLabel("Search Child:");
		lblSearchChild.setBounds(27, 31, 77, 30);
		add(lblSearchChild);

		txtChildName = new JTextField();
		txtChildName.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent arg0) {
				searchList(txtChildName.getText());
			}
		});

		txtChildName.setBounds(100, 31, 162, 30);
		add(txtChildName);
		txtChildName.setColumns(10);

	}

	public void setChildren(List<Child> list) {
		children = (ArrayList<Child>) list;
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
		if(children == null)
		{
			
		}
		else
		{
			int size = children.size();
			for (int i = 0; i < size; i++) {
				Child temp = children.get(i);
				String childName = temp.getName().toLowerCase();
				text = text.toLowerCase();
			
				if (childName.contains(text)) {
					childListModel.addElement(temp);
				}
			}
		}
	}

	public PanelView getDestination() {
		return destination;
	}

	public void setDestination(PanelView p) {
		destination = p;
	}

	// Overrides the refreshView method in PanelView and refreshes the view of this panel
	public void refreshView() {
		txtChildName.setText("");
		searchList(txtChildName.getText());
	}

}
