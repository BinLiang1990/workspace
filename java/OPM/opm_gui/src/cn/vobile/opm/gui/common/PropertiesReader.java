package cn.vobile.opm.gui.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author wang_lin
 * 
 */
public class PropertiesReader {

	private static Log log = LogFactory.getLog(PropertiesReader.class);
	private static final Map<String, String> map = getPropertiesValue();
	public static final String EQS_URL = map.get("EQS_URL");
	public static final String WASU_URL = map.get("WASU_URL");
	public static final String MP3 = map.get("MP3");
	public static final String TIME = map.get("TIME");

	private static Map<String, String> getPropertiesValue() {
		Map<String, String> map = new HashMap<String, String>();
		Properties prop = new Properties();
		try {
			prop.load(ClassLoader
					.getSystemResourceAsStream("opm_gui.properties"));
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		map.put("EQS_URL", prop.getProperty("eqs_url"));
		map.put("WASU_URL", prop.getProperty("wasu_url"));
		map.put("MP3", prop.getProperty("mp3"));
		map.put("TIME", prop.getProperty("time"));
		return map;
	}

}
