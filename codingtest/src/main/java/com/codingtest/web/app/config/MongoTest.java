package com.codingtest.web.app.config;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.codingtest.data.entity.User;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;


//@Component

public class MongoTest {
	
	
 
	private static List<User> users;
	public static void main(String[] args) {
		MongoOperations mongoOperations = MongoDBContextOperations.getMongoOperations();
	/*	Query query = new Query();
		query.addCriteria(Criteria.where("County").regex("C$"));
		List<User> users = mongoOperations.find(query,User.class);
				System.out.println(users.size());*/
		 String url1 = "http://localhost:8080//codingtestrestapi/api/v1/location";
		 try {
	            Document doc = Jsoup.connect(url1)
	                            //.userAgent("curl/7.34.0")
	                            .header("Accept", "application/json")
	                            .header("Accept-Encoding", "gzip,deflate,sdch")
	                            .header("Accept-Language", "es-ES,es;q=0.8")
	                            .header("Connection", "keep-alive")
	                            .header("X-Requested-With", "XMLHttpRequest")
	                            //.header("Content-Type", "application/x-www-form-urlencoded")
	                            .data("{\"method\":\"Catalog.search\",\"params\":{\"pag\":1,\"business_url\":\"tecnologia\",\"categ‌​ory_url\":\"conectividad\",\"subcategory_url\":\"\",\"valmin\":-1,\"valmax\":-1}}", "")
	                            .post(); 

	            System.out.println(doc.toString());

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	       /* try {
	            URL url = new URL (url1);
	            Base64 b = new Base64();
	            String encoding = b.encodeAsString(new String("user:userPass").getBytes());


	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("POST");
	            connection.setDoOutput(true);
	            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
	            InputStream content = (InputStream)connection.getInputStream();
	            BufferedReader in   = 
	                new BufferedReader (new InputStreamReader (content));
	            String line;
	            while ((line = in.readLine()) != null) {
	                System.out.println(line);
	            }
	            Document data = Jsoup.connect(url1).ignoreContentType(true).get();
				String json = data.select("body").text();
				users = new Gson().fromJson(json, new TypeToken<List<User>>() {}.getType());
				
				System.out.println(users);
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
		//String url = "http://localhost:8080//codingtestrestapi/users";

		try {
			Document data = Jsoup.connect(url).ignoreContentType(true).get();
			String json = data.select("body").text();
			users = new Gson().fromJson(json, new TypeToken<List<User>>() {}.getType());
			
			System.out.println(users);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}
}




