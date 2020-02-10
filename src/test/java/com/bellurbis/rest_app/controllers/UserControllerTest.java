package com.bellurbis.rest_app.controllers;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bellurbis.rest_app.DTO.ResponseDTO;
import com.bellurbis.rest_app.DTO.UserDTO;
import com.bellurbis.rest_app.docs.ErrorMessages;
import com.bellurbis.rest_app.models.UserModel;
import com.bellurbis.rest_app.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest{
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserService service;
	private List<UserModel> collect = Stream.of(
			new UserModel(1,"Suman", "Maity"),
			new UserModel(2,"Harkesh", "Kumar"),
			new UserModel(3,"Shashank", "Saxena"),
			new UserModel(4,"Yash", "Rikhi"),
			new UserModel(5,"Mansi", "Gupta")
			)
	.collect(Collectors.toList());
	
	ObjectMapper om = new ObjectMapper();
	
	@Test
	public void testLoginPerfect() throws Exception {
		UserDTO user = new UserDTO("Suman","Maity");
		String jsonRequest = om.writeValueAsString(user);
		ResponseDTO res=new ResponseDTO(
				HttpStatus.ACCEPTED.value(), 
				Arrays.asList(collect.get(0)), 
				ErrorMessages.messageAccepted
				);
		when(service.login(any(UserDTO.class))).thenReturn(res);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/login")
				.accept(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		ResponseDTO response = om.readValue(resultContent, ResponseDTO.class);
		assertEquals(user.getPassword().toString(), response.getData().get(0).getPassword().toString());
	}
	@Test
	public void testRegister() throws Exception {
		UserDTO user = new UserDTO("Suraj","Mitra");
		String jsonRequest = om.writeValueAsString(user);
		ResponseDTO res=new ResponseDTO(
				HttpStatus.ACCEPTED.value(), 
				Arrays.asList(new UserModel(6,"Suraj","Mitra")), 
				ErrorMessages.messageAccepted
				);
		when(service.save(any(UserDTO.class))).thenReturn(res);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/register")
				.accept(MediaType.APPLICATION_JSON)
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		ResponseDTO response = om.readValue(resultContent, ResponseDTO.class);
		assertEquals(user.getUsername().toString(), response.getData().get(0).getUsername().toString());
	}
	@Test
	public void testAll() throws Exception {
		ResponseDTO res=new ResponseDTO(
				HttpStatus.ACCEPTED.value(), 
				collect, 
				ErrorMessages.messageAccepted
				);
		when(service.all()).thenReturn(res);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/user/all")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		ResponseDTO response = om.readValue(resultContent, ResponseDTO.class);
		assertEquals(collect.toString(), response.getData().toString());
	}
	@Test
	public void testFind() throws Exception {
		ResponseDTO res=new ResponseDTO(
				HttpStatus.ACCEPTED.value(), 
				Arrays.asList(collect.get(0)), 
				ErrorMessages.messageAccepted
				);
		when(service.find(1)).thenReturn(res);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/user/1")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		ResponseDTO response = om.readValue(resultContent, ResponseDTO.class);
		assertEquals(collect.get(0).toString(), response.getData().get(0).toString());
	}
}
