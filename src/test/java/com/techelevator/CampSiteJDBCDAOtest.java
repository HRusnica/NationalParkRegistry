package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;
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
import com.techelevator.JDBC.CampSiteJDBCDAO;
import com.techelevator.JDBC.ParkJDBCDAO;

public class CampSiteJDBCDAOtest {
	private static SingleConnectionDataSource dataSource;
	private CampGroundJDBCDAO daoCG;
	private ParkJDBCDAO daoP;
	private CampSiteJDBCDAO daoCS;
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

		daoCG = new CampGroundJDBCDAO(dataSource);	
		daoP = new ParkJDBCDAO(dataSource);
		daoCS = new CampSiteJDBCDAO(dataSource);
	}

	/* After each test, we rollback any changes that were made to the database so that
	 * everything is clean for the next test */
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	@Test
	public void getCampSitesByUserInputTest() {
		Date newDate;
		newDate = new Date(2001, 02, 01);
		daoP.createNewPark("The Best Park", "Cleveland", newDate, 203940, 2, "Peacefully peaceful");
		Long parkId = daoP.getAllParks().get(0).getParkId();

		List<CampGround> campGroundList = new ArrayList<>();
		daoCG.makeCampGround(parkId, "CAMP ANAWANA", "01", "12", new BigDecimal(0.00));
		campGroundList = daoCG.getAllCampgrounds(parkId);
		
		daoCS.createNewCampSite(Math.toIntExact(campGroundList.get(0).getCampgroundId()), 1, 10, true, 25, false);
		
		List<CampSite> ourCampSites= new ArrayList<>();
		ourCampSites = daoCS.getCampSitesByUserInput(campGroundList.get(0), LocalDate.parse("2017-09-20"), LocalDate.parse("2017-09-30"));
	
		assertNotNull(ourCampSites);
	}

}
