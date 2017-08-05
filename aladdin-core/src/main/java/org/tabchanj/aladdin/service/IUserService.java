package org.tabchanj.aladdin.service;

import java.util.List;

import org.tabchanj.aladdin.domain.User;

public interface IUserService {
	List<User> getAll();

	User get(int userid);
}
