package com.techelevator;

import java.time.LocalDate;

public interface ReservationDAO {
	
	public int makeReservation(String reserveName, CampSite selectedCampSite, LocalDate localDate1, LocalDate localdate2);

}
