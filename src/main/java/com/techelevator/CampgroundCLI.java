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

	private Menu menu;

	private ParkDAO daoP;
	private CampGroundDAO daoCG;
	private CampSiteDAO daoCS;
	private ReservationDAO daoR;


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




		while(true){
			Park choice = (Park)menu.getChoiceFromOptions(daoP.getAllParks().toArray());


			for(int i = 0; i < daoP.getAllParks().size(); i++){
				Long tempLong = daoP.getAllParks().get(i).getParkId();
				if(tempLong.equals(choice.getParkId())){
					while(true){
						String choice2 = (String)menu.getChoiceFromOptions(SECOND_MENU_OPTIONS);

						if(choice2.equals(VIEW_PARK_INFO)){
							System.out.println(choice.getParkName());
							System.out.println(String.format("%-30s", "Location: ") + choice.getParkLocation());
							System.out.println(String.format("%-20s", "Established: ") + choice.getParkEstablishDate());
							System.out.println(String.format("%-20s", "Area: ") + choice.getParkArea());
							System.out.println(String.format("%-20s", "Annual Visitors: ") + choice.getParkVisitors());
						}
						if(choice2.equals(VIEW_CAMPGROUNDS)){
							System.out.println(String.format("%-50s", "Name") + String.format("%-20s", "Open") + String.format("%-20s", "Close") + String.format("%-20s", "Daily Fee")); 
							for(int j = 0; j < daoCG.getAllCampgrounds(choice.getParkId()).size(); j++){
								System.out.print(String.format("%-50s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundName())  +
												String.format("%-20s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundOpenFromMonth()) +
												 String.format("%-20s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundOpenToMonth()) +
												  String.format("%-20s", daoCG.getAllCampgrounds(choice.getParkId()).get(j).getCampgroundDailyFee()) + "\n"
										);
										
								
								
							}
						}
						//CampGround Choice3 = (CampGround)menu.getChoiceFromOptions(daoCG.getAllCampgrounds(choice.getParkId()).toArray());
					}
				}
			} 
		}

	}


}

