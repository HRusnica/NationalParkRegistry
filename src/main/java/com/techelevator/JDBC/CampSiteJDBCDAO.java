package com.techelevator.JDBC;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.CampGround;
import com.techelevator.CampSite;
import com.techelevator.CampSiteDAO;
import com.techelevator.Park;

public class CampSiteJDBCDAO implements CampSiteDAO {

	private JdbcTemplate jdbcTemplate;

	public CampSiteJDBCDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@Override
	public List<CampSite> getCampSitesByUserInput(CampGround selectedCampGround, LocalDate localDate1,
			LocalDate localDate2) {
		String selectSites = "SELECT * FROM site "
				+ "WHERE campground_id = ? AND site_id NOT IN (SELECT DISTINCT site_id FROM reservation "
				+ "WHERE (from_date BETWEEN ? AND ?) OR (to_date BETWEEN ? AND ?) OR "
				+ "(from_date < ? AND to_date > ?)) LIMIT 5";
		SqlRowSet campSiteRowSet = jdbcTemplate.queryForRowSet(selectSites, selectedCampGround.getCampgroundId(), localDate1, localDate2, localDate1, localDate2, localDate1, localDate2);
		List<CampSite> availableCampSites= new ArrayList<>();
		while(campSiteRowSet.next()){
			CampSite ourCampSite = new CampSite();
			ourCampSite = mapRowToCampSite(campSiteRowSet);
			availableCampSites.add(ourCampSite);
		}
		return availableCampSites;
	}


	public CampSite mapRowToCampSite(SqlRowSet campSiteRowSet){
		CampSite ourCampSite = new CampSite();
		ourCampSite.setCampsiteId(campSiteRowSet.getLong("site_id"));
		ourCampSite.setCampgroundId(campSiteRowSet.getLong("campground_id"));
		ourCampSite.setCampsiteNumber(campSiteRowSet.getInt("site_number"));
		ourCampSite.setCampsiteMaxOccupancy(campSiteRowSet.getInt("max_occupancy"));
		ourCampSite.setCampsiteAccessible(campSiteRowSet.getBoolean("accessible"));
		ourCampSite.setCampsiteMaxRVLength(campSiteRowSet.getInt("max_rv_length"));
		ourCampSite.setCampsiteUtilities(campSiteRowSet.getBoolean("utilities"));
		return ourCampSite;
	}
	
	@Override
	public void createNewCampSite(int campGroundId, int siteNumber, int maxOccupancy, Boolean accessible, int maxRVLength, Boolean utilities){
		String sqlInsertCampSite = " INSERT INTO site (campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlInsertCampSite, campGroundId, siteNumber, maxOccupancy, accessible, maxRVLength, utilities);
	}

	@Override
	public List<CampSite> getCampSitesWithNoCGSelection(LocalDate localDate1, LocalDate localDate2) {
		String selectSites = "SELECT * FROM site "
				+ "WHERE campground_id = ? AND site_id NOT IN (SELECT DISTINCT site_id FROM reservation "
				+ "WHERE (from_date BETWEEN ? AND ?) OR (to_date BETWEEN ? AND ?) OR "
				+ "(from_date < ? AND to_date > ?)) LIMIT 5";
		SqlRowSet campSiteRowSet = jdbcTemplate.queryForRowSet(selectSites, localDate1, localDate2, localDate1, localDate2, localDate1, localDate2);
		List<CampSite> availableCampSites= new ArrayList<>();
		while(campSiteRowSet.next()){
			CampSite ourCampSite = new CampSite();
			ourCampSite = mapRowToCampSite(campSiteRowSet);
			availableCampSites.add(ourCampSite);
		}
		return availableCampSites;
	}
}
