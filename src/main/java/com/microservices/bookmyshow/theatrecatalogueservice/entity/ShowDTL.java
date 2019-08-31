package com.microservices.bookmyshow.theatrecatalogueservice.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ShowDTL {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int showId;

	private int theatreId;

	private Date showDateTime;
	// Indicate if Available, Almost full or House Full
	@Transient
	private String status;
	private int totalSeats;
	private int availableSeats;
	
	public ShowDTL() {
		
	}
	
	
	public ShowDTL(int showId, int theatreId, Date showTime, String status, int totalSeats, int availableSeats) {
		super();
		this.showId = showId;
		this.theatreId = theatreId;
        this.showDateTime = showTime;
		this.status = status;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
	}

	public int getShowId() {
		return showId;
	}
	public void setShowId(int showId) {
		this.showId = showId;
	}
	
	public int getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}
	public Date getShowTime() {
        return showDateTime;
	}
	public void setShowTime(Date showTime) {
        this.showDateTime = showTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
}
