package com.myjobkart.service;

import com.myjobkart.model.User;

public interface UserService {
	User save(User user);

	boolean findByLogin(String userName, String password);

	boolean findByUserName(String userName);
}
