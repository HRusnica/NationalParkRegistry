package com.techelevator;

import java.util.List;

public interface CampGroundDAO {

	public List<CampGround> getAllCampgrounds(Long choice);
	public CampGround findCampGroundsByCampGroundId();
		
}
