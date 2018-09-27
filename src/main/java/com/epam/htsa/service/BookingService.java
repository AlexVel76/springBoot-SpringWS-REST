package com.epam.htsa.service;

import com.epam.htsa.entity.Ticket;
import org.springframework.lang.NonNull;

import java.util.List;

public interface BookingService {

	public List<Ticket> bookTickets(@NonNull List<Ticket> tickets);

}
