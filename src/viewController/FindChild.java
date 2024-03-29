package viewController;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import system.individuals.Child;
import system.model.ESDMModel;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;

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
	private JButton btnChangeDetails;

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
		btnSubmit.setBounds(342, 406, 89, 23);
		add(btnSubmit);

		btnReset = new JButton("Reset");

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtChildName.setText("");
				searchList(txtChildName.getText());
			}
		});

		btnReset.setBounds(433, 406, 97, 23);
		add(btnReset);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(532, 406, 89, 23);
		add(btnCancel);

		childListModel = new DefaultListModel<Child>();

		JLabel lblSelectChild = new JLabel(
				"Select the Child that you wish to view marks of:");
		lblSelectChild.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectChild.setBounds(342, 167, 295, 14);
		add(lblSelectChild);

		JLabel lblSearchChild = new JLabel("Search Child:");
		lblSearchChild.setBounds(359, 105, 77, 30);
		add(lblSearchChild);

		txtChildName = new JTextField();
		txtChildName.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent arg0) {
				searchList(txtChildName.getText());
			}
		});

		txtChildName.setBounds(432, 105, 162, 30);
		add(txtChildName);
		txtChildName.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(342, 189, 312, 197);
		add(scrollPane);
		
				childList = new JList<Child>(childListModel);
				scrollPane.setViewportView(childList);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				
				btnChangeDetails = new JButton("Change Details");
				btnChangeDetails.setBounds(704, 356, 137, 73);
				add(btnChangeDetails);
	}

	public void setChildren(List<Child> list) {
		children = new ArrayList<Child>(list);
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

	// Takes in an ActionListener and adds it to the change details button
	public void changeDetailsListener(ActionListener al) {
		btnChangeDetails.addActionListener(al);
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

	public void setAccess(String acc) {
		if(acc.equalsIgnoreCase("g"))
		{
			btnChangeDetails.setVisible(true);
			btnCancel.setVisible(false);
		} else {
			btnChangeDetails.setVisible(false);
			btnCancel.setVisible(true);
		}
	}
}
