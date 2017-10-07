package com.techelevator;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface CampGroundDAO {

	public List<CampGround> getAllCampgrounds(Long choice);
	public CampGround mapRowToCampGround(SqlRowSet campGroundRowSet);
	public void makeCampGround(Long parkId, String name, String open, String close, BigDecimal fee);
		
}
