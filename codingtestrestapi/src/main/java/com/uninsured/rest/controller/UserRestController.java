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

	/*@RequestMapping(value = "/api/v1/location/updatedata/{name}/{location}/{department}/{category}/{subcategory}", method = RequestMethod.PUT)
	public ResponseEntity<SKU> updateUser(@PathVariable("name") String name,
			@PathVariable("location") String location,
			@PathVariable("department") String department,
			@PathVariable("category") String category,
			@PathVariable("subcategory") String subcategory,
			@RequestBody SKU user) {
		System.out.println("Updating User " + name);

		SKU currentUser = userRepository.update(name, location, department, category, subcategory, user);

		if (currentUser == null) {
			System.out.println("User with id " + name + " not found");
			return new ResponseEntity<SKU>(HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);
		return new ResponseEntity<SKU>(currentUser, HttpStatus.OK);
	}*/
	
	@RequestMapping(value = "/api/v1/location/updatedata/{name}/{location}/{department}/{category}/{subcategory}", method = RequestMethod.PUT)
	@ResponseBody
	public BasicResponse updatePersonByName(@PathVariable("name") String name,
			@PathVariable("location") String location,
			@PathVariable("department") String department,
			@PathVariable("category") String category,
			@PathVariable("subcategory") String subcategory,
			@ModelAttribute("sku") SKU sku) {
		LOGGER.info("updateskuByName()...");
		BasicResponse resp;
		SKU mysku = userRepository.update(name, location, department, category,
				subcategory, sku);
		if (mysku != null) {
			LOGGER.info("updated: " + mysku.toString());
			resp = new BasicResponse(true, "Successfully updated Person: " + mysku.toString());
		} else {
			LOGGER.info("name: " + name + ", NOT FOUND!");
			resp = new BasicResponse(false, "Failed to update Person: " + name);
		}
		LOGGER.info("...updatePersonByName()");
		return resp;
	}

	/*@RequestMapping(value = "/api/v1/location/updatedata/{name}/{location}/{department}/{category}/{subcategory}", method = RequestMethod.PUT)
	public @ResponseBody ResponseEntity<SKU> updateImageMetadata(
			@PathVariable("name") String name,
			@PathVariable("location") String location,
			@PathVariable("department") String department,
			@PathVariable("category") String category,
			@PathVariable("subcategory") String subcategory,
			@ModelAttribute("sku") SKU sku) {
		// BasicResponse extResp;
		// password = BCrypt.hashpw(password, BCrypt.gensalt(12));
		SKU mySKU = userRepository.update(name, location, department, category,
				subcategory, sku);

		if (mySKU != null) {
			LOGGER.info("Inside updateTutorByOTP, updated: " + mySKU.getName()
					+ mySKU.getLocation() + mySKU.getDepartment());
			return new ResponseEntity<SKU>(HttpStatus.OK);
		} else {
			LOGGER.info("Inside updateIssuerByTicker, ticker: " + name
					+ ", NOT FOUND!");
			return new ResponseEntity<SKU>(HttpStatus.NOT_FOUND);
			
			 * extResp = new BasicResponse(false, "Failed to update ticker: " +
			 * mySKU);
			 
		}
	}
*/
	/*
	 * @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT) public
	 * ResponseEntity<SKU> updateUser(@PathVariable("id") long id, @RequestBody
	 * User user) { System.out.println("Updating User " + id);
	 * 
	 * User currentUser = userService.findById(id);
	 * 
	 * if (currentUser==null) { System.out.println("User with id " + id +
	 * " not found"); return new ResponseEntity<User>(HttpStatus.NOT_FOUND); }
	 * 
	 * currentUser.setName(user.getName()); currentUser.setAge(user.getAge());
	 * currentUser.setSalary(user.getSalary());
	 * 
	 * userService.updateUser(currentUser); return new
	 * ResponseEntity<User>(currentUser, HttpStatus.OK); }
	 */

	/*
	 * @RequestMapping(value = "/login/{username}/{password}", method =
	 * RequestMethod.GET,produces="application/json") public @ResponseBody
	 * String login(@PathVariable("username") String
	 * username,@PathVariable("password") String password) throws
	 * JsonProcessingException {
	 * LOGGER.info("loginning as ..."+"------"+username); Boolean isLogin =
	 * userRepository.Login(username, password); ObjectMapper mapper = new
	 * ObjectMapper(); return mapper.writeValueAsString(isLogin);
	 * 
	 * }
	 */

}
