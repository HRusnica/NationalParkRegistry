package com.techelevator;

public class CampSite {

	private Long campsiteId;
	private Long campgroundId;
	private int campsiteNumber;
	private int campsiteMaxOccupancy;
	private boolean campsiteAccessible;
	private int campsiteMaxRVLength;
	private boolean campsiteUtilities;
	public Long getCampsiteId() {
		return campsiteId;
	}
	public void setCampsiteId(Long campsiteId) {
		this.campsiteId = campsiteId;
	}
	public Long getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(Long campgroundId) {
		this.campgroundId = campgroundId;
	}
	public int getCampsiteNumber() {
		return campsiteNumber;
	}
	public void setCampsiteNumber(int campsiteNumber) {
		this.campsiteNumber = campsiteNumber;
	}
	public int getCampsiteMaxOccupancy() {
		return campsiteMaxOccupancy;
	}
	public void setCampsiteMaxOccupancy(int campsiteMaxOccupancy) {
		this.campsiteMaxOccupancy = campsiteMaxOccupancy;
	}
	public boolean isCampsiteAccessible() {
		return campsiteAccessible;
	}
	public void setCampsiteAccessible(boolean campsiteAccessible) {
		this.campsiteAccessible = campsiteAccessible;
	}
	public int getCampsiteMaxRVLength() {
		return campsiteMaxRVLength;
	}
	public void setCampsiteMaxRVLength(int campsitMaxRVLength) {
		this.campsiteMaxRVLength = campsitMaxRVLength;
	}
	public boolean isCampsiteUtilities() {
		return campsiteUtilities;
	}
	public void setCampsiteUtilities(boolean campsiteUtilities) {
		this.campsiteUtilities = campsiteUtilities;
	}
	
	
	
}
