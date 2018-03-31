package com.uninsured.web.app.config;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.uninsured.data.entity.SKU;
import com.uninsured.data.entity.Admin;

public class MongoTest {

	/*
	 * public static String random(int size) {
	 * 
	 * StringBuilder generatedToken = new StringBuilder(); try { SecureRandom
	 * number = SecureRandom.getInstance("SHA1PRNG"); // Generate 20 integers
	 * 0..20 for (int i = 0; i < size; i++) {
	 * generatedToken.append(number.nextInt(9)); } } catch
	 * (NoSuchAlgorithmException e) { e.printStackTrace(); }
	 * 
	 * return generatedToken.toString(); }
	 */

	public static void main(String[] args) throws Exception {

		MongoOperations mongoOperations = MongoDBContextOperations
				.getMongoOperations();
		
		List<SKU> restaurantList =  mongoOperations.findAll(SKU.class);
		for (SKU restaurant : restaurantList) {
			System.out.println(restaurant.getName());
		}
	}
}
