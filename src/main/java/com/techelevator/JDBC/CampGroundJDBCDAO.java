package com.techelevator.JDBC;

import java.math.BigDecimal;
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
		String searchAllCampGroundsByParkId = "SELECT * FROM campground WHERE park_id = ?";
		SqlRowSet campGroundRowSet = jdbcTemplate.queryForRowSet(searchAllCampGroundsByParkId, choice);
		List<CampGround> allCampGrounds = new ArrayList<>();
		while(campGroundRowSet.next()){
			CampGround ourCampGround = new CampGround();
			ourCampGround = mapRowToCampGround(campGroundRowSet);
			allCampGrounds.add(ourCampGround);
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
	public void makeCampGround(Long parkId, String name, String open, String close, BigDecimal fee) {
		String sqlInsertCampGround = " INSERT INTO campground (park_id, name, open_from_mm, open_to_mm, daily_fee) VALUES (?, ?, ?, ?, ?) ";
		jdbcTemplate.update(sqlInsertCampGround, parkId, name, open, close, fee);	
	}	
}
