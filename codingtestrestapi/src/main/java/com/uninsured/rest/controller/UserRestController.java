package com.uninsured.rest.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uninsured.data.entity.SKU;
import com.uninsured.rest.repository.UserRepository;
import com.uninsured.rest.responses.BasicResponse;

@RestController
public class UserRestController {

	private static final Logger LOGGER = Logger
			.getLogger(UserRestController.class);

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/api/v1/location", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAllUsers() throws JsonProcessingException {
		LOGGER.info("getAllUsers() ...");
		String allSKUsListJson = StringUtils.EMPTY;

		if (StringUtils.isBlank(allSKUsListJson)) {
			List<SKU> skuList = userRepository.getAllUsersList();
			ObjectMapper mapper = new ObjectMapper();
			allSKUsListJson = mapper.writeValueAsString(skuList);

		}
		return allSKUsListJson;
	}

	@RequestMapping(value = "/api/v1/location/{location_id}/department", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String searchByLocation(
			@PathVariable("location_id") String location_id)
			throws JsonProcessingException {
		LOGGER.info("search sku ...");
		List<SKU> sku = userRepository.searchByLocation(location_id);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(sku);

	}

	@RequestMapping(value = "/api/v1/location/{location_id}/department/{department_id}/category", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String searchByLocation_department(
			@PathVariable("location_id") String location_id,
			@PathVariable("department_id") String department_id)
			throws JsonProcessingException {
		LOGGER.info("search sku ...");
		List<SKU> sku = userRepository.searchByLocation_department(location_id,
				department_id);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(sku);

	}

	@RequestMapping(value = "/api/v1/location/{location_id}/department/{department_id}/category/{category_id}/subcategory", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String searchByLocation_department_category(
			@PathVariable("location_id") String location_id,
			@PathVariable("department_id") String department_id,
			@PathVariable("category_id") String category_id)
			throws JsonProcessingException {
		LOGGER.info("search sku ...");
		List<SKU> sku = userRepository.searchByLocation_department_category(
				location_id, department_id, category_id);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(sku);

	}

	@RequestMapping(value = "/api/v1/location/{location_id}/department/{department_id}/category/{category_id}/subcategory/{subcategory_id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String searchByLocation_department_category_subcategory(
			@PathVariable("location_id") String location_id,
			@PathVariable("department_id") String department_id,
			@PathVariable("category_id") String category_id,
			@PathVariable("subcategory_id") String subcategory_id)
			throws JsonProcessingException {
		LOGGER.info("search sku ...");
		List<SKU> sku = userRepository
				.searchByLocation_department_category_subcategory(location_id,
						department_id, category_id, subcategory_id);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(sku);

	}
	
	@RequestMapping(value = "/api/v1/location/updatedata/{name}/{location}/{updatekey}", method = RequestMethod.PUT)
	@ResponseBody
	public void updatePersonByName(@PathVariable("name") String name,
			@PathVariable("location") String location,
			@PathVariable("updatekey") String updatekey,
			@ModelAttribute("sku") SKU sku) {
		LOGGER.info("updateskuByName()...");
		//BasicResponse resp;
		SKU mysku = userRepository.update(name, location, updatekey, sku);
		if (mysku != null) {
			LOGGER.info("updated: " + mysku.toString());
			//resp = new BasicResponse(true, "Successfully updated Person: " + mysku.toString());
		} else {
			LOGGER.info("name: " + name + ", NOT FOUND!");
			//resp = new BasicResponse(false, "Failed to update Person: " + name);
		}
		LOGGER.info("...updatePersonByName()");
		
	}
}
