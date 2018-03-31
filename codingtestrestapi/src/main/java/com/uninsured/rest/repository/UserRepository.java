package com.uninsured.rest.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.uninsured.data.entity.Admin;
import com.uninsured.data.entity.SKU;
import com.uninsured.web.app.config.MongoDBContextOperations;

@Repository
public class UserRepository {

	private static final Logger LOGGER = Logger.getLogger(UserRepository.class);

	MongoOperations mongoOperations = MongoDBContextOperations
			.getMongoOperations();
	

	public List<SKU> getAllUsersList() {
		List<SKU> getAllTutorsList= mongoOperations.findAll(SKU.class);
		return getAllTutorsList;

	}
	
	public List<SKU> searchByLocation(String location_id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("LOCATION").regex("^"+location_id, "i"));
		List<SKU> searchbylocation = mongoOperations.find(query, SKU.class);
		return searchbylocation;
	}
	
	public List<SKU> searchByLocation_department(String location_id,String department_id) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("LOCATION").regex("^"+location_id, "i"),Criteria.where("DEPARTMENT").regex("^"+department_id, "i"));
		query.addCriteria(criteria);
		List<SKU> searchbylocation = mongoOperations.find(query, SKU.class);
		return searchbylocation;
	}
	
	public List<SKU> searchByLocation_department_category(String location_id,String department_id,String category_id) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("LOCATION").regex("^"+location_id, "i"),Criteria.where("DEPARTMENT").regex("^"+department_id, "i"),Criteria.where("CATEGORY").regex("^"+category_id, "i"));
		query.addCriteria(criteria);
		List<SKU> searchbylocation = mongoOperations.find(query, SKU.class);
		return searchbylocation;
	}
	
	public List<SKU> searchByLocation_department_category_subcategory(String location_id,String department_id,String category_id,String subcategory_id) {
		Query query = new Query();
		Criteria criteria = new Criteria();
		criteria.orOperator(Criteria.where("LOCATION").regex("^"+location_id, "i"),Criteria.where("DEPARTMENT").regex("^"+department_id, "i"),Criteria.where("CATEGORY").regex("^"+category_id, "i"),Criteria.where("SUBCATEGORY").regex("^"+subcategory_id, "i"));
		query.addCriteria(criteria);
		List<SKU> searchbylocation = mongoOperations.find(query, SKU.class);
		return searchbylocation;
	}
	
	public SKU update(String name,String location,String department,String category,String subCategory,SKU sku){
		Query query = new Query();
		query.addCriteria(Criteria.where("NAME").is(name));

		//SKU sku1 = mongoOperations.findOne(query, SKU.class);
		Update update = new Update();
		//modify and update with save()
		update.set("NAME",name);
		update.set("LOCATION",location);
		update.set("DEPARTMENT",department);
		update.set("CATEGORY",category);
		update.set("SUBCATEGORY",subCategory);
		mongoOperations.updateFirst(query, update, SKU.class);
		return sku;
	}
	
	public SKU findbyname(String name){
		 Query query = new Query();
		 query.addCriteria(Criteria.where("NAME").is(name));
	        // Execute the query and find one matching entry
	        SKU sku = mongoOperations.findOne(query, SKU.class);
	        return sku;
	}

	/*public Boolean Login(String username, String password) {

		Criteria criteria = new Criteria();
		criteria.andOperator(Criteria.where("username").is(username),Criteria.where("password").is(password));
        Query query = new Query(criteria);
        Admin admin = mongoOperations.findOne(query, Admin.class);
        String dbPassword = admin.getPassword();
        if(mongoOperations.findOne(query, Admin.class) != null) {
			return true;
		}
        else
        {
        	return false;
        }

	}*/
}
