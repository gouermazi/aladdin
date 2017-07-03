package org.tabchanj.pangu.service;

import java.util.List;

import org.tabchanj.pangu.domain.User;

public interface IUserService {
	List<User> getAll();

	User get(int userid);
}
