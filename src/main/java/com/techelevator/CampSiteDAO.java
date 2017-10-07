package com.techelevator;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CampSiteDAO {

	public List<CampSite> getCampSitesByUserInput(CampGround selectedCampGround, LocalDate localDate1, LocalDate localDate2);

	public void createNewCampSite(int campGroundId, int siteNumber, int maxOccupancy, Boolean accessible, int maxRVLength,
			Boolean utilities);
	
	
}
