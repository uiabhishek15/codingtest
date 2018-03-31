package com.codingtest.web.app.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

/**
 * 
 * @author ANKEM
 *
 */

/**
 	MongoDB configuration class for the application.Dont make changes to this file
 */

@Configuration
public class MongoDBConfig {
	
	
	private static final Logger LOGGER = Logger.getLogger(MongoDBConfig.class);

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {

		Properties prop = fetchConfigProperties();
		String host = prop.getProperty("mongodb.host");
		int port = Integer.parseInt(prop.getProperty("mongodb.port"));
		String database = prop.getProperty("mongodb.db");

		return new SimpleMongoDbFactory(new MongoClient(host, port), database);
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		
		return mongoTemplate;
	}

	private Properties fetchConfigProperties() {
		Properties prop = new Properties();				
			try {
				prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
			} catch (IOException e) {
				LOGGER.error("Unable to load MongoDB configuration properties from the properties file");
			}
		
		return prop;
	}
}
