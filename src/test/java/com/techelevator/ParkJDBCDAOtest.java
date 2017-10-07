package com.techelevator;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.JDBC.CampGroundJDBCDAO;
import com.techelevator.JDBC.ParkJDBCDAO;

public class ParkJDBCDAOtest {

	private static SingleConnectionDataSource dataSource;
	private ParkJDBCDAO dao;
	private JdbcTemplate jdbcTemplate;	
		/* Before any tests are run, this method initializes the datasource for testing. */
		@BeforeClass
		public static void setupDataSource() {
			dataSource = new SingleConnectionDataSource();
			dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
			dataSource.setUsername("postgres");
			dataSource.setPassword("postgres1");
			/* The following line disables autocommit for connections 
			 * returned by this DataSource. This allows us to rollback
			 * any changes after each test */
			dataSource.setAutoCommit(false);
		}
		
		/* After all tests have finished running, this method will close the DataSource */
		@AfterClass
		public static void closeDataSource() throws SQLException {
			dataSource.destroy();
		}

		@Before
		public void setup() {
			jdbcTemplate = new JdbcTemplate(dataSource);
			jdbcTemplate.update("DELETE FROM reservation");
			jdbcTemplate.update("DELETE FROM site");
			jdbcTemplate.update("DELETE FROM campground");
			jdbcTemplate.update("DELETE FROM park");
			
			dao = new ParkJDBCDAO(dataSource);	
		}
		
		/* After each test, we rollback any changes that were made to the database so that
		 * everything is clean for the next test */
		@After
		public void rollback() throws SQLException {
			dataSource.getConnection().rollback();
		}
		
	@Test
	public void getAllParksTest() {
		Date newDate;
		newDate = new Date(2001, 02, 01);
		dao.createNewPark("The Best Park", "Cleveland", newDate, 203940, 2, "Peacefully peaceful");
		List<Park> parkList = new ArrayList<>();
		parkList = dao.getAllParks();
		
		assertNotNull(parkList);
		assertEquals("The Best Park", parkList.get(0).getParkName());
	}
}
