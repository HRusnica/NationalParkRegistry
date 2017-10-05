package com.techelevator;

import java.math.BigDecimal;

public class CampGround {

	private Long campgroundId;
	private int campgroundParkId;
	private String campgroundName;
	private String campgroundOpenFromMonth;
	private String campgroundOpenToMonth;
	private BigDecimal campgroundDailyFee;
	public Long getCampgroundId() {
		return campgroundId;
	}
	public void setCampgroundId(Long campgroundId) {
		this.campgroundId = campgroundId;
	}
	public int getCampgroundParkId() {
		return campgroundParkId;
	}
	public void setCampgroundParkId(int campgroundParkId) {
		this.campgroundParkId = campgroundParkId;
	}
	public String getCampgroundName() {
		return campgroundName;
	}
	public void setCampgroundName(String campgroundName) {
		this.campgroundName = campgroundName;
	}
	public String getCampgroundOpenFromMonth() {
		return campgroundOpenFromMonth;
	}
	public void setCampgroundOpenFromMonth(String campgroundOpenFromMonth) {
		this.campgroundOpenFromMonth = campgroundOpenFromMonth;
	}
	public String getCampgroundOpenToMonth() {
		return campgroundOpenToMonth;
	}
	public void setCampgroundOpenToMonth(String campgroundOpenToMonth) {
		this.campgroundOpenToMonth = campgroundOpenToMonth;
	}
	public BigDecimal getCampgroundDailyFee() {
		return campgroundDailyFee;
	}
	public void setCampgroundDailyFee(BigDecimal campgroundDailyFee) {
		this.campgroundDailyFee = campgroundDailyFee;
	}
	public String toString(){
		return campgroundName;
	}
	
	
}
