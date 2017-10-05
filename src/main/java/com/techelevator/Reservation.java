package com.techelevator;

import java.util.Date;

public class Reservation {

	private Long reservationId;
	private Long CampsiteId;
	private String reservationName;
	private Date reservationFromDate;
	private Date reservationToDate;
	private Date reservationCreateDate;
	public Long getReservationId() {
		return reservationId;
	}
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}
	public Long getCampsiteId() {
		return CampsiteId;
	}
	public void setCampsiteId(Long campsiteId) {
		CampsiteId = campsiteId;
	}
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public Date getReservationFromDate() {
		return reservationFromDate;
	}
	public void setReservationFromDate(Date reservationFromDate) {
		this.reservationFromDate = reservationFromDate;
	}
	public Date getReservationToDate() {
		return reservationToDate;
	}
	public void setReservationToDate(Date reservationToDate) {
		this.reservationToDate = reservationToDate;
	}
	public Date getReservationCreateDate() {
		return reservationCreateDate;
	}
	public void setReservationCreateDate(Date reservationCreateDate) {
		this.reservationCreateDate = reservationCreateDate;
	}
	
	
}
