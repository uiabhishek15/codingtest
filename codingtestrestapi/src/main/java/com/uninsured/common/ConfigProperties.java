package com.uninsured.common;

import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * 
 * @author ANKEM
 *
 */
public class ConfigProperties {
private static final Logger LOGGER = Logger.getLogger(ConfigProperties.class);
	
	public Properties fetchConfigProperties(String utility) {
		Properties prop = new Properties();				
			try {
				prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
			} catch (IOException e) {
				LOGGER.error("Unable to load "+utility+" configuration properties from the properties file");
			}
		
		return prop;
	}
}
