package org.tabchanj.aladdin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tabchanj.aladdin.domain.User;
import org.tabchanj.aladdin.mapper.UserMapper;
import org.tabchanj.aladdin.service.IUserService;

@Service
public class UserService implements IUserService  {
	@Autowired
	private UserMapper userMapper;

	public List<User> getAll() {

		return null;
	}

	public User get(int userid) {
		User user = (User) userMapper.get(userid);
		return user;
	}
}
