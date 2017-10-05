package com.techelevator;

import java.util.Date;

public class Park {
	
	private Long parkId;
	private String parkName;
	private String parkLocation;
	private Date parkEstablishDate;
	private int parkArea;
	private int parkVisitors;
	private String parkDescription;
	
	public Long getParkId() {
		return parkId;
	}
	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public String getParkLocation() {
		return parkLocation;
	}
	public void setParkLocation(String parkLocation) {
		this.parkLocation = parkLocation;
	}
	public Date getParkEstablishDate() {
		return parkEstablishDate;
	}
	public void setParkEstablishDate(Date parkEstablishDate) {
		this.parkEstablishDate = parkEstablishDate;
	}
	public int getParkArea() {
		return parkArea;
	}
	public void setParkArea(int parkArea) {
		this.parkArea = parkArea;
	}
	public int getParkVisitors() {
		return parkVisitors;
	}
	public void setParkVisitors(int parkVisitors) {
		this.parkVisitors = parkVisitors;
	}
	public String getParkDescription() {
		return parkDescription;
	}
	public void setParkDescription(String parkDescription) {
		this.parkDescription = parkDescription;
	}
	public String toString(){
		return parkName;
	}
	
	
}
