package com.uninsured.rest.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.*;

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
	
	public SKU update(String name,String updateVar,String updateKey,SKU sku){
		Query query = new Query();
		query.addCriteria(Criteria.where("NAME").is(name));

		SKU sku1 = mongoOperations.findOne(query, SKU.class);
		Update u = new Update();
		switch(updateKey){    
		case "SKU":    
			u.set("SKU", updateVar);
		 break;  
		case "NAME":    
			u.set("SKU", updateVar);   
		 break;
		case "LOCATION":    
			u.set("LOCATION", updateVar);
		 break;   
		case "DEPARTMENT":    
			u.set("DEPARTMENT", updateVar);  
		 break;  
		case "CATEGORY":    
			u.set("CATEGORY", updateVar);
		 break;  
		case "SUBCATEGORY":    
			u.set("SUBCATEGORY", updateVar);    
		 break;  
		}
		//u.set("LOCATION", updateVar);
		
		mongoOperations.updateFirst(query, u, SKU.class);

		System.out.println("userTest1_1 - " + sku1.getName());

		return sku;
	}

	public SKU save(String sku, String name, String location,
			String department, String category, String subcategory, SKU sku1) {
		SKU newSku = new SKU();
		newSku.setSku(sku);
		newSku.setName(name);
		newSku.setLocation(location);
		newSku.setDepartment(department);
		newSku.setCategory(category);
		newSku.setSubcategory(subcategory);
		mongoOperations.save(newSku);
		return newSku;
	}

	public SKU deleteSku(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("NAME").is(name));

		SKU sku1 = mongoOperations.findOne(query, SKU.class);
		mongoOperations.remove(sku1);
		

		return sku1;
	}
}
