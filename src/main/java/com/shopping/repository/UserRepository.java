package com.shopping.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shopping.model.User;

@Repository
public interface UserRepository extends IDAO<User>{

	@Query("from User u where u.loginName=?1 and u.password=?2")
	User login(String loginName, String password);
	
}
