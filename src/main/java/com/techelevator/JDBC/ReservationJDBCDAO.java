package com.techelevator.JDBC;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.CampSite;
import com.techelevator.Reservation;
import com.techelevator.ReservationDAO;

public class ReservationJDBCDAO implements ReservationDAO {

private JdbcTemplate jdbcTemplate;
	
	public ReservationJDBCDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	public int makeReservation(String reserveName, CampSite selectedCampSite, LocalDate localDate1, LocalDate localDate2){
		String siteId = Long.toString(selectedCampSite.getCampsiteId());
		String sqlInsertReservation = "INSERT INTO reservation (site_id, name, from_date, to_date) VALUES (?,?,?,?) RETURNING reservation_id";
		int ourReservationId = jdbcTemplate.update(sqlInsertReservation, siteId, reserveName, localDate1, localDate2);		
		return ourReservationId;
	}
	
}
