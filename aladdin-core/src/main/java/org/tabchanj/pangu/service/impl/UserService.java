package org.tabchanj.pangu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tabchanj.pangu.domain.User;
import org.tabchanj.pangu.mapper.UserMapper;
import org.tabchanj.pangu.service.IUserService;

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
