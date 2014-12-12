package web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import web.dao.impl.SpeciesNameDaoImpl;



/**
 * General configuration system.
 */
public class SysConfig {

	private static final Log log = LogFactory.getLog(SysConfig.class);
	private static Properties properties = new Properties();
	private static InputStream fis = null;
	private static SysConfig config;

	private SysConfig() {
		fis = SysConfig.class.getResourceAsStream("config/config.properties");
		if (fis == null) fis = SysConfig.class.getResourceAsStream("/config.properties");
		try {
			properties.load(fis);
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

	public static SysConfig getInstance() {
		if (config == null) {
			config = new SysConfig();
		}
		return config;
	}

	public String getValue(String propertyName) {
		return properties.getProperty(propertyName);
	}
}
