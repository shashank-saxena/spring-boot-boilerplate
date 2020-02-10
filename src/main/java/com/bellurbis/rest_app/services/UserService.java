package com.bellurbis.rest_app.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bellurbis.rest_app.DTO.ResponseDTO;
import com.bellurbis.rest_app.DTO.UserDTO;
import com.bellurbis.rest_app.docs.ErrorMessages;
import com.bellurbis.rest_app.models.UserModel;
import com.bellurbis.rest_app.repositories.UserRepo;
@Service
public class UserService{
	@Autowired
	private UserRepo repo;
	public ResponseDTO login(UserDTO model) 
	{
		try {
				UserModel data=repo.findUserModelByUsername(model.getUsername());
				if(data.getPassword().equals(model.getPassword()))
				{
					return new ResponseDTO(HttpStatus.ACCEPTED.value(), Arrays.asList(data), ErrorMessages.messageAccepted);
				}
				else
					return new ResponseDTO(HttpStatus.UNAUTHORIZED.value(), Arrays.asList(new UserModel()), ErrorMessages.messageUserNotFound);
		}
		catch(NullPointerException e)
		{
			return new ResponseDTO(HttpStatus.NOT_ACCEPTABLE.value(), Arrays.asList(new UserModel()), ErrorMessages.messageFieldsAreNotToBeNull);
		}
	}
	public ResponseDTO save(UserDTO model) {
			Boolean exist=repo.existsUserModelByUsername(model.getUsername());
			if(exist==true)
			{
				return new ResponseDTO(HttpStatus.CONFLICT.value(),Arrays.asList(new UserModel()),ErrorMessages.messageAlreadyExist);
			}
			else
			{
				UserModel data=new UserModel(model.getUsername(),model.getPassword());
				return new ResponseDTO(HttpStatus.CREATED.value(), Arrays.asList(repo.save(data)), ErrorMessages.messageCreated) ;
			}
	}
	public ResponseDTO all() {
		return new ResponseDTO( HttpStatus.OK.value(), repo.findAll(), ErrorMessages.messageOk) ;
	}
	public ResponseDTO find(int id) {
		if(repo.existsById(id)==true)
			return new ResponseDTO(HttpStatus.OK.value(), Arrays.asList(repo.findUserModelById(id)), ErrorMessages.messageOk);
		else
			return new ResponseDTO(HttpStatus.NOT_FOUND.value(), Arrays.asList(new UserModel()), ErrorMessages.messageUserNotFound);
	}
}
