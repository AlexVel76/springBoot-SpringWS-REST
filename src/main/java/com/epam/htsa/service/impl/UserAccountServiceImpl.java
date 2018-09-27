package com.epam.htsa.service.impl;

import com.epam.htsa.dao.UserAccountDao;
import com.epam.htsa.dao.UserDao;
import com.epam.htsa.entity.User;
import com.epam.htsa.entity.UserAccount;
import com.epam.htsa.exception.HomeTaskExceptioin;
import com.epam.htsa.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	private UserAccountDao userAccountDao;

	@Autowired
	private UserDao userDao;

	@Override
	public UserAccount save(UserAccount entity) {
		return userAccountDao.save(entity);
	}

	@Override
	public void remove(UserAccount entity) {
		userAccountDao.delete(entity);
	}

	@Override
	public UserAccount getById(Long id) {
		return userAccountDao.findById(id).orElseGet(null);
	}

	@Override
	public Iterable<UserAccount> getAll() {
		return userAccountDao.findAll();
	}

	@Override
	public UserAccount getUserAccountByUser(User user) {
		return userAccountDao.findByUser(user);
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public UserAccount refillingAcount(Long userId, double amount) {
		User user = userDao.findById(userId).orElse(null);
		UserAccount result = null;
		if (user == null) {
			throw new HomeTaskExceptioin("User not found");
		} else {
			UserAccount account = userAccountDao.findByUser(user);
			if (account == null) {
				userAccountDao.save(new UserAccount(user, amount));
			} else {
				account.setAmount(account.getAmount() + amount);
				result = userAccountDao.save(account);
			}
		}

		return result;
	}

}
