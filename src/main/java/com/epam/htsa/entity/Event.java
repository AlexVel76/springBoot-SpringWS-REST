package com.epam.htsa.entity;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.*;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "event", propOrder = {
		"name",
		"airDate",
		"basePrice",
		"rating"
})
public class Event extends Item {

	private String name;

	private String airDate;

	private double basePrice;

	@XmlSchemaType(name = "string")
	private EventRating rating;

	@ManyToOne
	@JoinColumn(name = "auditorium_id", referencedColumnName = "id")
	@XmlTransient
	private Auditorium auditorium;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public EventRating getRating() {
		return rating;
	}

	public void setRating(EventRating rating) {
		this.rating = rating;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name);
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
		Event other = (Event) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public String getAirDate() {
		return airDate;
	}

	public void setAirDate(String airDate) {
		this.airDate = airDate;
	}

}
