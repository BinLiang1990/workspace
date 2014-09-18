package cn.vobile.opm.gui.controller;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.vobile.opm.gui.common.PropertiesReader;

/**
 * When GUI receive alarm message,MusicPlayer starts to play music
 * 
 * @author wang_lin
 * 
 */
public class MusicPlayer {

	private static Log log = LogFactory.getLog(MusicPlayer.class);
	// when flag is true, music is played
	private boolean flag = true;

	public void playMusic() throws Exception, IOException {
		AudioInputStream audioInputStream;
		AudioFormat audioFormat;
		SourceDataLine sourceDataLine;
		File file = new File("resource/" + PropertiesReader.MP3 + ".mp3");
		audioInputStream = AudioSystem.getAudioInputStream(file);
		audioFormat = audioInputStream.getFormat();

		if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
			audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
					audioFormat.getSampleRate(), 16, audioFormat.getChannels(),
					audioFormat.getChannels() * 2, audioFormat.getSampleRate(),
					false);
			audioInputStream = AudioSystem.getAudioInputStream(audioFormat,
					audioInputStream);
		}
		DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
				audioFormat, AudioSystem.NOT_SPECIFIED);
		sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
		sourceDataLine.open(audioFormat);
		sourceDataLine.start();
		byte tempBuffer[] = new byte[320];
		try {
			int cnt;
			while ((cnt = audioInputStream.read(tempBuffer, 0,
					tempBuffer.length)) != -1 && flag == true) {
				if (cnt > 0) {
					sourceDataLine.write(tempBuffer, 0, cnt);
				}
			}
			sourceDataLine.drain();
			sourceDataLine.close();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void stopMusic() {
		flag = false;
	}
}