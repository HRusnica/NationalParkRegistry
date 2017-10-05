package com.techelevator.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.Park;
import com.techelevator.ParkDAO;

public class ParkJDBCDAO implements ParkDAO {

	private JdbcTemplate jdbcTemplate;
	
	public ParkJDBCDAO(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getAllParks() {
		String searchAllParks = "SELECT * FROM park";
		SqlRowSet parkRowSet = jdbcTemplate.queryForRowSet(searchAllParks);
		List<Park> allParks = new ArrayList<>();
		while(parkRowSet.next()){
			Park tempPark = new Park();
			tempPark = mapRowToPark(parkRowSet);
			allParks.add(tempPark);
		}
		return allParks;
	}

	@Override
	public Park findParkByParkId() {
		
		return null;
	}
	
	public Park mapRowToPark(SqlRowSet parkRowSet){
		Park ourPark = new Park();
		ourPark.setParkId(parkRowSet.getLong("park_id"));
		ourPark.setParkName(parkRowSet.getString("name"));
		ourPark.setParkLocation(parkRowSet.getString("location"));
		ourPark.setParkEstablishDate(parkRowSet.getDate("establish_date"));
		ourPark.setParkArea(parkRowSet.getInt("area"));
		ourPark.setParkVisitors(parkRowSet.getInt("visitors"));
		ourPark.setParkDescription(parkRowSet.getString("description"));
		return ourPark;
	}

	@Override
	public String[] getStringFromParks() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
