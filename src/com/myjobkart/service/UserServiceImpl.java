package com.myjobkart.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myjobkart.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findByLogin(String userName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByUserName(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

}
