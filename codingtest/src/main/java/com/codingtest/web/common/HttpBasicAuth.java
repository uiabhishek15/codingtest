package com.codingtest.web.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;


public class HttpBasicAuth {

	 public  void myauth() {
		 String url1 = "http://localhost:8080//codingtestrestapi/api/v1/location";
	        try {
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
	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	    }

}
/*
 * URL url = new URL ("http://ip:port/login"); String encoding =
 * Base64.getEncoder().encodeToString(("test1:test1").getBytes(‌"UTF‌​-8"​));
 * 
 * HttpURLConnection connection = (HttpURLConnection) url.openConnection();
 * connection.setRequestMethod("POST"); connection.setDoOutput(true);
 * connection.setRequestProperty ("Authorization", "Basic " + encoding);
 * InputStream content = (InputStream)connection.getInputStream();
 * BufferedReader in = new BufferedReader (new InputStreamReader (content));
 * String line ; while ((line = in.readLine()) != null) {
 * System.out.println(line); }
 */

