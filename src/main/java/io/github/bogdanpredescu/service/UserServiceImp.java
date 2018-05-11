package io.github.bogdanpredescu.service;

import io.github.bogdanpredescu.restsample.models.User;

public class UserServiceImp implements UserService {
	
	@Override
	public void createUser(String userId, String name, String email) {
		
		User user = new User(userId, name, email);
		
		DBService.getDbSession().save(user);
			
	}
		 
}
