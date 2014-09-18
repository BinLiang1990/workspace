package cn.vobile.opm.gui.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.vobile.opm.gui.common.PropertiesReader;
import cn.vobile.opm.gui.view.AlarmView;

/**
 * AlarmMessageController send query infomation to web server by http protocol.
 * 
 * @author wang_lin
 * 
 */
public class AlarmMessageController implements Runnable {

	private static Log log = LogFactory.getLog(AlarmMessageController.class);
	private static final int ACCESSFAIL = 1;
	private static final int ALARM = 2;
	private static final int NORMAL = 3;

	public int queryAlarmMessage(String opmURL) {
		boolean flag = false;
		try {
			String urlString = "http://" + opmURL
					+ "/opm_web/AlarmGUIAction.action";
			URL url = new URL(urlString);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url
					.openConnection();
			httpUrlConnection.setConnectTimeout(1000 * 60 * 30);
			httpUrlConnection.setReadTimeout(1000 * 60 * 30);
			httpUrlConnection.setDoOutput(true);
			httpUrlConnection.setDoInput(true);
			httpUrlConnection.setUseCaches(false);
			httpUrlConnection.setRequestProperty("Content-type",
					"application/x-java-serialized-object");
			httpUrlConnection.setRequestMethod("POST");
			try {
				httpUrlConnection.connect();
			} catch (ConnectException e) {
				log.error(e.getMessage());
				return ACCESSFAIL;
			} catch (SocketTimeoutException e) {
				log.error(e.getMessage());
				return ACCESSFAIL;
			} catch (Exception e) {
				log.error(e.getMessage());
				return ACCESSFAIL;
			}
			OutputStream outStrm = httpUrlConnection.getOutputStream();
			ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm);
			objOutputStrm.writeObject(new String("Is there alarm message?"));
			objOutputStrm.flush();
			objOutputStrm.close();
			InputStream inStrm = httpUrlConnection.getInputStream();
			ObjectInputStream objInputStrm = new ObjectInputStream(inStrm);
			flag = (Boolean) objInputStrm.readObject();
			objInputStrm.close();
		} catch (MalformedURLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		if (flag) {
			return ALARM;
		}
		return NORMAL;
	}

	@Override
	public void run() {
		while (true) {
			if (queryAlarmMessage(PropertiesReader.EQS_URL) == ACCESSFAIL) {
				new AlarmView("Can not access EQX!", PropertiesReader.EQS_URL);
			}
			if (queryAlarmMessage(PropertiesReader.WASU_URL) == ACCESSFAIL) {
				new AlarmView("Can not access WASU!", PropertiesReader.WASU_URL);
			}
			if (queryAlarmMessage(PropertiesReader.EQS_URL) == ALARM) {
				new AlarmView("Please access EQX to handle alarm!",
						PropertiesReader.EQS_URL);
			}
			if (queryAlarmMessage(PropertiesReader.WASU_URL) == ALARM) {
				new AlarmView("Please access WASU to handle alarm!",
						PropertiesReader.WASU_URL);
			}
			try {
				Thread.sleep(Long.parseLong(PropertiesReader.TIME) * 1000 * 60);
			} catch (InterruptedException e) {
				log.error(e.getMessage());
				e.printStackTrace();
			}

		}
	}
}
