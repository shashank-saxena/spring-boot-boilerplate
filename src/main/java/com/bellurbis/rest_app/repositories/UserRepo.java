package com.bellurbis.rest_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bellurbis.rest_app.models.UserModel;
@Repository
public interface UserRepo extends JpaRepository<UserModel, Integer> {
	UserModel findUserModelByUsername(String username);
	Boolean existsUserModelByUsername(String username);
	UserModel findUserModelById(int id);
}
