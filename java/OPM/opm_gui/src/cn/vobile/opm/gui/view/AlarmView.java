package cn.vobile.opm.gui.view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.vobile.opm.gui.controller.MusicPlayer;

@SuppressWarnings("serial")
public class AlarmView extends JFrame implements ActionListener {

	private static Log log = LogFactory.getLog(AlarmView.class);
	private final JButton message;
	private final MusicPlayer musicPlayer;
	private final String uriString;

	public AlarmView(String alarmType, String url) {
		uriString = "http://" + url + "/opm_web";
		message = new JButton();
		message.setText(alarmType);
		int x = Toolkit.getDefaultToolkit().getScreenSize().width;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height;
		int width = 300, height = 200;
		this.setBounds(x - width, y - height, width, height);
		this.setLayout(new BorderLayout());
		message.addActionListener(this);
		this.add(message, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Alarm");
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				musicPlayer.stopMusic();
			}
		});
		this.setVisible(true);
		musicPlayer = new MusicPlayer();
		try {
			musicPlayer.playMusic();
			this.dispose();
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			open(new URI(uriString));
		} catch (URISyntaxException e1) {
			log.error(e1.getMessage());
		}
	}

	private void open(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) { /* TODO: error handling */
			}
		} else {
			log.error("click fails");
		}
	}
}
