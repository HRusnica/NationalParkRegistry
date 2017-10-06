package com.techelevator;

import java.util.Scanner;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.JDBC.CampGroundJDBCDAO;
import com.techelevator.JDBC.CampSiteJDBCDAO;
import com.techelevator.JDBC.ParkJDBCDAO;
import com.techelevator.JDBC.ReservationJDBCDAO;

public class CampgroundCLI {
	private static final String WELCOME = "WELCOME TO OUR NATIONAL PARK RESERVATION SYSTEM";
	private static final String VIEW_PARKS = "PLEASE SELECT A PARK";

	private static final String VIEW_PARK_INFO = "VIEW PARK INFORMATION";
	private static final String VIEW_CAMPGROUNDS = "VIEW CAMPGROUNDS";
	private static final String BOOK_RESERVATIONS = "BOOK RESERVATIONS";
	private static final String GO_BACK = "GO BACK TO PREVIOUS SCREEN";
	private static final String[] SECOND_MENU_OPTIONS = {VIEW_PARK_INFO, 
														VIEW_CAMPGROUNDS,
														BOOK_RESERVATIONS,
														GO_BACK
														};
	private static final String SEARCH_RESERVATIONS = "SEARCH FOR AVAILABLE RESERVATION";
	private static final String[] THIRD_MENU_OPTIONS = {SEARCH_RESERVATIONS,
														GO_BACK
														};

	private Menu menu;

	private ParkDAO daoP;
	private CampGroundDAO daoCG;
	private CampSiteDAO daoCS;
	private ReservationDAO daoR;
	private String choice2;


	public static void main(String[] args) {

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		CampgroundCLI application = new CampgroundCLI(dataSource);
		application.run();

	}

	public CampgroundCLI(DataSource datasource) {
		// create your DAOs here
		daoP = new ParkJDBCDAO(datasource);
		daoCG = new CampGroundJDBCDAO(datasource);
		//		daoCS = new CampSiteJDBCDAO(datasource);
		//		daoR = new ReservationJDBCDAO(datasource);
	}

	public void run() {
		menu = new Menu(System.in, System.out);
		System.out.println(WELCOME);
		System.out.println(VIEW_PARKS);

		//while(true){
			Park choice = (Park)menu.getChoiceFromOptions(daoP.getAllParks().toArray());

			for(int i = 0; i < daoP.getAllParks().size(); i++){
				Long indexParkIdValue = daoP.getAllParks().get(i).getParkId();
				if(indexParkIdValue.equals(choice.getParkId())){
					//while(true){
						String choice2 = (String)menu.getChoiceFromOptions(SECOND_MENU_OPTIONS);

						if(choice2.equals(VIEW_PARK_INFO)){
							System.out.print(choice.getParkName() + "\n" 
							+ String.format("%-20s", "Location: ") + choice.getParkLocation() + "\n" 
							+ String.format("%-20s", "Established: ") + choice.getParkEstablishDate() + "\n" 
							+ String.format("%-20s", "Area: ") + choice.getParkArea() + " Acres" + "\n" 
							+ String.format("%-20s", "Annual Visitors: ") + choice.getParkVisitors() + "\n");
						}
						if(choice2.equals(VIEW_CAMPGROUNDS)){
							System.out.println(String.format("%-50s", "Name") + String.format("%-20s", "Open") + String.format("%-20s", "Close") + String.format("%-20s", "Daily Fee")); 
							for(int j = 0; j < daoCG.getAllCampgrounds(choice.getParkId()).size(); j++){
								System.out.print(String.format("%-50s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundName())  +
												String.format("%-20s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundOpenFromMonth()) +
												 String.format("%-20s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundOpenToMonth()) +
												  String.format("%-20s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundDailyFee()) + "\n");	
							}
							String choice3 = (String)menu.getChoiceFromOptions(THIRD_MENU_OPTIONS);
							if(choice3.equals(SEARCH_RESERVATIONS)){
								
							}
							if(choice3.equals(GO_BACK)){
								
							}
						}
						//CampGround Choice3 = (CampGround)menu.getChoiceFromOptions(daoCG.getAllCampgrounds(choice.getParkId()).toArray());
				//}
				//}
			} 
		}

	}


}

