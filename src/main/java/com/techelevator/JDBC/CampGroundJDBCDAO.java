package com.techelevator.JDBC;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.CampGround;
import com.techelevator.CampGroundDAO;
import com.techelevator.Park;

public class CampGroundJDBCDAO implements CampGroundDAO {

	JdbcTemplate jdbcTemplate;
	
	public CampGroundJDBCDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}


	@Override
	public List<CampGround> getAllCampgrounds(Long choice) {
		String searchAllCampGrounds = "SELECT * FROM campground WHERE park_id = ?";
		SqlRowSet campGroundRowSet = jdbcTemplate.queryForRowSet(searchAllCampGrounds, choice);
		List<CampGround> allCampGrounds = new ArrayList<>();
		while(campGroundRowSet.next()){
			CampGround tempCampGround = new CampGround();
			tempCampGround = mapRowToCampGround(campGroundRowSet);
			allCampGrounds.add(tempCampGround);
		}
		return allCampGrounds;
	}

	
	public CampGround mapRowToCampGround(SqlRowSet campGroundRowSet){
		CampGround ourCampGround = new CampGround();
		ourCampGround.setCampgroundId(campGroundRowSet.getLong("campground_id"));
		ourCampGround.setCampgroundParkId(campGroundRowSet.getInt("park_id"));
		ourCampGround.setCampgroundName(campGroundRowSet.getString("name"));
		ourCampGround.setCampgroundOpenFromMonth(campGroundRowSet.getString("open_from_mm"));
		ourCampGround.setCampgroundOpenToMonth(campGroundRowSet.getString("open_to_mm"));
		ourCampGround.setCampgroundDailyFee(campGroundRowSet.getBigDecimal("daily_fee"));
		return ourCampGround;
	}


	@Override
	public CampGround findCampGroundsByCampGroundId() {
		// TODO Auto-generated method stub
		return null;
	}


	
}
