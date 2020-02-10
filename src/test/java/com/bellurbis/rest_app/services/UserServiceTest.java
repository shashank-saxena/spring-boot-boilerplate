package com.bellurbis.rest_app.services;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bellurbis.rest_app.DTO.UserDTO;
import com.bellurbis.rest_app.models.UserModel;
import com.bellurbis.rest_app.repositories.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@InjectMocks
	private UserService userService;
	@Mock
	private UserRepo repo;
	
	 @Before
	    public void setUp() throws Exception {

	         MockitoAnnotations.initMocks(this);
	    }
	
	private List<UserModel> collect = Stream.of(
			new UserModel(1,"Suman", "Maity"),
			new UserModel(2,"Harkesh", "Kumar"),
			new UserModel(3,"Shashank", "Saxena"),
			new UserModel(4,"Suraj", "Mitra"),
			new UserModel(5,"Mansi", "Gupta")
			)
	.collect(Collectors.toList());
	@Test
	public void loginFailForWrongPassword()//for wrong password
	{
		UserDTO model=new UserDTO("Suman","Maity1");
		when(repo.findUserModelByUsername(model.getUsername()))
		.thenReturn(collect.get(0));//return perticular data from database
		assertTrue(userService.login(model).getData().get(0).toString().equals(new UserModel().toString()));
	}
	@Test
	public void loginFailForWrongUsername()//for wrong username
	{
		UserDTO model=new UserDTO("Suman1","Maity");
		when(repo.findUserModelByUsername(model.getUsername()))
		.thenThrow(new NullPointerException());//return perticular data from database
		assertTrue(new UserModel().toString().equals(userService.login(model).getData().get(0).toString()) );
	}
	@Test
	public void loginSuccess()
	{
		UserDTO model=new UserDTO("Suman","Maity");
		when(repo.findUserModelByUsername(model.getUsername())).thenReturn(collect.get(0));//return perticular data from database
		assertEquals(collect.get(0), userService.login(model).getData().get(0));
		
	}
	@Test
	public void saveFailForDuplicateUsername() {
		UserDTO model= new UserDTO("Suman","Maity");
		when(repo.existsUserModelByUsername(model.getUsername())).thenReturn(true);
		assertTrue(userService.save(model).getData().get(0).toString().equals(new UserModel().toString()));
	}
	@Test
	public void saveSuccess() {
		UserDTO model=new UserDTO("Divya","Rai");
		UserModel returndata=new UserModel(6,"Divya","Rai");
		when(repo.existsUserModelByUsername(model.getUsername())).thenReturn(false);
		when(repo.save(any(UserModel.class))).thenReturn(returndata);
		assertEquals(returndata, userService.save(model).getData().get(0));
	}
	@Test
	public void allSuccess() {
				when(repo.findAll()).thenReturn(
				collect);
		assertEquals(5,userService.all().getData().size());
	}
	@Test
	public void findSuccess() {
		when(repo.existsById(1)).thenReturn(true);
		when(repo.findUserModelById(1))
		.thenReturn(collect.get(0));
		assertEquals(collect.get(0).toString(),userService.find(1).getData().get(0).toString());
	}
	@Test
	public void findFailforWrongId() {
		when(repo.existsById(1)).thenReturn(false);
		assertEquals(new UserModel().toString(),userService.find(1).getData().get(0).toString());
	}
}
