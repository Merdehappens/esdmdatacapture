package system.helper;

import javax.swing.JLabel;

public class MessageFade implements Runnable{

	private JLabel label;
	// Takes in the label that the run method needs to "fade"
	public MessageFade(JLabel label)
	{
		this.label = label;
	}
	@Override
	// sleeps the thread for 3 seconds then sets the labels text to the empty word
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		} finally{
		label.setText("");
		}
	}

}
