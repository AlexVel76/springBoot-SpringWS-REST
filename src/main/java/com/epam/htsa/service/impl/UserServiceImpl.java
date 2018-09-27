package com.epam.htsa.service.impl;

import com.epam.htsa.dao.UserDao;
import com.epam.htsa.entity.User;
import com.epam.htsa.exception.HomeTaskExceptioin;
import com.epam.htsa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Oleksandr_Velchenko
 */
@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	/**
	 * Finding user by email
	 * 
	 * @param email
	 *            Email of the user
	 * @return found user or <code>null</code>
	 */
	public User getUserByEmail(final String email) {
		return userDao.findByEmail(email);

	}

	@Override
	public User save(final User user) {
		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

	@Override
	public void remove(final User object) {
		userDao.delete(object);
	}

	@Override
	public User getById(final Long id) {
		return userDao.findById(id).orElseThrow(()-> new HomeTaskExceptioin("User is not available!"));
	}

	@Override
	public List<User> getAll() {
		return (List<User>) userDao.findAll();
	}

	@Override
	public User getByLogin(String login) {
		return userDao.findByLogin(login);
	}
}
