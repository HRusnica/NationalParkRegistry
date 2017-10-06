package com.techelevator;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CampSiteDAO {

	public List<CampSite> getCampSitesByUserInput(CampGround selectedCampGround, LocalDate localDate1, LocalDate localDate2);
	
	
}
