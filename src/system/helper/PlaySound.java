package system.helper;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineEvent.Type;

public class PlaySound implements Runnable {

	private File file;
	
	public PlaySound(File file)
	{
		this.file = file;
	}
	
	@Override
	public void run() {
		
		  class AudioListener implements LineListener {
			    private boolean done = false;
			    @Override public synchronized void update(LineEvent event) {
			      Type eventType = event.getType();
			      if (eventType == Type.STOP || eventType == Type.CLOSE) {
			        done = true;
			        notifyAll();
			      }
			    }
			    public synchronized void waitUntilDone() throws InterruptedException {
			      while (!done) { wait(); }
			    }
			  }

			Clip clip = null;

		try{
		AudioListener al = new AudioListener();
		AudioInputStream ais = 
				AudioSystem.getAudioInputStream(file);
		AudioFormat af = ais.getFormat();
		DataLine.Info lineInfo = new DataLine.Info(Clip.class, af);
		clip = (Clip)AudioSystem.getLine(lineInfo);
		clip.addLineListener(al);
		clip.open(ais);
		
		clip.start();
		al.waitUntilDone();
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			clip.close();
		}
	}
	  
	
	
	
}
