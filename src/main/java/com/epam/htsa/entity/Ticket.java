package com.epam.htsa.entity;


import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ticket", propOrder = {
		"dateTime",
		"event",
		"price",
		"seat",
		"user"
})
public class Ticket extends Item implements Comparable<Ticket> {

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	private Event event;

	private String dateTime;

	private long seat;

	private double price;

	public Ticket() {
	}

	public Ticket(User user, Event event, LocalDateTime dateTime, long seat, double price) {
		this.user = user;
		this.event = event;
		this.dateTime = dateTime.toString();
		this.seat = seat;
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public Event getEvent() {
		return event;
	}

	public String getDateTime() {
		return dateTime;
	}

	public long getSeat() {
		return seat;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(dateTime, event, seat);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Ticket other = (Ticket) obj;
		if (dateTime == null) {
			if (other.dateTime != null) {
				return false;
			}
		} else if (!dateTime.equals(other.dateTime)) {
			return false;
		}
		if (event == null) {
			if (other.event != null) {
				return false;
			}
		} else if (!event.equals(other.event)) {
			return false;
		}
		if (seat != other.seat) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(Ticket other) {
		if (other == null) {
			return 1;
		}
		int result = dateTime.compareTo(other.getDateTime());

		if (result == 0) {
			result = event.getName().compareTo(other.getEvent().getName());
		}
		if (result == 0) {
			result = Long.compare(seat, other.getSeat());
		}
		return result;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public void setSeat(long seat) {
		this.seat = seat;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
