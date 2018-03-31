package com.uninsured.web.app.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.MongoClient;


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
	
	/*Properties prop = fetchConfigProperties();
	String host = prop.getProperty("mlab.host");
	int port = Integer.parseInt(prop.getProperty("mlab.port"));
	String database = prop.getProperty("mlab.db");
	String username = prop.getProperty("mlab.username");
	String password = prop.getProperty("mlab.password");
	MongoClientURI uri  = new MongoClientURI("mongodb://"+username+":"+password+"@"+host+":"+port+"/"+database);
	return new SimpleMongoDbFactory(new MongoClient(uri), database);*/
	
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

@Bean
public GridFsTemplate gridFsTemplate(MongoDbFactory mongoDbFactory, MongoTemplate mongoTemplate) throws Exception {
    return new GridFsTemplate(mongoDbFactory, mongoTemplate.getConverter());
}

/*	public @Bean
MongoDbFactory mongoDbFactory() throws Exception {
    return new SimpleMongoDbFactory(new MongoClient(), "yuppmeal");
}

public @Bean
MongoTemplate mongoTemplate() throws Exception {
    MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
    return mongoTemplate;
}*/
}
