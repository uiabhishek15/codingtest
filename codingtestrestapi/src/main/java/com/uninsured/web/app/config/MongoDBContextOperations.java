package com.uninsured.web.app.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

/**
 * 
 * @author ABHI
 *
 */
public class MongoDBContextOperations {
	
	public static MongoOperations getMongoOperations(){
		ApplicationContext ctx = 
	            new AnnotationConfigApplicationContext(MongoDBConfig.class);
		MongoOperations mongoOperations = (MongoOperations) ctx.getBean("mongoTemplate");
		((ConfigurableApplicationContext)ctx).close();
		return mongoOperations;
	}	
}
