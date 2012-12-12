package gabewest.project2;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public enum SoundPlayer {
	KEY("key"),
	OUCH("ouch"),
	STEP("step"),
	WIN("win");
	
	private Clip clip;
	
	SoundPlayer(String path){
		String filePath = path + ".wav";
		try{
			URL url = this.getClass().getResource(filePath);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		if (clip.isRunning()){
			clip.stop();
		}
		clip.setFramePosition(0);
		clip.start();
		
	}
	
	static void init(){
		values();
	}
}
