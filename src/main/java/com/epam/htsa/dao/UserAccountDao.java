package com.epam.htsa.dao;

import com.epam.htsa.entity.User;
import com.epam.htsa.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountDao extends CrudRepository<UserAccount, Long> {

	UserAccount findByUser(User user);

}
