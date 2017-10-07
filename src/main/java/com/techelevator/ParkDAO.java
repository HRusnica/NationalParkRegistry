package com.techelevator;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface ParkDAO {
	
	public List<Park> getAllParks();
	public Park mapRowToPark(SqlRowSet parkRowSet);
	public Park createNewPark(String name, String location, Date theDate, int area, int visitors, String description);
}
