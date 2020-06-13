package com.test.user.userdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.test.user.userdemo.error.UserNotFoundException;
import com.test.user.userdemo.model.UserData;
import com.test.user.userdemo.service.UserService;


import io.swagger.annotations.ApiOperation;

@RestController

public class UserController {
	@Autowired
	UserService userservice;

	@GetMapping("/users/all")
	public ResponseEntity<List<UserData>> getAllUsers() {
		List<UserData> list = userservice.getAllUsers();
		return new ResponseEntity<List<UserData>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation("Get All User Order By Name")
	@GetMapping("/users/sort")
	public ResponseEntity<List<UserData>> getEmployeesByOrder() {
		List<UserData> list = userservice.getEmployeesByOrder();
		return ResponseEntity.ok(list);
	}

	@ApiOperation(value = "Add user")
	@PostMapping("/users/{userid}")
	public ResponseEntity<UserData> createorUpdateuser(@RequestBody UserData user) {
		UserData updated = userservice.createorUpdateuser(user);
		return new ResponseEntity<UserData>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	
	@ApiOperation("Below 5 years")
	@GetMapping("/users/below")
	public ResponseEntity<List<UserData>> userBelowfive() {
		List<UserData> list = userservice.findbelowfiveexperience();
		return new ResponseEntity<List<UserData>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation("Above 10 years")
	@GetMapping("/users/above")
	public ResponseEntity<List<UserData>> getByExpten() {
		List<UserData> list = userservice.findabovetenexperience();
		return new ResponseEntity<List<UserData>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@ApiOperation("Mid level Experience")
	@GetMapping("/users/mid")
	public ResponseEntity<List<UserData>> getByExpbetween() {
		List<UserData> list = userservice.getByExpbetween();
		return new ResponseEntity<List<UserData>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "delete a user")
	@DeleteMapping("/users/{userid}")
	public HttpStatus deleteuser(@PathVariable("userid") int userid) throws UserNotFoundException {
		userservice.deleteUserId(userid);		
		return HttpStatus.FORBIDDEN;
	}

	@ApiOperation("All User using PageRequest")
	@RequestMapping(value = "/users/page", method = RequestMethod.GET)
	public List<UserData> getAllItemCategoryByPage(@RequestParam("page") int pageIndex,
			@RequestParam("size") int pageSize) {
		return userservice.getAllItemCategoriesByPageable(PageRequest.of(pageIndex, pageSize)).getContent();
	}

}
