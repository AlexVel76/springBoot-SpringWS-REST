package com.epam.htsa.service.impl;

import com.epam.htsa.dao.TicketDao;
import com.epam.htsa.dao.UserAccountDao;
import com.epam.htsa.entity.Ticket;
import com.epam.htsa.entity.UserAccount;
import com.epam.htsa.exception.HomeTaskExceptioin;
import com.epam.htsa.service.BookingService;
import com.epam.htsa.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Oleksandr_Velchenko
 */
@Component
public class BookingServiceImpl implements BookingService {
	@Autowired
	private TicketDao ticketDao;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private UserAccountDao userAccountDao;


	@Override
	@Transactional
	public List<Ticket> bookTickets(@NonNull List<Ticket> tickets) {
		UserAccount userAccount = userAccountService.getUserAccountByUser(tickets.iterator().next().getUser());
		if (userAccount == null)
			throw new HomeTaskExceptioin("User account not found");

		double chargeAmount = tickets.stream().mapToDouble(Ticket::getPrice).sum();

		if (Double.compare(userAccount.getAmount(), chargeAmount) < 0)
			throw new HomeTaskExceptioin("Not enough money.");

		userAccount.setAmount(userAccount.getAmount() - chargeAmount);

		userAccountDao.save(userAccount);
		ticketDao.saveAll(tickets);
		return (List<Ticket>) tickets;
	}
}
