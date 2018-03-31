package com.codingtest.web.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


/**
 * 
 * @author ANKEM
 *
 */

@Configuration
@ComponentScan({"com.codingtest.web","com.codingtest.data"})
@EnableMongoRepositories({"com.codingtest.web.app.config"}) 
@Import({MongoDBConfig.class})
public class AppConfig {
	
}
