package com.epam.htsa.service;

import com.epam.htsa.entity.User;
import com.epam.htsa.entity.UserAccount;


public interface UserAccountService extends ItemService<UserAccount> {

	public UserAccount getUserAccountByUser(User user);

	public UserAccount refillingAcount(Long userId, double amount);

}
