package com.bellurbis.rest_app.controllers;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bellurbis.rest_app.DTO.ResponseDTO;
import com.bellurbis.rest_app.DTO.UserDTO;
import com.bellurbis.rest_app.services.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.service.Contact;
@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	@ApiOperation(value = "user login with username and password",
	notes="it validate the user with username and password credentials",response=Contact.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Working fine"),
			@ApiResponse(code = 401, message = "You are unauthorize"),
			@ApiResponse(code = 403, message = "Forbidden, i don't know why"),
			@ApiResponse(code = 404, message = "api not found")
			})
	public ResponseDTO login(@ApiParam(value="need to provide 'username' and 'password' value",required=true)@RequestBody UserDTO model)
	{
		LocalDateTime request=LocalDateTime.now();
		ResponseDTO back= userService.login(model);
		LocalDateTime response=LocalDateTime.now();
		Duration diff=Duration.between(request, response);
		Long millis=diff.toMillis();
		back.setTimestamp(millis.toString()+"ms");
		return back;
	}
	
	
	@PostMapping("/register")
	@ApiOperation(value="new user addition",
	notes="it take credentials and save into the database",response=Contact.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Working fine"),
			@ApiResponse(code = 401, message = "You are unauthorize"),
			@ApiResponse(code = 403, message = "Forbidden, i don't know why"),
			@ApiResponse(code = 404, message = "api not found")
			})
	public ResponseDTO register(@ApiParam(value="need to provide new 'username' and 'password' value",required=true)@RequestBody UserDTO model)
	{
		LocalDateTime request=LocalDateTime.now();
		ResponseDTO back= userService.save(model);
		LocalDateTime response=LocalDateTime.now();
		Duration diff=Duration.between(request, response);
		Long millis=diff.toMillis();
		back.setTimestamp(millis.toString()+"ms");
		return back;
	}
	
	
	@GetMapping("/all")
	@ApiOperation(value="get all data",notes="just hit the api to retrive all the data",response=Contact.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Working fine"),
			@ApiResponse(code = 401, message = "You are unauthorize"),
			@ApiResponse(code = 403, message = "Forbidden, i don't know why"),
			@ApiResponse(code = 404, message = "api not found")
			})
	public ResponseDTO all()
	{
		LocalDateTime request=LocalDateTime.now();
		ResponseDTO back= userService.all();
		LocalDateTime response=LocalDateTime.now();
		Duration diff=Duration.between(request, response);
		Long millis=diff.toMillis();
		back.setTimestamp(millis.toString()+"ms");
		return back;
	}
	@GetMapping("/{id}")
	@ApiOperation(value="get id data",notes="just hit the api to retrive the data corresponding id",response=Contact.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Working fine"),
			@ApiResponse(code = 401, message = "You are unauthorize"),
			@ApiResponse(code = 403, message = "Forbidden, i don't know why"),
			@ApiResponse(code = 404, message = "api not found")
			})
	public ResponseDTO find(@PathVariable("id") int id)
	{
		LocalDateTime request=LocalDateTime.now();
		ResponseDTO back= userService.find(id);
		LocalDateTime response=LocalDateTime.now();
		Duration diff=Duration.between(request, response);
		Long millis=diff.toMillis();
		back.setTimestamp(millis.toString()+"ms");
		return back;
	}
}