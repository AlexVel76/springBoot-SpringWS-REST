package com.epam.htsa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "auditorium", propOrder = {
		"events",
		"name",
		"numberOfSeats",
		"vipSeats"
})
public class Auditorium extends Item {

	@Column(unique = true)
	private String name;

	private long numberOfSeats;

	private String vipSeats;

	@XmlElement(nillable = true)
	@OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL)
	private Set<Event> events;

	public Auditorium() {
	}

	public Auditorium(String name, long numberOfSeats, String vipSeats) {
		super();
		this.name = name;
		this.numberOfSeats = numberOfSeats;
		this.vipSeats = vipSeats;
	}

	/**
	 * Counts how many vip seats are there in supplied <code>seats</code>
	 * 
	 * @param seats
	 *            Seats to process
	 * @return number of vip seats in request
	 */
	public long countVipSeats(Collection<Long> seats) {
		Set<Long> sets = Arrays.stream(vipSeats.split(",")).map(String::trim).mapToLong(Long::parseLong).boxed()
				.collect(Collectors.toSet());
		return seats.stream().filter(seat -> sets.contains(seat)).count();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(long numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Set<Long> getAllSeats() {
		return LongStream.range(1, numberOfSeats + 1).boxed()
				.collect(Collectors.toSet());
	}

	public String getVipSeats() {
		return vipSeats;
	}

	public void setVipSeats(String vipSeats) {
		this.vipSeats = vipSeats;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
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
		Auditorium other = (Auditorium) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

}
