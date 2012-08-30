package system.helper;

import javax.swing.JLabel;

public class MessageFade implements Runnable{

	private JLabel label;
	
	public MessageFade(JLabel label)
	{
		this.label = label;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		label.setText("");
	}

}
